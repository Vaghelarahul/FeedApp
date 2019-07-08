package com.droidapps.joshtalk.feedapp.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

/**
 * Created By Rahul Vaghela on 8/7/19
 */

@Dao
public interface ImageDao {

    @Insert
    void insertImageList(List<ImageEntity> entityList);

    @Query("SELECT * FROM imageList")
    LiveData<List<ImageEntity>> getImageList();

    @Query("DELETE FROM imageList")
    void deleteImageList();

}
