package com.moradi.nima.quran.fragment;


import android.app.DialogFragment;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.moradi.nima.quran.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;

/**
 * A simple {@link DialogFragment} subclass.
 * Activities that contain this fragment must implement the
 * create an instance of this fragment.
 */
public class DownloadTask extends AppCompatActivity {


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_download_task);
        try {
            parse();
        } catch (JSONException e) {
            e.printStackTrace();
        }
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

        try {
            JSONObject obj = null;

            obj = new JSONObject(loadJSONFromAsset("2"));//singers
            Log.i("json", obj.toString());
            obj.get("name");
        } catch (Exception e) {
        }


    }

    public void SingerChooser(View view) {
    }

    public void StartDownload(View view) {


    }

    public void Cancel(View view) {
    }

    class Singer {
        String singer;
        String provider;
        String[] Quality;


    }

}
