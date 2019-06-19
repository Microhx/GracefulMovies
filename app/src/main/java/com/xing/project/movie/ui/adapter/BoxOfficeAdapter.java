package com.xing.project.movie.ui.adapter;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.DiffUtil;

import com.xing.project.movie.R;
import com.xing.project.movie.data.db.entity.BoxOfficeEntity;
import com.xing.project.movie.ui.adapter.base.BaseBindingListAdapter;
import com.xing.project.movie.databinding.ItemBoxOfficeBinding;

/**
 * BoxOfficeAdapter
 * <p>
 * Created by woxignxiao on 2018-09-02.
 */
public class BoxOfficeAdapter extends BaseBindingListAdapter<BoxOfficeEntity> {

    public BoxOfficeAdapter() {
        super(new DiffUtil.ItemCallback<BoxOfficeEntity>() {
            @Override
            public boolean areItemsTheSame(@NonNull BoxOfficeEntity oldItem, @NonNull BoxOfficeEntity newItem) {
                return oldItem.getIrank().equals(newItem.getIrank());
            }

            @Override
            public boolean areContentsTheSame(@NonNull BoxOfficeEntity oldItem, @NonNull BoxOfficeEntity newItem) {
                return oldItem.toString().equals(newItem.toString());
            }
        });
    }

    @Override
    protected int itemLayoutRes() {
        return R.layout.item_box_office;
    }

    @Override
    protected void onBind(@NonNull ViewDataBinding binding, BoxOfficeEntity item, int position) {
        super.onBind(binding, item, position);

        ItemBoxOfficeBinding binding_ = (ItemBoxOfficeBinding) binding;
        if ("1".equals(item.getIrank())) {
            binding_.boRankIv.setVisibility(View.VISIBLE);
            binding_.boRankIv.setImageResource(R.drawable.svg_ic_gold_medal);
            binding_.boRankTv.setText("");
        } else if ("2".equals(item.getIrank())) {
            binding_.boRankIv.setVisibility(View.VISIBLE);
            binding_.boRankIv.setImageResource(R.drawable.svg_ic_silver_medal);
            binding_.boRankTv.setText("");
        } else if ("3".equals(item.getIrank())) {
            binding_.boRankIv.setVisibility(View.VISIBLE);
            binding_.boRankIv.setImageResource(R.drawable.svg_ic_bronze_medal);
            binding_.boRankTv.setText("");
        } else {
            binding_.boRankIv.setVisibility(View.INVISIBLE);
            binding_.boRankTv.setText(item.getIrank());
        }
    }
}