package com.example.rebound;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

public class user_profile extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_information2);

        Intent intent = getIntent();
        String id = intent.getStringExtra("id");
        String birth = intent.getStringExtra("birth");
        String height = intent.getStringExtra("height");
        String weight = intent.getStringExtra("weight");
        String team = intent.getStringExtra("team");
        String uri = intent.getStringExtra("uri");


        TextView textView = (TextView) findViewById(R.id.myinfo_name);
        TextView textView1 = (TextView) findViewById(R.id.myinfo_birth);
        TextView textView2 = (TextView) findViewById(R.id.aab);
        TextView textView3 = (TextView) findViewById(R.id.aac);
        TextView textView4 = (TextView) findViewById(R.id.myinfo_team);
        ImageView imageView = (ImageView)findViewById(R.id.myinfo_image);
        textView.setText(id);
        textView1.setText(birth);
        textView2.setText(height);
        textView3.setText(weight);
        textView4.setText(team);
        Glide.with(this)
                .load(Uri.parse(uri))
                .error(R.drawable.ic_baseline_add_photo_alternate_24)
                .into(imageView);
    }
}
