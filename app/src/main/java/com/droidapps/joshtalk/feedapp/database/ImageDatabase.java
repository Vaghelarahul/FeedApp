package com.droidapps.joshtalk.feedapp.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

/**
 * Created By Rahul Vaghela on 8/7/19
 */

@Database(entities = {ImageEntity.class}, version = 1, exportSchema = false)
public abstract class ImageDatabase extends RoomDatabase {

    private static final Object LOCK = new Object();
    private static ImageDatabase sInstance;

    public static ImageDatabase getDatabase(Context context) {
        if (sInstance == null) {
            synchronized (LOCK) {
                if (sInstance == null) {
                    sInstance = Room.databaseBuilder(context.getApplicationContext(), ImageDatabase.class, "ImagesDatabase")
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }
        return sInstance;
    }

    public abstract ImageDao imageDao();
}
