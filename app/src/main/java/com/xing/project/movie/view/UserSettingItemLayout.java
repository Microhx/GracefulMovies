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
 * Created by xinghe on 27/02/2018.
 * <p>
 * 用户界面 我的条目设置
 */

public class UserSettingItemLayout extends RelativeLayout {

    ImageView ivLeft;
    TextView tvContent;
    ImageView ivRight;
    View line ;

    View mRedDot;
    TextView mTvRightContent;

    public UserSettingItemLayout(Context context) {
        this(context, null);
    }

    public UserSettingItemLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public UserSettingItemLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initViews();
        initAttrs(attrs);
    }

    private void initAttrs(AttributeSet attrs) {
       TypedArray array =  getContext().obtainStyledAttributes(attrs, R.styleable.UserSettingItemLayout);
       int leftDrawableRes = array.getResourceId(R.styleable.UserSettingItemLayout_left_res,0);
       if(leftDrawableRes > 0){
           ivLeft.setImageResource(leftDrawableRes);
       }

       int content = array.getResourceId(R.styleable.UserSettingItemLayout_tv_content,0);
       if(content>0){
           tvContent.setText(content);
       }


       boolean rightRowShow = array.getBoolean(R.styleable.UserSettingItemLayout_right_setting_arrow_show,true);
       ivRight.setVisibility(rightRowShow ? VISIBLE : INVISIBLE);

       boolean bottomLineShow = array.getBoolean(R.styleable.UserSettingItemLayout_bottom_line_show,true);
       line.setVisibility(bottomLineShow ? VISIBLE : GONE);

       array.recycle();
    }


    private void initViews() {
       View rootView =  View.inflate(getContext(), R.layout.layout_user_setting_item,this);
       ivLeft = rootView.findViewById(R.id.id_default_icon);
       tvContent = rootView.findViewById(R.id.id_tv_content);
       ivRight = rootView.findViewById(R.id.id_right_icon);
       line = rootView.findViewById(R.id.id_line);
        mRedDot = rootView.findViewById(R.id.id_remind);
        mTvRightContent =  rootView.findViewById(R.id.id_right_content);
    }

    public void setRedDotVisiable(int visibility){
        mRedDot.setVisibility(visibility);
    }

    public void setRightContent(@StringRes int content) {
        mTvRightContent.setText(content);
    }

    public void setRightContent(String content) {
        mTvRightContent.setText(content);
    }




}
