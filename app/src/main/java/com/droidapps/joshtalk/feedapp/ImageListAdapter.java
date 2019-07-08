package com.droidapps.joshtalk.feedapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.droidapps.joshtalk.feedapp.database.ImageEntity;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * Created By Rahul Vaghela on 8/7/19
 */
public class ImageListAdapter extends RecyclerView.Adapter<ImageListAdapter.ImageViewHolder> {

    private List<ImageEntity> imageList;

    private PageScrollListener listener;

    interface PageScrollListener {
        void onPageScrolled();
    }

    public ImageListAdapter(PageScrollListener listener, List<ImageEntity> imageLis) {
        this.listener = listener;
        this.imageList = imageLis;
    }

    @NonNull
    @Override
    public ImageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.image_list_item, parent, false);
        return new ImageViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ImageViewHolder holder, int position) {
        ImageEntity entity = imageList.get(position);
        holder.updateData(entity);

        if (position == imageList.size() - 1) {
            listener.onPageScrolled();
        }
    }

    @Override
    public int getItemCount() {
        return imageList != null ? imageList.size() : 0;
    }

    void updateList(List<ImageEntity> list) {
        if (list == null) return;
        this.imageList.addAll(list);
        notifyDataSetChanged();
    }

    class ImageViewHolder extends RecyclerView.ViewHolder {

        TextView eventName, eventDate, likes, views, shares;
        ImageView thumbnailView;

        ImageViewHolder(@NonNull View itemView) {
            super(itemView);

            thumbnailView = itemView.findViewById(R.id.thumbnail);
            eventName = itemView.findViewById(R.id.event_name);
            eventDate = itemView.findViewById(R.id.event_date);
            likes = itemView.findViewById(R.id.likes);
            views = itemView.findViewById(R.id.views);
            shares = itemView.findViewById(R.id.shares);
        }

        private void updateData(ImageEntity entity) {

            eventName.setText(entity.getEventName());
            eventDate.setText(getFormattedDate(entity.getEventDate()));

            likes.setText(String.valueOf(entity.getLikes()));
            views.setText(String.valueOf(entity.getViews()));
            shares.setText(String.valueOf(entity.getShares()));

            loadImage(entity.getThumbnailImage(), thumbnailView);
        }

        private String getFormattedDate(long millis) {
            Date date = new Date(millis * 1000L);
            return new SimpleDateFormat("EEEE, MMM dd yyyy", Locale.getDefault()).format(date);
        }

        private void loadImage(String url, ImageView imageView) {
            Glide.with(imageView.getContext())
                    .load(url)
                    .into(imageView);
        }
    }
}
