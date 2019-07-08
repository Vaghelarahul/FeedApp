package com.droidapps.joshtalk.feedapp.networking;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created By Rahul Vaghela on 9/7/19
 */

public interface RetrofitService {

    @GET("{path}")
    Call<ImageDataModel> fetchImages(@Path("path") String path);

}
