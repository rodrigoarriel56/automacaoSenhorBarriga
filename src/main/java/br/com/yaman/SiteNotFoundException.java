package br.com.yaman;


public class SiteNotFoundException extends java.io.IOException {

    private String url;
    public SiteNotFoundException(String url) {
        super(url);
        this.url = url;
    }

    public String getUrl() {
        return this.url;
    }
}