package com.moradi.nima.quran.Adpter;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.moradi.nima.quran.R;
import com.moradi.nima.quran.download.DownloadHelper;

import java.util.List;

/**
 * Created by nima on 7/26/2017.
 */

public class SingerAdapter extends RecyclerView.Adapter<SingerAdapter.MyViewHolder> {
    private Context context;
    private List<Singer> SingerList;
    private int suraLen, suraId;
    private RecyclerView recyclerView;

    public SingerAdapter(List<Singer> SingerList, Context context, int suraLen, int suraId, RecyclerView recyclerView) {
        this.SingerList = SingerList;
        this.context = context;
        this.suraId = suraId;
        this.suraLen = suraLen;
        this.recyclerView = recyclerView;
    }

    @Override
    public MyViewHolder onCreateViewHolder(final ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_download_task, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        final Singer singer = SingerList.get(position);
        holder.name.setText(singer.getSinger());
        holder.progressNum.setText(singer.getProgrss());
        holder.progressNum.setText(singer.getProgrss());
        holder.quality.removeAllViews();
        for (int i = 0; i < singer.getQuality().length; i++) {
            RadioButton rad = new RadioButton(getContext());
            rad.setText(singer.getQuality()[i]);
            rad.toggle();
            rad.setId(i);
            holder.quality.addView(rad);
        }

        holder.download.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recyclerView.stopScroll();

                holder.quality.getCheckedRadioButtonId();

                downloadWithPro helper = new downloadWithPro(holder,
                        "Quarn/audio/" + singer.getSinger() + "/" + ((RadioButton) v.getRootView().findViewById(holder.quality.getCheckedRadioButtonId())).getText().toString()
                        , getContext(),
                        suraId
                        , holder.name.getText().toString()
                        , ((RadioButton) v.getRootView().findViewById(holder.quality.getCheckedRadioButtonId())).getText().toString()
                        , singer.getProvider()
                        , suraLen);


                helper.addToDownload();
            }
        });//Abdul_Basit_Murattal_192kbps_/093001.mp3


    }

    @Override
    public int getItemCount() {
        return SingerList.size();
    }

    public Context getContext() {
        return context;
    }

    class downloadWithPro extends DownloadHelper {
        MyViewHolder holder;

        public downloadWithPro(MyViewHolder holder, String savePath, Context context, int suranumber, String singer, String qualty, @Nullable String provider, int SuraLenght) {
            super(savePath, context, suranumber, singer, qualty, provider, SuraLenght);
            this.holder = holder;

        }

        @Override

        public void onCompleted() {
            super.onCompleted();
            float i = (float) super.Downloaded / super.suralenght * 100;
            holder.progressBar.setProgress((int) i);
            holder.progressNum.setText(super.Downloaded + "/" + super.suralenght);


        }

        @Override
        public void getDownloaded(int d) {
            float i = (float) super.Downloaded / super.suralenght * 100;
            holder.progressBar.setProgress((int) i);
            holder.progressNum.setText(super.Downloaded + "/" + super.suralenght);

        }


    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView name, progressNum;
        public RadioGroup quality;
        public ProgressBar progressBar;
        public ImageView SingerImage;
        public Button download, cancel;

        public MyViewHolder(View view) {
            super(view);
            name = (TextView) view.findViewById(R.id.SingerName);
            progressNum = (TextView) view.findViewById(R.id.progressNum);
            quality = (RadioGroup) view.findViewById(R.id.fileQ);
            SingerImage = (ImageView) view.findViewById(R.id.singerImage);
            progressBar = (ProgressBar) view.findViewById(R.id.progressBar);
            download = (Button) view.findViewById(R.id.downloadButt);
            cancel = (Button) view.findViewById(R.id.cancel);
        }

    }
}