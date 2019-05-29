package com.example.v2swimgallery_list4;

import android.graphics.Bitmap;

import java.io.File;

public class Photo {
    private Bitmap bitmap;
    public int name;
    private File file;

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public Photo(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    public Bitmap getBitmap(){
        return bitmap;
    }
    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

}
