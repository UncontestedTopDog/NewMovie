package com.example.administrator.newmovie.Data;

import android.support.annotation.NonNull;
import android.util.Log;

import com.google.gson.Gson;

import java.util.List;

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

    public Observable<MovieDetail> getDetailByLocationIdAndMovieId(@NonNull int locationId, @NonNull int movieId) {
        return MovieService.getDetailByLocationIdAndMovieId(locationId, movieId);
    }

    public Observable<List<DoubanMovieId>> getDoubanMovieIdByMovieName(@NonNull String movieName) {
        return MovieService.getDoubanMovieIdByMovieName(movieName);
    }

    public Observable<MaoyanMovieId> getMaoyanMovieIdByMovieName(@NonNull String movieName) {
        return MovieService.getMaoyanMovieIdByMovieName(movieName);
    }

    public Observable<String> getTimeMovieIdByMovieNameAndTime(@NonNull String movieName,@NonNull String time) {
        return MovieService.getTimeMovieIdByMovieNameAndTime(movieName,time);
    }

    public TimeMovieId handTimeMovieIdInitialData(String result){
        TimeMovieId timeMovieId ;
        String s = " var getSearchResult = ";
        int i = result.indexOf(s);
        result = result.substring(s.length()-1,result.length()-3);
        Gson gson = new Gson();
        timeMovieId = gson.fromJson(result, TimeMovieId.class);
        for (TimeMovieId.ValueBean.MovieResultBean.MoreMoviesBean moreMoviesBean : timeMovieId.getValue().getMovieResult().getMoreMovies())
            Log.i("TCQS",moreMoviesBean.getMovieId()+moreMoviesBean.getMovieTitle());
        return timeMovieId;
    }

}
