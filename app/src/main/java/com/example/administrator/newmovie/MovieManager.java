package com.example.administrator.newmovie;

import android.support.annotation.NonNull;

import com.example.administrator.newmovie.Trailer.TrailerData;

import rx.Observable;

/**
 * Created by Administrator on 2017/9/14.
 */

public class MovieManager {

    public static MovieManager mMovieManager = new MovieManager();

    public static MovieManager INSTANCE() {
        return mMovieManager;
    }

    public Observable<ShowingMovie> getShowingMovieByLocationId(@NonNull int locationid) {
        return MovieService.getShowingMovieByLocationId(locationid);
    }

    public Observable<ComingMovie> getComingMovieByLocationId(@NonNull int locationid) {
        return MovieService.getComingMovieByLocationId(locationid);
    }

    public Observable<TrailerData> getTrailerByPageIndexAndMovieId(@NonNull int pageindex, @NonNull int movieid) {
        return MovieService.getTrailerByPageIndexAndMovieId(pageindex, movieid);
    }

}
