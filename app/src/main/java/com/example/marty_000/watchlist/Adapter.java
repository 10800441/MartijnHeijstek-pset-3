package com.example.marty_000.watchlist;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Adapter stucture from: https://guides.codepath.com/android/Using-an-ArrayAdapter-with-ListView
 */

public class Adapter {
    private ArrayList<MovieInformation> moviesList;
    private View.OnClickListener enlargeInfo;
}/*
    public Adapter(ArrayList<MovieInformation> moviesList) {
        this.moviesList = moviesList;
        enlargeInfo = new View.OnClickListener() {

            @Override
            public void onClick(View view) {

            }
        }
    }
    /*

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        User user = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_user, parent, false);
        }
        // Lookup view for data population
        TextView tvName = (TextView) convertView.findViewById(R.id.tvName);
        TextView tvHome = (TextView) convertView.findViewById(R.id.tvHome);
        // Populate the data into the template view using the data object
        tvName.setText(user.name);
        tvHome.setText(user.hometown);
        // Return the completed view to render on screen
        return convertView;
    }
}*/