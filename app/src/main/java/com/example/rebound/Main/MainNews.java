package com.example.rebound.Main;

import android.content.Intent;
import android.content.SharedPreferences;
import android.icu.text.SimpleDateFormat;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.rebound.Adapter.Post_Adapter_NC;
import com.example.rebound.Myinfomation;
import com.example.rebound.data.Post_Data_NC;
import com.example.rebound.post.Post_news;
import com.example.rebound.R;
import com.example.rebound.Search_Player_side;
import com.example.rebound.Search_Team_side;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

@RequiresApi(api = Build.VERSION_CODES.N)
public class MainNews extends AppCompatActivity {

    public int pos;
    int REQUEST_CODE_ADD = 301;
    int REQUEST_CODE_SUJUNG = 302;
    private ArrayList<Post_Data_NC> arrayList;
    private Post_Adapter_NC post_adapter_nc;
    private RecyclerView recyclerView;
    EditText aa;
    EditText bb;
    RecyclerView.ViewHolder viewHolder;
    String cot;
    String ttx;
    String ttd;
    String tit;
    String edittile;
    String editcontent;
    String editdate;
    String listtitle;
    String listcontent;
    String global_id;


    int listnum;
    int user;
    static int posinum = 0;
    String id1, ps1, psc1, na1, bri1, gen, pho1, pho2, pho3, ema1, ema2, hei1, wei1, team1, area, position, elite, title_theme;
    Post_Data_NC post_data_nc;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_basketball);

        /////////////////////////하단 버튼 정의 + 추가버튼
        ImageButton imageButton1 = (ImageButton) findViewById(R.id.home);
        ImageButton imageButton2 = (ImageButton) findViewById(R.id.guest);
        ImageButton imageButton3 = (ImageButton) findViewById(R.id.team);
        ImageButton imageButton4 = (ImageButton) findViewById(R.id.news);
        ImageButton imageButton5 = (ImageButton) findViewById(R.id.comunity);
        ImageButton imageButton6 = (ImageButton) findViewById(R.id.button99);


        SharedPreferences sharedPreferences;
        SharedPreferences.Editor editor;
        sharedPreferences = getSharedPreferences("News_rcv", MODE_PRIVATE);
        String news_list = sharedPreferences.getString("nrcv", "");
        Log.i("news_list", news_list);


        //////////////////리사이클러뷰 어댑터 어레이리스트 생성
        recyclerView = findViewById(R.id.news_rv);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainNews.this));
        arrayList = new ArrayList<>();
        post_adapter_nc = new Post_Adapter_NC(arrayList);
        recyclerView.setAdapter(post_adapter_nc);


        DividerItemDecoration dividerItemDecoration =
                new DividerItemDecoration(recyclerView.getContext(), new LinearLayoutManager(this).getOrientation());
        // 리싸이클러뷰 구분선
        recyclerView.addItemDecoration(dividerItemDecoration);
        try {
            JSONArray jsonArray = new JSONArray(news_list);
//            ArrayList<Post_Data> arrayList1 = new ArrayList<>();
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                String title = jsonObject.getString("title");
                String content = jsonObject.getString("content");
                String titletheme = jsonObject.getString("title_theme");
                String date = jsonObject.getString("date");
                String id = jsonObject.getString("id");
                String uri = jsonObject.getString("uri");
//                String id = jsonObject.getString("id");

                Post_Data_NC post_data_nc = new Post_Data_NC(title, content, id, date, id, na1, bri1, gen, pho1, pho2, pho3, ema1, ema2, hei1, wei1, team1, area, position, titletheme, "", uri);

                arrayList.add(post_data_nc);


            }
            post_adapter_nc.notifyItemChanged(pos);


        } catch (Exception e) {
            e.printStackTrace();
        }
        //////////////////////데이터
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
        user = sharedPreferences.getInt("user_num", 0);
        Log.i("유저 로그", String.valueOf(user));
        sharedPreferences = getSharedPreferences("UserFile", MODE_PRIVATE);
        String setuser = sharedPreferences.getString("user", "");
        String[] userprofile = setuser.split("#");
        String[] userprofile2 = userprofile[user].split("-");
        id1 = userprofile2[0];
        Log.i("유저 아이디", id1);


//        MySwipeHelper swipeHelper = new MySwipeHelper(MainNews.this, recyclerView, 150) {
//            @Override
//            public void instantiatrMyButton(final RecyclerView.ViewHolder viewHolder, List<MyButton> buffer) {
//                buffer.add(new MyButton(MainNews.this,
//                        "Delete",
//                        10,
//                        R.drawable.ic_baseline_delete_24,
//                        Color.parseColor("#EF2828"),
//                        new MyButtonClickListener() {
//                            @Override
//                            public void onClick(int pos) {
//
//                                Toast.makeText(MainNews.this, "글이 삭제 되었습니다.", Toast.LENGTH_SHORT).show();
//                                Log.d("TAG", viewHolder.getAdapterPosition() + "");
//                                arrayList.remove(viewHolder.getAdapterPosition());                // 해당 항목 삭제
//
//                                try {
//                                    JSONArray jsonArray = new JSONArray();
//                                    for (int i = 0; i < arrayList.size(); i++) {
//                                        JSONObject jsonObject = new JSONObject();
//                                        jsonObject.put("title", arrayList.get(i).getTitle());
//                                        jsonObject.put("content", arrayList.get(i).getContent());
//                                        jsonObject.put("title_theme",arrayList.get(i).getTitletheme());
//                                        jsonObject.put("id",arrayList.get(i).getId());
//                                        jsonObject.put("date",ttd);
////                jsonObject.put("id",arrayList.get(i).getID());
//                                        jsonArray.put(jsonObject);
//                                    }
//                                    String newsjson = jsonArray.toString();
//                                    Log.i("제이슨", jsonArray.toString());
//                                    SharedPreferences sharedPreferences;
//                                    SharedPreferences.Editor editor;
//                                    sharedPreferences = getSharedPreferences("News_rcv", MODE_PRIVATE);
//                                    editor = sharedPreferences.edit();
//                                    editor.putString("nrcv", newsjson);
//                                    editor.apply();
//                                    String rctest = sharedPreferences.getString("nrcv", "");
//                                    Log.i("뉴스 리사이클러뷰", rctest);
//                                } catch (JSONException e) {
//                                    e.printStackTrace();
//                                }
//                                post_adapter_nc.notifyItemChanged(viewHolder.getAdapterPosition());
//                            }
//                        }));
//                buffer.add(new MyButton(MainNews.this,
//                        "Update",
//                        10,
//                        R.drawable.ic_edit_edit_24dp,
//                        Color.parseColor("#ffffff"),
//                        new MyButtonClickListener() {
//                            @Override
//                            public void onClick(int pos) {
//
//
//                                edittile = arrayList.get(pos).getTitle().toString();
////                                editcontent = arrayList.get(pos).getContent().toString();
//////                                editdate = arrayList.get(pos).getDate().toString();
////                                Post_Data_NC a = arrayList.get(pos);
////                                Log.i("수정", edittile);
////                                Log.i("수정", editcontent);
//////                                Log.i("수정", editdate);
////                                Intent intent = new Intent(MainNews.this, Post_news.class);
////                                intent.putExtra("titleedit", edittile);
////                                intent.putExtra("contentedit", editcontent);
//////                                intent.putExtra("dateedit", editdate);
////                                startActivityForResult(intent, REQUEST_CODE_SUJUNG);
////
////                                posinum = viewHolder.getAdapterPosition();
//
//
//                                //TODO: 편집할 코드
//                            }
//                        }));
//            }
//        };

        Spinner spinner = (Spinner) findViewById(R.id.spinner7);//send.xml의 스피너 아이디
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.news, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(adapter);




        imageButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainNews.this, MainActivity.class);
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
                finish();

            }
        });

        imageButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainNews.this, MainGuest.class);
//                intent.putExtra("id", id1);
//                intent.putExtra("password", ps1);
//                intent.putExtra("password_check", psc1);
//                intent.putExtra("name", na1);
//                intent.putExtra("brith", bri1);
//                intent.putExtra("phone", pho1);
//                intent.putExtra("phone2", pho2);
//                intent.putExtra("phone3", pho3);
//                intent.putExtra("email", ema1);
//                intent.putExtra("email2", ema2);
//                intent.putExtra("height", hei1);
//                intent.putExtra("weight", wei1);
//                intent.putExtra("team", team1);
                startActivity(intent);
                overridePendingTransition(R.anim.hold, R.anim.hold);
                finish();

            }
        });

        imageButton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainNews.this, MainTeam.class);
//                intent.putExtra("id", id1);
//                intent.putExtra("password", ps1);
//                intent.putExtra("password_check", psc1);
//                intent.putExtra("name", na1);
//                intent.putExtra("brith", bri1);
//                intent.putExtra("phone", pho1);
//                intent.putExtra("phone2", pho2);
//                intent.putExtra("phone3", pho3);
//                intent.putExtra("email", ema1);
//                intent.putExtra("email2", ema2);
//                intent.putExtra("height", hei1);
//                intent.putExtra("weight", wei1);
//                intent.putExtra("team", team1);
                startActivity(intent);
                overridePendingTransition(R.anim.hold, R.anim.hold);
                finish();
            }
        });


        imageButton5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainNews.this, MainComunity.class);
                //                intent.putExtra("id", id1);
//                intent.putExtra("password", ps1);
//                intent.putExtra("password_check", psc1);
//                intent.putExtra("name", na1);
//                intent.putExtra("brith", bri1);
//                intent.putExtra("phone", pho1);
//                intent.putExtra("phone2", pho2);
//                intent.putExtra("phone3", pho3);
//                intent.putExtra("email", ema1);
//                intent.putExtra("email2", ema2);
//                intent.putExtra("height", hei1);
//                intent.putExtra("weight", wei1);
//                intent.putExtra("team", team1);
                startActivity(intent);
                overridePendingTransition(R.anim.hold, R.anim.hold);
                finish();
            }
        });

        imageButton6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainNews.this, Post_news.class);
                //                intent.putExtra("id", id1);
//                intent.putExtra("password", ps1);
//                intent.putExtra("password_check", psc1);
//                intent.putExtra("name", na1);
//                intent.putExtra("brith", bri1);
//                intent.putExtra("phone", pho1);
//                intent.putExtra("phone2", pho2);
//                intent.putExtra("phone3", pho3);
//                intent.putExtra("email", ema1);
//                intent.putExtra("email2", ema2);
//                intent.putExtra("height", hei1);
//                intent.putExtra("weight", wei1);
//                intent.putExtra("team", team1);
                startActivityForResult(intent, REQUEST_CODE_ADD);
            }
        });

        Toolbar toolbar = findViewById(R.id.toolbar_add);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("농구 소식");
//        actionBar.setDisplayShowTitleEnabled(false); // 기존 title 지우기
        actionBar.setDisplayHomeAsUpEnabled(true); // 뒤로가기 버튼 만들기
        actionBar.setHomeAsUpIndicator(R.drawable.ic_baseline_dehaze_24);


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_ADD) {
            if (resultCode == RESULT_OK) {
                SimpleDateFormat dfDate = new SimpleDateFormat("yyyy-MM-dd/HH:mm:ss.SSS", Locale.KOREA);
                String TODAY = dfDate.format(new Date());
                ttx = data.getExtras().getString("title");
                cot = data.getExtras().getString("content");
                tit = data.getExtras().getString("title_theme");
                String uri = data.getExtras().getString("uri");
                Log.i("데이터", "게스트에서 받아왔음");
                Log.i("시간", TODAY);
                Post_Data_NC post_data_nc = new Post_Data_NC(ttx, cot, id1, TODAY, id1, ps1, psc1, na1, bri1, gen, pho1, pho2, pho3, ema1, ema2, hei1, wei1, team1, tit, "", uri);
                arrayList.add(0, post_data_nc);
                post_adapter_nc.notifyDataSetChanged();


                try {
                    JSONArray jsonArray = new JSONArray();
                    for (int i = 0; i < arrayList.size(); i++) {
                        JSONObject jsonObject = new JSONObject();
                        jsonObject.put("title", arrayList.get(i).getTitle());
                        jsonObject.put("content", arrayList.get(i).getContent());
                        jsonObject.put("title_theme", arrayList.get(i).getTitletheme());
                        jsonObject.put("id", arrayList.get(i).getId());
                        jsonObject.put("date", TODAY);
                        jsonObject.put("id", arrayList.get(i).getId());
                        jsonObject.put("uri", arrayList.get(i).getUri());
                        jsonArray.put(jsonObject);
                    }
                    String newsjson = jsonArray.toString();
                    Log.i("제이슨", jsonArray.toString());
                    SharedPreferences sharedPreferences;
                    SharedPreferences.Editor editor;
                    sharedPreferences = getSharedPreferences("News_rcv", MODE_PRIVATE);
                    editor = sharedPreferences.edit();
                    editor.putString("nrcv", newsjson);
                    editor.apply();
                    String rctest = sharedPreferences.getString("nrcv", "");
                    Log.i("뉴스 리사이클러뷰", rctest);
                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        } else if (requestCode == REQUEST_CODE_SUJUNG) {
            if (resultCode == RESULT_OK) {
                ttx = data.getExtras().getString("title");
                cot = data.getExtras().getString("content");
                ttd = data.getExtras().getString("date");
                tit = data.getExtras().getString("title_theme");
                String uri = data.getExtras().getString("uri");
                int num = data.getExtras().getInt("number", 0);
                Log.i("데이터", "게스트에서 받아왔음");

                Post_Data_NC post_data_nc = new Post_Data_NC(ttx, cot, id1, ttd, id1, ps1, psc1, na1, bri1, gen, pho1, pho2, pho3, ema1, ema2, hei1, wei1, team1, tit, "", uri);
//                arrayList.remove(posinum);
                arrayList.set(num, post_data_nc);
                post_adapter_nc.notifyItemChanged(num);
                try {
                    JSONArray jsonArray = new JSONArray();
                    for (int i = 0; i < arrayList.size(); i++) {
                        JSONObject jsonObject = new JSONObject();
                        jsonObject.put("title", arrayList.get(i).getTitle());
                        jsonObject.put("content", arrayList.get(i).getContent());
                        jsonObject.put("title_theme", arrayList.get(i).getTitletheme());
                        jsonObject.put("id", arrayList.get(i).getId());
                        jsonObject.put("date", ttd);
                        jsonObject.put("id", arrayList.get(i).getId());
                        jsonObject.put("uri", arrayList.get(i).getUri());
                        jsonArray.put(jsonObject);
                    }
                    String newsjson = jsonArray.toString();
                    Log.i("제이슨", jsonArray.toString());
                    SharedPreferences sharedPreferences;
                    SharedPreferences.Editor editor;
                    sharedPreferences = getSharedPreferences("News_rcv", MODE_PRIVATE);
                    editor = sharedPreferences.edit();
                    editor.putString("nrcv", newsjson);
                    editor.apply();
                    String rctest = sharedPreferences.getString("nrcv", "");
                    Log.i("뉴스 리사이클러뷰", rctest);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }


    }

//    public boolean onCreateOptionsMenu(Menu menu) {
//        MenuInflater menuInflater = getMenuInflater();
//        menuInflater.inflate(R.menu.side_navi, menu);
//        return true;
//    }

    public boolean onOptionsItemSelected(MenuItem item) {
        //return super.onOptionsItemSelected(item);
        switch (item.getItemId()) {
            case R.id.myinfo_menu:
                Intent intent = new Intent(MainNews.this, Myinfomation.class);
//                intent.putExtra("id", id1);
//                intent.putExtra("password", ps1);
//                intent.putExtra("password_check", psc1);
//                intent.putExtra("name", na1);
//                intent.putExtra("brith", bri1);
//                intent.putExtra("phone", pho1);
//                intent.putExtra("phone2", pho2);
//                intent.putExtra("phone3", pho3);
//                intent.putExtra("email", ema1);
//                intent.putExtra("email2", ema2);
//                intent.putExtra("height", hei1);
//                intent.putExtra("weight", wei1);
//                intent.putExtra("team", team1);
                startActivity(intent);

                return true;
//            case R.id.mypost_side:
//                Intent intent1 = new Intent(MainNews.this, Mypostside.class);
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
                Intent intent2 = new Intent(MainNews.this, Search_Team_side.class);
//                intent2.putExtra("id", id1);
//                intent2.putExtra("password", ps1);
//                intent2.putExtra("password_check", psc1);
//                intent2.putExtra("name", na1);
//                intent2.putExtra("brith", bri1);
//                intent2.putExtra("phone", pho1);
//                intent2.putExtra("phone2", pho2);
//                intent2.putExtra("phone3", pho3);
//                intent2.putExtra("email", ema1);
//                intent2.putExtra("email2", ema2);
//                intent2.putExtra("height", hei1);
//                intent2.putExtra("weight", wei1);
//                intent2.putExtra("team", team1);
                startActivity(intent2);

                return true;
            case R.id.search_player_side:
                Intent intent3 = new Intent(MainNews.this, Search_Player_side.class);
//                intent3.putExtra("id", id1);
//                intent3.putExtra("password", ps1);
//                intent3.putExtra("password_check", psc1);
//                intent3.putExtra("name", na1);
//                intent3.putExtra("brith", bri1);
//                intent3.putExtra("phone", pho1);
//                intent3.putExtra("phone2", pho2);
//                intent3.putExtra("phone3", pho3);
//                intent3.putExtra("email", ema1);
//                intent3.putExtra("email2", ema2);
//                intent3.putExtra("height", hei1);
//                intent3.putExtra("weight", wei1);
//                intent3.putExtra("team", team1);
                startActivity(intent3);

//                return true;
//            case R.id.match_side:
//                Intent intent4 = new Intent(MainNews.this, Match_side.class);
////                intent4.putExtra("id", id1);
////                intent4.putExtra("password", ps1);
////                intent4.putExtra("password_check", psc1);
////                intent4.putExtra("name", na1);
////                intent4.putExtra("brith", bri1);
////                intent4.putExtra("phone", pho1);
////                intent4.putExtra("phone2", pho2);
////                intent4.putExtra("phone3", pho3);
////                intent4.putExtra("email", ema1);
////                intent4.putExtra("email2", ema2);
////                intent4.putExtra("height", hei1);
////                intent4.putExtra("weight", wei1);
////                intent4.putExtra("team", team1);
//                startActivity(intent4);
//                return true;
//            case R.id.shop_side:
//                Intent intent5 = new Intent(MainNews.this, Shop_side.class);
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
                finish();

                return true;

            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
//                Toast.makeText(getApplicationContext(), "나머지 버튼 클릭됨", Toast.LENGTH_LONG).show();
                return super.onOptionsItemSelected(item);

        }
    }



    @Override
    protected void onPause() {
        try {
            JSONArray jsonArray = new JSONArray();
            for (int i = 0; i < arrayList.size(); i++) {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("title", arrayList.get(i).getTitle());
                jsonObject.put("content", arrayList.get(i).getContent());
                jsonObject.put("title_theme", arrayList.get(i).getTitletheme());
                jsonObject.put("id", arrayList.get(i).getId());
                jsonObject.put("date", arrayList.get(i).getDate());
                jsonObject.put("id", arrayList.get(i).getId());
                jsonObject.put("uri", arrayList.get(i).getUri());
                jsonArray.put(jsonObject);
            }
            String newsjson = jsonArray.toString();
            Log.i("제이슨", jsonArray.toString());
            SharedPreferences sharedPreferences;
            SharedPreferences.Editor editor;
            sharedPreferences = getSharedPreferences("News_rcv", MODE_PRIVATE);
            editor = sharedPreferences.edit();
            editor.putString("nrcv", newsjson);
            editor.apply();
            String rctest = sharedPreferences.getString("nrcv", "");
            Log.i("뉴스 리사이클러뷰", rctest);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        super.onPause();

    }

}