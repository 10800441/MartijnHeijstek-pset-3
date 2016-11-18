package com.example.marty_000.watchlist;
/* Watch List Mprog week 3
 * Martijn Heijstek, 10800441
 * 18-11-2016
 *
 * Activity where the WatchList is displayed to the user
 */
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
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
        prefs = getApplicationContext().getSharedPreferences("MyPref", MODE_PRIVATE);
        String WatchListString = prefs.getString("WatchListPref", null);
        JSONArray watchList = new JSONArray();

        // Display a message if the user has no movies added to the WatchList
        if(moviesList.isEmpty() && !(prefs.contains("emptyWatchList"))){

            // Message with ok button
            //Source: http://stackoverflow.com/questions/6264694/how-to-add-message-box-with-ok-button

            AlertDialog.Builder dlgAlert  = new AlertDialog.Builder(this);
            dlgAlert.setMessage("You have no saved movies yet!\n" +
                    "Search for movies and add them to your personal watchList");
            dlgAlert.setTitle("WatchList");
            dlgAlert.setPositiveButton("Ok",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            //dismiss the dialog
                        }
                    });
            dlgAlert.setCancelable(true);
            dlgAlert.create().show();
            SharedPreferences.Editor editor = prefs.edit();
            editor.putString("emptyWatchList", "emptyWatchList");
            editor.apply();
        }
        try {
            if (WatchListString != null) {
                watchList = new JSONArray(WatchListString);
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

    // Empty the WatchList totally
    public void ClearPreferences(View view) {
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("WatchListPref", new JSONArray().toString());
        editor.apply();
        startActivity(new Intent (this, WatchList.class));
        finish();
    }
}


