package com.moradi.nima.quran;


import android.Manifest;
import android.app.ListActivity;
import android.content.pm.PackageManager;

import android.os.Build;

import android.support.annotation.RequiresApi;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.aspsine.multithreaddownload.CallBack;
import com.aspsine.multithreaddownload.DownloadConfiguration;
import com.aspsine.multithreaddownload.DownloadException;
import com.aspsine.multithreaddownload.DownloadManager;
import com.aspsine.multithreaddownload.DownloadRequest;
import com.moradi.nima.quran.Adpter.SuraData;
import com.moradi.nima.quran.Adpter.SuraList;


import java.io.File;
import java.util.List;


public class MainActivity extends ListActivity {


    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        DatabaseAccess databaseAccess = DatabaseAccess.getInstance(this);
        databaseAccess.open();
        List<SuraData> Sura = databaseAccess.getSurah();
        databaseAccess.close();

        SuraList listview = new SuraList(this, Sura, databaseAccess);
        setListAdapter(listview);


        //  if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        //   if (true)
        requestPermissions(
                new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                1);
        initDownloader();

        //   }

        // }

    }


    public void createFolder(String fname) {
        String myfolder = android.os.Environment.getExternalStorageDirectory() + "/" + fname;
        File f = new File(myfolder);
        if (!f.exists())
            if (!f.mkdirs()) {
                Toast.makeText(this, myfolder + " can't be created.", Toast.LENGTH_SHORT).show();

            } else {

                Toast.makeText(this, myfolder + " can be created.", Toast.LENGTH_LONG).show();
            }
        else
            Toast.makeText(this, myfolder + " already exits.", Toast.LENGTH_LONG).show();

    }


    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case 1: {

                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    // permission was granted, yay! Do the job man
                    //// TODO: 7/16/2017
                } else {

                    //  initDownloader();

                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                    Toast.makeText(MainActivity.this, "Permission denied to read your External storage",
                            Toast.LENGTH_SHORT).show();
                }
                return;
            }

            // other 'case' lines to check for other
            // permissions this app might request
        }
    }

    int i = 1;

    private void initDownloader() {
        createFolder("Quarn/audio/AbdulSamad/64kbps/");
        DownloadConfiguration configuration = new DownloadConfiguration();
        configuration.setMaxThreadNum(10);
        configuration.setThreadNum(3);
        DownloadManager.getInstance().init(getApplicationContext(), configuration);


        // first: build a DownloadRequest:
        DownloadRequest request = new DownloadRequest.Builder()
                .setName("00400" + i + ".mp3")
                .setUri("http://www.everyayah.com/data/AbdulSamad_64kbps_QuranExplorer.Com/00200" + i + ".mp3")
                .setFolder(new File(android.os.Environment.getExternalStorageDirectory() + "/Quarn/audio/AbdulSamad/64kbps/"))
                .build();
        Log.i("download", android.os.Environment.getExternalStorageDirectory() + "/Quarn/audio/AbdulSamad/64kbps/");

// download:
// the tag here, you can simply use download uri as your tag;
        DownloadManager.getInstance().download(request,
                "http://www.everyayah.com/data/AbdulSamad_64kbps_QuranExplorer.Com/00400" + i + ".mp3", new CallBack() {
            @Override
            public void onStarted() {
                Log.i("download", "onStarted");
            }

            @Override
            public void onConnecting() {
                Log.i("download", "onConnecting");
            }

            @Override
            public void onConnected(long total, boolean isRangeSupport) {
                Log.e("download", "done");
            }

            @Override
            public void onProgress(long finished, long total, int progress) {
                Log.i("download", "onProgress");
            }

            @Override
            public void onCompleted() {
                Log.i("download", "onCompleted");
                i++;
                if(i<8)
                initDownloader();

            }

            @Override
            public void onDownloadPaused() {
                Log.i("download", "onDownloadPaused");
            }

            @Override
            public void onDownloadCanceled() {
                Log.i("download", "onDownloadCanceled");

            }

            @Override
            public void onFailed(DownloadException e) {
                Log.i("download", "onFailed");
            }

        });

//
////pause
//        DownloadManager.getInstance().pause("");
//
////pause all
//        DownloadManager.getInstance().pauseAll();
//
////cancel
//        DownloadManager.getInstance().cancel(tag);
//
////cancel all
//        DownloadManager.getInstance().cancelAll();
//
////delete
//        DownloadManager.getInstance().delete(tag);
    }


}
