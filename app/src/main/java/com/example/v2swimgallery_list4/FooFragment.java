package com.example.v2swimgallery_list4;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.List;
import java.util.prefs.Preferences;

public class FooFragment extends Fragment {
    private List<Photo> photoList = new ArrayList<>();
    private RecyclerView recyclerView;
    private PhotoAdapter mAdapter;
//    static String PATH = null;
    static int List_orGrid = 0;

    SharedPreferences preferences;
    SharedPreferences.Editor editor;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        preferences = this.getActivity().getSharedPreferences("pref", Context.MODE_PRIVATE);
        editor = preferences.edit();

        super.onCreate(savedInstanceState);
        mAdapter = new PhotoAdapter(photoList);
        setHasOptionsMenu(true);
//        prepareMovieData();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.main_menu, menu );
//        MenuItem menuItem = menu.findItem(R.id.detailsItem);
//        menuItem.setTitle("Back");
        super.onCreateOptionsMenu(menu, inflater);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        // Defines the xml file for the fragment
        View rootView = inflater.inflate(R.layout.fragment_foo, parent, false);
        recyclerView = (RecyclerView) rootView.findViewById(R.id.recycler_view);
        mAdapter = new PhotoAdapter(photoList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);

        String PATH = preferences.getString("Sciezka", "");

        if (PATH == "" || PATH == null) {
            Intent i = new Intent(Intent.ACTION_OPEN_DOCUMENT_TREE);
            i.addCategory(Intent.CATEGORY_DEFAULT);
            startActivityForResult(Intent.createChooser(i, "Choose directory"), 99);

        }
        else {
            prepareMovieData();
            RecyclerView.LayoutManager layoutManager;
            layoutManager = new GridLayoutManager(recyclerView.getContext(), 3);
            recyclerView.setLayoutManager(layoutManager);
        }

        return rootView;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.gridItem:
                if (List_orGrid == 0) {
                    RecyclerView.LayoutManager layoutManager2;
                    layoutManager2 = new LinearLayoutManager(recyclerView.getContext());
                    recyclerView.setLayoutManager(layoutManager2);
                    item.setTitle("Grid");
                    List_orGrid = 1;
                }
                else if (List_orGrid == 1){
                    RecyclerView.LayoutManager layoutManager;
                    layoutManager = new GridLayoutManager(recyclerView.getContext(), 3);
                    recyclerView.setLayoutManager(layoutManager);
                    item.setTitle("List");
                    List_orGrid = 0;
                }
                break;
//            case R.id.listItem:
//                RecyclerView.LayoutManager layoutManager2;
//                layoutManager2 = new LinearLayoutManager(recyclerView.getContext());
//                recyclerView.setLayoutManager(layoutManager2);
//                break;
            case R.id.folderItem:
                Intent i = new Intent(Intent.ACTION_OPEN_DOCUMENT_TREE);
                i.addCategory(Intent.CATEGORY_DEFAULT);
                startActivityForResult(Intent.createChooser(i, "Choose directory"), 99);
                break;

        }
        return super.onOptionsItemSelected(item);
    }


    private void prepareMovieData() {

        photoList.clear();
//        PATH = PreferenceManager.getDefaultSharedPreferences(this).getString(MY_PREFS_NAME, "");
//        PATH = "/storage/emulated/0/Download/";
//        final File dir = new File(PATH);
        String PATHFinal = null;
        String path = preferences.getString("Sciezka", "");

        PATHFinal = Environment.getExternalStorageDirectory().getAbsolutePath() + "/" + path;
        PATHFinal = "/storage/emulated/0/Download/";


        final File dir = new File(PATHFinal);


        final String[] EXTENSIONS = new String[]{
                "gif", "png", "bmp", "jpg", "jpeg", "JPG"
        };
        final FilenameFilter IMAGE_FILTER = new FilenameFilter() {

            @Override
            public boolean accept(final File dir, final String name) {
                for (final String ext : EXTENSIONS) {
                    if (name.endsWith("." + ext)) {
                        return (true);
                    }
                }
                return (false);
            }
        };
//        if (dir.isDirectory()) { // make sure it's a directory

            int i = 0;
            for (final File f : dir.listFiles(IMAGE_FILTER)) {
                Bitmap myBitmap = BitmapFactory.decodeFile(f.getAbsolutePath());
                Photo photo = new Photo(myBitmap);
                photo.name = i;
                photo.setFile(f);
                i++;
                photoList.add(photo);
            }
//        }

        mAdapter.setPhotoList(photoList);
        mAdapter.notifyDataSetChanged();
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        String PATH = "";
        switch(requestCode) {
            case 99:

                String directoryPath;
                try {
                    directoryPath = data.getData().getPath().split(":")[1];
                    editor.putString("Sciezka", directoryPath + "/");
                    editor.apply();
                    PATH = directoryPath + "/";
                    Log.i("<><><><>1", Environment.getExternalStorageDirectory().getAbsolutePath() + "/" + PATH);
                    prepareMovieData();
                    if (List_orGrid == 0) {
                        RecyclerView.LayoutManager layoutManager2;
                        layoutManager2 = new LinearLayoutManager(recyclerView.getContext());
                        recyclerView.setLayoutManager(layoutManager2);
                    }
                    else if (List_orGrid == 1){
                        RecyclerView.LayoutManager layoutManager;
                        layoutManager = new GridLayoutManager(recyclerView.getContext(), 3);
                        recyclerView.setLayoutManager(layoutManager);
                    }

                } catch (ArrayIndexOutOfBoundsException e) {
                    directoryPath = "";
                    PATH = directoryPath;
                    editor.putString("Sciezka", directoryPath);
                    editor.apply();
                    Log.i("<><><><>2", Environment.getExternalStorageDirectory().getAbsolutePath() + "/" + PATH);
                    prepareMovieData();
                    if (List_orGrid == 0) {
                        RecyclerView.LayoutManager layoutManager2;
                        layoutManager2 = new LinearLayoutManager(recyclerView.getContext());
                        recyclerView.setLayoutManager(layoutManager2);
                        List_orGrid = 1;
                    }
                    else if (List_orGrid == 1){
                        RecyclerView.LayoutManager layoutManager;
                        layoutManager = new GridLayoutManager(recyclerView.getContext(), 3);
                        recyclerView.setLayoutManager(layoutManager);
                        List_orGrid = 0;
                    }
                }
//                spe.putString("directoryPath", directoryPath);
//                spe.commit();
                break;
        }
    }



}