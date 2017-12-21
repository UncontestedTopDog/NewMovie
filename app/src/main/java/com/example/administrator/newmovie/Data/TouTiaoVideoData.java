package com.example.administrator.newmovie.Data;

/**
 * Created by Administrator on 2017/11/30.
 */

public class TouTiaoVideoData {
    private String group_id ;
    private String title ;
    private String source ;
    private String video_duration_str ;
    private String share_url ;
    private String image_url ;
    private String media_avatar_url ;
    private String player_url ;

    public TouTiaoVideoData(TouTiaoVideoOriginalData.DataBean dataBean){
        group_id = dataBean.getGroup_id() ;
        title = dataBean.getTitle() ;
        source  = dataBean.getSource() ;
        video_duration_str  = dataBean.getVideo_duration_str() ;
        share_url  = "https://www.ixigua.com/i"+group_id;
        image_url  = dataBean.getImage_url() ;
        media_avatar_url  = dataBean.getMedia_avatar_url() ;
    }

    public String getGroup_id() {
        return group_id;
    }

    public void setGroup_id(String group_id) {
        this.group_id = group_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
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

    public String getPlayer_url() {
        return player_url;
    }

    public void setPlayer_url(String player_url) {
        this.player_url = player_url;
    }
}
