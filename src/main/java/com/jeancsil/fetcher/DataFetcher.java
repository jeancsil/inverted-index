package com.jeancsil.fetcher;

//import jdk.incubator.http.HttpResponse;
//import com.mashape.unirest.http.*;


import com.jeancsil.dto.Movie;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

public class DataFetcher implements Fetcher {

    private final String baseUrl;
    private final String apiKey;
    private final String defaultFilters;

    public DataFetcher(final String apiKey) {
        this.apiKey = apiKey;
        this.baseUrl = "https://api.themoviedb.org/3/discover/movie";
        this.defaultFilters = "include_video=false&include_adult=false&sort_by=popularity.desc&language=en-US&region=US";
    }

    @Override
    public List<Movie> fetch(final int page) {
//            Unirest.setTimeouts(100, 1500);

        final List<Movie> movies = new ArrayList<>();
        try {
            Unirest.setDefaultHeader("accept", "application/json");

            final HttpResponse<JsonNode> jsonNode = Unirest
                .get(getFullUrl(page))
                .asJson();

            final JSONArray results = jsonNode.getBody().getObject().getJSONArray("results");

            for (int i = 0; i < results.length(); i++) {
                final JSONObject jsonMovie = results.getJSONObject(i);

                final Movie movie = new Movie(
                    jsonMovie.getInt("id"),
                    jsonMovie.getString("overview"),
                    jsonMovie.getString("original_language"),
                    jsonMovie.getString("original_title"),
                    jsonMovie.getBoolean("video"),
                    jsonMovie.getString("title"),
                    getStringValue(jsonMovie, "poster_path"),
                    getStringValue(jsonMovie, "backdrop_path"),
                    jsonMovie.getString("release_date"),
                    jsonMovie.getDouble("vote_average"),
                    jsonMovie.getDouble("popularity"),
                    jsonMovie.getBoolean("adult"),
                    jsonMovie.getLong("vote_count")
                );

                movies.add(movie);
            }
        } catch (UnirestException e) {
            System.out.println("Error: " + e.getLocalizedMessage());
        }

        return movies;
    }

    private String getStringValue(final JSONObject jsonMovie, final String key) {
        if (!jsonMovie.isNull(key)) {
            return jsonMovie.getString(key);
        }

        return "";
    }

    private String getFullUrl(final int page) {
        return String.format("%s?%s&page=%s&api_key=%s", baseUrl, defaultFilters, page, apiKey);
    }

    private void asdf() {
        //https://api.themoviedb.org/3/movie/550?api_key=8d09c246d94b9ea1b5630a49de05effa
        //https://api.themoviedb.org/3/authentication/token/new?api_key=8d09c246d94b9ea1b5630a49de05effa
    }
}
