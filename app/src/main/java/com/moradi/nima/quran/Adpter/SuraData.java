package com.moradi.nima.quran.Adpter;

/**
 * Created by nima on 6/28/2017.
 */

public class SuraData {


    private int SuraID;
    private int SuraVerse;
    private String SuraName;
    private boolean voice;
    private String nozol;


    public int getSuraID() {
        return SuraID;
    }

    public void setSuraID(int suraID) {
        SuraID = suraID;
    }

    public int getSuraVerse() {
        return SuraVerse;
    }

    public void setSuraVerse(int suraVerse) {
        SuraVerse = suraVerse;
    }

    public String getSuraName() {
        return SuraName;
    }

    public void setSuraName(String suraName) {
        SuraName = suraName;
    }

    public boolean isVoice() {
        return voice;
    }

    public void setVoice(boolean voice) {
        this.voice = voice;
    }

    public String getNozol() {
        return nozol;
    }

    public void setNozol(String nozol) {
        this.nozol = nozol;
    }


}
