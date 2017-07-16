package com.moradi.nima.quran.Adpter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.moradi.nima.quran.DatabaseAccess;
import com.moradi.nima.quran.R;
import com.moradi.nima.quran.test;

import java.util.List;

/**
 * Created by nima on 6/28/2017.
 */

public class SuraList extends ArrayAdapter<SuraData> {
    DatabaseAccess databaseAccess;

    public SuraList(@NonNull Context context, List<SuraData> Sura, DatabaseAccess databaseAccess) {

        super(context, R.layout.sura_list, Sura);
        this.databaseAccess = databaseAccess;


    }


    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.sura_list, parent, false);
            TextView textView1 = (TextView) convertView.findViewById(R.id.sura_id);
            TextView textView2 = (TextView) convertView.findViewById(R.id.sura_name);
            TextView textView3 = (TextView) convertView.findViewById(R.id.nozal);
            TextView textView4 = (TextView) convertView.findViewById(R.id.verse);

            ImageView imageView = (ImageView) convertView.findViewById(R.id.voice_avilable);


            viewHolder = new ViewHolder(textView1, textView2, textView3, textView4, imageView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        TextView textView1 = viewHolder.getTextView1();
        TextView textView2 = viewHolder.getTextView2();
        TextView textView3 = viewHolder.getTextView3();
        TextView textView4 = viewHolder.getTextView4();
        ImageView imageView = viewHolder.getImageView();

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(getContext(),test.class);
                intent.putExtra(getContext().getString(R.string.SuraID),getItem(position).getSuraID());
                getContext().startActivity(intent);





            }
        });
        Log.d("SuraList", getItem(position).getSuraID() + "");


        textView1.setText(getItem(position).getSuraID() + "");
        textView2.setText(getItem(position).getSuraName() + "");
        textView3.setText(getItem(position).getNozol() + "");
        textView4.setText(getItem(position).getSuraVerse() + "");
        //// TODO: 6/28/2017  add image src later 


        return convertView;
    }

    private class ViewHolder {
        private TextView textView1;
        private TextView textView2;
        private TextView textView3;
        private TextView textView4;

        private ImageView imageView;

        public ViewHolder(TextView textView1, TextView textView2, TextView textView3, TextView textView4, ImageView imageView) {
            setTextView1(textView1);
            setTextView2(textView2);
            setTextView3(textView3);
            setTextView4(textView4);

            setImageView(imageView);

        }

        public TextView getTextView1() {
            return textView1;
        }

        public void setTextView1(TextView textView1) {
            this.textView1 = textView1;
        }

        public TextView getTextView2() {
            return textView2;
        }

        public void setTextView2(TextView textView2) {
            this.textView2 = textView2;
        }

        public TextView getTextView3() {
            return textView3;
        }

        public void setTextView3(TextView textView3) {
            this.textView3 = textView3;
        }

        public TextView getTextView4() {
            return textView4;
        }

        public void setTextView4(TextView textView4) {
            this.textView4 = textView4;
        }


        public ImageView getImageView() {
            return imageView;
        }

        public void setImageView(ImageView imageView) {
            this.imageView = imageView;
        }
    }


}
