package com.example.rebound;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.rebound.Adapter.Search_user_Adapter;
import com.example.rebound.data.Post_Data;

import java.util.ArrayList;

public class Search_Player_side extends AppCompatActivity {
    String id1, ps1, psc1, na1, bri1, gen, pho1, pho2, pho3, ema1, ema2, hei1, wei1, team1, area, position, elite, title_theme,uri;
    private ArrayList<Post_Data> arrayList;
    private Search_user_Adapter search_userAdapter;
    private RecyclerView recyclerView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_player);
        recyclerView = findViewById(R.id.search_user);
        recyclerView.setLayoutManager(new LinearLayoutManager(Search_Player_side.this));
        arrayList = new ArrayList<>();
        search_userAdapter = new Search_user_Adapter(arrayList);
        recyclerView.setAdapter(search_userAdapter);

        DividerItemDecoration dividerItemDecoration =
                new DividerItemDecoration(recyclerView.getContext(), new LinearLayoutManager(this).getOrientation());
        // 리싸이클러뷰 구분선
        recyclerView.addItemDecoration(dividerItemDecoration);

        SharedPreferences sharedPreferences;
        sharedPreferences = getSharedPreferences("User_number", MODE_PRIVATE);

        ArrayList<String> user = new ArrayList<>();
        sharedPreferences = getSharedPreferences("UserFile", MODE_PRIVATE);
        String setuser = sharedPreferences.getString("user", "");
        String[] userprofile = setuser.split("#");
        Log.i("쪼개기전",setuser.toString());
        for (int i = 0; i < userprofile.length-1; i++) {
            String[] userprofile2 = userprofile[i].split("-");
//        String    id1 = userprofile2[0];
//            String   ps1 = userprofile2[1];
//            String   psc1 = userprofile2[2];
//            String   na1 = userprofile2[3];
//            String    bri1 = userprofile2[4];
//            String   gen = userprofile2[5];
//            String   pho1 = userprofile2[6];
//            String   pho2 = userprofile2[7];
//            String   pho3 = userprofile2[8];
//            String   ema1 = userprofile2[9];
//            String   ema2 = userprofile2[10];
//            String   hei1 = userprofile2[11];
//            String   wei1 = userprofile2[12];
//            String   team1 = userprofile2[13];
//            String   area = userprofile2[14];
//            String    position = userprofile2[15];
//            String   elite = userprofile2[16];
//            String   uri = userprofile2[17];
            Log.i("유저검색", String.valueOf(i));

            Post_Data post_data = new Post_Data("","","","",userprofile2[0],"","",userprofile2[3],userprofile2[4],userprofile2[5],userprofile2[6],userprofile2[7],userprofile2[8],userprofile2[9],userprofile2[10],userprofile2[11],userprofile2[12],userprofile2[13],userprofile2[16],userprofile2[15],userprofile2[14],userprofile2[17],"",0,0,"",0);
            arrayList.add(post_data);

        }


    }
}
