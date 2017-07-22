package com.moradi.nima.quran.download;

import android.content.Context;
import android.support.annotation.Nullable;

import com.aspsine.multithreaddownload.DownloadException;

/**
 * Created by nima on 7/20/2017.
 */

public class DownloadList extends DownloadHelper {
    public DownloadList(String savePath, Context context, int suranumber, String singer, String qualty, @Nullable String provider, int SuraLenght) {
        super(savePath, context, suranumber, singer, qualty, provider, SuraLenght);
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
        super.onCompleted();


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


}
