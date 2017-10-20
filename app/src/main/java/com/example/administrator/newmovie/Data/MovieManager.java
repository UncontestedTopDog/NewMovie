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

    public String  getTimeMovieIdByMovieName(String movieName,String time) throws Exception {
        String path = "http://service.channel.mtime.com/Search.api?" +
                "Ajax_CallBack=true&Ajax_CallBackType=Mtime.Channel.Services&Ajax_CallBackMethod=GetSearchResult&Ajax_CrossDomain=1&Ajax_RequestUrl=" +
                "http://search.mtime.com/search/?q=" +
                movieName+"&t=0&t="+time+"64523&Ajax_CallBackArgument0="+
                movieName+"&Ajax_CallBackArgument1=0&Ajax_CallBackArgument2=373&Ajax_CallBackArgument3=0&Ajax_CallBackArgument4=1";
        String result = HtmlService.getHtml(path);
        int i = result.indexOf("movieId");
        System.out.println(result.substring(i,result.length()));
        return null  ;
    }

}
