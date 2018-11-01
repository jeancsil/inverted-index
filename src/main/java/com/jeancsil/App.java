package com.jeancsil;

import com.jeancsil.dto.Movie;
import com.jeancsil.error.InvalidArgumentException;
import com.jeancsil.fetcher.DataFetcher;
import com.jeancsil.fetcher.Fetcher;
import com.jeancsil.index.InvertedIndexFactory;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("Unchecked")
public class App {

    private static String apiKey;
    private static final List<Movie> movies = new ArrayList<>();
    private static final int requestsPerSecond = 4;
    private static final int throttleInMillis = 1000 / requestsPerSecond;

    public static void main(String[] args) {
        try {
            if (args.length == 0) {
                System.out.println("[ERROR]: Api Key is mandatory.");
                System.exit(1);
            }

            apiKey = args[0];
            final int startPage = (args.length >= 2) ? Integer.valueOf(args[1]) : 1;
            final int endPage = (args.length >= 3) ? Integer.valueOf(args[2]) : startPage;

            System.out.println("Starting the application...");
            System.out.println(String.format("Reading TMDB from page %s to %s.", startPage, endPage));

            App application = new App();
            application.fetchMovies(startPage, endPage);

            final InvertedIndexFactory invertedIndexFactory = new InvertedIndexFactory(movies);
            invertedIndexFactory.create();
            System.out.println("Inverted index:");
            System.out.println(invertedIndexFactory.getInvertedIndex());
        } catch (Exception e) {
//            System.out.println("[ERROR]: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void fetchMovies(final int startPage, final int endPage) throws InterruptedException {
        if (startPage <= 0) {
            throw new InvalidArgumentException("Start page must be greater than 0");
        }

        if (endPage < startPage) {
            throw new InvalidArgumentException("End page must be greater or equal than start page");
        }

        Fetcher dataFetcher = new DataFetcher(apiKey);

        for (int currentPage = startPage; currentPage <= endPage; currentPage++) {
            System.out.println(String.format("========= Page #%s ========= ", currentPage));
            movies.addAll(dataFetcher.fetch(currentPage));
            Thread.sleep(throttleInMillis);
        }
    }
}
