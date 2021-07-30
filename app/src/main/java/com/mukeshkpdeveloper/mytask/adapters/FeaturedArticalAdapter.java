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

public class FeaturedArticalAdapter extends RecyclerView.Adapter<FeaturedArticalAdapter.FeaturedArticalViewHolder> {

    private Context context;
    private List<AllArtical> list;

    public FeaturedArticalAdapter(Context context, List<AllArtical> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public FeaturedArticalViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view =inflater.inflate(R.layout.featured_artical_item,parent,false);
        return new FeaturedArticalViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FeaturedArticalViewHolder holder, int position) {

        holder.title.setText(list.get(position).getTitle());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class FeaturedArticalViewHolder extends RecyclerView.ViewHolder {

        TextView title,description;

        public FeaturedArticalViewHolder(@NonNull View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.title);
            description = itemView.findViewById(R.id.description);

        }
    }
}

