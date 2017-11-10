package com.example.administrator.newmovie.Data;

import java.util.List;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;
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

    public static Observable<MovieDetail> getDetailByLocationIdAndMovieId(int locationId, int movieId) {

        String URL = "https://ticket-api-m.mtime.cn/movie/";
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URL)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit.create(IService.class).getDetailByLocationIdAndMovieId(locationId,movieId);
    }

    public static Observable<MovieAward> getAwardByLocationIdAndMovieId(int locationId, int movieId) {

        String URL = "https://api-m.mtime.cn/movie/";
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URL)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit.create(IService.class).getAwardByLocationIdAndMovieId(locationId,movieId);
    }

    public static Observable<List<DoubanMovieId>> getDoubanMovieIdByMovieName(String movieName) {

        String URL = " https://movie.douban.com/j/";
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URL)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit.create(IService.class).getDoubanMovieIdByMovieName(movieName);
    }

    public static Observable<MaoyanMovieId> getMaoyanMovieIdByMovieName(String movieName) {

        String URL = "http://maoyan.com/ajax/";
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URL)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit.create(IService.class).getMaoyanMovieIdByMovieName(movieName);
    }

    public static Observable<String> getTimeMovieIdByMovieNameAndTime(String movieName , String time) {


        String URL = "http://service.channel.mtime.com/";
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URL)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(new ToStringConverterFactory())
                .build();
        //        http://service.channel.mtime.com/Search.api?
//         Ajax_CallBack              =true
//        &Ajax_CallBackType          =Mtime.Channel.Services
//        &Ajax_CallBackMethod        =GetSearchResult
//        &Ajax_CrossDomain           =1
//        &Ajax_RequestUrl            =http://search.mtime.com/search/?q=复仇者联盟&t=2017102115145089883
//        &Ajax_CallBackArgument0     =复仇者联盟
//        &Ajax_CallBackArgument1     =0
//        &Ajax_CallBackArgument2     =373
//        &Ajax_CallBackArgument3     =0
//        &Ajax_CallBackArgument4     =1
        String s1 ,s2 ,s3 ,s4,s5,s6,s7,s8,s9,s0;
        s1 = "true";
        s2 = "Mtime.Channel.Services";
        s3 = "GetSearchResult";
        s4 = "1";
        s5 = "http://search.mtime.com/search/?q=复仇者联盟&t=2017102115145089883";
        s6 = "复仇者联盟";
        s7 = "0";
        s8 = "373";
        s9 = "0";
        s0 = "1";


        return retrofit.create(IService.class).getTimeMovieIdByMovieNameAndTime(s1 ,s2 ,s3 ,s4,s5,s6,s7,s8,s9,s0);
    }

    public static Observable<MovieImageAll> getMovieImageAllByMovieId(int movieId) {

        String URL = "https://api-m.mtime.cn/Movie/";
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URL)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit.create(IService.class).getMovieImageAllByMovieId(movieId);
    }

    public static Observable<String> getBanaerDataById(String id){
        String URL = "http://www.jianshu.com/";
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URL)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(new ToStringConverterFactory())
                .build();

        return retrofit.create(IService.class).getBanaerDataById(id);
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
    }

}
