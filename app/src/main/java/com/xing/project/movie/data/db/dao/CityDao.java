package com.xing.project.movie.data.db.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Transaction;

import com.xing.project.movie.data.db.entity.CityEntity;
import com.xing.project.movie.data.db.entity.CityEntity;
import com.xing.project.movie.data.db.entity.CityEntity;

/**
 * <p>
 * Created by woxingxiao on 2018-08-08.
 */
@Dao
public abstract class CityDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public abstract void insert(CityEntity city);

    @Query("SELECT * FROM cities WHERE isUpper = 0 LIMIT 1")
    public abstract LiveData<CityEntity> loadCity();

    @Query("DELETE FROM cities")
    public abstract void delete();

    @Transaction
    public void updateCity(CityEntity city) {
        delete();
        insert(city);
    }
}
