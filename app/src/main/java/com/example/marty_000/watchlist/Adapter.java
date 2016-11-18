package com.example.marty_000.watchlist;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Adapter stucture from: https://guides.codepath.com/android/Using-an-ArrayAdapter-with-ListView
 */

public class Adapter extends ArrayAdapter<MovieInformation>{
    private ArrayList<MovieInformation> moviesList;
    private View.OnClickListener enlargeInfo;
    Context context;


        public Adapter(final Context context, final ArrayList<MovieInformation> moviesList) {
            super(context, 0, moviesList);
            this.context = context;
            this.moviesList = moviesList;
            enlargeInfo = new View.OnClickListener() {

            // Give more information to the user
            @Override
            public void onClick(View view) {

                int position = ((ViewGroup) view.getParent()).indexOfChild(view);
                MovieInformation movie = moviesList.get(position);

                    Intent enlargeInfoMovie = new Intent(context, MovieInformation.class);
                    enlargeInfoMovie.putExtra("movie", movie.toString());
                    context.startActivity(enlargeInfoMovie);

            }
        };
    }
}