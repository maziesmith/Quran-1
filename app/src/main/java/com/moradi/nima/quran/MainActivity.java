package com.moradi.nima.quran;


import android.Manifest;
import android.app.ListActivity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.moradi.nima.quran.Adpter.SuraData;
import com.moradi.nima.quran.Adpter.SuraList;
import com.moradi.nima.quran.fragment.DownloadTask;

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

        SuraList listview = new SuraList(this, Sura, databaseAccess) {
            @Override
            public void OnClick(View v) {
                v = (View) v.getParent();
                Intent intent = new Intent(MainActivity.this, DownloadTask.class);

                intent.putExtra("SuraName", ((TextView) v.findViewById(R.id.sura_name)).getText().toString());
                intent.putExtra(getContext().getString(R.string.SuraID), ((TextView) v.findViewById(R.id.sura_id)).getText().toString());
                intent.putExtra("SuraLength", ((TextView) v.findViewById(R.id.verse)).getText().toString());
                Log.i(this + " SuraLength: ", ((TextView) v.findViewById(R.id.verse)).getText() + "");
                startActivity(intent);
            }
        };
        setListAdapter(listview);


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            //   if (true)
            requestPermissions(
                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    1);
        }


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


}
