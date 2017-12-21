package com.example.administrator.newmovie.Command;

/**
 * Created by Administrator on 2017/12/6.
 */

public class HideImageCommand {
    private boolean show;
    private String media_creator_id;

    public HideImageCommand(boolean show, String media_creator_id) {
        this.show = show;
        this.media_creator_id = media_creator_id;

    }

    public boolean isShow() {
        return show;
    }

    public void setShow(boolean show) {
        this.show = show;
    }

    public String getMedia_creator_id() {
        return media_creator_id;
    }

    public void setMedia_creator_id(String media_creator_id) {
        this.media_creator_id = media_creator_id;
    }
}
