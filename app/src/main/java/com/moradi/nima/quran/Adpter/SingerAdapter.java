package com.moradi.nima.quran.Adpter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.moradi.nima.quran.R;

import java.util.List;

/**
 * Created by nima on 7/26/2017.
 */

public class SingerAdapter extends RecyclerView.Adapter<SingerAdapter.MyViewHolder> {

    private List<Singer> SingerList;

    public SingerAdapter(List<Singer> SingerList) {
        this.SingerList = SingerList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_download_task, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Singer singer = SingerList.get(position);
        holder.name.setText(singer.getSinger());
        holder.progressNum.setText(singer.getProgrss());
        //// TODO: 7/26/2017 add view child for radiogroup butoon


        holder.progressNum.setText(singer.getProgrss());

    }

    @Override
    public int getItemCount() {
        return SingerList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView name, progressNum;
        public RadioGroup quality;
        public ProgressBar progressBar;
        private ImageView SingerImage;


        public MyViewHolder(View view) {
            super(view);
            name = (TextView) view.findViewById(R.id.SingerName);
            progressNum = (TextView) view.findViewById(R.id.progressNum);
            quality = (RadioGroup) view.findViewById(R.id.fileQ);
            SingerImage = (ImageView) view.findViewById(R.id.singerImage);
            progressBar = (ProgressBar) view.findViewById(R.id.progressBar);

        }
    }
}