package com.xing.project.movie.ui.adapter;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;

import com.xing.project.movie.R;
import com.xing.project.movie.data.ao.OpenProject;
import com.xing.project.movie.ui.adapter.base.BaseBindingListAdapter;

import java.util.List;

/**
 * 开源许可列表适配器
 * <p>
 * Created by woxignxiao on 2018-09-02.
 */
public class LicensesAdapter extends BaseBindingListAdapter<OpenProject> {

    public LicensesAdapter(List<OpenProject> data) {
        super(data, new DiffUtil.ItemCallback<OpenProject>() {
            @Override
            public boolean areItemsTheSame(@NonNull OpenProject oldItem, @NonNull OpenProject newItem) {
                return oldItem.name.equals(newItem.name);
            }

            @Override
            public boolean areContentsTheSame(@NonNull OpenProject oldItem, @NonNull OpenProject newItem) {
                return oldItem.name.equals(newItem.name);
            }
        });
    }

    @Override
    protected int itemLayoutRes() {
        return R.layout.item_license;
    }
}