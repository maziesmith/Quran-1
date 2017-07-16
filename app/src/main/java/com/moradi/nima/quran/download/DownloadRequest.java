package com.moradi.nima.quran.download;

import android.os.Environment;
import android.system.Os;

import java.io.File;

/**
 * Created by nima on 7/16/2017.
 */

public class DownloadRequest {
    /**check is it downloaded before via path
     *  @param path string path to file from Quran/
     */
    public boolean isAvailableViaPath(String path){
        File file=new File(android.os.Environment.getExternalStorageDirectory() + "/Quarn/"+path);
       return file.exists();



    }
    /**check is it downloaded before via url*
     * @param link string path to file from Quran/
     */

    public boolean isAvailableViaLink(String link){

        return true;
    }
}
