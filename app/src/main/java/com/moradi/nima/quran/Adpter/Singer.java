package com.moradi.nima.quran.Adpter;

import org.json.JSONArray;
import org.json.JSONException;

/**
 * Created by nima on 7/25/2017.
 */

public class Singer {
    private String singer;
    private String provider;
    private String[] Quality;
    private String progrss;

    public Singer(String singer, String provider, JSONArray quality) throws JSONException {
        this.singer = singer;
        this.provider = provider;
        Quality = new String[quality.length()];
        for (int i = 0; i < quality.length(); i++) {
            this.Quality[i] = quality.get(i).toString();
        }

    }

    public Singer(String singer, JSONArray quality) throws JSONException {
        this.singer = singer;

        for (int i = 0; i < quality.length(); i++) {
            this.Quality[i] = quality.get(i).toString();
        }

    }

    public Singer() {

    }


    public String getSinger() {
        return singer;
    }

    public void setSinger(String singer) {
        this.singer = singer;
    }

    public String getProvider() {
        return provider;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }

    public String[] getQuality() {
        return Quality;
    }

    public void setQuality(JSONArray quality) {
        Quality = new String[quality.length()];
        for (int i = 0; i < quality.length(); i++) {
            try {

                this.Quality[i] = quality.getString(i);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    public void setQuality(String quality) {
        Quality = new String[1];
        this.Quality[0] = quality;
    }

    public void setQuality(String[] quality) {
        Quality = quality;
    }

    public String getProgrss() {
        return progrss;
    }

    public void setProgrss(String progrss) {
        this.progrss = progrss;
    }

    public void setProgress(int a, int b) {
        this.progrss = a + "/" + b;


    }
}
