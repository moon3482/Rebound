package com.example.rebound;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

public class Team_info extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.team_info);
        Intent intent = getIntent();
        String naem = intent.getStringExtra("naem");
        String area = intent.getStringExtra("area");
        String temabirth = intent.getStringExtra("temabirth");
        String manager = intent.getStringExtra("manager");
        String coch = intent.getStringExtra("coch");
        String uri = intent.getStringExtra("uri");

        TextView name1=(TextView) findViewById(R.id.team_info_name2);
        TextView temabirth1=(TextView) findViewById(R.id.team_info_makedate2);
        TextView area1=(TextView) findViewById(R.id.team_info_home2);
        TextView manager1=(TextView) findViewById(R.id.team_info_manager2);
        TextView coch1=(TextView) findViewById(R.id.team_info_coch2);
        ImageView uri1 =(ImageView)findViewById(R.id.team_info_image);
        name1.setText(naem);
        temabirth1.setText(temabirth);
        area1.setText(area);
        manager1.setText(manager);
        coch1.setText(coch);
        Glide.with(this).load(Uri.parse(uri)).into(uri1);
    }
}
