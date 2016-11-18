package com.example.marty_000.watchlist;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
/* Watch List Mprog week 3
 * Martijn Heijstek, 10800441
 * 18-11-2016
 *
 * Class that retrieves a bitmap from a url
 * Source: http://stackoverflow.com/questions/2471935/how-to-load-an-imageview-by-url-in-android
 */
import java.io.IOException;
import java.net.URL;

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