package com.example.rebound;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.rebound.Adapter.Team_Adapter;
import com.example.rebound.data.Team_data;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class Search_Team_side extends AppCompatActivity {
    private ArrayList<Team_data> arrayList;
    private ArrayList<Team_data> arrayList1;
    private ArrayList<Team_data> arrayList2;
    private ArrayList<Team_data> arrayList3;
    private ArrayList<Team_data> arrayList4;
    private Team_Adapter team_adapter;
    private Team_Adapter team_adapter1;
    private Team_Adapter team_adapter2;
    private Team_Adapter team_adapter3;
    private Team_Adapter team_adapter4;

    private RecyclerView recyclerView;
    private RecyclerView recyclerView1;
    private RecyclerView recyclerView2;
    private RecyclerView recyclerView3;
    private RecyclerView recyclerView4;
    private int REQUESTCODE_ADD = 116;
    public int pos;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_team);
        ImageButton ib = (ImageButton) findViewById(R.id.button1414);
        SharedPreferences sharedPreferences1 = getSharedPreferences("User_number",MODE_PRIVATE);
        int usernum = sharedPreferences1.getInt("user_num",0);
        SharedPreferences sharedPreferences2 = getSharedPreferences("UserFile",MODE_PRIVATE);
        String a = sharedPreferences2.getString("user","");
        String[] users = a.split("#");
        String[] userinfo = users[usernum].split("-");
        String userid = userinfo[0];
        if(userid.equals("admin")){
            ib.setVisibility(View.VISIBLE);
        }else {
            ib.setVisibility(View.GONE);
        }
        recyclerView = findViewById(R.id.stseoul);
        recyclerView1 = findViewById(R.id.gyeonggi);
        recyclerView2 = findViewById(R.id.daejeon);
        recyclerView3 = findViewById(R.id.busan);
        recyclerView4 = findViewById(R.id.gangwon);
        recyclerView.setLayoutManager(new LinearLayoutManager(Search_Team_side.this,LinearLayoutManager.HORIZONTAL,false));
        recyclerView1.setLayoutManager(new LinearLayoutManager(Search_Team_side.this,LinearLayoutManager.HORIZONTAL,false));
        recyclerView2.setLayoutManager(new LinearLayoutManager(Search_Team_side.this,LinearLayoutManager.HORIZONTAL,false));
        recyclerView3.setLayoutManager(new LinearLayoutManager(Search_Team_side.this,LinearLayoutManager.HORIZONTAL,false));
        recyclerView4.setLayoutManager(new LinearLayoutManager(Search_Team_side.this,LinearLayoutManager.HORIZONTAL,false));
        arrayList = new ArrayList<>();
        arrayList1 = new ArrayList<>();
        arrayList2 = new ArrayList<>();
        arrayList3 = new ArrayList<>();
        arrayList4 = new ArrayList<>();
        team_adapter = new Team_Adapter(arrayList);
        team_adapter1 = new Team_Adapter(arrayList1);
        team_adapter2 = new Team_Adapter(arrayList2);
        team_adapter3 = new Team_Adapter(arrayList3);
        team_adapter4 = new Team_Adapter(arrayList4);
        recyclerView.setAdapter(team_adapter);
        recyclerView1.setAdapter(team_adapter1);
        recyclerView2.setAdapter(team_adapter2);
        recyclerView3.setAdapter(team_adapter3);
        recyclerView4.setAdapter(team_adapter4);
        DividerItemDecoration dividerItemDecoration =
                new DividerItemDecoration(recyclerView.getContext(), new LinearLayoutManager(this).getOrientation());
        // 리싸이클러뷰 구분선
        recyclerView.addItemDecoration(dividerItemDecoration);

        DividerItemDecoration dividerItemDecoration1 =
                new DividerItemDecoration(recyclerView1.getContext(), new LinearLayoutManager(this).getOrientation());
        // 리싸이클러뷰 구분선
        recyclerView1.addItemDecoration(dividerItemDecoration1);
        DividerItemDecoration dividerItemDecoration2 =
                new DividerItemDecoration(recyclerView2.getContext(), new LinearLayoutManager(this).getOrientation());
        // 리싸이클러뷰 구분선
        recyclerView1.addItemDecoration(dividerItemDecoration2);
        DividerItemDecoration dividerItemDecoration3 =
                new DividerItemDecoration(recyclerView3.getContext(), new LinearLayoutManager(this).getOrientation());
        // 리싸이클러뷰 구분선
        recyclerView1.addItemDecoration(dividerItemDecoration3);
        DividerItemDecoration dividerItemDecoration4 =
                new DividerItemDecoration(recyclerView4.getContext(), new LinearLayoutManager(this).getOrientation());
        // 리싸이클러뷰 구분선
        recyclerView1.addItemDecoration(dividerItemDecoration4);

        SharedPreferences sharedPreferences;
        SharedPreferences.Editor editor;
        sharedPreferences = getSharedPreferences("Search_team", MODE_PRIVATE);
        editor = sharedPreferences.edit();
        String teamS_list = sharedPreferences.getString("seoul", "");
        String temmGg_list = sharedPreferences.getString("gyeonggi", "");
        String teamdj_list = sharedPreferences.getString("daejeon", "");
        String teambs_list = sharedPreferences.getString("busan", "");
        String teamgw_list = sharedPreferences.getString("gangwon", "");
        Log.i("news_list1", "1"+teamS_list);
        Log.i("news_list2", "2"+temmGg_list);
        Log.i("news_list3", "3"+teamdj_list);
        Log.i("news_list4", "4"+teambs_list);
        Log.i("news_list5", "5"+teamgw_list);


        if (teamS_list == null) {
            editor.putString("seoul", "null");
            editor.apply();
        } else {
        }
        if (temmGg_list == null) {
            editor.putString("gyeonggi", "null");
            editor.apply();
        } else {
        }
        if (teamdj_list == null) {
            editor.putString("daejeon", "null");
            editor.apply();
        } else {
        }
        if (teambs_list == null) {
            editor.putString("busan", "null");
            editor.apply();
        } else {
        }
        if (teamgw_list == null) {
            editor.putString("gangwon", "null");
            editor.apply();
        } else {
        }

        try {
            JSONArray jsonArray = new JSONArray(teamS_list);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                String naem = jsonObject.getString("naem");
                String manager = jsonObject.getString("manager");
                String coch = jsonObject.getString("coch");
                String temabirth = jsonObject.getString("temabirth");
                String area = jsonObject.getString("area");
                String uri = jsonObject.getString("uri");

                Team_data team_data = new Team_data(naem, area, manager, coch, temabirth, uri);

                arrayList.add(team_data);


            }
            team_adapter.notifyDataSetChanged();

        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            JSONArray jsonArray = new JSONArray(temmGg_list);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                String naem = jsonObject.getString("naem");
                String manager = jsonObject.getString("manager");
                String coch = jsonObject.getString("coch");
                String temabirth = jsonObject.getString("temabirth");
                String area = jsonObject.getString("area");
                String uri = jsonObject.getString("uri");

                Team_data team_data = new Team_data(naem, area, manager, coch, temabirth, uri);

                arrayList1.add(team_data);


            }
            team_adapter1.notifyDataSetChanged();

        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            JSONArray jsonArray = new JSONArray(teamdj_list);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                String naem = jsonObject.getString("naem");
                String manager = jsonObject.getString("manager");
                String coch = jsonObject.getString("coch");
                String temabirth = jsonObject.getString("temabirth");
                String area = jsonObject.getString("area");
                String uri = jsonObject.getString("uri");

                Team_data team_data = new Team_data(naem, area, manager, coch, temabirth, uri);

                arrayList2.add(team_data);


            }
            team_adapter2.notifyDataSetChanged();

        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            JSONArray jsonArray = new JSONArray(teambs_list);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                String naem = jsonObject.getString("naem");
                String manager = jsonObject.getString("manager");
                String coch = jsonObject.getString("coch");
                String temabirth = jsonObject.getString("temabirth");
                String area = jsonObject.getString("area");
                String uri = jsonObject.getString("uri");

                Team_data team_data = new Team_data(naem, area, manager, coch, temabirth, uri);

                arrayList3.add(team_data);


            }
            team_adapter3.notifyDataSetChanged();

        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            JSONArray jsonArray = new JSONArray(teamgw_list);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                String naem = jsonObject.getString("naem");
                String manager = jsonObject.getString("manager");
                String coch = jsonObject.getString("coch");
                String temabirth = jsonObject.getString("temabirth");
                String area = jsonObject.getString("area");
                String uri = jsonObject.getString("uri");

                Team_data team_data = new Team_data(naem, area, manager, coch, temabirth, uri);

                arrayList4.add(team_data);


            }
            team_adapter4.notifyDataSetChanged();

        } catch (Exception e) {
            e.printStackTrace();
        }
        Toolbar toolbar = findViewById(R.id.team_info_toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        ib.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Search_Team_side.this, Team_add.class);
                startActivityForResult(intent, REQUESTCODE_ADD);
            }
        });

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUESTCODE_ADD) {
            if (resultCode == RESULT_OK) {
                Intent intent = getIntent();
                String naem = data.getStringExtra("naem");
                String area = data.getStringExtra("area");
                String manager = data.getStringExtra("manager");
                String coch = data.getStringExtra("coch");
                String temabirth = data.getStringExtra("temabirth");
                String uri = data.getStringExtra("uri");

                Team_data team_datan = new Team_data(naem, area, manager, coch, temabirth, uri);
                if (area.equals("서울")) {
                    arrayList.add(team_datan);
                    team_adapter.notifyDataSetChanged();
                    try {
                        JSONArray jsonArray = new JSONArray();
                        for (int i = 0; i < arrayList.size(); i++) {
                            JSONObject jsonObject = new JSONObject();
                            jsonObject.put("naem", arrayList.get(i).getNaem());
                            jsonObject.put("area", arrayList.get(i).getArea());
                            jsonObject.put("manager", arrayList.get(i).getManager());
                            jsonObject.put("coch", arrayList.get(i).getCoch());
                            jsonObject.put("temabirth",arrayList.get(i).getTemabirth());
                            jsonObject.put("uri", arrayList.get(i).getUri());

                            jsonArray.put(jsonObject);
                        }
                        String seouljson = jsonArray.toString();
                        Log.i("제이슨", jsonArray.toString());
                        SharedPreferences sharedPreferences;
                        SharedPreferences.Editor editor;
                        sharedPreferences = getSharedPreferences("Search_team", MODE_PRIVATE);
                        editor = sharedPreferences.edit();
                        editor.putString("seoul", seouljson);
                        editor.apply();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else if (area.equals("경기") || area.equals("인천")) {
                    arrayList1.add(team_datan);
                    team_adapter1.notifyDataSetChanged();
                    try {
                        JSONArray jsonArray = new JSONArray();
                        for (int i = 0; i < arrayList1.size(); i++) {
                            JSONObject jsonObject = new JSONObject();
                            jsonObject.put("naem", arrayList1.get(i).getNaem());
                            jsonObject.put("area", arrayList1.get(i).getArea());
                            jsonObject.put("manager", arrayList1.get(i).getManager());
                            jsonObject.put("coch", arrayList1.get(i).getCoch());
                            jsonObject.put("uri", arrayList1.get(i).getUri());
                            jsonObject.put("temabirth",arrayList1.get(i).getTemabirth());


                            jsonArray.put(jsonObject);
                        }
                        String ggjson = jsonArray.toString();
                        Log.i("제이슨", jsonArray.toString());
                        SharedPreferences sharedPreferences;
                        SharedPreferences.Editor editor;
                        sharedPreferences = getSharedPreferences("Search_team", MODE_PRIVATE);
                        editor = sharedPreferences.edit();
                        editor.putString("gyeonggi", ggjson);
                        editor.apply();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else if (area.equals("충북") || area.equals("충남") || area.equals("대전") || area.equals("광주") || area.equals("전북") || area.equals("전남")) {
                    arrayList2.add(team_datan);
                    team_adapter2.notifyDataSetChanged();
                    try {
                        JSONArray jsonArray = new JSONArray();
                        for (int i = 0; i < arrayList2.size(); i++) {
                            JSONObject jsonObject = new JSONObject();
                            jsonObject.put("naem", arrayList2.get(i).getNaem());
                            jsonObject.put("area", arrayList2.get(i).getArea());
                            jsonObject.put("manager", arrayList2.get(i).getManager());
                            jsonObject.put("coch", arrayList2.get(i).getCoch());
                            jsonObject.put("uri", arrayList2.get(i).getUri());
                            jsonObject.put("temabirth",arrayList2.get(i).getTemabirth());


                            jsonArray.put(jsonObject);
                        }
                        String djjson = jsonArray.toString();
                        Log.i("제이슨", jsonArray.toString());
                        SharedPreferences sharedPreferences;
                        SharedPreferences.Editor editor;
                        sharedPreferences = getSharedPreferences("Search_team", MODE_PRIVATE);
                        editor = sharedPreferences.edit();
                        editor.putString("daejeon", djjson);
                        editor.apply();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else if (area.equals("경북") || area.equals("대구") || area.equals("경남") || area.equals("울산") || area.equals("부산")) {
                    arrayList3.add(team_datan);
                    team_adapter3.notifyDataSetChanged();
                    try {
                        JSONArray jsonArray = new JSONArray();
                        for (int i = 0; i < arrayList3.size(); i++) {
                            JSONObject jsonObject = new JSONObject();
                            jsonObject.put("naem", arrayList3.get(i).getNaem());
                            jsonObject.put("area", arrayList3.get(i).getArea());
                            jsonObject.put("manager", arrayList3.get(i).getManager());
                            jsonObject.put("coch", arrayList3.get(i).getCoch());
                            jsonObject.put("uri", arrayList3.get(i).getUri());
                            jsonObject.put("temabirth",arrayList3.get(i).getTemabirth());


                            jsonArray.put(jsonObject);
                        }
                        String bsjson = jsonArray.toString();
                        Log.i("제이슨", jsonArray.toString());
                        SharedPreferences sharedPreferences;
                        SharedPreferences.Editor editor;
                        sharedPreferences = getSharedPreferences("Search_team", MODE_PRIVATE);
                        editor = sharedPreferences.edit();
                        editor.putString("busan", bsjson);
                        editor.apply();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else if (area.equals("강원") || area.equals("제주")) {
                    arrayList4.add(team_datan);
                    team_adapter4.notifyDataSetChanged();
                    try {
                        JSONArray jsonArray = new JSONArray();
                        for (int i = 0; i < arrayList4.size(); i++) {
                            JSONObject jsonObject = new JSONObject();
                            jsonObject.put("naem", arrayList4.get(i).getNaem());
                            jsonObject.put("area", arrayList4.get(i).getArea());
                            jsonObject.put("manager", arrayList4.get(i).getManager());
                            jsonObject.put("coch", arrayList4.get(i).getCoch());
                            jsonObject.put("uri", arrayList4.get(i).getUri());
                            jsonObject.put("temabirth",arrayList4.get(i).getTemabirth());


                            jsonArray.put(jsonObject);
                        }
                        String gwjson = jsonArray.toString();
                        Log.i("제이슨", jsonArray.toString());
                        SharedPreferences sharedPreferences;
                        SharedPreferences.Editor editor;
                        sharedPreferences = getSharedPreferences("Search_team", MODE_PRIVATE);
                        editor = sharedPreferences.edit();
                        editor.putString("gangwon", gwjson);
                        editor.apply();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                } else {
                }


            }
        }
    }
}
