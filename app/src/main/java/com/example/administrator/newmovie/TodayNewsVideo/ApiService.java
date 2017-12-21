package com.example.administrator.newmovie.TodayNewsVideo;

import retrofit2.http.GET;
import retrofit2.http.Url;
import rx.Observable;

/**
 *
 */
public interface ApiService {
    //baseUrl
    String HOST = "http://www.toutiao.com/";
    String API_SERVER_URL = HOST + "api/";
    String HOST_VIDEO = "http://i.snssdk.com";
    String URL_VIDEO="/video/urls/v/1/toutiao/mp4/%s?r=%s";




    /**
     * 获取视频页的html代码
     */
    @GET
    Observable<String> getVideoHtml(@Url String url);

    /**
     * 获取视频数据json
     * @param url
     * @return
     */
    @GET
    Observable<ResultResponse<VideoModel>> getVideoData(@Url String url);

}
