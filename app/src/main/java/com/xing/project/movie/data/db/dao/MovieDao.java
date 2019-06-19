package com.xing.project.movie.data.db.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Transaction;

import com.xing.project.movie.data.db.entity.MovieEntity;
import com.xing.project.movie.data.db.entity.MovieEntity;
import com.xing.project.movie.data.db.entity.MovieEntity;

import java.util.List;

/**
 * <p>
 * Created by woxingxiao on 2018-08-08.
 */
@Dao
public abstract class MovieDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public abstract void insertAll(List<MovieEntity> movies);

    @Query("SELECT * FROM movies WHERE isNow = 1 ORDER BY rank ASC")
    public abstract LiveData<List<MovieEntity>> loadMovieNowList();

    @Query("SELECT * FROM movies WHERE isNow = 0 ORDER BY rank ASC")
    public abstract LiveData<List<MovieEntity>> loadMovieFutureList();

    @Query("DELETE FROM movies WHERE isNow = 1")
    public abstract void deleteNow();

    @Query("DELETE FROM movies WHERE isNow = 0")
    public abstract void deleteFuture();

    @Transaction
    public void updateMovieNowList(List<MovieEntity> movies) {
        deleteNow();
        insertAll(movies);
    }

    @Transaction
    public void updateMovieFutureList(List<MovieEntity> movies) {
        deleteFuture();
        insertAll(movies);
    }
}
