package com.example.rebound.post;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.PointF;
import android.icu.text.SimpleDateFormat;
import android.location.Address;
import android.location.Geocoder;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.rebound.Login.LoginActivity;
import com.example.rebound.R;
import com.example.rebound.Adapter.Subscription_Adapter;
import com.example.rebound.data.Post_Data2;
import com.naver.maps.geometry.LatLng;
import com.naver.maps.map.CameraUpdate;
import com.naver.maps.map.MapFragment;
import com.naver.maps.map.NaverMap;
import com.naver.maps.map.OnMapReadyCallback;
import com.naver.maps.map.overlay.InfoWindow;
import com.naver.maps.map.overlay.Marker;

import org.json.JSONArray;
import org.json.JSONObject;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class Post_View_Team extends AppCompatActivity implements OnMapReadyCallback {
    private ArrayList<Post_Data2> arrayList;
    private Subscription_Adapter subscription_adapter;
    private RecyclerView recyclerView;
    String dayday;
    String title;
    String content;
    String wrighter;
    String date;
    static String post_id;
    String password;
    String passwordCheck;
    String id;
    String birthday;
    String gender;
    String phone1;
    String phone2;
    String phone3;
    String email1;
    String email2;
    String height;
    String weight;
    String team;
    String titletheme;
    TextView textViewtitle;
    TextView textViewcontent;
    TextView textname;
    TextView textbirth;
    TextView textheight;
    TextView textweight;
    TextView textteam;
    TextView textarea;
    TextView textneedposi;
    TextView textneedpsi2;
    TextView dayday1;

    ImageView imageViewpro;
    String id1, ps1, psc1, na1, bri1, gen, pho1, pho2, pho3, ema1, ema2, hei1, wei1, team1, posi, yt, ss, uri2, area, needposi;
    int user;
    Button sub;
    String uri, uri3;
    Uri photoUri;
    boolean sub_rcv = true;
    double x;
    double y;


    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.post_view);
        Intent intent = getIntent();
        SharedPreferences sharedPreferences;
        sharedPreferences = getSharedPreferences("User_number", MODE_PRIVATE);
        user = sharedPreferences.getInt("user_num", 0);
        Log.i("유저 로그", String.valueOf(user));
        sharedPreferences = getSharedPreferences("UserFile", MODE_PRIVATE);
        String setuser = sharedPreferences.getString("user", "");
        String[] userprofile = setuser.split("#");
        final String[] userprofile2 = userprofile[user].split("-");
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
        posi = userprofile2[15];
        uri = userprofile2[17];


        recyclerView = findViewById(R.id.post_subscription);
        recyclerView.setLayoutManager(new LinearLayoutManager(Post_View_Team.this));
        arrayList = new ArrayList<>();
        subscription_adapter = new Subscription_Adapter(arrayList);
        recyclerView.setAdapter(subscription_adapter);
        int num = intent.getIntExtra("number", 0);
        uri3 = intent.getStringExtra("aoa");
        date = intent.getStringExtra("date");
        x = intent.getDoubleExtra("x", 0);
        y = intent.getDoubleExtra("y", 0);

        FragmentManager fm = getSupportFragmentManager();
        MapFragment mapFragment = (MapFragment) fm.findFragmentById(R.id.map);
        if (mapFragment == null) {
            mapFragment = MapFragment.newInstance();
            fm.beginTransaction().add(R.id.map, mapFragment).commit();
        }

        mapFragment.getMapAsync(this);

        sharedPreferences = getSharedPreferences("Team_rcv", MODE_PRIVATE);
        String news_list = sharedPreferences.getString("trcv", "");
//        try{
//            JSONArray jsonArray =new JSONArray(news_list);
//            JSONObject jsonObject = jsonArray.getJSONObject(num);
//             title = jsonObject.getString("title");
//             content = jsonObject.getString("content");
//             id = jsonObject.getString("id");
//             birthday = jsonObject.getString("brith");
//             height = jsonObject.getString("height");
//             weight = jsonObject.getString("weight");
//             team = jsonObject.getString("team");
//             uri2 = jsonObject.getString("uri");
//            date = jsonObject.getString("date");
//            area = jsonObject.getString("area");
//            needposi = jsonObject.getString("needposi");
//
//
//            Log.i("사진uri 정보", uri2);
////            photoUri = FileProvider.getUriForFile( getApplicationContext(), BuildConfig.APPLICATION_ID + ".fileprovider", new File(uri2));
////            Log.i("사진uri 정보22", String.valueOf(photoUri));
//
//Log.i("키 정보", height);
//
//        }catch(Exception e){
//          e.printStackTrace();
//        }
        String Greply = sharedPreferences.getString(date, "");


        title = intent.getStringExtra("title");
        content = intent.getStringExtra("content");
        id = intent.getStringExtra("id");
        birthday = intent.getStringExtra("birth");
        String height = intent.getStringExtra("height");
        String weight = intent.getStringExtra("weight");
        team = intent.getStringExtra("team");
        uri2 = intent.getStringExtra("aoa");
        post_id = intent.getStringExtra("id");
        area = intent.getStringExtra("area");
        needposi = intent.getStringExtra("needposi");
        String theme = intent.getStringExtra("title_theme");
        int end = intent.getIntExtra("end", 0);
        String end_date = intent.getStringExtra("end_date");
        Log.i("날짜보자", end_date);
        SimpleDateFormat dfDate1 = new SimpleDateFormat("yyyy-MM-dd/HH:mm:ss.SSS", Locale.KOREA);

        SimpleDateFormat dfDate = new SimpleDateFormat("yyyy년 MM월 dd일 HH시 mm분 까지", Locale.KOREA);
        try {
            Date kor = dfDate1.parse(end_date);
            dayday = dfDate.format(kor);
            Log.i("데이데이", dayday);
        } catch (ParseException e) {
            e.printStackTrace();
        }


        imageViewpro = findViewById(R.id.myinfo_image2);
        textViewtitle = findViewById(R.id.post_title);
        textViewcontent = findViewById(R.id.post_content);
        textname = findViewById(R.id.myinfo_name);
        textbirth = findViewById(R.id.myinfo_birth);
        textheight = findViewById(R.id.aab);
        textweight = findViewById(R.id.aac);
        textteam = findViewById(R.id.myinfo_team);
        sub = findViewById(R.id.post_guest_application);
        textarea = findViewById(R.id.post_area2);
        textneedposi = findViewById(R.id.post_position2);
        textneedpsi2 = findViewById(R.id.post_position);
        dayday1 = findViewById(R.id.post_end22);

        textViewtitle.setText(title);
        textViewcontent.setText(content);
        textname.setText(id);
        textbirth.setText(birthday);
        textheight.setText(height);
        textweight.setText(weight);
        textteam.setText(team);
        textarea.setText(area);
        textneedposi.setText(needposi);
        dayday1.setText(dayday);
        if (theme.equals("[갑니다]")) {
            textneedpsi2.setText("주 포지션");
        }else if (theme.equals("[팀 구함]")){
            textneedpsi2.setText("주 포지션");
        }else {
            textneedpsi2.setText("필요 포지션");
        }


        if (end == 1) {
            sub.setVisibility(View.INVISIBLE);
        } else if (theme.equals("[갑니다]")) {
            sub.setText("게스트 구인 신청");
        } else {

        }
        Glide.with(Post_View_Team.this)
                .load(Uri.parse(uri2))

                .into(imageViewpro);
        if (Greply.length() == 0) {

        } else {
            try {
                JSONArray jsonArray = new JSONArray(Greply);
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    String id = jsonObject.getString("sub_id");
                    String birth = jsonObject.getString("sub_birth");
                    String height3 = jsonObject.getString("sub_height");
                    String weight3 = jsonObject.getString("sub_weight");
                    String posi1 = jsonObject.getString("sub_posi");
                    String uri = jsonObject.getString("sub_uri");
                    String re = jsonObject.getString("sub_reply");
                    Post_Data2 post_data2 = new Post_Data2("", "", "", "", id, "", "", "", birth, "", "", "", "", "", "", height3, weight3, "", "", posi1, "", uri, re);
                    if(re.equals("1")){
                        sub.setVisibility(View.GONE);
                    }else {

                    }

                    arrayList.add(post_data2);
                }
                subscription_adapter.notifyDataSetChanged();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        sub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (id.equals(LoginActivity.user_id)) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                    builder.setTitle("신청 불가");
                    builder.setMessage("게시자는 신청 할 수 없습니다.");
                    builder.setNegativeButton("확인", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    });

                    builder.show();
                } else {

                    for (int i = 0; i < arrayList.size(); i++) {
                        if (arrayList.get(i).getId().equals(LoginActivity.user_id)) {
                            sub_rcv = false;
                            break;
                        } else if (!arrayList.get(i).getId().equals(LoginActivity.user_id)) {
                            sub_rcv = true;
                        } else if (arrayList.size() <= 0) {
                            sub_rcv = true;
                        }

                    }

                    if (sub_rcv == false) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                        builder.setTitle("신청 중복");
                        builder.setMessage("이미 신청되어있습니다.");
                        builder.setNegativeButton("확인", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        });

                        builder.show();
                    } else if (sub_rcv == true) {
                        Post_Data2 post_data2 = new Post_Data2("", "", "", "", id1, "", "", "", bri1, "", pho1, pho2, pho3, ema1, ema2, hei1, wei1, team, "", posi, "", uri, "0");
                        arrayList.add(post_data2);
                        subscription_adapter.notifyDataSetChanged();
                    }


                }
            }
        });


    }

//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//    }

    @Override
    protected void onPause() {
        super.onPause();
        try {
            JSONArray jsonArray = new JSONArray();
            for (int i = 0; i < arrayList.size(); i++) {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("sub_id", arrayList.get(i).getId());
                jsonObject.put("sub_birth", arrayList.get(i).getBirthday());
                jsonObject.put("sub_height", arrayList.get(i).getHeight());
                jsonObject.put("sub_weight", arrayList.get(i).getWeight());
                jsonObject.put("sub_posi", arrayList.get(i).getPosition());
                jsonObject.put("sub_uri", arrayList.get(i).getUri());
                jsonObject.put("sub_reply", arrayList.get(i).getReply());


                jsonArray.put(jsonObject);
            }

            String Greply = jsonArray.toString();
            SharedPreferences sharedPreferences;
            sharedPreferences = getSharedPreferences("Team_rcv", MODE_PRIVATE);
            SharedPreferences.Editor editor;
            editor = sharedPreferences.edit();
            editor.putString(date, Greply);
            editor.apply();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    public void onMapReady(@NonNull NaverMap naverMap) {
        Geocoder geocoder = new Geocoder(Post_View_Team.this, Locale.KOREA);
        List<Address> list = null;
        Marker marker = new Marker();


        try {
            list = geocoder.getFromLocation(x, y, 1);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (list != null) {
            if (list.size() == 0) {
                Log.e("좌표주소", "입출력 오류 - 서버에서 주소변환시 에러발생");
            } else {
//                        Log.e("좌표주소", list.get(0).toString());
                Address addr = list.get(0);
                String add = addr.getAddressLine(0);
                InfoWindow infoWindow = new InfoWindow();
                infoWindow.setAdapter(new InfoWindow.DefaultTextAdapter(Post_View_Team.this) {
                    @NonNull
                    @Override
                    public CharSequence getText(@NonNull InfoWindow infoWindow) {
                        return add;
                    }
                });
                marker.setPosition(new LatLng(x, y));

                marker.setMap(naverMap);
                infoWindow.open(marker);
                CameraUpdate cameraUpdate = CameraUpdate.scrollAndZoomTo(new LatLng(x, y), 15);


                naverMap.moveCamera(cameraUpdate);
            }
            naverMap.setOnMapClickListener(new NaverMap.OnMapClickListener() {
                @Override
                public void onMapClick(@NonNull PointF pointF, @NonNull LatLng latLng) {
                    String url = "nmap://place?lat=" + x + "&lng=" + y + "&appname=com.example.rebound";
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                    intent.addCategory(Intent.CATEGORY_BROWSABLE);

                    List<ResolveInfo> list = getPackageManager().queryIntentActivities(intent, PackageManager.MATCH_DEFAULT_ONLY);
                    if (list == null || list.isEmpty()) {
                        Post_View_Team.this.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=com.nhn.android.nmap")));
                    } else {
                        Post_View_Team.this.startActivity(intent);
                    }
                }
            });


        }
    }
}





