package com.example.rebound.Main;

import android.Manifest;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.BitmapFactory;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.content.ContextCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.rebound.Adapter.Home_adapter;
import com.example.rebound.Login.LoginActivity;
import com.example.rebound.Myinfomation;
import com.example.rebound.data.Post_Data;
import com.example.rebound.R;
import com.example.rebound.Search_Player_side;
import com.example.rebound.Search_Team_side;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {
    private DrawerLayout mDrawerLayout;
    private Context context = this;
    String id1, ps1, psc1, na1, bri1, gen, pho1, pho2, pho3, ema1, ema2, hei1, wei1, team1, area, position, elite, title_theme;
    int user;
    public static final int NOTIFICATION_ID = 1;
    private NotificationManager mNotificationManager;
    NotificationCompat.Builder builder;
    public static final String NOTIFICATION_CHANNEL_ID = "10001";
    private static final int MY_PERMISSION_STORAGE = 1111;
    public int pos;
    String noti;
    private ArrayList<Post_Data> arrayList;
    private ArrayList<Post_Data> arrayList1;
    private Home_adapter home_adapter;
    private Home_adapter home_adapter1;
    private RecyclerView recyclerView;
    private RecyclerView recyclerView1;


//    Handler handler = new Handler();
//      Runnable runnable = new Runnable() {
//          @Override
//          public void run() {
//             handler.postDelayed(this,1000);
//
//          }
//      };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//         handler.post(runnable);
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

        //        EditText idText = (EditText) findViewById(R.id.sign_up_user_id);
//        EditText passowordText = (EditText) findViewById(R.id.sign_up_password_writer);
//
//        EditText nameText = (EditText) findViewById(R.id.sign_up_user_name_writer);
        checkPermission();
//        ImageButton imageButton1 = (ImageButton) findViewById(R.id.home);
        ImageButton imageButton2 = (ImageButton) findViewById(R.id.guest);
        ImageButton imageButton3 = (ImageButton) findViewById(R.id.team);
        ImageButton imageButton4 = (ImageButton) findViewById(R.id.news);
        ImageButton imageButton5 = (ImageButton) findViewById(R.id.comunity);

        recyclerView = findViewById(R.id.home_guest);
        recyclerView1 = findViewById(R.id.hoem_team);
        recyclerView1.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        arrayList = new ArrayList<>();
        arrayList1 = new ArrayList<>();
        home_adapter1 = new Home_adapter(arrayList1);
        home_adapter = new Home_adapter(arrayList);
        recyclerView.setAdapter(home_adapter);
        recyclerView1.setAdapter(home_adapter1);
        DividerItemDecoration dividerItemDecoration =
                new DividerItemDecoration(recyclerView.getContext(), new LinearLayoutManager(this).getOrientation());
        // 리싸이클러뷰 구분선
        recyclerView.addItemDecoration(dividerItemDecoration);

        DividerItemDecoration dividerItemDecoration1 =
                new DividerItemDecoration(recyclerView1.getContext(), new LinearLayoutManager(this).getOrientation());
        // 리싸이클러뷰 구분선
        recyclerView1.addItemDecoration(dividerItemDecoration);

        SharedPreferences sharedPreferences2;
        SharedPreferences.Editor editor2;
        sharedPreferences2 = getSharedPreferences("Team_rcv", MODE_PRIVATE);
        editor2 = sharedPreferences2.edit();
        String team_list = sharedPreferences2.getString("trcv", "");


        SharedPreferences sharedPreferences1;
        SharedPreferences.Editor editor;
        sharedPreferences1 = getSharedPreferences("Guest_rcv", MODE_PRIVATE);
        editor = sharedPreferences1.edit();
        String news_list = sharedPreferences1.getString("grcv", "");
        Log.i("news_list", news_list);
//        NotificationSomethings("postid", "게스트 알림");

        try {
            JSONArray jsonArray = new JSONArray(news_list);
//            ArrayList<Post_Data> arrayList1 = new ArrayList<>();
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                String title = jsonObject.getString("title");
                String content = jsonObject.getString("content");
                String titletheme = jsonObject.getString("title_theme");
                String id = jsonObject.getString("id");
                String date = jsonObject.getString("date");
                String pass = jsonObject.getString("password");
                String passch = jsonObject.getString("password_check");
                String name = jsonObject.getString("name");
                String birth = jsonObject.getString("brith");
                String phon1 = jsonObject.getString("phone");
                String phon2 = jsonObject.getString("phone2");
                String phon3 = jsonObject.getString("phone3");
                String email = jsonObject.getString("email");
                String email2 = jsonObject.getString("email2");
                String height = jsonObject.getString("height");
                String weight = jsonObject.getString("weight");
                String team = jsonObject.getString("team");
                String uri2 = jsonObject.getString("uri");
                String area = jsonObject.getString("area");
                String needpois1 = jsonObject.getString("needposi");
                double x = jsonObject.getDouble("x");
                double y = jsonObject.getDouble("y");
                String end_date = jsonObject.getString("end_date");


//                String id = jsonObject.getString("id");

                Post_Data post_data = new Post_Data(title, content, id, date, id, pass, passch, name, birth, gen, phon1, phon2, phon3, email, email2, height, weight, team, titletheme, needpois1, area, uri2, "", x, y, end_date, 0);

                arrayList.add(post_data);


            }
            home_adapter.notifyItemChanged(pos);

        } catch (Exception e) {
            e.printStackTrace();
        }


        try {
            JSONArray jsonArray = new JSONArray(team_list);
//            ArrayList<Post_Data> arrayList1 = new ArrayList<>();
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                String title = jsonObject.getString("title");
                String content = jsonObject.getString("content");
                String titletheme = jsonObject.getString("title_theme");
                String id = jsonObject.getString("id");
                String date = jsonObject.getString("date");
                String pass = jsonObject.getString("password");
                String passch = jsonObject.getString("password_check");
                String name = jsonObject.getString("name");
                String birth = jsonObject.getString("brith");
                String phon1 = jsonObject.getString("phone");
                String phon2 = jsonObject.getString("phone2");
                String phon3 = jsonObject.getString("phone3");
                String email = jsonObject.getString("email");
                String email2 = jsonObject.getString("email2");
                String height = jsonObject.getString("height");
                String weight = jsonObject.getString("weight");
                String team = jsonObject.getString("team");
                String uri2 = jsonObject.getString("uri");
                String area = jsonObject.getString("area");
                String needpois1 = jsonObject.getString("needposi");
                double x = jsonObject.getDouble("x");
                double y = jsonObject.getDouble("y");
                String end_date = jsonObject.getString("end_date");




                Post_Data post_data = new Post_Data(title, content, id, date, id, pass, passch, name, birth, gen, phon1, phon2, phon3, email, email2, height, weight, team, titletheme, needpois1, area, uri2, "", x, y, end_date, 0);

                arrayList1.add(post_data);


            }
            home_adapter1.notifyItemChanged(pos);

        } catch (Exception e) {
            e.printStackTrace();
        }


        noti = sharedPreferences1.getString("notify", "");
        Log.i("노티 로드", noti);

        if (noti.length() != 0) {
            try {
                JSONArray jsonArray = new JSONArray(noti);
                JSONArray jsonArray1 = new JSONArray();
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);

                    String id = jsonObject.getString("id");
                    String postid = jsonObject.getString("postid");

                    if (id.equals(LoginActivity.user_id)) {


                        NotificationSomethings(postid, "게스트 알림");
                        jsonArray.getJSONObject(i).remove("id");
                        jsonArray.getJSONObject(i).remove("postid");
                    } else {

                        JSONObject jsonObject1 = new JSONObject();
                        jsonObject1.put("id", id);
                        jsonObject1.put("postid", postid);
                        jsonArray1.put(jsonObject1);
                    }


                }
                String nono = jsonArray1.toString();
                Log.i("노티 끝난후", nono);

                editor.putString("notify", nono);
                editor.apply();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
        }
        ;


//        PermissionListener permissionlistener = new PermissionListener() {
//            @Override
//            public void onPermissionGranted() {
//                Toast.makeText(MainActivity.this, "Permission Granted", Toast.LENGTH_SHORT).show();
//            }
//
//            @Override
//            public void onPermissionDenied(List<String> deniedPermissions) {
//                Toast.makeText(MainActivity.this, "Permission Denied\n" + deniedPermissions.toString(), Toast.LENGTH_SHORT)
//                        .show();
//            }
//
//
//        };
//
//
//        TedPermission.with(this)
//                .setPermissionListener(permissionlistener)
//                .setRationaleTitle(R.string.rationale_title)
//                .setRationaleMessage(R.string.rationale_message)
//                .setDeniedTitle("Permission denied")
//                .setDeniedMessage(
//                        "If you reject permission,you can not use this service\n\nPlease turn on permissions at [Setting] > [Permission]")
//                .setGotoSettingButtonText("bla bla")
//                .setPermissions(Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.ACCESS_FINE_LOCATION)
//                .check();


        SharedPreferences sharedPreferences = getSharedPreferences("User_number", MODE_PRIVATE);
        user = sharedPreferences.getInt("user_num", 0);
        Log.i("유저 로그", String.valueOf(user));
        sharedPreferences = getSharedPreferences("UserFile", MODE_PRIVATE);
        String setuser = sharedPreferences.getString("user", "");


        setuser.split("#");


        imageButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, MainGuest.class);
//                intent.putExtra("id",id1);
//                intent.putExtra("password",ps1);
//                intent.putExtra("password_check",psc1);
//                intent.putExtra("name",na1);
//                intent.putExtra("brith",bri1);
//                intent.putExtra("phone",pho1);
//                intent.putExtra("phone2",pho2);
//                intent.putExtra("phone3",pho3);
//                intent.putExtra("email",ema1);
//                intent.putExtra("email2",ema2);
//                intent.putExtra("height",hei1);
//                intent.putExtra("weight",wei1);
//                intent.putExtra("team",team1);
                startActivity(intent);
                overridePendingTransition(R.anim.hold, R.anim.hold);


            }
        });

        imageButton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, MainTeam.class);
//                intent.putExtra("id",id1);
//                intent.putExtra("password",ps1);
//                intent.putExtra("password_check",psc1);
//                intent.putExtra("name",na1);
//                intent.putExtra("brith",bri1);
//                intent.putExtra("phone",pho1);
//                intent.putExtra("phone2",pho2);
//                intent.putExtra("phone3",pho3);
//                intent.putExtra("email",ema1);
//                intent.putExtra("email2",ema2);
//                intent.putExtra("height",hei1);
//                intent.putExtra("weight",wei1);
//                intent.putExtra("team",team1);
                startActivity(intent);
                overridePendingTransition(R.anim.hold, R.anim.hold);

            }
        });

        imageButton4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, MainNews.class);
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
                overridePendingTransition(R.anim.hold, R.anim.hold);

            }
        });

        imageButton5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, MainComunity.class);
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
                overridePendingTransition(R.anim.hold, R.anim.hold);

            }
        });

        Toolbar toolbar = findViewById(R.id.toolbar_add);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("메인");
//        actionBar.setDisplayShowTitleEnabled(false); // 기존 title 지우기
        actionBar.setDisplayHomeAsUpEnabled(true); // 뒤로가기 버튼 만들기
        actionBar.setHomeAsUpIndicator(R.drawable.ic_baseline_dehaze_24);


    }

//    Intent intent = getIntent();
//    final String id1 = intent.getStringExtra("id");
//    final String name1 = intent.getStringExtra("name");

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.side_navi, menu);
        MenuItem team = menu.findItem(R.id.search_team_side);

        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        //return super.onOptionsItemSelected(item);

        switch (item.getItemId()) {
            case R.id.myinfo_menu:
                Intent intent = new Intent(MainActivity.this, Myinfomation.class);
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

                return true;
//            case R.id.mypost_side:
//                Intent intent1 = new Intent(MainActivity.this, Mypostside.class);
//                intent1.putExtra("id",id1);
//                intent1.putExtra("password",ps1);
//                intent1.putExtra("password_check",psc1);
//                intent1.putExtra("name",na1);
//                intent1.putExtra("brith",bri1);
//                intent1.putExtra("phone",pho1);
//                intent1.putExtra("phone2",pho2);
//                intent1.putExtra("phone3",pho3);
//                intent1.putExtra("email",ema1);
//                intent1.putExtra("email2",ema2);
//                intent1.putExtra("height",hei1);
//                intent1.putExtra("weight",wei1);
//                intent1.putExtra("team",team1);
//                startActivity(intent1);
//
//                return true;
            case R.id.search_team_side:
                Intent intent2 = new Intent(MainActivity.this, Search_Team_side.class);
                intent2.putExtra("id", id1);
                intent2.putExtra("password", ps1);
                intent2.putExtra("password_check", psc1);
                intent2.putExtra("name", na1);
                intent2.putExtra("brith", bri1);
                intent2.putExtra("phone", pho1);
                intent2.putExtra("phone2", pho2);
                intent2.putExtra("phone3", pho3);
                intent2.putExtra("email", ema1);
                intent2.putExtra("email2", ema2);
                intent2.putExtra("height", hei1);
                intent2.putExtra("weight", wei1);
                intent2.putExtra("team", team1);
                startActivity(intent2);

                return true;
            case R.id.search_player_side:
                Intent intent3 = new Intent(MainActivity.this, Search_Player_side.class);
                intent3.putExtra("id", id1);
                intent3.putExtra("password", ps1);
                intent3.putExtra("password_check", psc1);
                intent3.putExtra("name", na1);
                intent3.putExtra("brith", bri1);
                intent3.putExtra("phone", pho1);
                intent3.putExtra("phone2", pho2);
                intent3.putExtra("phone3", pho3);
                intent3.putExtra("email", ema1);
                intent3.putExtra("email2", ema2);
                intent3.putExtra("height", hei1);
                intent3.putExtra("weight", wei1);
                intent3.putExtra("team", team1);
                startActivity(intent3);

                return true;
//            case R.id.match_side:
//                Intent intent4 = new Intent(MainActivity.this, Match_side.class);
//                intent4.putExtra("id", id1);
//                intent4.putExtra("password", ps1);
//                intent4.putExtra("password_check", psc1);
//                intent4.putExtra("name", na1);
//                intent4.putExtra("brith", bri1);
//                intent4.putExtra("phone", pho1);
//                intent4.putExtra("phone2", pho2);
//                intent4.putExtra("phone3", pho3);
//                intent4.putExtra("email", ema1);
//                intent4.putExtra("email2", ema2);
//                intent4.putExtra("height", hei1);
//                intent4.putExtra("weight", wei1);
//                intent4.putExtra("team", team1);
//                startActivity(intent4);
//                return true;
//            case R.id.shop_side:
//                Intent intent5 = new Intent(MainActivity.this, Shop_side.class);
//                intent5.putExtra("id",id1);
//                intent5.putExtra("password",ps1);
//                intent5.putExtra("password_check",psc1);
//                intent5.putExtra("name",na1);
//                intent5.putExtra("brith",bri1);
//                intent5.putExtra("phone",pho1);
//                intent5.putExtra("phone2",pho2);
//                intent5.putExtra("phone3",pho3);
//                intent5.putExtra("email",ema1);
//                intent5.putExtra("email2",ema2);
//                intent5.putExtra("height",hei1);
//                intent5.putExtra("weight",wei1);
//                intent5.putExtra("team",team1);
//                startActivity(intent5);
//                return true;
            case R.id.logout_side:
//                SharedPreferences sharedPreferences;
//                SharedPreferences.Editor editor;
//                sharedPreferences = getSharedPreferences("UserFile", MODE_PRIVATE);
//                String auto = sharedPreferences.getString("auto_login", "");
//                String[] autosplit = auto.split("-");
//                autosplit[2] = "false";
//                String autoplus = autosplit[0] + "-" + autosplit[1] + "-" + autosplit[2] + "-";
//                editor = sharedPreferences.edit();
//                editor.putString("auto_login", autoplus);
//                editor.apply();
                Intent intent1 = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent1);
                finish();

                return true;

            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
//                Toast.makeText(getApplicationContext(), "나머지 버튼 클릭됨", Toast.LENGTH_LONG).show();
                return super.onOptionsItemSelected(item);

        }
    }

    private void checkPermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            // 다시 보지 않기 버튼을 만드려면 이 부분에 바로 요청을 하도록 하면 됨 (아래 else{..} 부분 제거)
            // ActivityCompat.requestPermissions((Activity)mContext, new String[]{Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE}, MY_PERMISSION_CAMERA);

            // 처음 호출시엔 if()안의 부분은 false로 리턴 됨 -> else{..}의 요청으로 넘어감
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                new AlertDialog.Builder(this)
                        .setTitle("알림")
                        .setMessage("저장소 권한이 거부되었습니다. 사용을 원하시면 설정에서 해당 권한을 직접 허용하셔야 합니다.")
                        .setNeutralButton("설정", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                                intent.setData(Uri.parse("package:" + getPackageName()));
                                startActivity(intent);
                            }
                        })
                        .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                finish();
                            }
                        })
                        .setCancelable(false)
                        .create()
                        .show();
            } else {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE}, MY_PERMISSION_STORAGE);
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSION_STORAGE:
                for (int i = 0; i < grantResults.length; i++) {
                    // grantResults[] : 허용된 권한은 0, 거부한 권한은 -1
                    if (grantResults[i] < 0) {
                        Toast.makeText(MainActivity.this, "해당 권한을 활성화 하셔야 합니다.", Toast.LENGTH_SHORT).show();
                        return;
                    }
                }
                // 허용했다면 이 부분에서..

                break;
        }
    }

    public void NotificationSomethings(String a, String b) {


        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        Intent notificationIntent = new Intent();

        notificationIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, (int) (System.currentTimeMillis() / 1000), notificationIntent, PendingIntent.FLAG_ONE_SHOT);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, NOTIFICATION_CHANNEL_ID)
                .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.basketball_appicon)) //BitMap 이미지 요구
                .setContentTitle(b)
                .setContentText(a + "님의 " + "게스트 초대를 선택 받으셧습니다.")//내용
                // 더 많은 내용이라서 일부만 보여줘야 하는 경우 아래 주석을 제거하면 setContentText에 있는 문자열 대신 아래 문자열을 보여줌
                //.setStyle(new NotificationCompat.BigTextStyle().bigText("더 많은 내용을 보여줘야 하는 경우..."))
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setContentIntent(pendingIntent) // 사용자가 노티피케이션을 탭시 ResultActivity로 이동하도록 설정
                .setAutoCancel(true);

        //OREO API 26 이상에서는 채널 필요
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            builder.setSmallIcon(R.drawable.basketball_appicon); //mipmap 사용시 Oreo 이상에서 시스템 UI 에러남
            CharSequence channelName = "노티페케이션 채널";
            String description = "오레오 이상을 위한 것임";
            int importance = NotificationManager.IMPORTANCE_HIGH;

            NotificationChannel channel = new NotificationChannel(NOTIFICATION_CHANNEL_ID, channelName, importance);
            channel.setDescription(description);

            // 노티피케이션 채널을 시스템에 등록
            assert notificationManager != null;
            notificationManager.createNotificationChannel(channel);

        } else
            builder.setSmallIcon(R.mipmap.ic_launcher32313312312_foreground); // Oreo 이하에서 mipmap 사용하지 않으면 Couldn't create icon: StatusBarIcon 에러남

        assert notificationManager != null;
        notificationManager.notify((int) (System.currentTimeMillis() / 1000), builder.build()); // 고유숫자로 노티피케이션 동작시킴

    }

    private void sendNotification(String a, String b) {

        Intent intent = new Intent();
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        mNotificationManager = (NotificationManager)
                this.getSystemService(Context.NOTIFICATION_SERVICE);

        PendingIntent contentIntent = PendingIntent.getActivity(this, (int) (System.currentTimeMillis() / 1000),
                intent, PendingIntent.FLAG_ONE_SHOT);

        NotificationCompat.BigTextStyle bigTextStyle = new NotificationCompat.BigTextStyle();
        //bigTextStyle.setSummaryText("...");

        Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(this)
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setLargeIcon(BitmapFactory.decodeResource(this.getResources(), R.mipmap.ic_launcher))
                        //.setLargeIcon(R..ic_launcher)
                        .setContentTitle(b)
                        .setStyle(bigTextStyle
                                .bigText(a))
                        .setSound(defaultSoundUri)
                        .setDefaults(Notification.FLAG_NO_CLEAR)
                        .setContentText(a + "님의 " + "게스트 초대를 선택 받으셧습니다.");

        mBuilder.setContentIntent(contentIntent);
        mNotificationManager.notify((int) (System.currentTimeMillis() / 1000), mBuilder.build());
    }

}
//    @Override
//    public void onBackPressed() {
//        super.onBackPressed();
//        SharedPreferences sharedPreferences;
//        SharedPreferences.Editor editor;
//        sharedPreferences = getSharedPreferences("UserFile",MODE_PRIVATE);
//        String auto = sharedPreferences.getString("auto_login","");
//        String[] autosplit = auto.split("-");
//        autosplit[2]="false";
//        String autoplus = autosplit[0]+"-"+autosplit[1]+"-"+autosplit[2]+"-";
//        editor = sharedPreferences.edit();
//        editor.putString("auto_login",autoplus);
//        editor.apply();
//        Intent intent = new Intent(MainActivity.this,LoginActivity.class);
//        startActivity(intent);
//        finish();
//    }

