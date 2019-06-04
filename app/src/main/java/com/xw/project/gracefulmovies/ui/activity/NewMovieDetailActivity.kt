package com.xw.project.gracefulmovies.ui.activity

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import com.xw.project.gracefulmovies.R
import com.xw.project.gracefulmovies.data.DataResource
import com.xw.project.gracefulmovies.entity.NewMovieDetailData
import com.xw.project.gracefulmovies.ui.activity.base.NewBaseActivity
import com.xw.project.gracefulmovies.ui.fragment.PreviewImageFragment
import com.xw.project.gracefulmovies.ui.widget.BlurTransformation
import com.xw.project.gracefulmovies.util.Logy
import com.xw.project.gracefulmovies.util.MathUtils
import com.xw.project.gracefulmovies.util.StringUtils
import com.xw.project.gracefulmovies.viewmodel.MovieDetailViewModel
import kotlinx.android.synthetic.main.activity_movie_detail_new.*

/**
 * 影片详情
 *
 *
 * Created by woxingxiao on 2018-08-15.
 */
class NewMovieDetailActivity : NewBaseActivity() {

  /**
   * 电影id
   */
  private var movieId: String? = null

  private var mPreviewImageFragment: PreviewImageFragment? = null

  private var mViewModel: MovieDetailViewModel? = null

  override fun getContentLayout() = R.layout.activity_movie_detail_new

  override fun receiveIntent(intent: Intent?) {
    intent?.let {
        movieId = it.getStringExtra("movieId")
    }
  }

  override fun initDatas() {
    transparentStatusBar()

    mViewModel = ViewModelProviders.of(this).get(MovieDetailViewModel::class.java)
    mViewModel!!.getNewMovieDetails(movieId).observe(this, Observer<DataResource<NewMovieDetailData>> {
        showData(it)
    })

    mViewModel!!.load()
  }

  private fun showData(resource: DataResource<NewMovieDetailData>?) {
    if(resource == null) {
      showError()
      return
    }

    val status = resource.status
    when(status) {
      DataResource.Status.LOADING -> {
        showLoading()
      }

      DataResource.Status.SUCCESS -> {
        showContent()
        addData(resource.data)
      }
      else -> {
        showError()
      }
    }
  }


  @SuppressLint("SetTextI18n")
  private fun addData(data: NewMovieDetailData?) {
    transparentStatusBar()

    data?.apply {
      Glide.with(this@NewMovieDetailActivity)
        .load(this.image)
        .transform(BlurTransformation(this@NewMovieDetailActivity, 10f))
        .into(header_bg_iv)

      Glide.with(this@NewMovieDetailActivity).load(this.image).into(cover_iv)

      name_tv.text = this.movieName
      name_en_tv.text = this.transitionName
      rating_bar.rating = MathUtils.calculateRate(this.score)
      rating_tv.text = "${this.score} 分"
      type_container.setTagData(StringUtils.parseTag(this.movieType),R.color.colorAccent)

      duration_tv.text = this.movieSize
      release_date_tv.text = this.showTime

      story_brief_tv.text = this.description
    }
  }

  companion object {

     fun start(context: Context, movieId:String) {
      context.startActivity(Intent(context, NewMovieDetailActivity::class.java).putExtra("movieId",movieId))
    }

  }

}