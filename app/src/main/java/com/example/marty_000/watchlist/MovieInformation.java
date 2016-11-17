package com.example.marty_000.watchlist;

/* 18-11-2016
 * This class stores the information of a single movie
 */
class MovieInformation  {
        private String title;
        private String year;
        private String type;
        private String imbdID;
        private String poster;


        MovieInformation(String title, String year, String type, String imbdID, String poster){
            this.title = title;
            this.year = year;
            this.type = type;
            this.imbdID = imbdID;
            this.poster = poster;
        }

    public String toString() {
        return title + "; ("+ year + ")";
    }
}
