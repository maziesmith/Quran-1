package com.moradi.nima.quran.download;

import android.util.Log;

/**
 * Created by nima on 7/14/2017.
 */

public class LinkCreator {
    private String singer;
    private String quality;
    private String provider;

    private String SuraNumberS;
    private int currentPos = 1;


    public LinkCreator(String Singer, String quality, String provider, int SuraNumber) {
        singer = Singer;
        this.quality = quality;
        this.provider = provider;


        if (SuraNumber < 100) {
            if (SuraNumber < 10)
                SuraNumberS = "00" + SuraNumber;
            else
                SuraNumberS = "0" + SuraNumber;
        } else SuraNumberS = SuraNumber + "";


    }

    public LinkCreator(String Singer, String quality, int SuraNumber) {
        singer = Singer;
        this.quality = quality;




    }

    public int getCurrentPos() {
        return currentPos;
    }

    public void setCurrentPos(int pos) {
        if (pos > 0)
            currentPos = pos;


    }

    public void resetCurrentPos() {
        currentPos = 1;

    }

    private String getLinkNumber() {
        String temp;
        if (currentPos < 100) {
            if (currentPos < 10)
                temp = "00" + currentPos;
            else
                temp = "0" + currentPos;
        } else temp = currentPos + "";
        return SuraNumberS + temp;


    }

    public String getLink() {
        StringBuilder link =new StringBuilder("http://www.everyayah.com/data/");

        link .append (singer + "_" + quality);
        if (provider != null)
            link .append("_"+provider);
        link.append("/"+getLinkNumber()+".mp3");
        currentPos++;
        Log.i("link maker",link.toString());
        return link.toString();

    }
    //http://www.everyayah.com/data/Ahmed_ibn_Ali_al-Ajamy_128kbps_ketaballah.net/004001.mp3


}
