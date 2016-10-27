package com.crawler.entity;

import java.util.Date;

/**
 * url
 */
public class Href {
    private String url;//访问的地址
    private String urlMd5;//MD5散列之后的地址
    private Date time;//时间

    public Href() {
    }

    public Href(String url, String urlMd5, Date time) {
        this.url = url;
        this.urlMd5 = urlMd5;
        this.time = time;
    }

    public String getUrl() {
        return url;
    }
    public void setUrl(String url) {
        this.url = url;
    }
    public String getUrlMd5() {
        return urlMd5;
    }
    public void setUrlMd5(String urlMd5) {
        this.urlMd5 = urlMd5;
    }
    public Date getTime() {
        return time;
    }
    public void setTime(Date time) {
        this.time = time;
    }
}
