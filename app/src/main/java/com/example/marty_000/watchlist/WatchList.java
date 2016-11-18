package com.example.marty_000.watchlist;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class WatchList extends AppCompatActivity {
    ArrayList<MovieInformation> moviesList = new ArrayList<>();
    SharedPreferences prefs;
    TextView initWatchList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_watch_list);

        // Retrieve the watchlist
        prefs = getApplicationContext().getSharedPreferences("MyPref", 0);
        String WatchListString = prefs.getString("WatchListPref", null);
        JSONArray watchList = new JSONArray();

        initWatchList = (TextView) findViewById(R.id.initWatchList);
        initWatchList.setVisibility(View.VISIBLE);

        try {
            if (WatchListString != null) {
                watchList = new JSONArray(WatchListString);
                initWatchList.setVisibility(View.INVISIBLE);
            }
            for (int i = 0; i < watchList.length(); i++) {
                JSONObject movie = watchList.getJSONObject(i);
                moviesList.add(new MovieInformation(movie.getString("Title"), movie.getString("Year")
                        , movie.getString("imdbID"), movie.getString("Poster")));
            }
        } catch ( JSONException ex) {
            ex.printStackTrace();
        }

        final ListView WatchListView = (ListView) findViewById(R.id.WatchListView);

        // standard adapter
        ArrayAdapter<MovieInformation> moviesAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, moviesList);
        WatchListView.setAdapter(moviesAdapter);

        // on click
        WatchListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                MovieInformation movie = (MovieInformation)  WatchListView.getItemAtPosition(i);
                Intent ShowInfo = new Intent(WatchList.this, ShowInfoActivity.class);
                ShowInfo.putExtra("movieID", movie.getImdb());
                ShowInfo.putExtra("poster", movie.getPoster());
                ShowInfo.putExtra("", movie.getPoster());
                startActivity(ShowInfo);
            }
        });
    }

    // Go back to searchPage
    public void BackToSearchList(View view) {
        startActivity(new Intent (this, MainActivity.class));
        finish();
    }

    // Empty the Watchlist totally
    public void ClearPreferences(View view) {
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("WatchListPref", new JSONArray().toString());
        editor.commit();
        startActivity(new Intent (this, WatchList.class));
        finish();
    }
}


