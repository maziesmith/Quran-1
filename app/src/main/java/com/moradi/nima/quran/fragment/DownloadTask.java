package com.moradi.nima.quran.fragment;


import android.app.DialogFragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PagerSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SnapHelper;
import android.util.Log;

import com.moradi.nima.quran.Adpter.Singer;
import com.moradi.nima.quran.Adpter.SingerAdapter;
import com.moradi.nima.quran.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link DialogFragment} subclass.
 * Activities that contain this fragment must implement the
 * create an instance of this fragment.
 */
public class DownloadTask extends AppCompatActivity {
    private String SuraLength;
    private List<com.moradi.nima.quran.Adpter.Singer> SingerList = new ArrayList<>();
    private RecyclerView recyclerView;
    private SingerAdapter mAdapter;
    private Singer sing;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.downloadtask);

        SuraLength = getIntent().getExtras().getString("SuraLength");
        int suraId = Integer.parseInt(getIntent().getExtras().getString(this.getString(R.string.SuraID)));
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        Log.i(this + " SuraLength:", SuraLength);

        mAdapter = new SingerAdapter(SingerList, this, Integer.parseInt(SuraLength), suraId, recyclerView) {
            @Override
            public void OnBackSupport(Singer singer) {
                sing = singer;
            }
        };
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);
        mLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        SnapHelper snapHelper = new PagerSnapHelper();
        snapHelper.attachToRecyclerView(recyclerView);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);

        try {
            parse();
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }


    //to return data
    @Override
    public void onBackPressed() {

        Intent data = new Intent();
        data.putExtra("SingerName", sing.getSinger());
        data.putExtra("Quality", sing.getQuality()[0]);
        setResult(RESULT_OK, data);
        super.onBackPressed();
//---close the activity---

    }

    private String loadJSONFromAsset(String file) {
        String json = null;
        try {
            InputStream is = this.getAssets().open("json/" + file + ".json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }

        return json;
    }

    private void parse() throws JSONException {
        Singer singer;
        try {


            for (int i = 1; i < 8; i++) {
                JSONObject obj;
                obj = new JSONObject(loadJSONFromAsset(i + ""));//singers
                obj = obj.getJSONObject("Singer");

                singer = new Singer();
                singer.setSinger(obj.getString("name"));
                singer.setProvider(obj.getString("provider"));
                singer.setQuality(obj.getJSONArray("Quality"));
                singer.setProgrss(0 + "/" + SuraLength);
                this.SingerList.add(singer);
                Log.i("test" + i, singer.getSinger() + singer.getProvider() + singer.getQuality());
            }
        } catch (Exception e) {
            Log.e(this + "", String.valueOf(e.getStackTrace()));
        }


        mAdapter.notifyDataSetChanged();
    }




}
