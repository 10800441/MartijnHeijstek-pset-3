package com.example.marty_000.watchlist;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {
    SharedPreferences prefs;
    ArrayList<MovieInformation> moviesList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Make a new watch list
        prefs = getApplicationContext().getSharedPreferences("MyPref", 0);
        if (!prefs.contains("WatchListPref")){
            SharedPreferences.Editor editor = prefs.edit();
            editor.putString("WatchListPref", new JSONArray().toString());
            editor.commit();
        }
    }



    //    Searches the database upon query and updates the recycler view with the output.
    public void searchQuery(View view) throws IOException {

        final ListView listView = (ListView) findViewById(R.id.listView);
        EditText editText = (EditText) findViewById(R.id.editText);

        String query = URLEncoder.encode(editText.getText().toString().trim(), "UTF-8");
        URL url = new URL(String.format("http://www.omdbapi.com/?s=%s", query));
        editText.getText().clear();

        // Retrieve the information from the API
        try {
            // Get dict from the AsyncTask
            JSONObject jsonDict = new JSONObject(new OmdbAsyncTask().execute(url).get());

            if (jsonDict.getString("Response").equals("False")) {
                Toast.makeText(this, jsonDict.getString("Error"), Toast.LENGTH_SHORT).show();
            } else {
                JSONArray returnedMovies = new JSONArray(jsonDict.getString("Search"));
                for (int i = 0; i < returnedMovies.length(); i++) {
                    JSONObject movie = returnedMovies.getJSONObject(i);
                    moviesList.add(new MovieInformation(movie.getString("Title"), movie.getString("Year")
                            , movie.getString("imdbID"), movie.getString("Poster")));
                }
            }

        } catch (InterruptedException | ExecutionException | JSONException e) {
            e.printStackTrace();
        }
    // standard adapter
    ArrayAdapter<MovieInformation> moviesAdapter = new ArrayAdapter<>(this,
            android.R.layout.simple_list_item_1, moviesList);
    listView.setAdapter(moviesAdapter);

        // on click
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                MovieInformation movie = (MovieInformation) listView.getItemAtPosition(i);
                Intent ShowInfo = new Intent(MainActivity.this, ShowInfoActivity.class);
                ShowInfo.putExtra("movieID", movie.getImdb());
                ShowInfo.putExtra("poster", movie.getPoster());
                ShowInfo.putExtra("", movie.getPoster());
                startActivity(ShowInfo);
            }


        });

    }
    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
        outState.putSerializable("movies", moviesList);
    }
}