package com.example.rebound;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.rebound.Login.sign_up2;

public class Myinfomation extends AppCompatActivity {
    private static final int REQUEST_CODE = 3;
    private static final int REQUEST_CODE1 = 5;
    private static final int REQUEST_CODE2 = 80;

    ImageView imageView;
    TextView tv;
    String id1, ps1, psc1, na1, bri1, gen, pho1, pho2, pho3, ema1, ema2, hei1, wei1, team1, image;
    int user;
    String uri;
    ImageButton imageButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_information);
        SharedPreferences sharedPreferences;
//        Intent intent = getIntent();
//        id1 = intent.getStringExtra("id");
//        ps1 = intent.getStringExtra("password");
//        psc1 = intent.getStringExtra("password_check");
//        na1 = intent.getStringExtra("name");
//        bri1 = intent.getStringExtra("brith");
//        pho1 = intent.getStringExtra("phone");
//        pho2 = intent.getStringExtra("phone2");
//        pho3 = intent.getStringExtra("phone3");
//        ema1 = intent.getStringExtra("email");
//        ema2 = intent.getStringExtra("email2");
//        hei1 = intent.getStringExtra("height");
//        wei1 = intent.getStringExtra("weight");
//        team1 = intent.getStringExtra("team");

        sharedPreferences = getSharedPreferences("User_number", MODE_PRIVATE);
        user = sharedPreferences.getInt("user_num", 100);
        Log.i("유저 로그", String.valueOf(user));
        sharedPreferences = getSharedPreferences("UserFile", MODE_PRIVATE);
        String setuser = sharedPreferences.getString("user", "");
        String[] userprofile = setuser.split("#");
        String[] userprofile2 = userprofile[user].split("-");
        id1 = userprofile2[0];
        ps1 = userprofile2[1];
        psc1 = userprofile2[2];
        na1 = userprofile2[3];
        bri1 = userprofile2[4];
        gen = userprofile2[5];
        pho1 = userprofile2[6];
        pho2 = userprofile2[7];
        pho3 = userprofile2[8];
        ema1 = userprofile2[9];
        ema2 = userprofile2[10];
        hei1 = userprofile2[11];
        wei1 = userprofile2[12];
        team1 = userprofile2[13];
        uri = userprofile2[17];
        Intent intent = getIntent();
        String id = intent.getStringExtra("id");
        String birth = intent.getStringExtra("birth");
        String height = intent.getStringExtra("height");
        String weight = intent.getStringExtra("weight");
        String team = intent.getStringExtra("team");



        TextView textView = (TextView) findViewById(R.id.myinfo_name);
        TextView textView1 = (TextView) findViewById(R.id.myinfo_birth);
        TextView textView2 = (TextView) findViewById(R.id.aab);
        TextView textView3 = (TextView) findViewById(R.id.aac);
        TextView textView4 = (TextView) findViewById(R.id.myinfo_team);
        textView.setText(na1);
        textView1.setText(bri1);
        textView2.setText(hei1);
        textView3.setText(wei1);
        textView4.setText(team1);

        imageButton = (ImageButton) findViewById(R.id.susu);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Myinfomation.this, sign_up2.class);
                startActivityForResult(intent, REQUEST_CODE1);
            }
        });

//        Intent intent = getIntent();

//        String inf = intent.getExtras().getString("name");
//        nameview= (TextView)findViewById(R.id.myinfo_name);
//        nameview.setText(inf);

////        final String id1 = intent.getStringExtra("id");
////        final String name1 = intent.getStringExtra("name");
//        tv=findViewById(R.id.myinfo_name);
//        tv.setText(name1);


//        Toast.makeText(Myinfomation.this, "토스트", Toast.LENGTH_SHORT).show();


        imageView = findViewById(R.id.myinfo_image);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(android.content.Intent.ACTION_SEND);
//                intent.setType("image/*");
//                intent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION | Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
//                intent.putExtra(Intent.EXTRA_STREAM, FileProvider.getUriForFile(Myinfomation.this, BuildConfig.APPLICATION_ID, new File(filePath)));
//                Intent chooser = Intent.createChooser(intent, Myinfomation.this.getString(R.string.share));
//                Myinfomation.this.startActivity(chooser);


                Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
//                intent.setAction(Intent.ACTION_GET_CONTENT);

                intent.addFlags(Intent.FLAG_GRANT_PERSISTABLE_URI_PERMISSION);
                intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                intent.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
//                intent.addFlags(Intent.FLAG_GRANT_PREFIX_URI_PERMISSION); //useful?

                intent.setType("image/*");

                startActivityForResult(intent, REQUEST_CODE);


            }
        });


        Glide.with(this)
                .load(Uri.parse(userprofile2[17]))
                .error(R.drawable.ic_baseline_add_photo_alternate_24)

                .into(imageView);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE1) {
            if (resultCode == RESULT_OK) {
                String aa = data.getExtras().getString("height");
                String ab = data.getExtras().getString("weight");
                String ac = data.getExtras().getString("area1");
                String id = data.getExtras().getString("id");
                TextView textView = (TextView) findViewById(R.id.aab);
                ;
                TextView textView1 = (TextView) findViewById(R.id.aac);
                TextView textView2 = (TextView) findViewById(R.id.myinfo_team);
                TextView textView3 = (TextView) findViewById(R.id.myinfo_name);

                textView3.setText(id);
                textView.setText(aa);
                textView1.setText(ab);
                textView2.setText(ac);
            }


        } else if (requestCode == REQUEST_CODE) {

            if (resultCode == RESULT_OK) {


                try {


//                        Uri selectedImageUri = data.getData();
//                        imageView.setImageURI(selectedImageUri);


                    SharedPreferences sharedPreferences;

                    SharedPreferences.Editor editor;
                    sharedPreferences = getSharedPreferences("UserFile", MODE_PRIVATE);
                    editor = sharedPreferences.edit();
                    String setuser = sharedPreferences.getString("user", "");
                    String[] userprofile = setuser.split("#");
                    String[] userprofile2 = userprofile[user].split("-");
                    userprofile2[17] = data.getData().toString();
                    Glide.with(this)
                            .load(Uri.parse(userprofile2[17]))
                            .into(imageView);
                    String tag = new String();
                    for (int i = 0; i < userprofile2.length; i++) {
                        tag = tag + userprofile2[i] + "-";

                    }
                    Log.i("정보태그", tag);
                    userprofile[user] = tag;

                    Log.i("유저 사진 수정후", userprofile[user]);
                    String h = new String();
                    for (int j = 0; j < userprofile.length; j++) {
                        h = h + userprofile[j] + "#";
                        Log.i("정보태그", userprofile[j]);

                    }
                    Log.i("수정수정", h);

                    editor.putString("user", h);
                    editor.apply();


                } catch (Exception e) {

                }
            } else if (resultCode == RESULT_CANCELED) {
                Toast.makeText(this, "사진 선택 취소", Toast.LENGTH_LONG).show();
            }


        }
    }
}


