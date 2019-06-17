package com.xw.project.gracefulmovies.ui.fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProviders;
import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.xw.project.gracefulmovies.R;
import com.xw.project.gracefulmovies.data.DataResource;
import com.xw.project.gracefulmovies.databinding.FragmentMovieListBinding;
import com.xw.project.gracefulmovies.entity.NewMovieItemData;
import com.xw.project.gracefulmovies.ui.activity.NewMovieDetailActivity;
import com.xw.project.gracefulmovies.ui.adapter.NewMovieItemAdapter;
import com.xw.project.gracefulmovies.ui.widget.BlurTransformation;
import com.xw.project.gracefulmovies.util.Logy;
import com.xw.project.gracefulmovies.viewmodel.MovieViewModel;
import com.yarolegovich.discretescrollview.DiscreteScrollView;
import com.yarolegovich.discretescrollview.transform.ScaleTransformer;
import java.util.List;

/**
 *
 * 电影列表
 *
 */
public class MovieListFragment extends BaseFragment<FragmentMovieListBinding> implements
    DiscreteScrollView.ScrollStateChangeListener<BaseViewHolder>,  BaseQuickAdapter.OnItemClickListener {

  public static MovieListFragment getInstance(int position) {
    MovieListFragment fragment = new MovieListFragment();
    Bundle bundle = new Bundle();
    bundle.putInt("position", position);
    fragment.setArguments(bundle);
    return fragment;
  }

  private int mPosition;
  private boolean isIntentTriggered;
  private int mPreIntentPos;
  private BlurTransformation mBlurTransformation;
  private int mMaxIndex;

  private MovieViewModel mMovieViewModel;
  private NewMovieItemAdapter mAdapter;

  @Override
  public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    mBlurTransformation = new BlurTransformation(mActivity, 10);
  }

  @Override protected void receiveArguments(Bundle bundle) {
    if (bundle != null) {
      mPosition = bundle.getInt("position",0);
    }
  }

  @Override
  protected int viewLayoutRes() {
    return R.layout.fragment_movie_list;
  }

  @Override
  protected void afterInflateView() {
    mBinding.infiniteViewPager.setItemTransformer(
        new ScaleTransformer.Builder()
            .setMinScale(0.8f)
            .build()
    );

    mBinding.infiniteViewPager.addScrollStateChangeListener(this);

    mBinding.bgIv1.setAlpha(0f);

    mMovieViewModel = ViewModelProviders.of(this).get(MovieViewModel.class);
    mMovieViewModel.getMainPageMovie(mPosition).observe(getViewLifecycleOwner(), resource -> {
      assert resource != null;
      processStatusView(mBinding.container, resource);

      if (resource.getStatus() == DataResource.Status.LOADING) {
        mBinding.infiniteViewPager.setVisibility(View.INVISIBLE);

      } else if (resource.getStatus() == DataResource.Status.SUCCESS) {
        // MovieDao dao = GMApplication.getInstance().getDatabase().movieDao();
        //Observer<List<NewMovieItemData>> observer = movies -> {

        List<NewMovieItemData> movies = resource.getData();
        if (mAdapter == null) {
          mAdapter = new NewMovieItemAdapter();
          mAdapter.setOnItemClickListener(this);

          mAdapter.addData(movies);
          mBinding.infiniteViewPager.setAdapter(mAdapter);
        } else {
          mAdapter.replaceData(movies);

          try {
            mBinding.infiniteViewPager.smoothScrollToPosition(0);
          } catch (IllegalArgumentException ignored) {}


        }

        mBinding.infiniteViewPager.setVisibility(View.VISIBLE);
        mBinding.bgIv1.animate().alpha(1).setDuration(1000)
            .withStartAction(() -> displayBgImage(0, mBinding.bgIv1));
        mBinding.bgIv2.animate().alpha(0).setDuration(1000)
            .withEndAction(() -> displayBgImage(1, mBinding.bgIv2));
      }
      ;

      // if (isNow) {
      //     dao.loadMovieNowList().observe(getViewLifecycleOwner(), observer);
      // } else {
      //     dao.loadMovieFutureList().observe(getViewLifecycleOwner(), observer);
      // }
      // }
    });

    //
    // GMApplication.getInstance().getCityRepository().getCity()
    //         .observe(getViewLifecycleOwner(), mMovieViewModel::setCity);
  }

  @Override
  protected void onLazyLoad() {
    mMovieViewModel.load();
  }

  private void displayBgImage(int index, ImageView imageView) {
    FragmentActivity activity = getActivity();
    if (activity == null) { return; }

    List<NewMovieItemData> data = mAdapter.getData();
    if (data == null || data.isEmpty() || index >= data.size()) { return; }

    Glide.with(activity)
        .load(data.get(index).getImage())
        .transform(mBlurTransformation)
        .placeholder(R.drawable.pic_got)
        .error(R.drawable.pic_got)
        .into(imageView);
  }

  @Override
  protected void onReload() {
    mMovieViewModel.load();
  }

  @Override
  public void onScrollStart(@NonNull BaseViewHolder currentItemHolder, int adapterPosition) {
    isIntentTriggered = true;
  }

  @Override
  public void onScrollEnd(@NonNull BaseViewHolder currentItemHolder, int adapterPosition) {
    List<NewMovieItemData> data = mAdapter.getData();
    if (data.isEmpty()) { return; }

    final int size = data.size();
    mMaxIndex = adapterPosition > mMaxIndex ? adapterPosition : mMaxIndex;

    boolean isOdd = adapterPosition % 2 != 0;
    if (isOdd) {
      displayBgImage(adapterPosition, mBinding.bgIv2);

      if (mMaxIndex < adapterPosition + 1 && adapterPosition + 1 < size) { // 预加载右边一张
        displayBgImage(adapterPosition + 1, mBinding.bgIv1);
      }
    } else {
      displayBgImage(adapterPosition, mBinding.bgIv1);

      if (mMaxIndex < adapterPosition + 1 && adapterPosition + 1 < size) { // 预加载右边一张
        displayBgImage(adapterPosition + 1, mBinding.bgIv2);
      }
    }
  }

  @Override
  public void onScroll(float scrollPosition, int currentPosition, int newPosition,
                       @Nullable BaseViewHolder currentHolder,
                       @Nullable BaseViewHolder newCurrent) {
    List<NewMovieItemData> data = mAdapter.getData();
    if (data.isEmpty()) { return; }

    final int size = data.size();
    final float position = Math.abs(scrollPosition);
    final boolean isOdd = currentPosition % 2 != 0;

    if (mPreIntentPos != newPosition) {
      isIntentTriggered = false;
    }
    if (isOdd) {
      if (!isIntentTriggered && newPosition >= 0 && newPosition <= size - 1) {
        displayBgImage(newPosition, mBinding.bgIv1);

        isIntentTriggered = true;
      }

      mBinding.bgIv1.setAlpha(position);
      mBinding.bgIv2.setAlpha(1 - position);
    } else {
      if (!isIntentTriggered && newPosition >= 0 && newPosition <= size - 1) {
        displayBgImage(newPosition, mBinding.bgIv2);

        isIntentTriggered = true;
      }

      mBinding.bgIv1.setAlpha(1 - position);
      mBinding.bgIv2.setAlpha(position);
    }
    mPreIntentPos = newPosition;

    float alpha;
    if (newCurrent != null) {
      alpha = position / 2 + 0.5f;
      newCurrent.itemView.setAlpha(alpha);
    }
    if (currentHolder != null) {
      alpha = -position / 2 + 1f;
      currentHolder.itemView.setAlpha(alpha);
    }
  }


  @Override public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
    NewMovieItemData itemData = mAdapter.getData().get(position);
    if(null != itemData) {
      NewMovieDetailActivity.Companion.start(requireContext(), itemData.getMovieId());
    }
  }
}