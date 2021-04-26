package com.example.rebound.Login;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.rebound.R;

public class sign_up2 extends AppCompatActivity {

    String id1, ps1, psc1, na1, bri1, gen, pho1, pho2, pho3, ema1, ema2, hei1, wei1, team1, area,position,elite, sign_up2;
    EditText team;
    EditText height;
    EditText weight;
    CheckBox pointguard;
    CheckBox shootingguard;
    CheckBox smallfowerd;
    CheckBox powerfowerd;
    CheckBox center;
    int check;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_up2);
        team = findViewById(R.id.sign_team_index);
        height = findViewById(R.id.sign_height_field);
        weight = findViewById(R.id.sign_weight_field);



        String sign_up2_a;

//
//        final String id1 = intent.getStringExtra("id");
//         String password1 = intent.getStringExtra("password");
//         String password_check1 = intent.getStringExtra("password_check");
//         String name1 = intent.getStringExtra("name");
//          String brith1 = intent.getStringExtra("brith");
//         String phone1 = intent.getStringExtra("phone");
//         String phone21 = intent.getStringExtra("phone2");
//         String email1 = intent.getStringExtra("email");
//          String email21 = intent.getStringExtra("email2");

        Intent intent = getIntent();
        id1 = intent.getStringExtra("id");
        ps1 = intent.getStringExtra("password");
        psc1 = intent.getStringExtra("password_check");
        na1 = intent.getStringExtra("name");
        bri1 = intent.getStringExtra("brith");
        gen = intent.getStringExtra("gender");
        pho1 = intent.getStringExtra("phone");
        pho2 = intent.getStringExtra("phone2");
        pho3 = intent.getStringExtra("phone3");
        ema1 = intent.getStringExtra("email");
        ema2 = intent.getStringExtra("email2");
        hei1 = intent.getStringExtra("height");
        wei1 = intent.getStringExtra("wright");
        team1 = intent.getStringExtra("team");

        sign_up2 = intent.getStringExtra("sign_up");


        Spinner spinner = (Spinner) findViewById(R.id.sign_up_activityarea_spinner);//send.xml의 스피너 아이디
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.area, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Spinner spinner1 = (Spinner) findViewById(R.id.sign_elite_spinner);//send.xml의 스피너 아이디
        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this, R.array.elite, android.R.layout.simple_spinner_item);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Spinner spinner2 = (Spinner) findViewById(R.id.position_pic);
       ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this, R.array.position, android.R.layout.simple_spinner_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(adapter);
        spinner1.setAdapter(adapter1);
        spinner2.setAdapter(adapter2);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                area = adapterView.getItemAtPosition(i).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                elite = adapterView.getItemAtPosition(i).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                position = adapterView.getItemAtPosition(i).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        Button button = (Button) findViewById(R.id.sing_up_done);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                hei1 = height.getText().toString();
                wei1 = weight.getText().toString();
                team1 = team.getText().toString();
                if(area.equals("지역")){
                    Toast.makeText(sign_up2.this,"지역을 선택해주세요",Toast.LENGTH_SHORT).show();
                }else if(position.equals("포지션")){
                    Toast.makeText(sign_up2.this,"포지션을 선택해주세요",Toast.LENGTH_SHORT).show();

                }else if(elite.equals("선출여부")){
                    Toast.makeText(sign_up2.this,"선출 여부를 선택해주세요",Toast.LENGTH_SHORT).show();

                }else {
                    String[] call = {hei1, wei1, team1, area, position, elite, "image"};
                    for (int i = 0; i < call.length; i++) {
                        sign_up2 = sign_up2 + call[i] + "-";
                        Log.i("두번째", sign_up2);
                    }

                    //쉐어드 프리퍼런스
                    SharedPreferences sharedPreferences;
                    SharedPreferences.Editor editor;
                    sharedPreferences = getSharedPreferences("UserFile", MODE_PRIVATE);
                    editor = sharedPreferences.edit();
                    String fff = sharedPreferences.getString("user", "");
                    String chu = fff + sign_up2;
                    editor.putString("user", fff + sign_up2 + "#");
                    editor.apply();
                    Log.i("정보태그", fff);


                    intent.putExtra("id", id1);
                    intent.putExtra("password", ps1);
                    intent.putExtra("password_check", psc1);
                    intent.putExtra("name", na1);
                    intent.putExtra("brith", bri1);
                    intent.putExtra("phone", pho1);
                    intent.putExtra("phone2", pho2);
                    intent.putExtra("phone3", pho3);
                    intent.putExtra("email", ema1);
                    intent.putExtra("email2", ema2);
                    intent.putExtra("height", hei1);
                    intent.putExtra("weight", wei1);
                    intent.putExtra("team", team1);

                    Log.i("가입2", team1);
                    setResult(RESULT_OK, intent);
                    finish();

                }
//                startActivity(intent);
//finish();
            }
        });
    }


}



