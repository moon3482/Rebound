package com.example.rebound;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class Password_find extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.password);

//        TextView textView = (TextView) findViewById(R.id.Id_question);
//        TextView textView1= (TextView)findViewById(R.id.id_question);
//TextView textView2 = (TextView)findViewById(R.id.password_id);
//
//        textView.setText("성함을 입력해주세요.");
//        textView1.setText("이메일을 입력해주세요.");
//textView2.setText("아이디를 입력해주세요");
//        Toolbar toolbar = findViewById(R.id.team_info_toolbar);
//        setSupportActionBar(toolbar);
//        ActionBar actionBar = getSupportActionBar();
//        actionBar.setTitle("비밀번호 찾기");

        Button button = (Button) findViewById(R.id.button2);
        button.setText("비밀번호 찾기");

    }
}
