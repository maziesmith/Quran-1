package com.moradi.nima.quran;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.jean.jcplayer.JcAudio;
import com.example.jean.jcplayer.JcPlayerView;
import com.moradi.nima.quran.Adpter.SuraTextData;
import com.moradi.nima.quran.download.DownloadHelper;
import com.moradi.nima.quran.download.LinkCreator;

import java.util.List;


public class test1 extends AppCompatActivity {
    JcPlayerView player;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        StringBuilder ayah = new StringBuilder();

        DatabaseAccess databaseAccess = DatabaseAccess.getInstance(this);
        databaseAccess.open();
//
        int pos = getIntent().getExtras().getInt(getString(R.string.SuraID));
        String SuraName = getIntent().getExtras().getString("SuraName");
        SuraTextData suraTextData = databaseAccess.getSuraText(pos);
        List<String> list = suraTextData.getAyah();
        ayah.append(list.get(0).replace("بِسْمِ اللَّهِ الرَّحْمَٰنِ الرَّحِيمِ", "بِسْمِ اللَّهِ الرَّحْمَٰنِ الرَّحِيمِ\t\n"));

        for (int i = 1; i < list.size(); i++) {
            ayah.append(list.get(i));
        }
        TextView text = (TextView) findViewById(R.id.Sura_text);
        TextView suraName = (TextView) findViewById(R.id.sura_name);
        suraName.setText(SuraName);
        text.setText(ayah);
        Typeface typeFace = Typeface.createFromAsset(getAssets(), "fonts/UthmanTN1 Ver10.otf");
        text.setTypeface(typeFace);
        suraName.setTypeface(typeFace);

        player = (JcPlayerView) findViewById(R.id.jcplayer);
        DownloadHelper helper=new DownloadHelper("Quarn/audio/AbdulSamad/64kbps/",this,pos
                ,"AbdulSamad","64kbps","QuranExplorer.Com",list.size());
        helper.addToDownload();


LinkCreator linky=new LinkCreator("AbdulSamad","64kbps",pos);

        for (int i = 1; i <=list.size(); i++)
            player.addAudio(JcAudio.createFromFilePath("AbdulSamad ayah" + i, linky.pathMaker(i)));

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
