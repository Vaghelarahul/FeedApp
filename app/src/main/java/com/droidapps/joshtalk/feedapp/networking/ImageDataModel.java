package com.droidapps.joshtalk.feedapp.networking;

import com.droidapps.joshtalk.feedapp.database.ImageEntity;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created By Rahul Vaghela on 9/7/19
 */
public class ImageDataModel {

    @SerializedName("page")
    @Expose
    private Integer page;

    @SerializedName("posts")
    @Expose
    private List<ImageEntity> imageList;

    public int getPage() {
        return page == null ? 0 : page;
    }

    public List<ImageEntity> getImageList() {
        return imageList;
    }
}
