package com.example.marty_000.watchlist;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

// Standard AsyncTask class
public class OmdbAsyncTask extends AsyncTask<URL,Integer,String>{

    // Retrieve the information from the server on a seperate thread
        @Override
        protected String doInBackground(URL... params) {
            URL url = params[0];
            BufferedReader rd = null;

            try {
                rd = new BufferedReader(new InputStreamReader(url.openStream()));
            } catch (IOException ex) {
                ex.printStackTrace();
            }

            String result= "";
            String jsonLine;

            try {
                while ((jsonLine = rd.readLine()) != null) {
                    result += jsonLine;
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            } finally {
                try {
                    rd.close();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
            // Returns the json dict as a string
            return result;
        }
    }