package com.example.rebound.post;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.bumptech.glide.Glide;
import com.example.rebound.Main.MainComunity;
import com.example.rebound.R;

@RequiresApi(api = Build.VERSION_CODES.N)
public class Post_comunity extends AppCompatActivity {

    int REQUEST_CODE = 200;
    EditText title;
    EditText content;
    String date;
    String titleedit;
    String contentedit;
    String dateedit;
    String title_theme;
    String uri;
    TextView add_image;
    ImageView imageView;
    int num;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.post2);
        add_image = findViewById(R.id.add_image);
        imageView = findViewById(R.id.Post_image);

        title = findViewById(R.id.editTextTextPersonName7);
        content = findViewById(R.id.editpost);


        Intent intent = getIntent();
        date = intent.getStringExtra("date");
        titleedit = intent.getStringExtra("titleedit");
        contentedit = intent.getStringExtra("contentedit");
        dateedit = intent.getStringExtra("dateedit");
        num = intent.getIntExtra("number", 0);
        uri = intent.getStringExtra("uri");
        title.setText(titleedit);
        content.setText(contentedit);
        if(uri!=null){
            Glide.with(this)
                    .load(Uri.parse(uri))
                    .into(imageView);
        }
        else {

        }
        Spinner spinner = (Spinner) findViewById(R.id.category);//send.xml??? ????????? ?????????
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.malmuri, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                title_theme = adapterView.getItemAtPosition(i).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        Toolbar toolbar = findViewById(R.id.team_info_toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("???????????? ?????????");
        Button button = (Button) findViewById(R.id.post_guest_application);


        add_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);


                intent.addFlags(Intent.FLAG_GRANT_PERSISTABLE_URI_PERMISSION);
                intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                intent.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);


                intent.setType("image/*");

                startActivityForResult(intent, REQUEST_CODE);
            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String titleText = title.getText().toString();
                String contentText = content.getText().toString();
                if(uri==null){
                    uri = "null";
                }else {

                }

                if (titleText.length() == 0) {
                    Toast.makeText(Post_comunity.this, "????????? ??????????????????", Toast.LENGTH_SHORT).show();
                } else if (contentText.length() == 0) {
                    Toast.makeText(Post_comunity.this, "????????? ??????????????????", Toast.LENGTH_SHORT).show();

                } else {
                    Intent intent = new Intent(Post_comunity.this, MainComunity.class);
                    intent.putExtra("title", titleText);
                    intent.putExtra("content", contentText);
                    intent.putExtra("date", dateedit);
                    intent.putExtra("title_theme", "[" + title_theme + "]");
                    intent.putExtra("number", num);
                    intent.putExtra("uri", uri);

                    Log.i("??????", String.valueOf(num));


                    setResult(RESULT_OK, intent);
                    Log.i("?????????", "??????????????? ????????????");
                    finish();
                }

            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==REQUEST_CODE){
            if(resultCode== RESULT_OK){
                Glide.with(this)
                        .load(data.getData())
                        .into(imageView);
                uri = data.getDataString();
            }
        }
    }
}
