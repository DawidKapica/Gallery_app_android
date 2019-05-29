package com.example.v2swimgallery_list4;

import android.app.Activity;
import android.app.WallpaperManager;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.StrictMode;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.List;

public class Full_photo_container extends Fragment {

    ViewPagerAdapter mPager;
    List<Photo> lista;
    View view;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        // TODO Add your menu entries here
        inflater.inflate(R.menu.photo_menu, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.wallpaperItem:
                WallpaperManager myWallpaperManager = WallpaperManager.getInstance(getContext());
                try {
                    PageListener pageListener = new PageListener();
                    myWallpaperManager.setBitmap(lista.get(pageListener.getCurrentPage2()).getBitmap());

                    Toast.makeText(getContext(),
                            "Wallpaper successfully changed", Toast.LENGTH_SHORT)
                            .show();

                } catch (IOException e) { }
                break;
            case R.id.shareItem1:
                PageListener pageListener = new PageListener();
                shareImage(lista.get(pageListener.getCurrentPage2()).getFile());
                break;
            case R.id.detailsItem:
                Fragment newFragment = new Deatils_container();

                FragmentManager fragmentManager = getFragmentManager();

                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.your_placeholder, newFragment);
                fragmentTransaction.addToBackStack("tag3");
                fragmentTransaction.commit();
                break;


        }
        return super.onOptionsItemSelected(item);
    }

    public void shareImage(File image) {
        PageListener pageListener = new PageListener();
        Bitmap bitmap = lista.get(pageListener.getCurrentPage2()).getBitmap();
        Intent share = new Intent(Intent.ACTION_SEND);
        share.setType("image/jpeg");
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
        File f = new File(Environment.getExternalStorageDirectory() + File.separator + "temporary_file.jpg");
        try {
            f.createNewFile();
            FileOutputStream fo = new FileOutputStream(f);
            fo.write(bytes.toByteArray());
        } catch (IOException e) {
            e.printStackTrace();
        }
        if(Build.VERSION.SDK_INT>=24){
            try{
                Method m = StrictMode.class.getMethod("disableDeathOnFileUriExposure");
                m.invoke(null);
            }catch(Exception e){
                e.printStackTrace();
            }
        }
        share.putExtra(Intent.EXTRA_STREAM, Uri.parse("file:///sdcard/temporary_file.jpg"));
        startActivity(Intent.createChooser(share, "Share Image"));

    }




    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        PageListener pageListenerw = new PageListener();

        View rootView = inflater.inflate(R.layout.full_photo_pager, container, false);






        mPager = new ViewPagerAdapter(getChildFragmentManager(), lista, getContext());
        ViewPager viewPager = rootView.findViewById(R.id.view_pager);
        viewPager.setAdapter(mPager);
        viewPager.setCurrentItem(pageListenerw.getCurrentPage2());

        PageListener pageListener = new PageListener();
        viewPager.setOnPageChangeListener(pageListener);


        view = rootView;

        return rootView;
    }

    public void setLista(List<Photo> lista) {
        this.lista = lista;
    }
}
