package com.example.v2swimgallery_list4;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.os.Environment;
import android.support.v4.app.FragmentTabHost;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.widget.ImageView;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<Photo> photoList = new ArrayList<>();
    private RecyclerView recyclerView;
    private PhotoAdapter mAdapter;
    public static final String MY_PREFS_NAME = "MyPrefsFile";
    String PATH = null;
    int PICK_REQUEST_CODE = 99;

    SharedPreferences pref;


//    ImageView imageView;
//    Matrix matrix = new Matrix();
//    Float scale = 1f;
//    ScaleGestureDetector SGD;

//    public class ScaleListener extends ScaleGestureDetector.SimpleOnScaleGestureListener {
//        @Override
//        public boolean onScale(ScaleGestureDetector detector) {
//            scale = scale * detector.getScaleFactor();
//            scale = Math.max(0.1f, Math.min(scale, 5f));
//            matrix.setScale(scale, scale);
//            imageView.setImageMatrix(matrix);
//            return  true;
//        }
//    }
//
//    @Override
//    public boolean onTouchEvent(MotionEvent event) {
//        SGD.onTouchEvent(event);
//        return true;
//    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.your_placeholder, new FooFragment());
        ft.addToBackStack("tag");
        ft.commit();
    }
}
