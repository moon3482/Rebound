package com.example.rebound.post;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.rebound.R;

public class Post_shop extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



        setContentView(R.layout.post2);
        Spinner spinner = (Spinner) findViewById(R.id.category);//send.xml의 스피너 아이디
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.shop, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(adapter);


        Toolbar toolbar = findViewById(R.id.team_info_toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("장터 글쓰기");
        Button button =(Button) findViewById(R.id.post_guest_application);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

finish();

            }
        });
    }
}