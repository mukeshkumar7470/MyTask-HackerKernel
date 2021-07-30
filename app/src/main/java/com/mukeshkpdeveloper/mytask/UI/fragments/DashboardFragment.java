package com.mukeshkpdeveloper.mytask.UI.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mukeshkpdeveloper.mytask.R;
import com.mukeshkpdeveloper.mytask.adapters.FeaturedArticalAdapter;
import com.mukeshkpdeveloper.mytask.adapters.FeaturedVideosAdapter;
import com.mukeshkpdeveloper.mytask.models.AllArtical;

import java.util.ArrayList;


public class DashboardFragment extends Fragment {
    RecyclerView homeartical_recycler,videoartical_recycler;
    private  ArrayList<AllArtical> list = new ArrayList<>();



    public DashboardFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_dashboard, container, false);

        homeartical_recycler = view.findViewById(R.id.homeartical_recycler);
        videoartical_recycler = view.findViewById(R.id.videoartical_recycler);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL, false);
        homeartical_recycler.setLayoutManager(layoutManager);

        LinearLayoutManager videolayoutManager = new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL, false);
        videoartical_recycler.setLayoutManager(videolayoutManager);

        list.add(new AllArtical("","Much up your way","msdjbbsdj"));
        list.add(new AllArtical("","Much up your way","msdjbbsdj"));
        list.add(new AllArtical("","Much up your way","msdjbbsdj"));
        list.add(new AllArtical("","Much up your way","msdjbbsdj"));
        list.add(new AllArtical("","Much up your way","msdjbbsdj"));
        list.add(new AllArtical("","Much up your way","msdjbbsdj"));
        list.add(new AllArtical("","Much up your way","msdjbbsdj"));
        list.add(new AllArtical("","Much up your way","msdjbbsdj"));

        FeaturedArticalAdapter adapter = new FeaturedArticalAdapter(getActivity(),list);
        homeartical_recycler.setAdapter(adapter);

        FeaturedVideosAdapter videoadapter = new FeaturedVideosAdapter(getActivity(),list);
        videoartical_recycler.setAdapter(videoadapter);

        return view;
    }
}