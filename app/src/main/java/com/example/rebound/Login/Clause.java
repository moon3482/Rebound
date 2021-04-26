package com.example.rebound.Login;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.rebound.R;

public class Clause extends AppCompatActivity {
    int REQUEST_CODE_INDEX = 77;

    String id1, ps1, psc1, na1, bri1, pho1, pho2, pho3, ema1, ema2, hei1, wei1, team1;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clause);
        Button button = (Button) findViewById(R.id.clause_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Clause.this, sign_up1.class);

                startActivityForResult(intent, REQUEST_CODE_INDEX);


            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_INDEX) {
            if (resultCode == RESULT_OK) {
                id1 = data.getStringExtra("id");
                ps1 = data.getStringExtra("password");
                psc1 = data.getStringExtra("password_check");
                na1 = data.getStringExtra("name");
                bri1 = data.getStringExtra("brith");
                pho1 = data.getStringExtra("phone");
                pho2 = data.getStringExtra("phone2");
                pho3 = data.getStringExtra("phone3");
                ema1 = data.getStringExtra("email");
                ema2 = data.getStringExtra("email2");
                hei1 = data.getStringExtra("height");
                wei1 = data.getStringExtra("weight");
                team1 = data.getStringExtra("team");

                Intent intent = new Intent(Clause.this, LoginActivity.class);
                intent.putExtra("id",id1);
                intent.putExtra("password",ps1);
                intent.putExtra("password_check",psc1);
                intent.putExtra("name",na1);
                intent.putExtra("brith",bri1);
                intent.putExtra("phone",pho1);
                intent.putExtra("phone2",pho2);
                intent.putExtra("phone3",pho3);
                intent.putExtra("email",ema1);
                intent.putExtra("email2",ema2);
                intent.putExtra("height",hei1);
                intent.putExtra("weight",wei1);
                intent.putExtra("team",team1);
                Log.i("리절트",team1);
                startActivity(intent);
                finish();




            }
        }
    }
}
