package com.example.rebound.Login;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.rebound.R;

public class sign_up1 extends AppCompatActivity {
    EditText id;
    EditText password;
    EditText password_check;
    EditText name;
    EditText brith;
    EditText phone;
    EditText phone2;
    EditText phone3;
    EditText email;
    EditText email2;
    EditText height;
    EditText weight;
    EditText team;
    EditText email_in;
    TextView timer;
    Button email_btn;
    TextView examination;
    Button email_send;
    String id1, ps1, psc1, na1, bri1, gen, pho1, pho2, pho3, ema1, ema2, hei1, wei1, team1, sign_new, sEmail, sPassword, EmailCode, inputEmilCode;
    String[] user;
    JavaMailAPI javaMailAPI;
    boolean idc = false;
    boolean edc = false;
    int sec;
    private Handler handler = new Handler();
    private Runnable runnable = new Runnable() {
        @Override
        public void run() {

            timer.setText(sec + "초");
            sec--;
            handler.postDelayed(this, 1000);
            if (sec < 0) {

                handler.removeCallbacks(this);
//                timer.setVisibility(View.INVISIBLE);


            }
        }
    };


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_up1);
        id = findViewById(R.id.sign_up_user_id);
        password = findViewById(R.id.sign_up_password_writer);
        password_check = findViewById(R.id.signup_password_check_writer);
        name = findViewById(R.id.sign_up_user_name_writer);
        brith = findViewById(R.id.sign_brith);
        phone = findViewById(R.id.sign_up_cellphone_spinner);
        phone2 = findViewById(R.id.sign_up_cellphone_mid);
        phone3 = findViewById(R.id.sign_up_cellphone_last);
        email = findViewById(R.id.sign_up_email_front);
        email2 = findViewById(R.id.sign_up_email_last);
        email_in = findViewById(R.id.email_code_edit);
        email_btn = findViewById(R.id.inemeil_button);
        timer = findViewById(R.id.timer_textView);
        email_send = findViewById(R.id.email_button);
        timer.setVisibility(View.INVISIBLE);
        Intent intent = getIntent();
        SharedPreferences sharedPreferences;
        SharedPreferences.Editor editor;
        sharedPreferences = getSharedPreferences("UserFile", MODE_PRIVATE);
        String setuser = sharedPreferences.getString("user", "");
        Log.i("쉐어드 불러오기", setuser);
        user = setuser.split("#");
        Button button = (Button) findViewById(R.id.button_sign_up_next);
        sEmail = "m34821@gmail.com";
        sPassword = "mck2028716";
        Spinner spinner = (Spinner) findViewById(R.id.sign_up_user_sexpic);//send.xml의 스피너 아이디
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                gen = adapterView.getItemAtPosition(i).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
//        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.season_array, android.R.layout.simple_spinner_item);
//        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
////        Spinner spinner1 = (Spinner) findViewById(R.id.sign_up_cellphone_spinner);//send.xml의 스피너 아이디
////        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this, R.array.phone, android.R.layout.simple_spinner_item);
////        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//
//
////        spinner1.setAdapter(adapter1);
        email_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                androidx.appcompat.app.AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                builder.setTitle("이메일 인증번호 전송 완료");
                builder.setMessage("제한 시간 안에 이메일 인증을 완료해 주세요");
                builder.setNegativeButton("예", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String[] str = {"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s",
                                "t", "u", "v", "w", "x", "y", "z", "1", "2", "3", "4", "5", "6", "7", "8", "9"};
                        String newCode = new String();

                        for (int x = 0; x < 8; x++) {
                            int random = (int) (Math.random() * str.length);
                            newCode += str[random];
                        }
                        EmailCode = newCode;

                        sendMail();


//                        handler.removeCallbacks(runnable);
                        timer.setVisibility(View.VISIBLE);
                        sec = 120;
//                        handler.postDelayed(runnable,3500);


                    }
                }).setPositiveButton("아니오", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });
                builder.show();

            }


        });

        email_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                inputEmilCode = email_in.getText().toString();
                if (sec <= 0) {
                    androidx.appcompat.app.AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                    builder.setTitle("이메일 인증");
                    builder.setMessage("인증시간을 초과 하였습니다.");
                    builder.setNegativeButton("확인", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            edc = false;
                        }
                    });

                    builder.show();
                } else if (!EmailCode.equals(inputEmilCode)) {
                    androidx.appcompat.app.AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                    builder.setTitle("이메일 인증");
                    builder.setMessage("인증코드가 일치하지 않습니다.");
                    builder.setNegativeButton("확인", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            edc = false;
                        }
                    });

                    builder.show();
                } else if (EmailCode.equals(inputEmilCode) && sec > 0) {
                    androidx.appcompat.app.AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                    builder.setTitle("이메일 인증");
                    builder.setMessage("인증이 완료되었습니다.");
                    builder.setNegativeButton("확인", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            edc = true;
                            handler.removeCallbacks(runnable);
                            timer.setText("인증완료");
                            timer.setTextColor(Color.parseColor("#4FE200"));
                        }
                    });

                    builder.show();
                }
            }
        });
        examination = findViewById(R.id.examination);
        examination.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for (int i = 0; i < user.length; i++) {
                    String[] index = user[i].split("-");
                    String aa = id.getText().toString();
                    Log.i("id값", aa);
                    Log.i("id값2", user[i]);
                    Log.i("id값3", index[i]);


                    for (int j = 0; j < user.length; j++) {
//                        String aa = id.getText().toString();
                        if (index[0].equals(aa)) {
                            idc = false;
                            break;


                        } else if (!index[0].equals(aa)) {
                            idc = true;


                        }
                    }


                }
                if (idc == false) {
                    androidx.appcompat.app.AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                    builder.setTitle("중복확인");
                    builder.setMessage("사용하실 수 없는 아이디입니다.");
                    builder.setNegativeButton("확인", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    });

                    builder.show();
                } else if (idc == true) {
                    androidx.appcompat.app.AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                    builder.setTitle("중복확인");
                    builder.setMessage("사용가능한 아이디입니다.");
                    builder.setNegativeButton("확인", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    });

                    builder.show();

                }

            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (idc == false) {
                    androidx.appcompat.app.AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                    builder.setTitle("중복확인");
                    builder.setMessage("아이디 중복확인을 해주세요");
                    builder.setNegativeButton("확인", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    });

                    builder.show();
                } else if (edc == false) {
                    androidx.appcompat.app.AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                    builder.setTitle("이메일 인증");
                    builder.setMessage("이메일 인증을 완료해주세요");
                    builder.setNegativeButton("확인", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    });

                    builder.show();
                } else if (idc == true && edc == true) {
                    Intent intent = new Intent(sign_up1.this, sign_up2.class);
                    intent.putExtra("id", id.getText().toString());
                    id1 = id.getText().toString();
                    ps1 = password.getText().toString();
                    psc1 = password_check.getText().toString();
                    na1 = name.getText().toString();
                    bri1 = brith.getText().toString();
                    pho1 = phone.getText().toString();
                    pho2 = phone2.getText().toString();
                    pho3 = phone3.getText().toString();
                    ema1 = email.getText().toString();
                    ema2 = email2.getText().toString();
                    String[] signup1 = {id1, ps1, psc1, na1, bri1, gen, pho1, pho2, pho3, ema1, ema2};
                    String signup1_a = new String();


                    for (int i = 0; i < signup1.length; i++) {
                        signup1_a = signup1_a + signup1[i] + "-";

                        Log.i("정보태그", signup1_a);


                        Log.i("등록", signup1_a);

                    }
                    intent.putExtra("id", id1);
                    intent.putExtra("password", ps1);
                    intent.putExtra("password_check", psc1);
                    intent.putExtra("name", na1);
                    intent.putExtra("brith", bri1);
                    intent.putExtra("brith", gen);

                    intent.putExtra("phone", pho1);
                    intent.putExtra("phone2", pho2);
                    intent.putExtra("phone3", pho3);
                    intent.putExtra("email", ema1);
                    intent.putExtra("email2", ema2);
                    intent.putExtra("sign_up", signup1_a);


                    intent.addFlags(Intent.FLAG_ACTIVITY_FORWARD_RESULT);
                    startActivity(intent);

                    finish();

                }
            }
        });

    }

    private void sendMail() {
        ema1 = email.getText().toString();
        ema2 = email2.getText().toString();


        //Send Mail
        javaMailAPI = new JavaMailAPI(this, (ema1 + "@" + ema2).toString(), "[리바운드] 이메일 인증코드", "어플에서 코드를 입력하고 인증 버튼을 눌려주세요.\n" + "인증코드 : " + EmailCode,runnable,handler);

        javaMailAPI.execute();

    }
}
