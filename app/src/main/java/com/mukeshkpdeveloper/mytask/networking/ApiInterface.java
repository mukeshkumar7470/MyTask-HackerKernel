package com.mukeshkpdeveloper.mytask.networking;

import com.google.gson.JsonObject;
import com.mukeshkpdeveloper.mytask.models.PhotosModel;
import com.mukeshkpdeveloper.mytask.models.PostsModel;

import java.util.ArrayList;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiInterface {

    @FormUrlEncoded
    @POST("login")
    Call<JsonObject> login(@FieldMap Map<String,String> params);

    @GET("photos")
    Call<ArrayList<PhotosModel>> getPhotos();

    @GET("posts")
    Call<ArrayList<PostsModel>> getPostList();

}
