package com.example.administrator.newmovie.Data;

import java.util.List;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
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

        return retrofit.create(IMovieService.class).getShowingMovieByLocationId(locationid);
    }

    public static Observable<ComingMovie> getComingMovieByLocationId(final int locationid) {

        String URL = "https://api-m.mtime.cn/Movie/";
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URL)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit.create(IMovieService.class).getComingMovieByLocationId(locationid);
    }

    public static Observable<TrailerData> getTrailerByPageIndexAndMovieId(int pageindex, int movieid) {

        String URL = "https://api-m.mtime.cn/Movie/";
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URL)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit.create(IMovieService.class).getTrailerByPageIndexAndMovieId(pageindex,movieid);
    }

    public static Observable<MovieDetail> getDetailByLocationIdAndMovieId(int locationId, int movieId) {

        String URL = "https://ticket-api-m.mtime.cn/movie/";
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URL)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit.create(IMovieService.class).getDetailByLocationIdAndMovieId(locationId,movieId);
    }

    public static Observable<MovieAward> getAwardByLocationIdAndMovieId(int locationId, int movieId) {

        String URL = "https://api-m.mtime.cn/movie/";
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URL)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit.create(IMovieService.class).getAwardByLocationIdAndMovieId(locationId,movieId);
    }

    public static Observable<List<DoubanMovieId>> getDoubanMovieIdByMovieName(String movieName) {

        String URL = " https://movie.douban.com/j/";
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URL)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit.create(IMovieService.class).getDoubanMovieIdByMovieName(movieName);
    }

    public static Observable<MaoyanMovieId> getMaoyanMovieIdByMovieName(String movieName) {

        String URL = "http://maoyan.com/ajax/";
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URL)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit.create(IMovieService.class).getMaoyanMovieIdByMovieName(movieName);
    }

    public static Observable<String> getTimeMovieIdByMovieNameAndTime(String movieName , String time) {


        String URL = "http://service.channel.mtime.com/";
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URL)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(new ToStringConverterFactory())
                .build();
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


        return retrofit.create(IMovieService.class).getTimeMovieIdByMovieNameAndTime(s1 ,s2 ,s3 ,s4,s5,s6,s7,s8,s9,s0);
    }

    public static Observable<MovieImageAll> getMovieImageAllByMovieId(int movieId) {

        String URL = "https://api-m.mtime.cn/Movie/";
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URL)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit.create(IMovieService.class).getMovieImageAllByMovieId(movieId);
    }

    public static Observable<String> getBanaerDataById(String id){
        String URL = "http://www.jianshu.com/";
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URL)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(new ToStringConverterFactory())
                .build();

        return retrofit.create(IMovieService.class).getBanaerDataById(id);
    }

    public static Observable<TodayNewsKeyword> getTodayNewsByKeyword(String keyword) {

        String URL = "https://www.toutiao.com/";
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URL)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        int offset = 0 ;
        String format = "json" ;
        boolean autoload = true ;
        int count = 20 ;
        int cur_tab = 1 ;

        return retrofit.create(IMovieService.class).getTodayNewsByKeyword(offset,format,keyword,autoload,count,cur_tab);
    }

    public static Observable<XiGuaMovieOriginalData> getXiGuaMovieOriginalDataByKeyword(String keyword) {

        String URL = "https://www.ixigua.com/";
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URL)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        int offset = 0 ;
        String format = "json" ;
        boolean autoload = true ;
        int count = 20 ;
        int cur_tab = 1 ;

        return retrofit.create(IMovieService.class).getXiGuaMovieOriginalDataByKeyword(format,autoload,count,keyword,cur_tab,offset);
    }

    public static Observable<TouTiaoVideoOriginalData> getTouTiaoVideoDataByMaxBehotTime(String max_behot_time) {

        String URL = "https://www.ixigua.com/api/pc/";
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URL)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit.create(IMovieService.class).getTouTiaoVideoDataByMaxBehotTime(max_behot_time,"subv_movie");
    }

    public static Observable<TouTiaoVideoOriginalData> getTouTiaoVideoDataByMinBehotTime() {

        String URL = "https://www.ixigua.com/api/pc/";
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URL)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit.create(IMovieService.class).getTouTiaoVideoDataByMaxBehotTime("0","subv_movie");
    }

}
