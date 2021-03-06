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

import com.example.rebound.Adapter.Post_Adapter_C;
import com.example.rebound.Myinfomation;
import com.example.rebound.data.Post_Data_NC;
import com.example.rebound.post.Post_comunity;
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
public class MainComunity extends AppCompatActivity {
    public int pos;
    int REQUEST_CODE_ADD = 401;
    int REQUEST_CODE_SUJUNG = 302;
    private ArrayList<Post_Data_NC> arrayList;
    private Post_Adapter_C post_adapter_nc;
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
        setContentView(R.layout.activity_comunications);
        ImageButton imageButton1 = (ImageButton) findViewById(R.id.home);
        ImageButton imageButton2 = (ImageButton) findViewById(R.id.guest);
        ImageButton imageButton3 = (ImageButton) findViewById(R.id.team);
        ImageButton imageButton4 = (ImageButton) findViewById(R.id.news);
        ImageButton imageButton5 = (ImageButton) findViewById(R.id.button99);

        SharedPreferences sharedPreferences;
        SharedPreferences.Editor editor;
        sharedPreferences = getSharedPreferences("Community_rcv", MODE_PRIVATE);
        editor = sharedPreferences.edit();
        String community_list = sharedPreferences.getString("crcv", "");
        if (community_list == null) {
            editor.putString("crcv", "null");
            editor.apply();
        }else {}


        recyclerView = findViewById(R.id.comu_rv);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainComunity.this));
        arrayList = new ArrayList<>();
        post_adapter_nc = new Post_Adapter_C(arrayList);
        recyclerView.setAdapter(post_adapter_nc);

        DividerItemDecoration dividerItemDecoration =
                new DividerItemDecoration(recyclerView.getContext(), new LinearLayoutManager(this).getOrientation());
        // ?????????????????? ?????????
        recyclerView.addItemDecoration(dividerItemDecoration);

        try {
            JSONArray jsonArray = new JSONArray(community_list);
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

        sharedPreferences = getSharedPreferences("User_number", MODE_PRIVATE);
        user = sharedPreferences.getInt("user_num", 0);
        Log.i("?????? ??????", String.valueOf(user));
        sharedPreferences = getSharedPreferences("UserFile", MODE_PRIVATE);
        String setuser = sharedPreferences.getString("user", "");
        String[] userprofile = setuser.split("#");
        String[] userprofile2 = userprofile[user].split("-");
        id1 = userprofile2[0];
        Log.i("?????? ?????????", id1);

//        MySwipeHelper swipeHelper = new MySwipeHelper(MainComunity.this, recyclerView, 150) {
//            @Override
//            public void instantiatrMyButton(final RecyclerView.ViewHolder viewHolder, List<MyButton> buffer) {
//                buffer.add(new MyButton(MainComunity.this,
//                        "Delete",
//                        10,
//                        R.drawable.ic_baseline_delete_24,
//                        Color.parseColor("#EF2828"),
//                        new MyButtonClickListener() {
//                            @Override
//                            public void onClick(int pos) {
//
//                                Toast.makeText(MainComunity.this, "Delete click", Toast.LENGTH_SHORT).show();
//                                Log.d("TAG", viewHolder.getAdapterPosition() + "");
//                                arrayList.remove(viewHolder.getAdapterPosition());                // ?????? ?????? ??????
//                                post_adapter_nc.notifyItemRemoved(viewHolder.getAdapterPosition());    // Adapter??? ????????????.
//                            }
//                        }));
//                buffer.add(new MyButton(MainComunity.this,
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
//                                editcontent = arrayList.get(pos).getContent().toString();
//                                editdate = arrayList.get(pos).getDate().toString();
//                                Post_Data_NC a = arrayList.get(pos);
//                                Log.i("??????", edittile);
//                                Log.i("??????", editcontent);
//                                Log.i("??????", editdate);
//                                Intent intent = new Intent(MainComunity.this, Post_comunity.class);
//                                intent.putExtra("titleedit", edittile);
//                                intent.putExtra("contentedit", editcontent);
//                                intent.putExtra("dateedit", editdate);
//                                startActivityForResult(intent, REQUEST_CODE_SUJUNG);
//
//                                posinum = viewHolder.getAdapterPosition();
//
//
//
//
//
//
//
//
//                                //TODO: ????????? ??????
//                            }
//                        }));
//            }
//        };


        Spinner spinner = (Spinner) findViewById(R.id.spinner7);//send.xml??? ????????? ?????????
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.malmuri, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(adapter);


        imageButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainComunity.this, MainActivity.class);
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
                Intent intent = new Intent(MainComunity.this, MainGuest.class);
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
                Intent intent = new Intent(MainComunity.this, MainTeam.class);
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
                Intent intent = new Intent(MainComunity.this, MainNews.class);
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
                Intent intent = new Intent(MainComunity.this, Post_comunity.class);
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
        actionBar.setTitle("????????????");
//        actionBar.setDisplayShowTitleEnabled(false); // ?????? title ?????????
        actionBar.setDisplayHomeAsUpEnabled(true); // ???????????? ?????? ?????????
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
                Log.i("?????????", "??????????????? ????????????");
                Log.i("??????", TODAY);
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
                    Log.i("?????????", jsonArray.toString());
                    SharedPreferences sharedPreferences;
                    SharedPreferences.Editor editor;
                    sharedPreferences = getSharedPreferences("Community_rcv", MODE_PRIVATE);
                    editor = sharedPreferences.edit();
                    editor.putString("crcv", newsjson);
                    editor.apply();
                    String rctest = sharedPreferences.getString("crcv", "");
                    Log.i("?????? ??????????????????", rctest);
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
                Log.i("?????????", "??????????????? ????????????");

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
                    Log.i("?????????", jsonArray.toString());
                    SharedPreferences sharedPreferences;
                    SharedPreferences.Editor editor;
                    sharedPreferences = getSharedPreferences("Community_rcv", MODE_PRIVATE);
                    editor = sharedPreferences.edit();
                    editor.putString("crcv", newsjson);
                    editor.apply();
                    String rctest = sharedPreferences.getString("crcv", "");
                    Log.i("?????? ??????????????????", rctest);
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
                Intent intent = new Intent(MainComunity.this, Myinfomation.class);
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
//                Intent intent1 = new Intent(MainComunity.this, Mypostside.class);
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
                Intent intent2 = new Intent(MainComunity.this, Search_Team_side.class);
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
                Intent intent3 = new Intent(MainComunity.this, Search_Player_side.class);
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
//                Intent intent4 = new Intent(MainComunity.this, Match_side.class);
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
//                Intent intent5 = new Intent(MainComunity.this, Shop_side.class);
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
//                Toast.makeText(getApplicationContext(), "????????? ?????? ?????????", Toast.LENGTH_LONG).show();
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
            Log.i("?????????", jsonArray.toString());
            SharedPreferences sharedPreferences;
            SharedPreferences.Editor editor;
            sharedPreferences = getSharedPreferences("Community_rcv", MODE_PRIVATE);
            editor = sharedPreferences.edit();
            editor.putString("crcv", newsjson);
            editor.apply();
            String rctest = sharedPreferences.getString("crcv", "");
            Log.i("?????? ??????????????????", rctest);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        super.onPause();

    }

}