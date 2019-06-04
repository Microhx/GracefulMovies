package com.xw.project.gracefulmovies.ui.widget;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import androidx.annotation.ColorInt;
import androidx.annotation.ColorRes;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.drawable.DrawableCompat;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.xw.project.gracefulmovies.R;

import com.xw.project.gracefulmovies.util.Util;
import java.util.Arrays;
import java.util.List;

/**
 * <p>
 * Created by woxingxiao on 2017-02-14.
 */
public class TagGroup extends LinearLayout {

    public TagGroup(Context context) {
        this(context, null);
    }

    public TagGroup(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TagGroup(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        setOrientation(HORIZONTAL);
    }

    public void setTagData(String[] tags, @ColorRes int tintColorRes) {
        if (tags == null || tags.length == 0) {
            return;
        }

        setTagData(Arrays.asList(tags), tintColorRes);
    }

    public void setTagData(List<String> tags, @ColorRes int tintColorRes) {
        setTagData(tags, 0, tintColorRes);
    }


    public void setTagData(String[]  tags, @ColorRes int textColorRes, @ColorRes int tintColorRes) {
        setTagData(Arrays.asList(tags), textColorRes, tintColorRes);
    }

    public void setTagData(List<String> tags, @ColorRes int textColorRes, @ColorRes int tintColorRes) {
        if (tags == null || tags.isEmpty()) {
            return;
        }

        removeAllViews();

        int tintColor = ContextCompat.getColor(getContext(), tintColorRes);
        for (String tag : tags) {
            if(TextUtils.isEmpty(tag)) continue;
            createTag(tag, textColorRes, tintColor);
        }

        requestLayout();
    }

    private void createTag(String s, @ColorRes int textColorRes, @ColorInt int tintColor) {
        TextView textView = new TextView(getContext());
        LayoutParams lp = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        lp.rightMargin = Util.dp2px(4);
        textView.setLayoutParams(lp);
        textView.setTextSize(9);
        if (textColorRes != 0) {
            textView.setTextColor(ContextCompat.getColor(getContext(), textColorRes));
        } else {
            textView.setTextColor(Color.WHITE);
        }
        Drawable drawable = ContextCompat.getDrawable(getContext(), R.drawable.shape_round_stroke_bg_tag);
        if (drawable != null)
            DrawableCompat.setTint(drawable, tintColor);
        textView.setBackground(drawable);
        textView.setPadding(Util.dp2px(2), 0, Util.dp2px(2), 0);
        textView.setText(s);

        addView(textView);
    }

}
