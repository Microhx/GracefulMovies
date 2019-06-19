package com.xing.project.movie.ui.activity;

import com.xing.project.movie.R;
import com.xing.project.movie.data.ao.OpenProject;
import com.xing.project.movie.ui.activity.base.BaseActivity;
import com.xing.project.movie.ui.adapter.LicensesAdapter;
import com.xing.project.movie.databinding.ActivityOpenLicenseBinding;

import java.util.ArrayList;
import java.util.List;

/**
 * 开源许可
 * <p>
 * Created by woxingxiao on 2018-08-18.
 */
public class OpenLicenseActivity extends BaseActivity<ActivityOpenLicenseBinding> {

    @Override
    protected int contentLayoutRes() {
        return  R.layout.activity_open_license;
    }

    @Override
    protected void afterSetContentView() {
        initializeToolbar();

        String[] libs = getResources().getStringArray(R.array.libraries);
        String[] licenses = getResources().getStringArray(R.array.licenses);
        List<OpenProject> list = new ArrayList<>();
        OpenProject project;
        for (int i = 0; i < libs.length; i++) {
            project = new OpenProject();
            project.name = libs[i];
            project.license = licenses[i];
            list.add(project);
        }
        mBinding.licenseRecyclerView.setAdapter(new LicensesAdapter(list));
    }
}
