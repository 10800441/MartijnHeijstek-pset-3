package com.example.marty_000.watchlist;

import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    //    Searches the database upon query and updates the recycler view with the output.
    public void searchQuery(View view) throws IOException {
        ArrayList<MovieInformation> moviesList = new ArrayList<>();
        ListView listView = (ListView) findViewById(R.id.listView);
        EditText editText = (EditText) findViewById(R.id.editText);

        // get the url
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
                    moviesList.add(new MovieInformation(movie.getString("Title"), movie.getString("Type")
                            ,movie.getString("imdbID"), movie.getString("Year"),  movie.getString("Poster")));
                }
            }

        } catch (InterruptedException ex) {
            ex.printStackTrace();
        } catch (ExecutionException ex) {
            ex.printStackTrace();
        } catch (JSONException ex) {
            ex.printStackTrace();
        }

    // standard adapter
    ArrayAdapter<MovieInformation> moviesAdapter = new ArrayAdapter<>(this,
            android.R.layout.simple_list_item_1, moviesList);
    listView.setAdapter(moviesAdapter);

      /*  // TODO own adapter
        Adapter adapter = new Adapter(moviesList);
        listView.setAdapter(adapter);
  */}
}