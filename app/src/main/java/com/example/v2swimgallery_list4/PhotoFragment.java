package com.example.v2swimgallery_list4;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

public class PhotoFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View rootView = inflater.inflate(R.layout.sliding_images_layout, container, false);
        PhotoList photoList = new PhotoList();
        ImageView imageView = rootView.findViewById(R.id.image);
        imageView.setImageBitmap(photoList.getBitmap());

        return rootView;
    }


}
