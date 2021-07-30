package com.mukeshkpdeveloper.mytask.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mukeshkpdeveloper.mytask.R;
import com.mukeshkpdeveloper.mytask.UI.activity.FullScreenImageActivity;
import com.mukeshkpdeveloper.mytask.models.PhotosModel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class PhotosAdapter extends RecyclerView.Adapter<PhotosAdapter.RecyclerViewHolder> {

    // creating a variable for our array list and context.
    private ArrayList<PhotosModel> courseDataArrayList;
    private Context mcontext;
    boolean isImageFitToScreen;

    // creating a constructor class.
    public PhotosAdapter(ArrayList<PhotosModel> recyclerDataArrayList, Context mcontext) {
        this.courseDataArrayList = recyclerDataArrayList;
        this.mcontext = mcontext;
    }

    @NonNull
    @Override
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflate Layout
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.photos_layout_item, parent, false);
        return new RecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolder holder, int position) {
        // Set the data to textview from our modal class.
        PhotosModel modal = courseDataArrayList.get(position);
        holder.tvTitle.setText(modal.getTitle());
        holder.tvDesc.setText(modal.getUrl());
        Picasso.get().load(modal.getUrl()).into(holder.ivImg);

        holder.ivImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mainIntent = new Intent(mcontext, FullScreenImageActivity.class);
                mainIntent.putExtra("img_url", modal.getUrl());
                mcontext.startActivity(mainIntent);
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
        private ImageView ivImg;

        public RecyclerViewHolder(@NonNull View itemView) {
            super(itemView);
            // initializing our views with their ids.
            tvTitle = itemView.findViewById(R.id.tv_title);
            tvDesc = itemView.findViewById(R.id.tv_desc);
            ivImg = itemView.findViewById(R.id.iv_img);

            mcontext = itemView.getContext();
        }
    }
}
