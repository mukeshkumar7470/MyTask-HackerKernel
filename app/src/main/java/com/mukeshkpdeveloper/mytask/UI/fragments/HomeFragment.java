package com.mukeshkpdeveloper.mytask.UI.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.google.android.material.tabs.TabLayout;
import com.mukeshkpdeveloper.mytask.R;

public class HomeFragment extends Fragment {


    private PhotosFragment photosFragment;
    private PostFragment dthFragment;
    private TabLayout allTabs;



    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        allTabs  = (TabLayout)view.findViewById(R.id.tabs);

        bindWidgetsWithAnEvent();
        setupTabLayout();

        loadFragment(new PhotosFragment());

        return view;
    }

    private void loadFragment(Fragment fragment) {
        // load fragment
        FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_container, fragment);
        transaction.commit();
    }

    private void setupTabLayout() {
        photosFragment = new PhotosFragment();
        dthFragment = new PostFragment();
        allTabs.addTab(allTabs.newTab().setText("Photos"),true);
        allTabs.addTab(allTabs.newTab().setText("Posts"));
    }
    private void bindWidgetsWithAnEvent()
    {
        allTabs.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                setCurrentTabFragment(tab.getPosition());
            }
            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }
            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });
    }
    private void setCurrentTabFragment(int tabPosition)
    {
        switch (tabPosition)
        {
            case 0 :
                loadFragment(new PhotosFragment());
                break;
            case 1 :
                loadFragment(new PostFragment());
                break;
        }
    }
}