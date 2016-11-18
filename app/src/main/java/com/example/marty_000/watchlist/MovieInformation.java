package com.example.marty_000.watchlist;
/* Watch List Mprog week 3
 * Martijn Heijstek, 10800441
 * 18-11-2016
 *
 * Class that stores useful information from a movie
 */
import java.io.Serializable;

class MovieInformation implements Serializable{
        private String title;
        private String year;
        private String imbdID;
        private String poster;


        MovieInformation(String title, String year,  String imbdID, String poster){
            this.title = title;
            this.year = year;
            this.imbdID = imbdID;
            this.poster = poster;
        }
    public String getImdb(){
        return imbdID;
    }
    public String getPoster(){
        return poster;
    }
    public String toString() {
        return title + " ("+ year + ")";
    }
}
