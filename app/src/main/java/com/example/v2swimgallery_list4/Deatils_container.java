package com.example.v2swimgallery_list4;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class Deatils_container extends Fragment {
    ViewPagerAdapter_Details mPager;

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View rootView = inflater.inflate(R.layout.details_and_exif, container, false);
        ViewPager viewPager = rootView.findViewById(R.id.view_pager_details);
        mPager = new ViewPagerAdapter_Details(getChildFragmentManager());
        viewPager.setAdapter(mPager);
        TabLayout tabLayout = rootView.findViewById(R.id.tab_layout);
        tabLayout.setupWithViewPager(viewPager);
        return rootView;
    }

}
