package com.example.v2swimgallery_list4;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class ViewPagerAdapter_Details extends FragmentPagerAdapter {

    private Fragment[] childFragments;

    public ViewPagerAdapter_Details(FragmentManager fm) {
        super(fm);
        childFragments = new Fragment[] {
                new Fragment_details(), //0
                new Fragment_exif(), //1
        };
    }

    @Override
    public Fragment getItem(int position) {
        return childFragments[position];
    }

    @Override
    public int getCount() {
        return childFragments.length; //2 items
    }

    @Override
    public CharSequence getPageTitle(int position) {
        String title = getItem(position).getClass().getName();
        return title.subSequence(title.lastIndexOf(".") + 1, title.length());
    }
}