package com.mukeshkpdeveloper.mytask.adapters;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.mukeshkpdeveloper.mytask.R;
import com.mukeshkpdeveloper.mytask.models.PostsModel;

import java.util.ArrayList;

public class PostsAdapter extends RecyclerView.Adapter<PostsAdapter.RecyclerViewHolder> {

    private ArrayList<PostsModel> courseDataArrayList;
    private Context mcontext;


    // creating a constructor class.
    public PostsAdapter(ArrayList<PostsModel> recyclerDataArrayList, Context mcontext) {
        this.courseDataArrayList = recyclerDataArrayList;
        this.mcontext = mcontext;
    }

    @NonNull
    @Override
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflate Layout
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.posts_layout_item, parent, false);
        return new RecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolder holder, int position) {
        // Set the data to textview from our modal class.
        PostsModel modal = courseDataArrayList.get(position);
        holder.tvTitle.setText(modal.getTitle());
        holder.tvDesc.setText(modal.getBody());

        holder.cvDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                /*Retrofit retrofit = new Retrofit.Builder()
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
                        if (response.code() == 200) {

                        }
                    }

                    @Override
                    public void onFailure(Call<ArrayList<PostsModel>> call, Throwable t) {
                        // in the method of on failure we are displaying a
                        // toast message for fail to get data.
                        Toast.makeText(mcontext, "Fail to get data", Toast.LENGTH_SHORT).show();
                    }
                });*/

                final Dialog dialog = new Dialog(mcontext);
                dialog.setContentView(R.layout.detail_layout);
                dialog.setTitle("Position" + position);
                dialog.setCancelable(true);
                TextView dTitle=(TextView)dialog.findViewById(R.id.d_title);
                TextView dDesc=(TextView)dialog.findViewById(R.id.d_desc);

                dTitle.setText(modal.getTitle());
                dDesc.setText(modal.getBody());

                dialog.show();
            }
        });
    }

    @Override
    public int getItemCount() {
        // this method returns the size of recyclerview
        return courseDataArrayList.size();
    }

    // View Holder Class to handle Recycler View.
    public class RecyclerViewHolder extends RecyclerView.ViewHolder {
        // creating variables for our views.
        private TextView tvTitle, tvDesc;
        private CardView cvDetail;

        public RecyclerViewHolder(@NonNull View itemView) {
            super(itemView);
            // initializing our views with their ids.
            tvTitle = itemView.findViewById(R.id.tv_title);
            tvDesc = itemView.findViewById(R.id.tv_desc);
            cvDetail = itemView.findViewById(R.id.cv_detail);

            mcontext = itemView.getContext();
        }
    }
}
