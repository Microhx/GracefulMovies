package com.xw.project.gracefulmovies.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.util.Log;
import android.view.FrameMetrics;
import android.view.Window;
import android.widget.LinearLayout;
import androidx.databinding.DataBindingUtil;
import android.net.Uri;
import android.os.Build;
import androidx.annotation.NonNull;
import com.google.android.material.navigation.NavigationView;
import androidx.fragment.app.Fragment;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.xw.project.gracefulmovies.GMApplication;
import com.xw.project.gracefulmovies.R;
import com.xw.project.gracefulmovies.data.db.entity.CityEntity;
import com.xw.project.gracefulmovies.databinding.ActivityMainBinding;
import com.xw.project.gracefulmovies.service.LocationService;
import com.xw.project.gracefulmovies.ui.activity.base.BaseActivity;
import com.xw.project.gracefulmovies.ui.adapter.TabPagerAdapter;
import com.xw.project.gracefulmovies.ui.fragment.BaseFragment;
import com.xw.project.gracefulmovies.ui.fragment.MovieListFragment;
import com.xw.project.gracefulmovies.ui.search.MovieTypeSearchActivity;
import com.xw.project.gracefulmovies.util.Util;
import io.github.inflationx.viewpump.ViewPumpContextWrapper;

/**
 * 首页
 * <p>
 * Created by woxingxiao on 2018-08-12.
 */
public class MainActivity extends BaseActivity<ActivityMainBinding> implements NavigationView
        .OnNavigationItemSelectedListener {

    private TextView mCityTv;

    @Override
    protected int contentLayoutRes() {
        return R.layout.activity_main;
    }

    @Override
    protected void afterSetContentView() {
        transparentStatusBar();
        if (Build.VERSION.SDK_INT == Build.VERSION_CODES.KITKAT) {
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);

        mBinding = (ActivityMainBinding) DataBindingUtil.setContentView(this, R.layout.activity_main);

        setSupportActionBar(mBinding.toolbar);
        int resId = getResources().getIdentifier("status_bar_height", "dimen", "android");
        int statusBarHeight = getResources().getDimensionPixelSize(resId);

        FrameLayout.LayoutParams lp1 = (FrameLayout.LayoutParams) mBinding.toolbar.getLayoutParams();
        lp1.topMargin = statusBarHeight;
        mBinding.toolbar.setLayoutParams(lp1);

        FrameLayout.LayoutParams lp2 = (FrameLayout.LayoutParams) mBinding.radioGroup.getLayoutParams();
        lp2.topMargin = Util.dp2px(56) + statusBarHeight;
        mBinding.radioGroup.setLayoutParams(lp2);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, mBinding.drawerLayout, mBinding.toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        mBinding.drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navView = findViewById(R.id.nav_view);
        navView.setNavigationItemSelectedListener(this);
        mCityTv = navView.getHeaderView(0).findViewById(R.id.nav_city_tv);

        Fragment[] fragments = new Fragment[2];
        fragments[0] = MovieListFragment.getInstance(0);
        fragments[1] = MovieListFragment.getInstance(1);;
        
        TabPagerAdapter adapter = new TabPagerAdapter(getSupportFragmentManager(), fragments);
        adapter.setTabTitles(new String[]{getString(R.string.has_released), getString(R.string.going_to_release)});
        mBinding.viewPager.setAdapter(adapter);

        mBinding.radioGroup.setOnCheckedChangeListener((group, checkedId) -> {
            int position = checkedId == R.id.now_radio ? 0 : 1;
            if (mBinding.viewPager.getCurrentItem() == position)
                return;

            mBinding.viewPager.setCurrentItem(position);
        });
        mCityTv.setOnClickListener(v -> showLocatedCityDialog());

        LocationService.start(this);

        GMApplication.getInstance().getCityRepository().getCity()
                .observe(this, cityEntity ->
                        mCityTv.setText(cityEntity == null ? "位置" : cityEntity.getName())
                );

        checkFrameMetrics();
    }

    FrameMetrics mFrameMetrics ;
    Handler mHandler = new Handler();
    private void checkFrameMetrics() {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            getWindow().addOnFrameMetricsAvailableListener((window, frameMetrics, dropCountSinceLastInvocation) -> {
                mFrameMetrics = new FrameMetrics(frameMetrics);
            }, mHandler);
        }

        mHandler.postDelayed(() -> {

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                Log.d("Metrics", "animation_duration: " + mFrameMetrics.getMetric(FrameMetrics.ANIMATION_DURATION) / Math.pow(10, 6));
                Log.d("Metrics", "command_issue_duration: " + mFrameMetrics.getMetric(FrameMetrics.COMMAND_ISSUE_DURATION) / Math.pow(10, 6));
                Log.d("Metrics", "draw_duration: " + mFrameMetrics.getMetric(FrameMetrics.DRAW_DURATION) / Math.pow(10, 6));
                Log.d("Metrics", "first_draw_frame: " + mFrameMetrics.getMetric(FrameMetrics.FIRST_DRAW_FRAME) / Math.pow(10, 6));
                Log.d("Metrics", "input_handling_duration: " + mFrameMetrics.getMetric(FrameMetrics.INPUT_HANDLING_DURATION) / Math.pow(10, 6));
                Log.d("Metrics", "layout_measure_duration: " + mFrameMetrics.getMetric(FrameMetrics.LAYOUT_MEASURE_DURATION) / Math.pow(10, 6));
                Log.d("Metrics", "swap_buffers_duration: " + mFrameMetrics.getMetric(FrameMetrics.SWAP_BUFFERS_DURATION) / Math.pow(10, 6));
                Log.d("Metrics", "sync_duration: " + mFrameMetrics.getMetric(FrameMetrics.SYNC_DURATION) / Math.pow(10, 6));
                Log.d("Metrics", "total_duration: " + mFrameMetrics.getMetric(FrameMetrics.TOTAL_DURATION) / Math.pow(10, 6));
                Log.d("Metrics", "unknown_delay_duration: " + mFrameMetrics.getMetric(FrameMetrics.UNKNOWN_DELAY_DURATION) / Math.pow(10, 6));
            }else {
                Log.e("Metrics","build version_code :" + Build.VERSION.SDK_INT);
            }


        },300);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        final MenuItem item2 = menu.findItem(R.id.action_box_office);
        item2.getActionView().setOnClickListener(v -> onOptionsItemSelected(item2));
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_box_office) {
            navigateWithRippleCompat(
                    this,
                    //new Intent(this, BoxOfficeActivity.class),
                    new Intent(this, MovieTypeSearchActivity.class),

                    item.getActionView(),
                    R.color.colorPrimary
            );
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_settings:
                navigate(SettingsActivity.class);
                break;
            case R.id.nav_share: // 分享
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");
                intent.putExtra(Intent.EXTRA_SUBJECT, "分享");
                intent.putExtra(Intent.EXTRA_TEXT, getString(R.string.share_out_description));
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(Intent.createChooser(intent, "分享"));
                break;
            case R.id.nav_evaluate: // 评分
                Uri uri = Uri.parse("market://details?id=" + getPackageName());
                Intent intent2 = new Intent(Intent.ACTION_VIEW, uri);
                intent2.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(Intent.createChooser(intent2, "评价"));
                break;
            case R.id.nav_about:
                navigate(AboutActivity.class);
                break;

            case R.id.nav_debug:
                startActivity(new Intent(this, DebugActivity.class));
                break;
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    
    private long mStartMills;
    @Override
    public void onBackPressed() {
        if (mBinding.drawerLayout.isDrawerOpen(GravityCompat.START)) {
            mBinding.drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            if (System.currentTimeMillis() - mStartMills > 1000) {
                toast("再按一次，退出程序");
                mStartMills = System.currentTimeMillis();
            } else {
                super.onBackPressed();
            }
        }
    }

    public void showLocatedCityDialog() {
        CityEntity city = GMApplication.getInstance().getCityRepository().getCityEntity();
        if (city == null)
            return;

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(getString(R.string.location_default));
        final View view = getLayoutInflater().inflate(R.layout.layout_location_dialog, null);
        final TextView msgTv = view.findViewById(R.id.dialog_loc_msg_tv);
        msgTv.setText(getString(R.string.hint_located_city, city.getName()));
        builder.setView(view);
        builder.setNegativeButton(getString(R.string.locate_again), (dialogInterface, i) ->
                LocationService.relocate(this));
        builder.setPositiveButton(getString(R.string.confirm), null);
        builder.show();
    }

}
