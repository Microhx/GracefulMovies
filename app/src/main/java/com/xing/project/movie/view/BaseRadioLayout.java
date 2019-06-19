package com.xing.project.movie.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.IntegerRes;
import androidx.annotation.Nullable;
import androidx.annotation.StringRes;

import com.xing.project.movie.R;


/**
 * Created by xinghe on 20/03/2018.
 */

public class BaseRadioLayout extends LinearLayout implements View.OnClickListener {

    /**
     * 推荐
     */
    public static final int HOME_INDEX  = 0 ;

    /**
     * 分类
     */
    public static final int CATEGORY_INDEX  = 1 ;

    /**
     * 趋势
     */
    public static final int TREND_INDEX     = 2 ;

    /**
     * 个人空间
     */
    public static final int MINE_INDEX     = 3 ;


    private SparseArray<BaseRadioView> cacheArray = new SparseArray<>();

    private onRadioButtonClickListener radioButtonClickListener;

    private static final int[] TITLERES = {
                                           R.drawable.res_tab_home,
                                           R.drawable.res_tab_find ,
                                           R.drawable.res_tab_trend,
                                           R.drawable.res_tab_me
                                          } ;

    private static final int[] TITLES = {
                                            R.string.base_str_home,
                                            R.string.base_str_category,
                                            R.string.base_str_trend,
                                            R.string.base_str_me
                                        } ;


    private int mCurrentIndex = -1 ;

    public BaseRadioLayout(Context context) {
        this(context,null);
    }

    public BaseRadioLayout(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public BaseRadioLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setOrientation(HORIZONTAL);

        setData();
    }

    /**
     *
     * */
    public void setData() {
        removeAllViews();
        addItems(HOME_INDEX,        TITLES[0], TITLERES[0]);
        addItems(CATEGORY_INDEX,    TITLES[1], TITLERES[1]);
        addItems(TREND_INDEX,       TITLES[2], TITLERES[2]);
        addItems(MINE_INDEX,        TITLES[3], TITLERES[3]);
    }


    private void addItems(int index , @StringRes int title , @IntegerRes int drawable) {
        BaseRadioView view = new BaseRadioView(getContext());

        view.setTag(index);
        view.setCheck(getChildCount() == 0);
        view.setData(title, drawable);
        LayoutParams params = new LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT, 1);
        params.gravity = Gravity.CENTER;

        view.setOnClickListener(this);
        addView(view,params);

        cacheArray.put(index,view);
    }

    @Override
    public void onClick(View v) {
        int index = (int) v.getTag();
        changeChecked(index);

        if(mCurrentIndex != index) {
            mCurrentIndex = index;
            if(null != radioButtonClickListener) radioButtonClickListener.onRadioButtonClick(index);
        }
    }

    public void setIndex(int index) {
        changeChecked(index);
        if(mCurrentIndex != index) {
            mCurrentIndex = index;
            if(null != radioButtonClickListener) radioButtonClickListener.onRadioButtonClick(index);
        }
    }


    public void changeChecked(int index) {
        for(int i = 0 , size = cacheArray.size() ; i < size ; i++ ){
           int newIndex =  cacheArray.keyAt(i);
           cacheArray.get(newIndex).setCheck(newIndex == index);
        }
    }

    public void setRadioButtonClickListener(onRadioButtonClickListener radioButtonClickListener) {
        this.radioButtonClickListener = radioButtonClickListener;
    }

    public interface onRadioButtonClickListener{
        void onRadioButtonClick(int index) ;
    }

}

