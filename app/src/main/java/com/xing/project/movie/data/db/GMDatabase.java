package com.xing.project.movie.data.db;

import android.app.Application;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import androidx.annotation.WorkerThread;

import com.xing.project.movie.data.db.dao.BoxOfficeDao;
import com.xing.project.movie.data.db.dao.CityDao;
import com.xing.project.movie.data.db.dao.MovieDao;
import com.xing.project.movie.data.db.entity.BoxOfficeEntity;
import com.xing.project.movie.data.db.entity.CityEntity;
import com.xing.project.movie.data.db.entity.MovieEntity;
import com.xing.project.movie.data.db.dao.BoxOfficeDao;
import com.xing.project.movie.data.db.dao.CityDao;
import com.xing.project.movie.data.db.dao.MovieDao;
import com.xing.project.movie.data.db.entity.BoxOfficeEntity;
import com.xing.project.movie.data.db.entity.CityEntity;
import com.xing.project.movie.data.db.entity.MovieEntity;
import com.xing.project.movie.data.db.dao.BoxOfficeDao;
import com.xing.project.movie.data.db.dao.CityDao;
import com.xing.project.movie.data.db.dao.MovieDao;
import com.xing.project.movie.data.db.entity.BoxOfficeEntity;
import com.xing.project.movie.data.db.entity.CityEntity;
import com.xing.project.movie.data.db.entity.MovieEntity;

/**
 * <p>
 * Created by woxingxiao on 2018-08-08.
 */
@Database(entities = {
        CityEntity.class,
        MovieEntity.class,
        BoxOfficeEntity.class},
        version = 1,
        exportSchema = false)
@TypeConverters({Converters.class})
public abstract class GMDatabase extends RoomDatabase {

    private static final String DB_NAME = "graceful_movie.db";

    public abstract CityDao cityDao();

    public abstract MovieDao movieDao();

    public abstract BoxOfficeDao boxOfficeDao();

    @WorkerThread
    public static GMDatabase createAsync(Application application) {
        return Room.databaseBuilder(application, GMDatabase.class, DB_NAME).build();
    }
}
