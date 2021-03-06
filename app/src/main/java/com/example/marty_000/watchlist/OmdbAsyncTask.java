package com.example.marty_000.watchlist;
/* Watch List Mprog week 3
 * Martijn Heijstek, 10800441
 * 18-11-2016
 *
 * Class that retrieves strings from a website
 */
import android.os.AsyncTask;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

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