package com.example.administrator.newmovie.Data;

import java.util.List;

/**
 * Created by Administrator on 2017/10/21.
 */

public class MaoyanMovieId {

    /**
     * type : 0
     * movies : {"list":[{"cat":"剧情,悬疑,犯罪","dir":"纳塔吾·彭皮里亚","dur":130,"enm":"ฉลาดเกมส์โกง","fra":"泰国","frt":"2017-05-03","globalReleased":true,"id":1204596,"img":"http://p0.meituan.net/w.h/movie/385a6d22c2f1ed563aa58594d56cee39955568.jpg","movieType":0,"nm":"天才枪手","onlinePlay":false,"pubDesc":"2017-10-13大陆上映","rt":"2017-10-13","sc":8.8,"show":"","showst":3,"star":"茱蒂蒙·琼查容苏因,依莎亚·贺苏汪,披纳若·苏潘平佑","type":0,"ver":"2D/IMAX 2D","wish":44452,"wishst":0}],"total":1,"type":0}
     */

    private int type;
    private MoviesBean movies;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public MoviesBean getMovies() {
        return movies;
    }

    public void setMovies(MoviesBean movies) {
        this.movies = movies;
    }

    public static class MoviesBean {
        /**
         * list : [{"cat":"剧情,悬疑,犯罪","dir":"纳塔吾·彭皮里亚","dur":130,"enm":"ฉลาดเกมส์โกง","fra":"泰国","frt":"2017-05-03","globalReleased":true,"id":1204596,"img":"http://p0.meituan.net/w.h/movie/385a6d22c2f1ed563aa58594d56cee39955568.jpg","movieType":0,"nm":"天才枪手","onlinePlay":false,"pubDesc":"2017-10-13大陆上映","rt":"2017-10-13","sc":8.8,"show":"","showst":3,"star":"茱蒂蒙·琼查容苏因,依莎亚·贺苏汪,披纳若·苏潘平佑","type":0,"ver":"2D/IMAX 2D","wish":44452,"wishst":0}]
         * total : 1
         * type : 0
         */

        private int total;
        private int type;
        private List<ListBean> list;

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean {
            /**
             * cat : 剧情,悬疑,犯罪
             * dir : 纳塔吾·彭皮里亚
             * dur : 130
             * enm : ฉลาดเกมส์โกง
             * fra : 泰国
             * frt : 2017-05-03
             * globalReleased : true
             * id : 1204596
             * img : http://p0.meituan.net/w.h/movie/385a6d22c2f1ed563aa58594d56cee39955568.jpg
             * movieType : 0
             * nm : 天才枪手
             * onlinePlay : false
             * pubDesc : 2017-10-13大陆上映
             * rt : 2017-10-13
             * sc : 8.8
             * show :
             * showst : 3
             * star : 茱蒂蒙·琼查容苏因,依莎亚·贺苏汪,披纳若·苏潘平佑
             * type : 0
             * ver : 2D/IMAX 2D
             * wish : 44452
             * wishst : 0
             */

            private String cat;
            private String dir;
            private int dur;
            private String enm;
            private String fra;
            private String frt;
            private boolean globalReleased;
            private int id;
            private String img;
            private int movieType;
            private String nm;
            private boolean onlinePlay;
            private String pubDesc;
            private String rt;
            private double sc;
            private String show;
            private int showst;
            private String star;
            private int type;
            private String ver;
            private int wish;
            private int wishst;

            public String getCat() {
                return cat;
            }

            public void setCat(String cat) {
                this.cat = cat;
            }

            public String getDir() {
                return dir;
            }

            public void setDir(String dir) {
                this.dir = dir;
            }

            public int getDur() {
                return dur;
            }

            public void setDur(int dur) {
                this.dur = dur;
            }

            public String getEnm() {
                return enm;
            }

            public void setEnm(String enm) {
                this.enm = enm;
            }

            public String getFra() {
                return fra;
            }

            public void setFra(String fra) {
                this.fra = fra;
            }

            public String getFrt() {
                return frt;
            }

            public void setFrt(String frt) {
                this.frt = frt;
            }

            public boolean isGlobalReleased() {
                return globalReleased;
            }

            public void setGlobalReleased(boolean globalReleased) {
                this.globalReleased = globalReleased;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getImg() {
                return img;
            }

            public void setImg(String img) {
                this.img = img;
            }

            public int getMovieType() {
                return movieType;
            }

            public void setMovieType(int movieType) {
                this.movieType = movieType;
            }

            public String getNm() {
                return nm;
            }

            public void setNm(String nm) {
                this.nm = nm;
            }

            public boolean isOnlinePlay() {
                return onlinePlay;
            }

            public void setOnlinePlay(boolean onlinePlay) {
                this.onlinePlay = onlinePlay;
            }

            public String getPubDesc() {
                return pubDesc;
            }

            public void setPubDesc(String pubDesc) {
                this.pubDesc = pubDesc;
            }

            public String getRt() {
                return rt;
            }

            public void setRt(String rt) {
                this.rt = rt;
            }

            public double getSc() {
                return sc;
            }

            public void setSc(double sc) {
                this.sc = sc;
            }

            public String getShow() {
                return show;
            }

            public void setShow(String show) {
                this.show = show;
            }

            public int getShowst() {
                return showst;
            }

            public void setShowst(int showst) {
                this.showst = showst;
            }

            public String getStar() {
                return star;
            }

            public void setStar(String star) {
                this.star = star;
            }

            public int getType() {
                return type;
            }

            public void setType(int type) {
                this.type = type;
            }

            public String getVer() {
                return ver;
            }

            public void setVer(String ver) {
                this.ver = ver;
            }

            public int getWish() {
                return wish;
            }

            public void setWish(int wish) {
                this.wish = wish;
            }

            public int getWishst() {
                return wishst;
            }

            public void setWishst(int wishst) {
                this.wishst = wishst;
            }
        }
    }
}
