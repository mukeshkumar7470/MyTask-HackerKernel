package com.mukeshkpdeveloper.mytask.UI.fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.facebook.shimmer.ShimmerFrameLayout;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.mukeshkpdeveloper.mytask.R;

import com.mukeshkpdeveloper.mytask.UI.activity.MainActivity;
import com.mukeshkpdeveloper.mytask.adapters.PhotosAdapter;
import com.mukeshkpdeveloper.mytask.models.PhotosModel;
import com.mukeshkpdeveloper.mytask.networking.ApiInterface;
import com.mukeshkpdeveloper.mytask.networking.RetrofitClient;
import com.mukeshkpdeveloper.mytask.networking.RetrofitClientTwo;
import com.mukeshkpdeveloper.mytask.utils.AppPreference;
import com.mukeshkpdeveloper.mytask.utils.Constant;
import com.mukeshkpdeveloper.mytask.utils.Util;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class PhotosFragment extends Fragment {

    RecyclerView rvPhotos;
    Context context;
    private ArrayList<PhotosModel> recyclerDataArrayList;
    private PhotosAdapter recyclerViewAdapter;
    ProgressBar mprogressBar;
    private ShimmerFrameLayout mFrameLayout;

    public PhotosFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_photos, container, false);

        context = getActivity();
        rvPhotos = view.findViewById(R.id.rv_photos);

        recyclerDataArrayList = new ArrayList<>();
//        mprogressBar = view.findViewById(R.id.progress_bar);
        mFrameLayout =view.findViewById(R.id.shimmerLayout);

        // call api
        getPhotos();

        return view;
    }

    private void getPhotos() {
        if (Util.isNetworkAvailable(context)) {
//            mprogressBar.setVisibility(View.VISIBLE);
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(Constant.PHOTO_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            ApiInterface retrofitAPI = retrofit.create(ApiInterface.class);
            Call<ArrayList<PhotosModel>> call = retrofitAPI.getPhotos();
            call.enqueue(new Callback<ArrayList<PhotosModel>>() {
                @Override
                public void onResponse(Call<ArrayList<PhotosModel>> call, Response<ArrayList<PhotosModel>> response) {
                    // inside on response method we are checking
                    // if the response is success or not.
//                    mprogressBar.setVisibility(View.GONE);
                    if (response.code() == 200) {
                        recyclerDataArrayList = response.body();

                        // below line we are running a loop to add data to our adapter class.
                        for (int i = 0; i < recyclerDataArrayList.size(); i++) {
                            recyclerViewAdapter = new PhotosAdapter(recyclerDataArrayList, context);
                            LinearLayoutManager manager = new LinearLayoutManager(context);
                            rvPhotos.setLayoutManager(manager);
                            rvPhotos.setAdapter(recyclerViewAdapter);

                        }

                        mFrameLayout.startShimmer();
                        mFrameLayout.setVisibility(View.GONE);
                        rvPhotos.setVisibility(View.VISIBLE);
                    }
                }

                @Override
                public void onFailure(Call<ArrayList<PhotosModel>> call, Throwable t) {
                    // in the method of on failure we are displaying a
                    // toast message for fail to get data.
                    Toast.makeText(context, "Failed to get data", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    @Override
    public void onResume() {
        mFrameLayout.startShimmer();
        super.onResume();
    }

    @Override
    public void onPause() {
        mFrameLayout.stopShimmer();
        super.onPause();
    }
}