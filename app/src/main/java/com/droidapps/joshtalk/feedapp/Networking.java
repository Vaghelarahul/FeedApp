package com.droidapps.joshtalk.feedapp;

import android.content.Context;
import android.util.Log;

import com.droidapps.joshtalk.feedapp.database.ImageDao;
import com.droidapps.joshtalk.feedapp.database.ImageDatabase;
import com.droidapps.joshtalk.feedapp.database.ImageEntity;
import com.droidapps.joshtalk.feedapp.networking.ApiProvider;
import com.droidapps.joshtalk.feedapp.networking.ImageDataModel;

import java.util.List;
import java.util.concurrent.Executors;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created By Rahul Vaghela on 9/7/19
 */
public class Networking {

    private static final String TAG = "Networking";

    interface NetworkResponseListener {
        void onSuccess(int pageNum, List<ImageEntity> list);

        void onError(String errorMsg);
    }

    public static void fetchImageListFromServer(String path, Context context, NetworkResponseListener listener) {

        ApiProvider.getApiService().fetchImages(path)
                .enqueue(new Callback<ImageDataModel>() {
                    @Override
                    public void onResponse(Call<ImageDataModel> call, Response<ImageDataModel> response) {
                        Log.d(TAG, "onResponse: " + response.code());
                        Log.d(TAG, "onResponse: " + response.message());

                        if (response.isSuccessful()) {

                            ImageDataModel data = response.body();

                            if (data != null) {
                                Executors.newSingleThreadExecutor().execute(() -> {
                                    ImageDao imageDao = ImageDatabase.getDatabase(context.getApplicationContext()).imageDao();
                                    if (data.getPage() == 1) {
                                        imageDao.deleteImageList();
                                    }
                                    imageDao.insertImageList(data.getImageList());
                                });
                            }
                            listener.onSuccess(data.getPage(), data.getImageList());

                        } else {
                            listener.onError("Network error. Please try again");
                        }
                    }

                    @Override
                    public void onFailure(Call<ImageDataModel> call, Throwable t) {
                        listener.onError(t.getMessage());
                    }
                });

    }

}
