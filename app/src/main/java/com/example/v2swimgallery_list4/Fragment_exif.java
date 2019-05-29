package com.example.v2swimgallery_list4;

import android.content.Intent;
import android.media.ExifInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.io.IOException;

public class Fragment_exif extends Fragment {
    private TextView textView;


    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.photo_menu, menu );
        MenuItem menuItem = menu.findItem(R.id.detailsItem);
        menuItem.setTitle("");
        menuItem.setVisible(false);
        super.onCreateOptionsMenu(menu, inflater);
    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        switch (item.getItemId()) {
//            case R.id.detailsItem:
//                MainActivity main3Activity = new MainActivity();
//                Intent intention=new Intent(getContext(), MainActivity.class);
//                getContext().startActivity(intention);
//                break;
//
//
//        }
//        return super.onOptionsItemSelected(item);
//    }






    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View rootView = inflater.inflate(R.layout.fragment_exif, container, false);
        textView = rootView.findViewById(R.id.textView_fragment_exif);

        PhotoList photoList = new PhotoList();
        PageListener pageListener = new PageListener();

        double TAG_GPS_IMG_DIRECTION = 0;
        String DateType = "";
        String ISO = "";
        String Model = "";
        String Flash = "";
        String Artist = "";
        String Software = "";
        String Focal_lenght = "";
        String GPS_altidue = "";
        String Color_space = "";


        try {
            ExifInterface exifInterface = new ExifInterface(photoList.getPhotoList().get(pageListener.getCurrentPage2()).getFile().getAbsolutePath());
            TAG_GPS_IMG_DIRECTION = exifInterface.getAltitude(0);
            DateType = exifInterface.getAttribute(ExifInterface.TAG_DATETIME);
            ISO = exifInterface.getAttribute(ExifInterface.TAG_ISO);
            Model = exifInterface.getAttribute(ExifInterface.TAG_MODEL);
            Flash = exifInterface.getAttribute(ExifInterface.TAG_FLASH);
            Artist = exifInterface.getAttribute(ExifInterface.TAG_ARTIST);
            Software = exifInterface.getAttribute(ExifInterface.TAG_SOFTWARE);
            Focal_lenght = exifInterface.getAttribute(ExifInterface.TAG_FOCAL_LENGTH);
            GPS_altidue = exifInterface.getAttribute(ExifInterface.TAG_GPS_ALTITUDE);
            Color_space = exifInterface.getAttribute(ExifInterface.TAG_COLOR_SPACE);
        } catch (IOException e) {
            e.printStackTrace();
        }

        textView = rootView.findViewById(R.id.textView_exif_artist);
        textView.setText(Artist);

        textView = rootView.findViewById(R.id.textView_exif_color_space);
        textView.setText(Color_space);

        textView = rootView.findViewById(R.id.textView_exif_DataType);
        textView.setText(DateType);

        textView = rootView.findViewById(R.id.textView_exif_flash);
        textView.setText(Flash);

        textView = rootView.findViewById(R.id.textView_exif_gps_altitude);
        textView.setText(GPS_altidue);

        textView = rootView.findViewById(R.id.textView_exif_gps_image_direction);
        textView.setText(TAG_GPS_IMG_DIRECTION + "");

        textView = rootView.findViewById(R.id.textView_exif_ISO);
        textView.setText(ISO);

        textView = rootView.findViewById(R.id.textView_exif_model);
        textView.setText(Model);

        textView = rootView.findViewById(R.id.textView_exif_software);
        textView.setText(Software);

        textView = rootView.findViewById(R.id.textView_exif_focal_length);
        textView.setText(Focal_lenght);

        return rootView;
    }

}
