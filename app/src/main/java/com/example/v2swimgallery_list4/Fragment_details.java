package com.example.v2swimgallery_list4;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;

public class Fragment_details extends Fragment {

    private TextView textView;
    private ImageView imageView;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        // Do something that differs the Activity's menu here
        inflater.inflate(R.menu.photo_menu, menu );
        MenuItem menuItem = menu.findItem(R.id.detailsItem);
        menuItem.setTitle("");
        menuItem.setVisible(false);
        menuItem = menu.findItem(R.id.shareItem1);
        menuItem.setVisible(false);
        menuItem = menu.findItem(R.id.wallpaperItem);
        menuItem.setVisible(false);
        super.onCreateOptionsMenu(menu, inflater);

    }

//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
////        switch (item.getItemId()) {
////            case R.id.detailsItem:
////                MainActivity main3Activity = new MainActivity();
////                Intent intention=new Intent(getContext(), MainActivity.class);
////                getContext().startActivity(intention);
////                break;
////
////
////        }
//        return super.onOptionsItemSelected(item);
//    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        PhotoList photoList = new PhotoList();
        PageListener pageListener = new PageListener();

        super.onCreateView(inflater, container, savedInstanceState);
        View rootView = inflater.inflate(R.layout.fragment_details, container, false);
        textView = rootView.findViewById(R.id.textView_fragment_details);
        imageView = rootView.findViewById(R.id.icon_fragment_details);

        textView.setText(photoList.getCurrenPosition() + "");
        Bitmap b = photoList.getPhotoList().get(pageListener.getCurrentPage2()).getBitmap();
        textView.setText(b.getWidth() + "*" + b.getHeight());
        imageView.setImageBitmap(b);

        textView = rootView.findViewById(R.id.textView_fragment_details_weight);
        File f = photoList.getPhotoList().get(pageListener.getCurrentPage2()).getFile();
        long size = f.length();
        textView.setText(size/1024 + "kB");

        textView = rootView.findViewById(R.id.textView_fragment_details_Name);
        String name = f.getName();
        textView.setText(name);

        return rootView;
    }

}
