package com.example.rebound.Login;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.rebound.Id_find;
import com.example.rebound.Main.MainActivity;
import com.example.rebound.Password_find;
import com.example.rebound.R;

public class LoginActivity extends AppCompatActivity {
    private long lastTimeBackPressed;
    SharedPreferences sharedPreferences;

    SharedPreferences.Editor editor;
    String id1, ps1, psc1, na1, bri1, pho1, pho2, pho3, ema1, ema2, hei1, wei1, team1;
    String aa, bb;
    EditText idText;
    EditText passowordText;
    String[] user;
    CheckBox autoLogin;
    boolean login;
    boolean loginChecked = false;
    static int login_user_info;
    int i;
    public static String user_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        sharedPreferences = getSharedPreferences("UserFile", MODE_PRIVATE);
        String setuser = sharedPreferences.getString("user", "");
        String autolog = sharedPreferences.getString("auto_login", "");
        Log.i("쉐어드 불러오기", setuser);
        user = setuser.split("#");


        Button button1 = (Button) findViewById(R.id.login_button);
        idText = (EditText) findViewById(R.id.login_id);
        passowordText = (EditText) findViewById(R.id.login_password);
        autoLogin = (CheckBox) findViewById(R.id.auto_login);
        autoLogin.setChecked(false);
        final Intent intent = getIntent();
        id1 = intent.getStringExtra("id");
        ps1 = intent.getStringExtra("password");
        psc1 = intent.getStringExtra("password_check");
        na1 = intent.getStringExtra("name");
        bri1 = intent.getStringExtra("brith");
        pho1 = intent.getStringExtra("phone");
        pho2 = intent.getStringExtra("phone2");
        pho3 = intent.getStringExtra("phone3");
        ema1 = intent.getStringExtra("email");
        ema2 = intent.getStringExtra("email2");
        hei1 = intent.getStringExtra("height");
        wei1 = intent.getStringExtra("weight");
        team1 = intent.getStringExtra("team");


//        if(autolog.length()>0||autolog!=null){
//
//            String[] autosplit = autolog.split("-");
//        if (autosplit[2].equals("true")) {
//
//            for (i = 0; i < user.length; i++) {
//                String[] index = user[i].split("-");
////                    login_user_info = i-1;
////                    Log.i("유저 넘버0", String.valueOf(login_user_info));
//
//
//                for (int j = 0; j < user.length; j++) {
//
//                    if (!autosplit[0].equals(index[0]) || !autosplit[1].equals(index[1])) {
//                        Log.i("매치", index[0]);
//                        login = false;
//
//
//                    } else if (autosplit[0].equals(index[0]) && autosplit[0].equals(index[1])) {
//
//
//                        Intent intent1 = new Intent(LoginActivity.this, MainActivity.class);
//                        login_user_info = i;
//                        intent.putExtra("login_user_info", login_user_info);
//                        startActivity(intent1);
//                        SharedPreferences sharedPreferences = getSharedPreferences("User_number", MODE_PRIVATE);
//                        SharedPreferences.Editor editor;
//                        editor = sharedPreferences.edit();
//                        editor.putInt("user_num", login_user_info);
//                        editor.apply();
//                        user_id = index[0];
//                        Toast.makeText(LoginActivity.this, autosplit[0] + "님 로그인되었습니다.", Toast.LENGTH_SHORT).show();
//                        Log.i("유저 넘버", String.valueOf(login_user_info));
//                        Log.i("접속된", user_id);
//
//                        Log.i("접속된", String.valueOf(login_user_info));
//
//                        login = true;
//                        finish();
//
//
//                    }
//
//                }
//
//
//            }
//        }} else {
//
//        }

//      Toast.makeText(this,"값은"+id1, Toast.LENGTH_SHORT).show();
//        autoLogin.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
//                if (b) {
//                    loginChecked = true;
//                    Log.i("자동 ", String.valueOf(loginChecked));
//
//                } else {
//                    loginChecked = false;
//                    Log.i("자동2", String.valueOf(loginChecked));
//
//                }
//
//            }
//        });
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                for (i = 0; i < user.length; i++) {
                    String[] index = user[i].split("-");
//                    login_user_info = i-1;
//                    Log.i("유저 넘버0", String.valueOf(login_user_info));

                    aa = idText.getText().toString();
                    bb = passowordText.getText().toString();
                    if (aa.length() == 0) {
                        Toast.makeText(getApplicationContext(), "아이디를 입력해주세요", Toast.LENGTH_SHORT).show();

                        break;


                    } else if (bb.length() == 0) {
                        Toast.makeText(getApplicationContext(), "비밀번호를 입력해주세요", Toast.LENGTH_SHORT).show();
                        break;
                    }


                    for (int j = 0; j < user.length; j++) {

                        if (!aa.equals(index[0]) || !bb.equals(index[1])) {
                            Log.i("매치", index[0]);
                            login = false;


                        } else if (aa.equals(index[0]) && bb.equals(index[1])) {
//                            if (loginChecked == true) {
//                                String auto = index[0] + "-" + index[1] + "-" + loginChecked + "-";
//                                editor = sharedPreferences.edit();
//                                editor.putString("auto_login", auto);
//                                editor.apply();
//                                Log.i("오토로그인", auto);
//
//                            } else if (loginChecked == false) {
//                                String auto = index[0] + "-" + index[1] + "-" + loginChecked + "-";
//                                editor = sharedPreferences.edit();
//                                editor.putString("auto_login", auto);
//                                editor.apply();
//                                Log.i("오토로그인", auto);
//                            }


                            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                            login_user_info = i;
                            intent.putExtra("login_user_info", login_user_info);
                            startActivity(intent);
                            SharedPreferences sharedPreferences = getSharedPreferences("User_number", MODE_PRIVATE);
                            SharedPreferences.Editor editor;
                            editor = sharedPreferences.edit();
                            editor.putInt("user_num", login_user_info);
                            editor.apply();
                            user_id = index[0];
                            Toast.makeText(LoginActivity.this, aa + "님 로그인되었습니다.", Toast.LENGTH_SHORT).show();
                            Log.i("유저 넘버", String.valueOf(login_user_info));
                            Log.i("접속된", user_id);

                            Log.i("접속된", String.valueOf(login_user_info));

                            login = true;

                            finish();
                            return;


                        }

                    }


                }
                if (login == false) {
                    Toast.makeText(getApplicationContext(), "아이디와 비밀번호를 확인해주세요", Toast.LENGTH_SHORT).show();
                }

            }
        });
        Button button2 = (Button) findViewById(R.id.sign_up_button);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                SharedPreferences sharedPreferences;
//                SharedPreferences.Editor editor;
//                sharedPreferences = getSharedPreferences("UserFile", MODE_PRIVATE);
//                String auto = sharedPreferences.getString("auto_login", "");
//                String[] autosplit = auto.split("-");
//                autosplit[2] = false;
//                String autoplus = autosplit[0] + "-" + autosplit[1] + "-" + autosplit[2] + "-";
//                editor = sharedPreferences.edit();
//                editor.putString("auto_login", autoplus);
//                editor.apply();
                Intent intent = new Intent(LoginActivity.this, Clause.class);
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

                startActivity(intent);

            }
        });
        TextView textView = (TextView) findViewById(R.id.id_Textview);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, Id_find.class);
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
                startActivity(intent);
                onStop();
            }
        });
        TextView textView1 = (TextView) findViewById(R.id.password_Textview);
        textView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, Password_find.class);
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
                startActivity(intent);
                onStop();
            }
        });
//        ArrayList<User> userindex = new ArrayList<User>();


    }

    @Override
    public void onBackPressed() {
        if (System.currentTimeMillis() - lastTimeBackPressed < 1500) {
            finish();
            return;
        }
        lastTimeBackPressed = System.currentTimeMillis();
        Toast.makeText(this, "'뒤로' 버튼을 한 번 더 누르면 종료됩니다.", Toast.LENGTH_SHORT).show();
    }


}
