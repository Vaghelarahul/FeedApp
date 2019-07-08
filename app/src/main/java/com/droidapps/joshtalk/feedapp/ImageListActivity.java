package com.droidapps.joshtalk.feedapp;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.droidapps.joshtalk.feedapp.database.ImageDao;
import com.droidapps.joshtalk.feedapp.database.ImageDatabase;
import com.droidapps.joshtalk.feedapp.database.ImageEntity;
import com.droidapps.joshtalk.feedapp.databinding.ActivityImageListBinding;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.droidapps.joshtalk.feedapp.networking.ApiProvider.PAGE_1;
import static com.droidapps.joshtalk.feedapp.networking.ApiProvider.PAGE_2;
import static com.droidapps.joshtalk.feedapp.networking.ApiProvider.PAGE_3;

public class ImageListActivity extends AppCompatActivity implements Networking.NetworkResponseListener, ImageListAdapter.PageScrollListener {

    private static final String TAG = "ImageListActivity";

    private int pageNum = 0;

    private ActivityImageListBinding binding;

    private List<ImageEntity> imageList;
    private ImageListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_image_list);

        imageList = new ArrayList<>();

        adapter = new ImageListAdapter(this, imageList);
        binding.recyclerView.setAdapter(adapter);

        ImageDao database = ImageDatabase.getDatabase(this).imageDao();

        adapter.updateList(database.getImageList().getValue());

        onPageScrolled();
    }

    private void fetchImageList(String path) {
        if (pageNum == 0) {
            showLoader();
        } else if (pageNum != 2) {
            showPagingLoader();

        }

        Networking.fetchImageListFromServer(path, this, this);

    }

    @Override
    public void onPageScrolled() {
        switch (pageNum) {
            case 0:
                fetchImageList(PAGE_1);
                break;

            case 1:
                fetchImageList(PAGE_2);
                break;

            case 2:
                fetchImageList(PAGE_3);
                break;
        }
    }

    private void showLoader() {
        binding.loader.setVisibility(View.VISIBLE);
    }

    private void hideLoader() {
        binding.loader.setVisibility(View.GONE);
    }

    private void showPagingLoader() {
        binding.pagingLoader.setVisibility(View.VISIBLE);
    }

    private void hidePagingLoader() {
        binding.pagingLoader.setVisibility(View.GONE);
    }

    private void showEmptyMessage() {
        binding.errorTv.setVisibility(View.VISIBLE);
    }

    private void hideEmptyMessage() {
        binding.errorTv.setVisibility(View.GONE);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.image_list_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.sort_by_date:
                Collections.sort(imageList, (e1, e2) -> -e1.getEventDate().compareTo(e2.getEventDate()));
                break;

            case R.id.sort_by_likes:
                Collections.sort(imageList, (e1, e2) -> e1.getLikes().compareTo(e2.getLikes()));
                break;

            case R.id.sort_by_views:
                Collections.sort(imageList, (e1, e2) -> e1.getViews().compareTo(e2.getViews()));
                break;

            case R.id.sort_by_share:
                Collections.sort(imageList, (e1, e2) -> e1.getShares().compareTo(e2.getShares()));
                break;
        }
        adapter.updateList(imageList);

        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onSuccess(int pageNum, List<ImageEntity> list) {
        this.pageNum = pageNum;
        if (pageNum == 0) imageList.clear();
        adapter.updateList(list);

        hideLoader();
        hideEmptyMessage();
        hidePagingLoader();
    }

    @Override
    public void onError(String errorMsg) {

        if (pageNum == 0) {
            showEmptyMessage();
        } else {
            hideEmptyMessage();
        }

        hideLoader();
        hidePagingLoader();
    }

}
