package com.example.administrator.newmovie.Data;

/**
 * Created by Administrator on 2017/11/30.
 */

public class XiGuaMovieData {
    private long media_creator_id ;
    private String title ;
    private String media_name ;
    private String video_duration_str ;
    private String share_url ;
    private String image_url ;
    private String media_avatar_url ;
    private String media_url ;
    private String player_url ;

    public String getPlayer_url() {
        return player_url;
    }

    public void setPlayer_url(String player_url) {
        this.player_url = player_url;
    }

    public XiGuaMovieData(XiGuaMovieOriginalData.DataBean dataBean){
        media_creator_id = dataBean.getMedia_creator_id() ;
        title = dataBean.getTitle() ;
        media_name  = dataBean.getMedia_name() ;
        video_duration_str  = dataBean.getVideo_duration_str() ;
        share_url  = dataBean.getShare_url() ;
        image_url  = dataBean.getImage_url() ;
        media_avatar_url  = dataBean.getMedia_avatar_url() ;
    }

    public long getMedia_creator_id() {
        return media_creator_id;
    }

    public void setMedia_creator_id(long media_creator_id) {
        this.media_creator_id = media_creator_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMedia_name() {
        return media_name;
    }

    public void setMedia_name(String media_name) {
        this.media_name = media_name;
    }

    public String getVideo_duration_str() {
        return video_duration_str;
    }

    public void setVideo_duration_str(String video_duration_str) {
        this.video_duration_str = video_duration_str;
    }

    public String getShare_url() {
        return share_url;
    }

    public void setShare_url(String share_url) {
        this.share_url = share_url;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public String getMedia_avatar_url() {
        return media_avatar_url;
    }

    public void setMedia_avatar_url(String media_avatar_url) {
        this.media_avatar_url = media_avatar_url;
    }

    public String getMedia_url() {
        return media_url;
    }

    public void setMedia_url(String media_url) {
        this.media_url = media_url;
    }
}
