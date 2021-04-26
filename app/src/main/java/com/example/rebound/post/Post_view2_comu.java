package com.example.rebound.post;

import android.content.Intent;
import android.content.SharedPreferences;
import android.icu.text.SimpleDateFormat;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.rebound.Login.LoginActivity;
import com.example.rebound.R;
import com.example.rebound.data.Reple_Data;
import com.example.rebound.Adapter.Reply_adapter;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

@RequiresApi(api = Build.VERSION_CODES.N)
public class Post_view2_comu extends AppCompatActivity {
    int REQUSTCODE = 900;
    String title;
    String content;
    String id;
    int i;
    String titletheme;
    String date;
    String uri;

    TextView textViewtitle;
    TextView textViewcontent;
    TextView textname;
    TextView textbirth;
    TextView texttitletheme;
    ImageView post_image;

    EditText reply_et;

    ImageButton commit;

    private ArrayList<Reple_Data> arrayList;
    private Reply_adapter reply_adapter;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.post_view2);
        Intent intent = getIntent();
        recyclerView = findViewById(R.id.reply_rcv);
        recyclerView.setLayoutManager(new LinearLayoutManager(Post_view2_comu.this));
        arrayList = new ArrayList<>();
        reply_adapter = new Reply_adapter(arrayList);
        recyclerView.setAdapter(reply_adapter);

//        title = intent.getStringExtra("title");
//        content = intent.getStringExtra("content");
//        id = intent.getStringExtra("id");
//        titletheme = intent.getStringExtra("title_theme");
//        date = intent.getStringExtra("date");
        int num = intent.getIntExtra("number", 0);
        SharedPreferences sharedPreferences;
        SharedPreferences.Editor editor;
        sharedPreferences = getSharedPreferences("Community_rcv", MODE_PRIVATE);
        String news_list = sharedPreferences.getString("crcv", "");


        try {
            JSONArray jsonArray = new JSONArray(news_list);
//            ArrayList<Post_Data> arrayList1 = new ArrayList<>();

            JSONObject jsonObject = jsonArray.getJSONObject(num);
            title = jsonObject.getString("title");
            content = jsonObject.getString("content");
            titletheme = jsonObject.getString("title_theme");
            date = jsonObject.getString("date");
            id = jsonObject.getString("id");
            uri = jsonObject.getString("uri");
//                String id = jsonObject.getString("id");
            Log.i("뷰2 날짜", date);


        } catch (Exception e) {
            e.printStackTrace();
        }
        String Creply = sharedPreferences.getString(date, "");
        try {
            JSONArray jsonArray = new JSONArray(Creply);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                String reply = jsonObject.getString("reply_text");
                String id = jsonObject.getString("reply_id");
                String date = jsonObject.getString("reply_date");
                Reple_Data reple_data = new Reple_Data(reply, id, date);
                arrayList.add(reple_data);
            }
            reply_adapter.notifyDataSetChanged();
        } catch (Exception e) {
            e.printStackTrace();
        }
//        String[] reply1split = reply.split("#");
//
//
//
//                for (int i = 0; i < reply1split.length; i++) {
//            String[] reply2split = reply1split[i].split("!");
//                    Log.i(String.valueOf(i), reply1split[i]);
//
//
//
//                    if (date.equals(reply2split[0])) {
//
//                try {
//                    JSONArray jsonArray = new JSONArray(reply2split[2]);
//                    for (int k = 0; k < reply1split.length; k++) {
//                        JSONObject jsonObject = jsonArray.getJSONObject(0);
//                        String reply_text = jsonObject.getString("reply_text");
//                        String reply_id = jsonObject.getString("reply_id");
//                        String reply_date = jsonObject.getString("reply_date");
//                        Log.i("스플릿 제이슨", reply_text);
//                        Log.i("스플릿 제이슨2", reply_id);
//
//                        Log.i("스플릿 제이슨3", reply_date);
//
//                        Reple_Data reple_data = new Reple_Data(reply2split[0], reply_text, reply_id, reply_date);
//                        Log.i("댓글 저장후 날짜", reply2split[0]);
//
//                        arrayList.add(reple_data);
//
//                    }
//
//                } catch (Exception e) {
//
//                    e.printStackTrace();
//                }
//
//            } else {
//            }

//        }
        reply_adapter.notifyDataSetChanged();

        //        for(int i = 0; i<reply1split.length; i++){
//            String[] reply2split = reply1split[i].split("!");
//            if(reply2split[0].equals(date)){
//                try { JSONArray jsonArray = new JSONArray(reply2split[2]);
//                JSONObject jsonObject = jsonArray.getJSONObject(0);
//                jsonObject.getString()
//                }catch (Exception e){
//                    e.printStackTrace();
//                }
//            }
//        }


        reply_et = findViewById(R.id.reply_ed);

        commit = findViewById(R.id.imageButton);
        post_image = findViewById(R.id.view_image);

        textViewtitle = findViewById(R.id.post_title);
        textViewcontent = findViewById(R.id.post_content);
        texttitletheme = findViewById(R.id.title_theme);

        textViewtitle.setText(title);
        textViewcontent.setText(content);
        texttitletheme.setText(titletheme);
        if (uri != null) {
            Glide.with(this)
                    .load(Uri.parse(uri))
                    .into(post_image);
        } else {
        }
        commit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SimpleDateFormat dfDate = new SimpleDateFormat("MM-dd/HH:mm", Locale.KOREA);
                String TODAY = dfDate.format(new Date());
                String com;
                com = reply_et.getText().toString();
                Reple_Data reple_data = new Reple_Data(com, LoginActivity.user_id, TODAY);
                Log.i("댓글 날짜", date);
                arrayList.add( reple_data);
                reply_adapter.notifyDataSetChanged();
                reply_et.setText("");
            }
        });


    }

    @Override
    protected void onPause() {
        super.onPause();
        try {
            JSONArray jsonArray = new JSONArray();
            for (int i = 0; i < arrayList.size(); i++) {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("reply_text", arrayList.get(i).getReply());
                jsonObject.put("reply_id", arrayList.get(i).getId());
                jsonObject.put("reply_date", arrayList.get(i).getDate());

                jsonArray.put(jsonObject);
            }

            String Creply = jsonArray.toString();
            SharedPreferences sharedPreferences;
            sharedPreferences = getSharedPreferences("Community_rcv", MODE_PRIVATE);
            SharedPreferences.Editor editor;
            editor = sharedPreferences.edit();
            editor.putString(date, Creply);
            editor.apply();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}



