package com.example.administrator.newmovie.Data;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by Administrator on 2017/11/29.
 */

public interface IMovieService {
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
    Observable<MovieDetail> getDetailByLocationIdAndMovieId(
            @Query("locationId") int locationId,
            @Query("movieId") int movieId
    );


    @GET("detail.api")
    Observable<MovieAward> getAwardByLocationIdAndMovieId(
            @Query("locationId") int locationId,
            @Query("movieId") int movieId
    );

    @GET("subject_suggest")
    Observable<List<DoubanMovieId>> getDoubanMovieIdByMovieName(
            @Query("q") String movieName);

    @GET("suggest")
    Observable<MaoyanMovieId> getMaoyanMovieIdByMovieName(
            @Query("kw") String movieName);


    @GET("Search.api")
    Observable<String> getTimeMovieIdByMovieNameAndTime(
            @Query("Ajax_CallBack") String s1,
            @Query("Ajax_CallBackType") String s2,
            @Query("Ajax_CallBackMethod") String s3,
            @Query("Ajax_CrossDomain") String s4,
            @Query("Ajax_RequestUrl") String s5,
            @Query("Ajax_CallBackArgument0") String s6,
            @Query("Ajax_CallBackArgument1") String s7,
            @Query("Ajax_CallBackArgument2") String s8,
            @Query("Ajax_CallBackArgument3") String s9,
            @Query("Ajax_CallBackArgument4") String s0
    );


    @GET("ImageAll.api")
    Observable<MovieImageAll> getMovieImageAllByMovieId(
            @Query("movieId") int movieId
    );

    @GET("p/{id}")
    Observable<String> getBanaerDataById(@Path("id") String Id);

    @GET("search_content/")
    Observable<TodayNewsKeyword> getTodayNewsByKeyword(
            @Query("offset") int offset,
            @Query("format") String format,
            @Query("keyword") String keyword,
            @Query("autoload") boolean autoload,
            @Query("count") int count,
            @Query("cur_tab") int cur_tab
    );


    @GET("search_content/")
    Observable<XiGuaMovieOriginalData> getXiGuaMovieOriginalDataByKeyword(
            @Query("format") String format,
            @Query("autoload") boolean autoload,
            @Query("count") int count,
            @Query("keyword") String keyword,
            @Query("cur_tab") int cur_tab,
            @Query("offset") int offset
    );


    @GET("feed/")
    Observable<TouTiaoVideoOriginalData> getTouTiaoVideoDataByMaxBehotTime(
            @Query("max_behot_time") String max_behot_time,
            @Query("category") String category
    );

    @GET("feed/")
    Observable<TouTiaoVideoOriginalData> getTouTiaoVideoDataByMinBehotTime(
            @Query("min_behot_time") String min_behot_time,
            @Query("category") String category
    );
}
