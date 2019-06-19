package com.xing.project.movie.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;

import com.xing.project.movie.data.DataResource;
import com.xing.project.movie.entity.BaseSuperData;
import com.xing.project.movie.entity.NewMovieItemData;
import com.xing.project.movie.repository.MovieSearchRepository;
import com.xing.project.movie.util.CommonUtils;
import com.xing.project.movie.viewmodel.base.BaseViewModel;

import java.util.List;

/**
 * author: Java
 *
 * 纵然万劫不复，纵然相思入骨，我依然待你眉眼如初，岁月如故。
 *
 * date : 2019/6/10
 *
 * version : 1.0.1
 *
 * desc : 电影搜素ViewModel
 */
public class MovieSearchViewModel extends BaseViewModel {

  public MediatorLiveData<BaseSuperData<NewMovieItemData>> itemData = new MediatorLiveData<>();
  private MovieSearchRepository mMovieSearchRepository = new MovieSearchRepository();

  public void searchMovieInfo(String movieName, int pageIndex) {
    LiveData<DataResource<List<NewMovieItemData>>> liveData = mMovieSearchRepository.searchMovieData(movieName, pageIndex);
    liveData.observeForever(list -> {
      if(list.getStatus() == DataResource.Status.LOADING) return;

      BaseSuperData<NewMovieItemData> superData = new BaseSuperData<>();
      superData.index = pageIndex;
      superData.data = CommonUtils.getSafeArrayList(list.getData());

      itemData.setValue(superData);

    });

    // Transformations.map(mMovieSearchRepository.searchMovieData(movieName, pageIndex),
    //     new Function<DataResource<List<NewMovieItemData>>, List<NewMovieItemData>>() {
    //   @Override public List<NewMovieItemData> apply(DataResource<List<NewMovieItemData>> input) {
    //     return null;
    //   }
    // }).observe(new ); ;
    //

  }


}
