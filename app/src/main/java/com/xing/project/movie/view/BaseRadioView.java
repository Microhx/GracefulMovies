package com.xing.project.movie.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.StringRes;

import com.xing.project.movie.R;


/**
 * Created by xinghe on 20/03/2018.
 *
 * 自定义顶部RadioButton
 */

public class BaseRadioView extends RelativeLayout {

    TextView tvTitle ;

    ImageView ivTitle ;

    TextView tvUnReadCount ;


    public BaseRadioView(Context context) {
        this(context,null);
    }

    public BaseRadioView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public BaseRadioView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        initViews();

        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.BaseRadioView);
        int headRes = array.getResourceId(R.styleable.BaseRadioView_base_radio_view_res,0);
        if(headRes > 0) {
            ivTitle.setImageResource(headRes);
        }

        int titleRes = array.getResourceId(R.styleable.BaseRadioView_base_radio_view_title,0);
        if(titleRes > 0) {
            tvTitle.setText(titleRes);
        }

        int numCount = array.getInt(R.styleable.BaseRadioView_base_radio_view_count,0);
        setUnReadCount(numCount);
        
        array.recycle();
    }

    private void initViews() {
        View rootView = View.inflate(getContext(), R.layout.base_radio_view_layout,this);
        tvTitle = rootView.findViewById(R.id.id_tv_content);
        tvUnReadCount = rootView.findViewById(R.id.id_tv_unread_count);
        ivTitle = rootView.findViewById(R.id.id_iv_image);
    }


    public void setUnReadCount(int count) {
        tvUnReadCount.setVisibility(count <= 0 ? GONE : VISIBLE);
        if(count > 0) {
            tvUnReadCount.setText(count > 99 ? "99+" : String.valueOf(count));
        }
    }

    public void setUnReadCountWithoutLimit(int count){
        tvUnReadCount.setVisibility( count <= 0 ? GONE : VISIBLE);
        if(count > 0) {
            tvUnReadCount.setText(String.valueOf(count) );
        }
    }


    public void setCheck(boolean check) {
        tvTitle.setSelected(check);
        ivTitle.setSelected(check);
    }

    public void setData(@StringRes int title , int res){
        tvTitle.setText(title);
        ivTitle.setImageResource(res);
    }


}
