package com.example.administrator.newmovie.Home;

/**
 * Created by Administrator on 2017/9/15.
 */

public class MovieBanner {
    String name;
    String jpg;
    String href;

    public MovieBanner(String name,
                       String jpg,
                       String href) {
        this.name = name;
        this.jpg = jpg;
        this.href = href;
    }

    public String getName() {
        return name;
    }

    public String getHref() {
        return href;
    }

    public String getJpg() {
        return jpg;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public void setJpg(String jpg) {
        this.jpg = jpg;
    }
}
