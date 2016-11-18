package com.example.marty_000.watchlist;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;

import java.io.IOException;
import java.net.URL;

/**
 * Retriev an image from an URL
 * Source: http://stackoverflow.com/questions/2471935/how-to-load-an-imageview-by-url-in-android
 */

public class ImageAsyncTask extends AsyncTask<URL,Integer,Bitmap> {

    @Override
    protected Bitmap doInBackground(URL... params) {
        URL url = params[0];
        Bitmap imageBitmap = null;
        try {
            imageBitmap = BitmapFactory.decodeStream(url.openConnection().getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }

        return imageBitmap;
    }
}