package com.jeancsil.dto;

public final class Movie {

    private final long id;
    private final String overview;
    private final String originalLanguage;
    private final String originalTitle;
    private final boolean isVideo;
    private final String title;
    private final String posterPath;
    private final String backdropPath;
    private final String releaseDate;
    private final double voteAverage;
    private final double popularity;
    private final boolean adult;
    private final long voteCount;

    public Movie(long id, String overview, String originalLanguage, String originalTitle, boolean isVideo,
        String title, String posterPath, String backdropPath, String releaseDate, double voteAverage,
        double popularity, boolean adult, long voteCount) {
        this.id = id;
        this.overview = overview;
        this.originalLanguage = originalLanguage;
        this.originalTitle = originalTitle;
        this.isVideo = isVideo;
        this.title = title;
        this.posterPath = posterPath;
        this.backdropPath = backdropPath;
        this.releaseDate = releaseDate;
        this.voteAverage = voteAverage;
        this.popularity = popularity;
        this.adult = adult;
        this.voteCount = voteCount;
    }

    public long getId() {
        return id;
    }

    public String getOverview() {
        return overview;
    }

    public String getOriginalLanguage() {
        return originalLanguage;
    }

    public String getOriginalTitle() {
        return originalTitle;
    }

    public boolean isVideo() {
        return isVideo;
    }

    public String getTitle() {
        return title;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public String getBackdropPath() {
        return backdropPath;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public double getVoteAverage() {
        return voteAverage;
    }

    public double getPopularity() {
        return popularity;
    }

    public boolean isAdult() {
        return adult;
    }

    public long getVoteCount() {
        return voteCount;
    }

    @Override
    public String toString() {
        return "Movie{" +
            "id=" + id +
            ", overview='" + overview + '\'' +
            ", originalLanguage='" + originalLanguage + '\'' +
            ", originalTitle='" + originalTitle + '\'' +
            ", isVideo=" + isVideo +
            ", title='" + title + '\'' +
            ", posterPath='" + posterPath + '\'' +
            ", backdropPath='" + backdropPath + '\'' +
            ", releaseDate='" + releaseDate + '\'' +
            ", voteAverage=" + voteAverage +
            ", popularity=" + popularity +
            ", adult=" + adult +
            ", voteCount='" + voteCount + '\'' +
            '}';
    }
}
