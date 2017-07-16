package com.moradi.nima.quran.Adpter;

import com.moradi.nima.quran.DatabaseAccess;
import com.moradi.nima.quran.R;

import java.util.List;

/**
 * Created by nima on 7/14/2017.
 */

public class SuraTextData {

  private List<String>ayah;
  private List<String>translation;

  private int size;

  public SuraTextData (){


  }
  public SuraTextData (List<String>ayah,List<String>translation,int size){
    this.ayah=ayah;
    this.translation=translation;
    this.size=size;


  }
  public SuraTextData (List<String>ayah,int size){
    this.ayah=ayah;
    this.size=size;


  }


  public List<String> getAyah() {
    return ayah;
  }

  public List<String> getTranslation() {
    return translation;
  }

  public int getSize() {
    return size;
  }
}
