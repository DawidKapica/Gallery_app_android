package com.example.v2swimgallery_list4;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.List;

import static android.support.v7.widget.AppCompatDrawableManager.get;

public class PhotoAdapter extends RecyclerView.Adapter<PhotoAdapter.MyViewHolder> {
    private static List<Photo> photoList;

    FragmentTransaction ft;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public ImageView imageView;

        public MyViewHolder(View view) {
            super(view);
            imageView = (ImageView) view.findViewById(R.id.icon);
        }
    }


    public PhotoAdapter(List<Photo> photoList) {
        this.photoList = photoList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.photo_look_to_activity_main, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {


        final Photo photo = photoList.get(position);
        holder.imageView.setImageBitmap(photo.getBitmap());
        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PhotoList photoList_klasa = new PhotoList();
                photoList_klasa.setPhotoList(photoList);
                photoList_klasa.setCurrenPosition(photo.name);
                PageListener pageListener = new PageListener();
                pageListener.setCurrentPage2(position);
//                AppCompatActivity activity = (AppCompatActivity) v.getContext();
//                View rootView = inflater.inflate(R.layout.fragment_foo, parent, false);
//               Context context = v.getContext();
//
//                ViewPager viewPager = activity.findViewById(R.id.view_pager);
//                if(viewPager != null)
//                viewPager.setAdapter(new ViewPagerAdapter(activity.getSupportFragmentManager(), photoList));
                photoList_klasa.setBitmap(photoList.get(2).getBitmap());


                AppCompatActivity activity = (AppCompatActivity) v.getContext();
                Fragment myFragment = new Full_photo_container();
                ((Full_photo_container) myFragment).setLista(photoList);

                activity.getSupportFragmentManager().beginTransaction().replace(R.id.your_placeholder, myFragment).addToBackStack("tag2").commit();
//
//                AppCompatActivity activity = (AppCompatActivity) v.getContext();
//                Fragment myFragment = new PhotoFragment();
//                activity.getSupportFragmentManager().beginTransaction().replace(R.id.your_placeholder, myFragment).addToBackStack(null).commit();



                // Replace the contents of the container with the new fragment
//                Main3Activity main3Activity = new Main3Activity(photo.getBitmap(), photo.getFile(), photoList);
//                Intent intention=new Intent(v.getContext(), Main3Activity.class);
//                Toast.makeText(v.getContext(), "numer: " + photo.name, Toast.LENGTH_SHORT).show();
//                v.getContext().startActivity(intention);

////                View rootView = layoutInflater.inflate(R.layout.activity_main3, viewGroup, false);
////
////                setContentView(R.layout.details_and_exif);
////
////                ViewPager viewPager = findViewById(R.id.view_pager);
////                viewPager.setAdapter(new ViewPagerAdapter(getSupportFragmentManager()));
//
//                AppCompatActivity activity = (AppCompatActivity) v.getContext();
//                Fragment myFragment = new Fragment_full_photo();
////                activity.getSupportFragmentManager().beginTransaction().replace(R.id.pager, myFragment).addToBackStack(null).commit();

            }
        });

    }

    @Override
    public int getItemCount() {
        return photoList.size();
    }

    public void setPhotoList(List<Photo> list) {
        photoList = list;
    }

    public List<Photo> getPhotoList() {
        return photoList;
    }

    public void setFt(FragmentTransaction ft) {
        this.ft = ft;
    }
}