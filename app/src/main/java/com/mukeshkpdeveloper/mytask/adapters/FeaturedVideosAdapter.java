package com.mukeshkpdeveloper.mytask.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mukeshkpdeveloper.mytask.R;
import com.mukeshkpdeveloper.mytask.models.AllArtical;

import java.util.List;

public class FeaturedVideosAdapter extends RecyclerView.Adapter<FeaturedVideosAdapter.FeaturedVideoViewHolder> {

    private Context context;
    private List<AllArtical> list;

    public FeaturedVideosAdapter(Context context, List<AllArtical> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public FeaturedVideoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view =inflater.inflate(R.layout.featured_video_item,parent,false);
        return new FeaturedVideoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FeaturedVideoViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public class FeaturedVideoViewHolder extends RecyclerView.ViewHolder {

        TextView title,description;

        public FeaturedVideoViewHolder(@NonNull View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.title);
            description = itemView.findViewById(R.id.description);

        }
    }

}
