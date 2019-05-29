package com.example.v2swimgallery_list4;

import android.graphics.Bitmap;

import java.util.List;

public class PhotoList {

    private static List<Photo> photoList;
    private static  int currenPosition;
    private static Bitmap bitmap;

    public Bitmap getBitmap() {
        return bitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }


    public static int getCurrenPosition() {
        return currenPosition;
    }

    public static void setCurrenPosition(int currenPosition) {
        PhotoList.currenPosition = currenPosition;
    }

    public void setPhotoList(List<Photo> list) {
        photoList = list;
    }

    public List<Photo> getPhotoList() {
        return photoList;
    }
}
