package com.xw.project.gracefulmovies.ui.activity;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentActivity;

import com.xw.project.gracefulmovies.R;
import com.xw.project.gracefulmovies.ui.fragment.MovieCategoryFragment;

/**
 * created by xinghe
 * <p>
 * 纵然万劫不复，纵然相思入骨，我依然待你眉眼如初，岁月如故。
 * <p>
 * date : 2019-06-17
 * <p>
 * version :
 * <p>
 * desc :
 */
public class EmptyActivity extends FragmentActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       setContentView(R.layout.activity_empty);

       getSupportFragmentManager().
               beginTransaction().
               add(R.id.id_content_layout, new MovieCategoryFragment()).
               commitAllowingStateLoss();
    }



}
