package com.xing.project.movie.ui.adapter;

import android.text.TextUtils;

import androidx.annotation.NonNull;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.DiffUtil;

import com.xing.project.movie.R;
import com.xing.project.movie.data.ao.Actor;
import com.xing.project.movie.ui.activity.MovieDetailActivity;
import com.xing.project.movie.ui.adapter.base.BaseBindingListAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * ActorsAdapter
 * <p>
 * Created by woxignxiao on 2018-09-01.
 */
public class ActorsAdapter extends BaseBindingListAdapter<Actor> {

    public ActorsAdapter(List<Actor> data) {
        super(data, new DiffUtil.ItemCallback<Actor>() {
            @Override
            public boolean areItemsTheSame(@NonNull Actor oldItem, @NonNull Actor newItem) {
                return oldItem.name.equals(newItem.name);
            }

            @Override
            public boolean areContentsTheSame(@NonNull Actor oldItem, @NonNull Actor newItem) {
                return oldItem.name.equals(newItem.name) && oldItem.img.equals(newItem.img);
            }
        });
    }

    @Override
    protected int itemLayoutRes() {
        return R.layout.item_actor;
    }

    @Override
    protected void onBind(@NonNull ViewDataBinding binding, Actor item, int position) {
        super.onBind(binding, item, position);

        binding.getRoot().setOnClickListener(v -> {
            ArrayList<String> urls = new ArrayList<>();
            for (Actor actor : getData()) {
                urls.add(actor.img);
            }

            ((MovieDetailActivity) getContext()).gotoPreviewImages(urls, position);
        });
    }

    public static String displayRoleText(String role) {
        if ("导演".equals(role)) {
            return role;
        } else if (!TextUtils.isEmpty(role)) {
            return "饰 " + role;
        }
        return "";
    }
}