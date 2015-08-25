package com.codepath.rottentomatoesdemo;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;


public class RottenTomatoesClient {
    private final String API_KEY = "9htuhtcb4ymusd73d4z6jxcj";
    private final String API_BASE_URL = "http://api.rottentomatoes.com/api/public/v1.0/";
    private AsyncHttpClient client;
    private String myQuery;

    public RottenTomatoesClient() {
        this.client = new AsyncHttpClient();
        myQuery="";
    }

    private String getApiUrl(String relativeUrl) {
        return API_BASE_URL + relativeUrl;
    }

    public void getBoxOfficeMovies(JsonHttpResponseHandler handler) {
        String url = getApiUrl("lists/movies/box_office.json?apikey="+ API_KEY +"&limit=50");
        RequestParams params = new RequestParams("apikey", API_KEY);
        client.get(url, params, handler);
    }

    public void getMoviesFromSearch(JsonHttpResponseHandler handler) {
        String url = getApiUrl("movies.json?apikey="+ API_KEY +"&q="+getCustomQuery()+"&page_limit=10");
        RequestParams params = new RequestParams("apikey", API_KEY);
        client.get(url, params, handler);
    }

    public void setCustomQuery(String qUri) {
        myQuery = qUri;
    }

    private String getCustomQuery() { return myQuery; }

   /* public void getInTheatersMovies(JsonHttpResponseHandler handler) {
        String url = getApiUrl("lists/movies/in_theaters.json");
        RequestParams params = new RequestParams("apikey", API_KEY);
        client.get(url, params, handler);
    }*/

     public void getUpcomingMovies(JsonHttpResponseHandler handler) {
        String url = getApiUrl("lists/movies/upcoming.json?apikey="+ API_KEY);
        RequestParams params = new RequestParams("apikey", API_KEY);
        client.get(url, params, handler);
    }

   /*public void getMovies(JsonHttpResponseHandler handler) {
        String url = getApiUrl("movies.json");
        RequestParams params = new RequestParams("apikey", API_KEY);
        client.get(url, params, handler);
    } */


}
