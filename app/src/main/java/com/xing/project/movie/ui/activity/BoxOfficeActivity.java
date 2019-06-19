package com.xing.project.movie.ui.activity;

import androidx.lifecycle.ViewModelProviders;
import android.content.res.Resources;
import androidx.core.content.ContextCompat;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import androidx.appcompat.app.AlertDialog;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuItem;

import com.xing.project.movie.R;
import com.xing.project.movie.data.DataResource;
import com.xing.project.movie.ui.activity.base.BaseActivity;
import com.xing.project.movie.viewmodel.BoxOfficeViewModel;
import com.xing.project.movie.GMApplication;
import com.xing.project.movie.databinding.ActivityBoxOfficeBinding;
import com.xing.project.movie.ui.adapter.BoxOfficeAdapter;

public class BoxOfficeActivity extends BaseActivity<ActivityBoxOfficeBinding> implements SwipeRefreshLayout.OnRefreshListener {

    private BoxOfficeAdapter mAdapter;
    private BoxOfficeViewModel mViewModel;

    @Override
    protected int contentLayoutRes() {
        return R.layout.activity_box_office;
    }

    @Override
    protected void afterSetContentView() {

        initializeToolbar();

        mBinding.fab.setOnClickListener(view -> {
            if (mAdapter.getData() != null && !mAdapter.getData().isEmpty())
                mBinding.recyclerView.smoothScrollToPosition(0);
        });

        mBinding.swipeRefreshLayout.setColorSchemeColors(
                ContextCompat.getColor(this, R.color.colorAccent),
                ContextCompat.getColor(this, R.color.colorPrimary)
        );


        mBinding.swipeRefreshLayout.setProgressViewEndTarget(false, (int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP, 120, Resources.getSystem().getDisplayMetrics()));

        mBinding.swipeRefreshLayout.setOnRefreshListener(this);

        mAdapter = new BoxOfficeAdapter();
        mBinding.recyclerView.setAdapter(mAdapter);

        GMApplication.getInstance().getDatabase().boxOfficeDao().loadBoxOfficeList()
                .observe(this, list -> mAdapter.setData(list));

        mViewModel = ViewModelProviders.of(this).get(BoxOfficeViewModel.class);
        mViewModel.getBoxOffices().observe(this, resource -> {
            assert resource != null;
            processStatusView(resource);

            if (resource.getStatus() != DataResource.Status.LOADING) {
                mBinding.swipeRefreshLayout.setRefreshing(false);
            }
        });

        mBinding.swipeRefreshLayout.setRefreshing(true);
        mViewModel.load();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.box_office, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_attention) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("提示");
            builder.setView(R.layout.layout_box_office_attention_dialog);
            builder.setPositiveButton("好的", null);
            builder.show();

            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onReload() {
        mViewModel.load();
    }

    @Override
    public void onRefresh() {
        mViewModel.load();
    }
}
