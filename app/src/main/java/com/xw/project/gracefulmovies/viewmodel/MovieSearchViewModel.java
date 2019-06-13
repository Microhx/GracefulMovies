package com.xw.project.gracefulmovies.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import com.xw.project.gracefulmovies.data.DataResource;
import com.xw.project.gracefulmovies.entity.BaseSuperData;
import com.xw.project.gracefulmovies.entity.NewMovieItemData;
import com.xw.project.gracefulmovies.repository.MovieSearchRepository;
import com.xw.project.gracefulmovies.util.CommonUtils;
import com.xw.project.gracefulmovies.viewmodel.base.BaseViewModel;
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
