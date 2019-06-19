package com.xing.project.movie.ui.activity;

import com.xing.project.movie.R;
import com.xing.project.movie.ui.activity.base.BaseActivity;
import com.xing.project.movie.ui.fragment.SettingsFragment;

/**
 * 设置
 * <p>
 * Created by woxingxiao on 2018-08-24.
 */
public class SettingsActivity extends BaseActivity {

    @Override
    protected int contentLayoutRes() {
        return R.layout.activity_settings;
    }

    @Override
    protected void afterSetContentView() {
        initializeToolbar();

        getFragmentManager().beginTransaction()
                .replace(R.id.settings_container, SettingsFragment.newInstance())
                .commit();
    }
}
