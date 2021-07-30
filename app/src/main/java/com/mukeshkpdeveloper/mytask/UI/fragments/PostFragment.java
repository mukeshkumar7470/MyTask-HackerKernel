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
import com.mukeshkpdeveloper.mytask.R;
import com.mukeshkpdeveloper.mytask.adapters.PhotosAdapter;
import com.mukeshkpdeveloper.mytask.adapters.PostsAdapter;
import com.mukeshkpdeveloper.mytask.models.PostsModel;
import com.mukeshkpdeveloper.mytask.networking.ApiInterface;
import com.mukeshkpdeveloper.mytask.utils.Constant;
import com.mukeshkpdeveloper.mytask.utils.OnRecyclerClickListner;
import com.mukeshkpdeveloper.mytask.utils.Util;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class PostFragment extends Fragment {

    RecyclerView rvPosts;
    Context context;
    private ArrayList<PostsModel> recyclerDataArrayList;
    private PostsAdapter recyclerViewAdapter;
    ProgressBar mprogressBar;
    private ShimmerFrameLayout mFrameLayout;


    public PostFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_post, container, false);

        context =getActivity();
        rvPosts = view.findViewById(R.id.rv_posts);
//        mprogressBar = view.findViewById(R.id.progress_bar);
        mFrameLayout =view.findViewById(R.id.shimmerLayout);

        // call api
        getPosts();

        return view;
    }

    private void getPosts() {
        if (Util.isNetworkAvailable(context)) {
            mprogressBar.setVisibility(View.VISIBLE);
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(Constant.POST_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            ApiInterface retrofitAPI = retrofit.create(ApiInterface.class);
            Call<ArrayList<PostsModel>> call = retrofitAPI.getPostList();
            call.enqueue(new Callback<ArrayList<PostsModel>>() {
                @Override
                public void onResponse(Call<ArrayList<PostsModel>> call, Response<ArrayList<PostsModel>> response) {
                    // inside on response method we are checking
                    // if the response is success or not.
                    mprogressBar.setVisibility(View.GONE);
                    if (response.code() == 200) {
                        recyclerDataArrayList = response.body();

                        // below line we are running a loop to add data to our adapter class.
                        for (int i = 0; i < recyclerDataArrayList.size(); i++) {
                            recyclerViewAdapter = new PostsAdapter(recyclerDataArrayList, context);

                            LinearLayoutManager manager = new LinearLayoutManager(context);
                            rvPosts.setLayoutManager(manager);
                            rvPosts.setAdapter(recyclerViewAdapter);
                        }

                        mFrameLayout.startShimmer();
                        mFrameLayout.setVisibility(View.GONE);
                        rvPosts.setVisibility(View.VISIBLE);
                    }
                }

                @Override
                public void onFailure(Call<ArrayList<PostsModel>> call, Throwable t) {
                    // in the method of on failure we are displaying a
                    // toast message for fail to get data.
                    Util.showProgressBar(context, false);
                    Toast.makeText(context, "Fail to get data", Toast.LENGTH_SHORT).show();
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