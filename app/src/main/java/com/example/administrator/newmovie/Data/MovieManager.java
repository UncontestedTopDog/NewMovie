package com.example.administrator.newmovie.Data;

import android.support.annotation.NonNull;

import rx.Observable;

/**
 * Created by Administrator on 2017/9/14.
 */

public class MovieManager {

    public static MovieManager mMovieManager = new MovieManager();

    public static MovieManager INSTANCE() {
        return mMovieManager;
    }

    public Observable<ShowingMovie> getShowingMovieByLocationId(@NonNull int locationId) {
        return MovieService.getShowingMovieByLocationId(locationId);
    }

    public Observable<ComingMovie> getComingMovieByLocationId(@NonNull int locationId) {
        return MovieService.getComingMovieByLocationId(locationId);
    }

    public Observable<TrailerData> getTrailerByPageIndexAndMovieId(@NonNull int pageIndex, @NonNull int movieId) {
        return MovieService.getTrailerByPageIndexAndMovieId(pageIndex, movieId);
    }

    public Observable<MovieDetail> getDetailByLocationIdAndmovieId(@NonNull int locationId, @NonNull int movieId) {
        return MovieService.getDetailByLocationIdAndmovieId(locationId, movieId);
    }

}
