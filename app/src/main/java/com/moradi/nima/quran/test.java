package com.moradi.nima.quran;

import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Environment;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.SimpleItemAnimator;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;


import com.example.jean.jcplayer.JcAudio;
import com.example.jean.jcplayer.JcPlayerView;
import com.moradi.nima.quran.Adpter.SuraTextData;
import com.moradi.nima.quran.download.LinkCreator;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;


public class test extends AppCompatActivity {
    JcPlayerView player;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        StringBuilder ayah = new StringBuilder();

        DatabaseAccess databaseAccess = DatabaseAccess.getInstance(this);
        databaseAccess.open();
//
        int pos=getIntent().getExtras().getInt(getString(R.string.SuraID));
        SuraTextData suraTextData=databaseAccess.getSuraText(pos);
        List<String> list = suraTextData.getAyah();
ayah.append(list.get(0).replace("بِسْمِ اللَّهِ الرَّحْمَٰنِ الرَّحِيمِ","بِسْمِ اللَّهِ الرَّحْمَٰنِ الرَّحِيمِ\n"));
       for(int i=1;i<list.size();i++)
       {
           ayah.append(list.get(i));
       }
        TextView text = (TextView) findViewById(R.id.Sura_text);
        text.setText(ayah);
        Typeface typeFace = Typeface.createFromAsset(getAssets(), "fonts/Al Qalam Quran.ttf");
        text.setTypeface(typeFace);


        player = (JcPlayerView) findViewById(R.id.jcplayer);

//        LinkCreator linky = new LinkCreator("AbdulSamad", "64kbps", "QuranExplorer.Com", pos);
//        for (int i = 0; i < 286; i++) {
//
//            player.addAudio(JcAudio.createFromURL("ayah: "+(i+1),linky.getLink()));
//
//        }
//        jcplayerView.3

for(int i=0;i<8;i++)
player.addAudio(JcAudio.createFromFilePath(android.os.Environment.getExternalStorageDirectory() +
        "/Quarn/audio/AbdulSamad/64kbps/00200"+i+".mp3"));

    }



    @Override
    public void onPause() {
        super.onPause();
        player.createNotification();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        player.kill();
    }


}
