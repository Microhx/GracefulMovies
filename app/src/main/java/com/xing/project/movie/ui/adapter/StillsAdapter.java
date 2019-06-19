package com.xing.project.movie.ui.adapter;

import androidx.annotation.NonNull;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.DiffUtil;

import com.xing.project.movie.R;
import com.xing.project.movie.data.ao.MovieStills;
import com.xing.project.movie.ui.activity.MovieDetailActivity;
import com.xing.project.movie.ui.adapter.base.BaseBindingListAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * StillsAdapter
 *
 */
public class StillsAdapter extends BaseBindingListAdapter<MovieStills.Stills> {

    public StillsAdapter(List<MovieStills.Stills> data) {
        super(data, new DiffUtil.ItemCallback<MovieStills.Stills>() {
            @Override
            public boolean areItemsTheSame(@NonNull MovieStills.Stills oldItem, @NonNull MovieStills.Stills newItem) {
                return oldItem.imgUrl.equals(newItem.imgUrl);
            }

            @Override
            public boolean areContentsTheSame(@NonNull MovieStills.Stills oldItem, @NonNull MovieStills.Stills newItem) {
                return oldItem.imgUrl.equals(newItem.imgUrl);
            }
        });
    }

    @Override
    protected int itemLayoutRes() {
        return R.layout.item_stills;
    }

    @Override
    protected void onBind(@NonNull ViewDataBinding binding, MovieStills.Stills item, int position) {
        super.onBind(binding, item, position);

        binding.getRoot().setOnClickListener(v -> {
            ArrayList<String> urls = new ArrayList<>();
            for (MovieStills.Stills stills : getData()) {
                urls.add(stills.imgUrl);
            }

            ((MovieDetailActivity) getContext()).gotoPreviewImages(urls, position);
        });
    }
}