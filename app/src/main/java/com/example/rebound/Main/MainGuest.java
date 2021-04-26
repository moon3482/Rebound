package com.example.rebound.Main;

import android.content.Intent;
import android.content.SharedPreferences;
import android.icu.text.SimpleDateFormat;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
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

import com.example.rebound.Adapter.Post_adapter_GT;
import com.example.rebound.Myinfomation;
import com.example.rebound.data.Post_Data;
import com.example.rebound.post.Post_Guest;
import com.example.rebound.R;
import com.example.rebound.Search_Player_side;
import com.example.rebound.Search_Team_side;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

@RequiresApi(api = Build.VERSION_CODES.N)
public class MainGuest extends AppCompatActivity implements Post_adapter_GT.OnClickListener {
    public int pos;
    int REQUEST_CODE_ADD = 133;
    int REQUEST_CODE_SUJUNG = 156;
    private ArrayList<Post_Data> arrayList;
    private Post_adapter_GT post_adapter_GT;
    private RecyclerView recyclerView;


    Handler handler = new Handler();
    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            SimpleDateFormat dfDate = new SimpleDateFormat("yyyy-MM-dd/HH:mm:ss.SSS", Locale.KOREA);
            String TODAY = dfDate.format(new Date());
            Log.e("시간",TODAY);
            for (int i = 0; i < arrayList.size(); i++) {
                String end = arrayList.get(i).getEnd_date();
                try {
                    load = dfDate.parse(end);
                    now = dfDate.parse(TODAY);
                    bba = load.compareTo(now);

                } catch (ParseException e) {
                    e.printStackTrace();
                }
                
                if (bba == -1) {
                    arrayList.get(i).setEnd(1);
                    post_adapter_GT.notifyItemChanged(i);


                } else if (bba == 1) {
                    arrayList.get(i).setEnd(0);


                } else {
                }


            }

            handler.postDelayed(this, 5000);
        }
    };
    EditText bb;
    RecyclerView.ViewHolder viewHolder;
    String cot;
    String ttx;
    String ttd;
    String tit;
    int user;
    Date load;
    Date now;
    int bba;
    String edittile;
    String editcontent;
    String editdate;
    int listnum;
    static int posinum = 0;

    String id1, ps1, psc1, na1, bri1, gen, pho1, pho2, pho3, ema1, ema2, hei1, wei1, team1, area, position, elite, title_theme;

    String uri;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guest);

        ImageButton imageButton1 = (ImageButton) findViewById(R.id.home);
        ImageButton imageButton2 = (ImageButton) findViewById(R.id.guest);
        ImageButton imageButton3 = (ImageButton) findViewById(R.id.team);
        ImageButton imageButton4 = (ImageButton) findViewById(R.id.news);
        ImageButton imageButton5 = (ImageButton) findViewById(R.id.comunity);
        ImageButton imageButton6 = (ImageButton) findViewById(R.id.button1414);


        SharedPreferences sharedPreferences;
        SharedPreferences.Editor editor;
        sharedPreferences = getSharedPreferences("Guest_rcv", MODE_PRIVATE);
        editor = sharedPreferences.edit();
        String news_list = sharedPreferences.getString("grcv", "");
        Log.i("news_list", news_list);
        if (news_list == null) {
            editor.putString("grcv", "null");
            editor.apply();
        }else {}
        recyclerView = findViewById(R.id.guest_rv);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainGuest.this));
        arrayList = new ArrayList<>();
        post_adapter_GT = new Post_adapter_GT(arrayList);
        recyclerView.setAdapter(post_adapter_GT);

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
                int end = jsonObject.getInt("end");
                SimpleDateFormat dfDate = new SimpleDateFormat("yyyy-MM-dd/HH:mm:ss.SSS", Locale.KOREA);
                String TODAY = dfDate.format(new Date());
                Date load = dfDate.parse(end_date);
                Date now = dfDate.parse(TODAY);
                int bba = load.compareTo(now);
                if (bba <= 0) {
                    end = 1;
                }


//                String id = jsonObject.getString("id");

                Post_Data post_data = new Post_Data(title, content, id, date, id, pass, passch, name, birth, gen, phon1, phon2, phon3, email, email2, height, weight, team, titletheme, needpois1, area, uri2, "", x, y, end_date, end);

                arrayList.add(post_data);


            }
            post_adapter_GT.notifyItemChanged(pos);

        } catch (Exception e) {
            e.printStackTrace();
        }





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
//        uer

//        post_adapterGT.setOnItemClickListener(new Post_adapter_GT.OnItemClickListener() {
//            @Override
//            public void onItemClick(View view, int pos) {
//               Toast.makeText(MainGuest.this,"누름"+pos,Toast.LENGTH_SHORT).show();
//            }
//        }) ;


        ///////스와이프 버튼//////////////////////
//        MySwipeHelper swipeHelper = new MySwipeHelper(MainGuest.this, recyclerView, 150) {
//            @Override
//            public void instantiatrMyButton(final RecyclerView.ViewHolder viewHolder, List<MyButton> buffer) {
//                buffer.add(new MyButton(MainGuest.this,
//                        "Delete",
//                        10,
//                        R.drawable.ic_baseline_delete_24,
//                        Color.parseColor("#EF2828"),
//                        new MyButtonClickListener() {
//                            @Override
//                            public void onClick(int pos) {
//
//                                Toast.makeText(MainGuest.this, "삭제를 완료했습니다.", Toast.LENGTH_SHORT).show();
//                                Log.d("TAG", viewHolder.getAdapterPosition() + "");
//                                arrayList.remove(viewHolder.getAdapterPosition());                // 해당 항목 삭제
//                                post_adapter_GT.notifyItemRemoved(viewHolder.getAdapterPosition());    // Adapter에 알려주기.
//                            }
//                        }));
//                buffer.add(new MyButton(MainGuest.this,
//                        "Update",
//                        10,
//                        R.drawable.ic_edit_edit_24dp,
//                        Color.parseColor("#ffffff"),
//                        new MyButtonClickListener() {
//                            @Override
//                            public void onClick(int pos) {
//
//
//                                edittile = arrayList.get(pos).getTitle();
//                                editcontent = arrayList.get(pos).getContent();
//                                editdate = arrayList.get(pos).getDate();
//                                Post_Data a = arrayList.get(pos);
//                                Log.i("수정", edittile);
//                                Log.i("수정", editcontent);
//                                Log.i("수정", editdate);
//                                Intent intent = new Intent(MainGuest.this, Post_Guest.class);
//                                intent.putExtra("titleedit", edittile);
//                                intent.putExtra("contentedit", editcontent);
//                                intent.putExtra("dateedit", editdate);
//                                startActivityForResult(intent, REQUEST_CODE_SUJUNG);
//                                posinum = viewHolder.getAdapterPosition();
//
//                                post_adapter_GT.notifyItemChanged(pos);
//
//
//                                //TODO: 편집할 코드
//                            }
//                        }));
//            }
//        };

        sharedPreferences = getSharedPreferences("User_number", MODE_PRIVATE);
        user = sharedPreferences.getInt("user_num", 0);
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

        Log.i("게스트 uri", uri.toString());

        Log.i("유저 아이디", id1);

        Spinner spinner = (Spinner) findViewById(R.id.spinner7);//send.xml의 스피너 아이디
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.guest, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        Toast.makeText(this, postgettitle, Toast.LENGTH_SHORT).show();
        spinner.setAdapter(adapter);


        imageButton1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainGuest.this, MainActivity.class);
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


        imageButton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainGuest.this, MainTeam.class);
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

        imageButton4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainGuest.this, MainNews.class);
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

        imageButton5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainGuest.this, MainComunity.class);
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

        imageButton6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainGuest.this, Post_Guest.class);
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
                startActivityForResult(intent, REQUEST_CODE_ADD);

            }
        });

        Toolbar toolbar = findViewById(R.id.toolbar_add);

        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("게스트 구인");
//        actionBar.setDisplayShowTitleEnabled(false); // 기존 title 지우기
//        actionBar.setDisplayHomeAsUpEnabled(true); // 뒤로가기 버튼 만들기
//        actionBar.setHomeAsUpIndicator(R.drawable.ic_baseline_dehaze_24);

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
       SharedPreferences sharedPreferences2 = getSharedPreferences("User_number", MODE_PRIVATE);
        user = sharedPreferences2.getInt("user_num", 0);
        Log.i("유저 로그", String.valueOf(user));
        sharedPreferences2 = getSharedPreferences("UserFile", MODE_PRIVATE);
        String setuser = sharedPreferences2.getString("user", "");
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

        if (requestCode == REQUEST_CODE_ADD) {
            if (resultCode == RESULT_OK) {
                SimpleDateFormat dfDate = new SimpleDateFormat("yyyy-MM-dd/HH:mm:ss.SSS", Locale.KOREA);
                String TODAY = dfDate.format(new Date());
                ttx = data.getExtras().getString("title");
                cot = data.getExtras().getString("content");
                String tit = data.getExtras().getString("title_theme");
                String area = data.getStringExtra("area");
                String needposi = data.getStringExtra("needposi");
                double x = data.getDoubleExtra("x", 0);
                double y = data.getDoubleExtra("y", 0);
                String end_date = data.getStringExtra("end_date");


                Post_Data post_data = new Post_Data(ttx, cot, id1, TODAY, id1, ps1, psc1, na1, bri1, gen, pho1, pho2, pho3, ema1, ema2, hei1, wei1, team1, tit, needposi, area, uri, "", x, y, end_date, 0);
                arrayList.add(0, post_data);
//                post_adapter_GT.notifyItemChanged(0);


                try {
                    JSONArray jsonArray = new JSONArray();
                    for (int i = 0; i < arrayList.size(); i++) {
                        JSONObject jsonObject = new JSONObject();
                        jsonObject.put("title", arrayList.get(i).getTitle());
                        jsonObject.put("content", arrayList.get(i).getContent());
                        jsonObject.put("title_theme", arrayList.get(i).getTitletheme());
                        jsonObject.put("id", arrayList.get(i).getId());
                        jsonObject.put("date", arrayList.get(i).getDate());
                        jsonObject.put("password", arrayList.get(i).getPassword());
                        jsonObject.put("password_check", arrayList.get(i).getPasswordCheck());
                        jsonObject.put("name", arrayList.get(i).getName());
                        jsonObject.put("brith", arrayList.get(i).getBirthday());
                        jsonObject.put("phone", arrayList.get(i).getPhone1());
                        jsonObject.put("phone2", arrayList.get(i).getPhone2());
                        jsonObject.put("phone3", arrayList.get(i).getPhone3());
                        jsonObject.put("email", arrayList.get(i).getEmail1());
                        jsonObject.put("email2", arrayList.get(i).getEmail2());
                        jsonObject.put("height", arrayList.get(i).getHeight());
                        jsonObject.put("weight", arrayList.get(i).getWeight());
                        jsonObject.put("team", arrayList.get(i).getTeam());
                        jsonObject.put("uri", arrayList.get(i).getUri());
                        jsonObject.put("area", arrayList.get(i).getArea());
                        jsonObject.put("needposi", arrayList.get(i).getPosition());
                        jsonObject.put("x", arrayList.get(i).getX());
                        jsonObject.put("y", arrayList.get(i).getY());
                        jsonObject.put("end_date", arrayList.get(i).getEnd_date());
                        jsonObject.put("end", arrayList.get(i).getEnd());
                        Log.i("저장 시간", arrayList.get(i).getDate());

                        jsonArray.put(jsonObject);
                    }
                    String newsjson = jsonArray.toString();
                    Log.i("제이슨", jsonArray.toString());
                    SharedPreferences sharedPreferences;
                    SharedPreferences.Editor editor;
                    sharedPreferences = getSharedPreferences("Guest_rcv", MODE_PRIVATE);
                    editor = sharedPreferences.edit();
                    editor.putString("grcv", newsjson);
                    editor.apply();
                    String rctest = sharedPreferences.getString("grcv", "");
                    Log.i("게스트 리사이클러뷰", rctest);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                post_adapter_GT.notifyDataSetChanged();

            }
        } else if (requestCode == REQUEST_CODE_SUJUNG) {
            if (resultCode == RESULT_OK) {
                ttx = data.getExtras().getString("title");
                cot = data.getExtras().getString("content");
                ttd = data.getExtras().getString("date");
                tit = data.getExtras().getString("title_theme");
                int num = data.getExtras().getInt("number");
                String area = data.getStringExtra("area");
                String needposi = data.getStringExtra("needposi");
                double x = data.getDoubleExtra("x", 0);
                double y = data.getDoubleExtra("y", 0);
                String end_date = data.getStringExtra("end_date");

                Log.i("시간데이터", ttd);
                Post_Data post_data = new Post_Data(ttx, cot, id1, ttd, id1, ps1, psc1, na1, bri1, gen, pho1, pho2, pho3, ema1, ema2, hei1, wei1, team1, tit, needposi, area, uri, "", x, y, end_date, 0);
//                arrayList.remove(posinum);
                arrayList.set(num, post_data);

                try {
                    JSONArray jsonArray = new JSONArray();
                    for (int i = 0; i < arrayList.size(); i++) {
                        JSONObject jsonObject = new JSONObject();
                        jsonObject.put("title", arrayList.get(i).getTitle());
                        jsonObject.put("content", arrayList.get(i).getContent());
                        jsonObject.put("title_theme", arrayList.get(i).getTitletheme());
                        jsonObject.put("id", arrayList.get(i).getId());
                        jsonObject.put("date", arrayList.get(i).getDate());
                        jsonObject.put("password", arrayList.get(i).getPassword());
                        jsonObject.put("password_check", arrayList.get(i).getPasswordCheck());
                        jsonObject.put("name", arrayList.get(i).getName());
                        jsonObject.put("brith", arrayList.get(i).getBirthday());
                        jsonObject.put("phone", arrayList.get(i).getPhone1());
                        jsonObject.put("phone2", arrayList.get(i).getPhone2());
                        jsonObject.put("phone3", arrayList.get(i).getPhone3());
                        jsonObject.put("email", arrayList.get(i).getEmail1());
                        jsonObject.put("email2", arrayList.get(i).getEmail2());
                        jsonObject.put("height", arrayList.get(i).getHeight());
                        jsonObject.put("weight", arrayList.get(i).getWeight());
                        jsonObject.put("team", arrayList.get(i).getTeam());
                        jsonObject.put("uri", arrayList.get(i).getUri());
                        jsonObject.put("area", arrayList.get(i).getArea());
                        jsonObject.put("needposi", arrayList.get(i).getPosition());
                        jsonObject.put("x", arrayList.get(i).getX());
                        jsonObject.put("y", arrayList.get(i).getY());
                        jsonObject.put("end_date", arrayList.get(i).getEnd_date());
                        jsonObject.put("end", arrayList.get(i).getEnd());
                        Log.i("저장 시간", arrayList.get(i).getDate());

                        jsonArray.put(jsonObject);
                    }
                    String newsjson = jsonArray.toString();
                    Log.i("제이슨", jsonArray.toString());
                    SharedPreferences sharedPreferences;
                    SharedPreferences.Editor editor;
                    sharedPreferences = getSharedPreferences("Guest_rcv", MODE_PRIVATE);
                    editor = sharedPreferences.edit();
                    editor.putString("grcv", newsjson);
                    editor.apply();
                    String rctest = sharedPreferences.getString("grcv", "");
                    Log.i("게스트 리사이클러뷰", rctest);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                post_adapter_GT.notifyItemChanged(num);
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
        item.setIcon(R.drawable.ic_baseline_dehaze_24);
        switch (item.getItemId()) {
            case R.id.myinfo_menu:
                Intent intent = new Intent(MainGuest.this, Myinfomation.class);
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
//                Intent intent1 = new Intent(MainGuest.this, Mypostside.class);
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

//                return true;
            case R.id.search_team_side:
                Intent intent2 = new Intent(MainGuest.this, Search_Team_side.class);
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
                Intent intent3 = new Intent(MainGuest.this, Search_Player_side.class);
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
//                Intent intent4 = new Intent(MainGuest.this, Match_side.class);
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
//                Intent intent5 = new Intent(MainGuest.this, Shop_side.class);
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
        super.onPause();
        handler.removeCallbacks(runnable);
    }

    @Override
    protected void onStop() {

        super.onStop();
//        try {
//            JSONArray jsonArray = new JSONArray();
//            for (int i = 0; i < arrayList.size(); i++) {
//                JSONObject jsonObject = new JSONObject();
//                jsonObject.put("title", arrayList.get(i).getTitle());
//                jsonObject.put("content", arrayList.get(i).getContent());
//                jsonObject.put("title_theme", arrayList.get(i).getTitletheme());
//                jsonObject.put("id", arrayList.get(i).getId());
//                jsonObject.put("date", arrayList.get(i).getDate());
//                jsonObject.put("password", arrayList.get(i).getPassword());
//                jsonObject.put("password_check", arrayList.get(i).getPasswordCheck());
//                jsonObject.put("name", arrayList.get(i).getName());
//                jsonObject.put("brith", arrayList.get(i).getBirthday());
//                jsonObject.put("phone", arrayList.get(i).getPhone1());
//                jsonObject.put("phone2", arrayList.get(i).getPhone2());
//                jsonObject.put("phone3", arrayList.get(i).getPhone3());
//                jsonObject.put("email", arrayList.get(i).getEmail1());
//                jsonObject.put("email2", arrayList.get(i).getEmail2());
//                jsonObject.put("height", arrayList.get(i).getHeight());
//                jsonObject.put("weight", arrayList.get(i).getWeight());
//                jsonObject.put("team", arrayList.get(i).getTeam());
//                jsonObject.put("uri", arrayList.get(i).getUri());
//                jsonObject.put("area", arrayList.get(i).getArea());
//                jsonObject.put("needposi", arrayList.get(i).getPosition());
//                jsonObject.put("x", arrayList.get(i).getX());
//                jsonObject.put("y", arrayList.get(i).getY());
//                jsonObject.put("end_date", arrayList.get(i).getEnd_date());
//                jsonObject.put("end", arrayList.get(i).getEnd());
//                Log.i("저장 시간", arrayList.get(i).getDate());
//
//                jsonArray.put(jsonObject);
//            }
//            String newsjson = jsonArray.toString();
//            Log.i("제이슨", jsonArray.toString());
//            SharedPreferences sharedPreferences;
//            SharedPreferences.Editor editor;
//            sharedPreferences = getSharedPreferences("Guest_rcv", MODE_PRIVATE);
//            editor = sharedPreferences.edit();
//            editor.putString("grcv", newsjson);
//            editor.apply();
//            String rctest = sharedPreferences.getString("grcv", "");
//            Log.i("게스트 리사이클러뷰", rctest);
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
        handler.removeCallbacks(runnable);
    }

    @Override
    public void onClick(int pos) {

        edittile = arrayList.get(pos).getTitle();
        editcontent = arrayList.get(pos).getContent();
        editdate = arrayList.get(pos).getDate();
        Log.i("뭐지.;;", String.valueOf(arrayList.get(pos)));

    }

    @Override
    protected void onResume() {
        super.onResume();
        handler.post(runnable);
    }

}


