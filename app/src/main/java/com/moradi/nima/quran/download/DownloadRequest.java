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
//http://www.everyayah.com/data/AbdulSamad_64kbps_QuranExplorer.Com/001001.mp3
    public boolean isAvailableViaLink(String link) {//http://www.everyayah.com/data/Husary_Muallim_128kbps/001002.mp3
        link = link.replace("http://www.everyayah.com/data/", "");//Husary_Muallim_128kbps/001002.mp3 or AbdulSamad_64kbps_QuranExplorer.Com/001001.mp3
        String fileName = link.substring(link.lastIndexOf('/'));// /001002.mp3

        //quality or provider
        String quality = link.substring(link.lastIndexOf('_'), link.lastIndexOf('/'));// _128kbps _provider
        if (!quality.contains("kbps")) {
            link=link.replace(quality,"");

            quality = link.substring(link.lastIndexOf('_'), link.lastIndexOf('/'));// /128kbps

        }
        quality=  quality.replace('_','/');
        String singer = link.substring(0, link.lastIndexOf('_'));
        return isAvailableViaPath(singer + quality + fileName);
    }



}
