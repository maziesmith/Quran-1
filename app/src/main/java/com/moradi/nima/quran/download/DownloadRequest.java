package com.moradi.nima.quran.download;

import java.io.File;

/**
 * Created by nima on 7/16/2017.
 */

public class DownloadRequest {
    /**
     * check is it downloaded before via path
     *
     * @param path string path to file from Quran/Audio
     */
    public boolean isAvailableViaPath(String path) {
        File file = new File(android.os.Environment.getExternalStorageDirectory() + "/Quarn/Audio/" + path);
        return file.exists();


    }

    /**
     * check is it downloaded before via url*
     *
     * @param link string path to file from Quran/
     */

    public boolean isAvailableViaLink(String link) {//http://www.everyayah.com/data/Husary_Muallim_128kbps/001002.mp3
        link = link.replace("http://www.everyayah.com/data/", "");//Husary_Muallim_128kbps/001002.mp3
        String fileName = link.substring(link.lastIndexOf('/'));//001002.mp3
        String quality = link.substring(link.lastIndexOf('_'), link.lastIndexOf('/'));//128kbps
        String singer = link.substring(0, link.lastIndexOf('_'));
        return isAvailableViaPath(singer + "/" + quality + "/" + fileName);
    }
}
