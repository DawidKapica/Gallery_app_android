package com.example.v2swimgallery_list4;

import android.support.v4.view.ViewPager;
import android.util.Log;

public class PageListener extends ViewPager.SimpleOnPageChangeListener {

    private static int currentPage2;

    public static void setCurrentPage2(int currentPage2) {
        PageListener.currentPage2 = currentPage2;
    }

    public static int getCurrentPage2() {
        return currentPage2;
    }

    public void onPageSelected(int position) {
        Log.i("+=+=+=+=+=+=+=+=+=+=+=", "page selected " + position);
        currentPage2 = position;
    }
}
