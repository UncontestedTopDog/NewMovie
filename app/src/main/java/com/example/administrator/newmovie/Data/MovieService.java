package com.example.administrator.newmovie.Data;

import com.example.administrator.newmovie.Data.ComingMovie;
import com.example.administrator.newmovie.Data.MovieDetail;
import com.example.administrator.newmovie.Data.ShowingMovie;
import com.example.administrator.newmovie.Data.TrailerData;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by Administrator on 2017/9/18.
 */

public class MovieService {

    public static Observable<ShowingMovie> getShowingMovieByLocationId(final int locationid) {
        String URL = "https://api-m.mtime.cn/Showtime/";
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URL)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit.create(IService.class).getShowingMovieByLocationId(locationid);
    }

    public static Observable<ComingMovie> getComingMovieByLocationId(final int locationid) {

        String URL = "https://api-m.mtime.cn/Movie/";
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URL)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit.create(IService.class).getComingMovieByLocationId(locationid);
    }

    public static Observable<TrailerData> getTrailerByPageIndexAndMovieId(int pageindex, int movieid) {

        String URL = "https://api-m.mtime.cn/Movie/";
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URL)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit.create(IService.class).getTrailerByPageIndexAndMovieId(pageindex,movieid);
    }

    public static Observable<MovieDetail> getDetailByLocationIdAndmovieId(int locationId, int movieId) {

        String URL = "https://ticket-api-m.mtime.cn/movie/";
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URL)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit.create(IService.class).getDetailByLocationIdAndmovieId(locationId,movieId);
    }

    private interface IService {
        @GET("LocationMovies.api")
        Observable<ShowingMovie> getShowingMovieByLocationId(
                @Query("locationId") int locaionId
        );

        @GET("MovieComingNew.api")
        Observable<ComingMovie> getComingMovieByLocationId(
                @Query("locationId") int locaionId
        );

        @GET("Video.api")
        Observable<TrailerData> getTrailerByPageIndexAndMovieId(
                @Query("pageIndex") int pageIndex,
                @Query("movieId") int movieId
        );

        @GET("detail.api")
        Observable<MovieDetail> getDetailByLocationIdAndmovieId(
                @Query("locationId") int locationId,
                @Query("movieId") int movieId
        );

    }

}
