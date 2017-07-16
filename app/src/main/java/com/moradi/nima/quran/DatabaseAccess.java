package com.moradi.nima.quran;

/**
 * Created by nima on 6/25/2017.
 */
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.moradi.nima.quran.Adpter.SuraData;
import com.moradi.nima.quran.Adpter.SuraTextData;

import java.util.ArrayList;
import java.util.List;

public class DatabaseAccess {
    private SQLiteOpenHelper openHelper;
    private SQLiteDatabase database;
    private static DatabaseAccess instance;

    /**
     * Private constructor to avoid object creation from outside classes.
     *
     * @param context
     */
    private DatabaseAccess(Context context) {
        this.openHelper = new DatabaseOpenHelper(context);
    }

    /**
     * Return a singleton instance of DatabaseAccess.
     *
     * @param context the Context
     * @return the instance of DatabaseAccess
     */
    public static DatabaseAccess getInstance(Context context) {
        if (instance == null) {
            instance = new DatabaseAccess(context);
        }
        return instance;
    }

    /**
     * Open the database connection.
     */
    public void open() {
        this.database = openHelper.getWritableDatabase();
    }

    /**
     * Close the database connection.
     */
    public void close() {
        if (database != null) {
            this.database.close();
        }
    }

    /**
     * Read all sura from the database.
     *
     * @return a List of quotes
     */
    public List<SuraData> getSurah() {
        List<SuraData> list = new ArrayList<>();
        SuraData sura;
        Cursor cursor = database.query("SuraNames",null,null,null,null,null,null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            sura=new SuraData();
            sura.setSuraID(new Integer(cursor.getString(0)));
            sura.setNozol(cursor.getString(1));
            sura.setSuraVerse(new Integer(cursor.getString(2)));
            sura.setSuraName(cursor.getString(3));


            list.add(sura);
            sura=null;
            cursor.moveToNext();
        }
        cursor.close();
        return list;
    }
    public SuraTextData getSuraText(int SuraId ) {
        List<String> list = new ArrayList<>();
        Cursor cursor = database.query("SuraNames",new String[] {"Verses"},"SuraID"+"=?",new String[]{SuraId+""},null,null,null);
        cursor.moveToFirst();
        int size=5;//cursor.getInt(0);
         cursor = database.query("Quran",new String[] {"AyahText"},"SuraID"+"=?",new String[]{SuraId+""},null,null,null);
        cursor.moveToFirst();
        int i=1;
        while (!cursor.isAfterLast()) {
            list.add(cursor.getString(0)+"("+i+")");
            i++;
            cursor.moveToNext();
        }
        cursor.close();
        return new SuraTextData(list,size);
    }


    public List<String> getAyah(int VerseId ) {
        List<String> list = new ArrayList<>();

        Cursor cursor = database.query("Quran",new String[] {"AyahText"},"VerseId"+"=?",new String[]{VerseId+""},null,null,null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            list.add(cursor.getString(0));
            cursor.moveToNext();
        }
        cursor.close();
        return list;
    }

    public List<String> getTranslate(int VerseId) {
        List<String> list = new ArrayList<>();
        Cursor cursor = database.query("PTQuran",new String[] {"AyahText"},"VerseId"+"=?",new String[]{VerseId+""},null,null,null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            list.add(cursor.getString(0));
            cursor.moveToNext();
        }
        cursor.close();
        return list;
    }
}