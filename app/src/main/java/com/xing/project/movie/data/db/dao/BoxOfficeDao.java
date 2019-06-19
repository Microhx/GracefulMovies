package com.xing.project.movie.data.db.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Transaction;

import com.xing.project.movie.data.db.entity.BoxOfficeEntity;
import com.xing.project.movie.data.db.entity.BoxOfficeEntity;
import com.xing.project.movie.data.db.entity.BoxOfficeEntity;

import java.util.List;

/**
 * <p>
 * Created by woxingxiao on 2018-08-17.
 */
@Dao
public abstract class BoxOfficeDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract void insertAll(List<BoxOfficeEntity> boxOffices);

    @Query("SELECT * FROM box_offices ORDER BY id ASC")
    public abstract LiveData<List<BoxOfficeEntity>> loadBoxOfficeList();

    @Query("DELETE FROM box_offices")
    public abstract void delete();

    @Transaction
    public void update(List<BoxOfficeEntity> boxOffices) {
        delete();
        insertAll(boxOffices);
    }
}
