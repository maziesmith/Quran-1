package com.moradi.nima.quran;

import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import com.example.jean.jcplayer.JcAudio;
import com.example.jean.jcplayer.JcPlayerView;
import com.moradi.nima.quran.Adpter.SuraTextData;
import com.moradi.nima.quran.download.DownloadHelper;
import com.moradi.nima.quran.download.LinkCreator;

import java.util.List;


public class test1 extends AppCompatActivity {


    JcPlayerView player;

    private TextView suraName;
    private boolean touched = false;
    private Runnable hideControllerThread = new Runnable() {


        public void run() {

            player.setAnimation(AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade));
            suraName.setAnimation(AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade));
//            Runnable fadeControllerThread = new Runnable() {
//                @Override
//                public void run() {
            player.setVisibility(View.GONE);
            suraName.setVisibility(View.GONE);
//                }
//            };

        }
    };
    private Handler hidehandler = new Handler();
    private Handler AnimHandler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sura_text);
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
        suraName = (TextView) findViewById(R.id.sura_name);
        suraName.setText(SuraName);
        text.setText(ayah);
        Typeface typeFace = Typeface.createFromAsset(getAssets(), "fonts/UthmanTN1 Ver10.otf");
        text.setTypeface(typeFace);
        suraName.setTypeface(typeFace);

        player = (JcPlayerView) findViewById(R.id.jcplayer);
        DownloadHelper helper = new DownloadHelper("Quarn/audio/AbdulSamad/64kbps/", this, pos
                , "AbdulSamad", "64kbps", "QuranExplorer.Com", list.size());
        helper.addToDownload();


        LinkCreator linky = new LinkCreator("AbdulSamad", "64kbps", pos);

        for (int i = 1; i <= list.size(); i++)
            player.addAudio(JcAudio.createFromFilePath("AbdulSamad ayah" + i, linky.pathMaker(i)));


    }

    @Override
    public void onPause() {
        super.onPause();
        player.createNotification(R.mipmap.ic_launcher_round);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        player.kill();
    }

    public void hideControllers() {

        hidehandler.postDelayed(hideControllerThread, 6000);
    }

    public void showControllers() {

        player.setAnimation(AnimationUtils.loadAnimation(getApplicationContext(), R.anim.apper));
        suraName.setAnimation(AnimationUtils.loadAnimation(getApplicationContext(), R.anim.apper));

        player.setVisibility(View.VISIBLE);
        suraName.setVisibility(View.VISIBLE);
        hidehandler.removeCallbacks(hideControllerThread);
        hideControllers();
    }

    @Override
    public void onUserInteraction() {
        super.onUserInteraction();

        if (player.getVisibility() == View.VISIBLE) {
            hidehandler.removeCallbacks(hideControllerThread);
            hideControllers();
        } else {
            showControllers();
        }
    }


}
