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


    private boolean autoIncPos=true;


    public LinkCreator(String Singer, String quality, String provider, int SuraNumber) {

        this.provider = provider;
        this.singer = Singer;
        this.quality = quality;

        if (SuraNumber < 100) {
            if (SuraNumber < 10)
                SuraNumberS = "00" + SuraNumber;
            else
                SuraNumberS = "0" + SuraNumber;
        } else SuraNumberS = SuraNumber + "";
    }
    public LinkCreator(String Singer, String quality, int SuraNumber) {
        this.singer = Singer;
        this.quality = quality;

        if (SuraNumber < 100) {
            if (SuraNumber < 10)
                SuraNumberS = "00" + SuraNumber;
            else
                SuraNumberS = "0" + SuraNumber;
        } else SuraNumberS = SuraNumber + "";



    }

    public boolean getAutoIncPos() {
        return autoIncPos;
    }

    public void setAutoIncPos(boolean auto) {
        autoIncPos = auto;
    }

    public int getCurrentPos() {
        return currentPos;
    }


    public void setCurrentPos(int pos) {

            currentPos = pos;


    }

    public void resetCurrentPos() {
        currentPos = 1;

    }

    public String fileNamer() {
        String temp;
        if (currentPos < 100) {
            if (currentPos < 10)
                temp = "00" + currentPos;
            else
                temp = "0" + currentPos;
        } else temp = currentPos + "";
        return SuraNumberS + temp+".mp3";


    }

    public String getLink() {
        StringBuilder link =new StringBuilder("http://www.everyayah.com/data/");

        link .append (singer + "_" + quality);
        if (provider != null && !provider.isEmpty())
            link .append("_"+provider);
        link.append("/"+fileNamer());
        if(autoIncPos)
        currentPos++;
        Log.i("link maker",link.toString());
        return link.toString();

    }
    public String pathMaker(int i){
        return android.os.Environment.getExternalStorageDirectory() +
                "/Quarn/audio/"+singer+"/"+quality+"/"+fileNamer(i);
    }

    public String fileNamer(int currentPos) {
        String temp;
        if (currentPos < 100) {
            if (currentPos < 10)
                temp = "00" + currentPos;
            else
                temp = "0" + currentPos;
        } else temp = currentPos + "";
        return SuraNumberS + temp+".mp3";


    }


}
