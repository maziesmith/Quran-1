package com.moradi.nima.quran.download;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.Log;


import com.aspsine.multithreaddownload.*;


import java.io.File;

/**
 * Created by nima on 7/17/2017.
 */

public class DownloadHelper implements CallBack {
    private Context context;
    private DownloadConfiguration configuration;
    private File path;
    private LinkCreator linky;
    private int suralenght;
    String savePath;

    public DownloadHelper(String savePath, Context context, int suranumber, String singer,
                          String qualty, @Nullable String provider, int SuraLenght) {
        this.suralenght = SuraLenght;
        if (provider != null)
            linky = new LinkCreator(singer, qualty, provider, suranumber);
        else linky = new LinkCreator(singer, qualty, suranumber);
        this.context = context;
        path = new File(android.os.Environment.getExternalStorageDirectory() + "/" + savePath);
        createFolder(savePath);
        configuration = new DownloadConfiguration();
        configuration.setMaxThreadNum(10);
        configuration.setThreadNum(3);
        linky.setAutoIncPos(false);
        this.savePath = savePath.substring(savePath.indexOf('/'));
        this.savePath = this.savePath.substring(this.savePath.indexOf('/'));

    }


    private void DownLoad() {

        DownloadManager.getInstance().init(getContext(), configuration);


        // first: build a DownloadRequest:
        com.aspsine.multithreaddownload.DownloadRequest request = new com.aspsine.multithreaddownload.DownloadRequest.Builder()
                .setName(linky.fileNamer())
                .setUri(linky.getLink())
                .setFolder(path)
                .build();
        Log.i("DownloadHelper", path.getAbsolutePath());
// download:
// the tag here, you can simply use download uri as your tag;
        DownloadManager.getInstance().download(request,
                linky.getLink(), this);

    }

    @Override
    public void onStarted() {

    }

    @Override
    public void onConnecting() {

    }

    @Override
    public void onConnected(long total, boolean isRangeSupport) {
    }

    @Override
    public void onProgress(long finished, long total, int progress) {

    }

    @Override
    public void onCompleted() {
        if (linky.getCurrentPos() < suralenght) {
            linky.setCurrentPos(linky.getCurrentPos() + 1);
            DownLoad();
        }
        Log.i("DownloadHelper", "download compeleted");


    }

    @Override
    public void onDownloadPaused() {

    }

    @Override
    public void onDownloadCanceled() {

    }

    @Override
    public void onFailed(DownloadException e) {

    }


    public void createFolder(String fname) {
        String myfolder = android.os.Environment.getExternalStorageDirectory() + "/" + fname;
        File f = new File(myfolder);
        if (!f.exists())
            if (!f.mkdirs()) {
                Log.i("download helper", myfolder + " can't be created.");

            } else {

                Log.i("download helper", myfolder + " can be created.");
            }
        else
            Log.i("download helper", myfolder + " already exits.");

    }

    public Context getContext() {
        return context;
    }

    public void addToDownload() {
        DownloadRequest request = new DownloadRequest();
        if (!request.isAvailableViaLink(linky.getLink()))
            DownLoad();
        else {
            Log.i("download helper", linky.getLink() + " is avilable");
            if (linky.getCurrentPos() < suralenght) {
                linky.setCurrentPos(linky.getCurrentPos() + 1);
                addToDownload();
            }
            else return;


        }
    }
}
