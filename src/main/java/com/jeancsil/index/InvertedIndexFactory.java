package com.jeancsil.index;

import com.jeancsil.dto.Movie;
import java.util.List;

public class InvertedIndexFactory {
    private final List<Movie> movies;
    private final InvertedIndex invertedIndex;

    public InvertedIndexFactory(final List<Movie> movies) {
        this.movies = movies;
        this.invertedIndex = new InvertedIndex();
    }

    public void create() {
        for (final Movie movie : movies) {
            invertedIndex.addToken(new Token(movie.getOriginalTitle()), movie.getId());
//            System.out.println(movie.getId());
//            System.out.println(movie.getOriginalTitle());
//            System.out.println(movie.getTitle());
//            System.out.println();
        }
    }

    public InvertedIndex getInvertedIndex() {
        return invertedIndex;
    }
}
