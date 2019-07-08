package com.droidapps.joshtalk.feedapp.database;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created By Rahul Vaghela on 8/7/19
 */

@Entity(tableName = "imageList")
public class ImageEntity {

    @PrimaryKey(autoGenerate = true)
    private Long _ID;

    @SerializedName("thumbnail_image")
    @Expose
    private String thumbnailImage;

    @SerializedName("event_name")
    @Expose
    private String eventName;

    @SerializedName("event_date")
    @Expose
    private Long eventDate;

    @SerializedName("views")
    @Expose
    private Long views;

    @SerializedName("likes")
    @Expose
    private Long likes;

    @SerializedName("shares")
    @Expose
    private Long shares;

    @Ignore
    public ImageEntity(String thumbnailImage, String eventName, Long eventDate, Long views, Long likes, Long shares) {
        this.thumbnailImage = thumbnailImage;
        this.eventName = eventName;
        this.eventDate = eventDate;
        this.views = views;
        this.likes = likes;
        this.shares = shares;
    }

    public ImageEntity(Long _ID, String thumbnailImage, String eventName, Long eventDate, Long views, Long likes, Long shares) {
        this._ID = _ID;
        this.thumbnailImage = thumbnailImage;
        this.eventName = eventName;
        this.eventDate = eventDate;
        this.views = views;
        this.likes = likes;
        this.shares = shares;
    }

    public Long get_ID() {
        return _ID;
    }

    public void set_ID(Long _ID) {
        this._ID = _ID;
    }

    public String getThumbnailImage() {
        return thumbnailImage;
    }

    public void setThumbnailImage(String thumbnailImage) {
        this.thumbnailImage = thumbnailImage;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public Long getEventDate() {
        return eventDate;
    }

    public void setEventDate(Long eventDate) {
        this.eventDate = eventDate;
    }

    public Long getViews() {
        return views;
    }

    public void setViews(Long views) {
        this.views = views;
    }

    public Long getLikes() {
        return likes;
    }

    public void setLikes(Long likes) {
        this.likes = likes;
    }

    public Long getShares() {
        return shares;
    }

    public void setShares(Long shares) {
        this.shares = shares;
    }
}
