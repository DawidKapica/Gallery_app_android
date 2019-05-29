package com.example.v2swimgallery_list4;

import android.content.Context;
import android.graphics.Matrix;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.List;


    public class ViewPagerAdapter extends PagerAdapter {

        List<Photo> photoList;
        private Fragment[] childFragments;

        private LayoutInflater inflater;
        private Context context;

//        ImageView imageView;
//        Matrix matrix = new Matrix();
//        Float scale = 1f;
//        ScaleGestureDetector SGD;
//
//        private class ScaleListener extends ScaleGestureDetector.SimpleOnScaleGestureListener {
//            @Override
//            public boolean onScale(ScaleGestureDetector detector) {
//                scale = scale * detector.getScaleFactor();
//                scale = Math.max(0.1f, Math.min(scale, 5f));
//                matrix.setScale(scale, scale);
//                imageView.setImageMatrix(matrix);
//                return  true;
//            }
//        }
//
//        @Override
//        public boolean onTouchEvent(MotionEvent event) {
//            SGD.onTouchEvent(event);
//            return true;
//        }

        public ViewPagerAdapter(FragmentManager fm, List<Photo> photoList, Context context) {
            PhotoList photoList1 = new PhotoList();
            this.photoList = photoList;
            childFragments = new Fragment[photoList.size()];
            for (int i = 0; i < photoList.size(); i++) {
                photoList1.setBitmap(photoList.get(i).getBitmap());
                childFragments[i] = new PhotoFragment();
            }
            this.context = context;

            inflater = LayoutInflater.from(context);
        }


        ImageView imgZoom;
        Matrix matrix = new Matrix();
        float scale = 1f;
        ScaleGestureDetector SGD;


        @Override
        @NonNull public Object instantiateItem(ViewGroup view, int position) {
            View imageLayout = inflater.inflate(R.layout.sliding_images_layout, view, false);


            final ImageView imageView = (ImageView) imageLayout.findViewById(R.id.image);

            imgZoom = (ImageView) imageLayout.findViewById(R.id.image);
            SGD = new ScaleGestureDetector(imageLayout.getContext(),new ScaleListener());

            PageListener pageListener = new PageListener();
            PhotoList photoList1 = new PhotoList();
            imageView.setImageBitmap(photoList.get(position).getBitmap());
            imageLayout.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View view, MotionEvent motionEvent) {
                    SGD.onTouchEvent(motionEvent);
                    return true;
                }
            });

            view.addView(imageLayout, 0);



            return imageLayout;
        }

        private class ScaleListener extends ScaleGestureDetector.
                SimpleOnScaleGestureListener {
            @Override
            public boolean onScale(ScaleGestureDetector detector) {
                scale *= detector.getScaleFactor();
                scale = Math.max(0.1f, Math.min(scale, 5.0f));
                matrix.setScale(scale, scale);
                imgZoom.setImageMatrix(matrix);
                return true;
            }
        }

        @Override
        public void destroyItem(@Nullable ViewGroup container, int position,@NonNull Object object) {
            container.removeView((View) object);
        }

        @Override
        public int getCount() {
            return photoList.size(); //three fragments
        }


        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view.equals(object);
        }

        @Override
        public void restoreState(Parcelable state, ClassLoader loader) {
        }

        @Override
        public Parcelable saveState() {
            return null;
        }


    }

