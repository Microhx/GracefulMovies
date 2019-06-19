package com.xing.project.movie.ui.activity

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide

import com.xing.project.movie.data.DataResource
import com.xing.project.movie.entity.NewMovieDetailData
import com.xing.project.movie.ui.activity.base.NewBaseActivity
import com.xing.project.movie.ui.activity.play.PlayerDelegateActivity
import com.xing.project.movie.ui.fragment.PreviewImageFragment
import com.xing.project.movie.ui.widget.BlurTransformation
import com.xing.project.movie.util.Logy
import com.xing.project.movie.util.MathUtils
import com.xing.project.movie.util.StringUtils
import com.xing.project.movie.viewmodel.MovieDetailViewModel
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
  private var mMovieDetailData : NewMovieDetailData?= null

  override fun getContentLayout() = com.xing.project.movie.R.layout.activity_movie_detail_new


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
      mMovieDetailData = this

      Glide.with(this@NewMovieDetailActivity)
        .load(this.image)
        .transform(BlurTransformation(this@NewMovieDetailActivity, 10f))
        .into(header_bg_iv)

      Glide.with(this@NewMovieDetailActivity).load(this.image).into(cover_iv)

      name_tv.text = this.movieName
      name_en_tv.text = this.transitionName
      rating_bar.rating = MathUtils.calculateRate(this.score)
      rating_tv.text = "${this.score} 分"
      type_container.setTagData(StringUtils.parseTag(this.movieType), com.xing.project.movie.R.color.colorAccent)

      duration_tv.text = this.movieSize
      release_date_tv.text = this.showTime

      story_brief_tv.text = this.description

      linear_movie_detail.addData(this)

      //导演
      id_tv_director.text = this.movieDirector

      // 演员
      id_tv_actor.text = StringUtils.parseActors(this.movieActors)

      Logy.i("imageContent:" + this.contentImage)

      //TODO
      Glide.with(applicationContext).load(this.contentImage).into(id_image_view)

    }
  }

  fun gotoMovie(v:View) {
    mMovieDetailData?.apply {
      PlayerDelegateActivity.start(this@NewMovieDetailActivity, this.movieId)
    }
  }


  companion object {

     fun start(context: Context, movieId:String) {
      context.startActivity(Intent(context, NewMovieDetailActivity::class.java).putExtra("movieId",movieId))
    }

  }

}
