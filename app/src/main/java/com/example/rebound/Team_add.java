package com.example.rebound;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

import java.util.Calendar;
import java.util.Locale;

public class Team_add extends AppCompatActivity {
    int REQUEST_CODE = 778;
    String area;
    ImageView ivlogo;
    String uri;
    Button button;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.team_add);
        button = findViewById(R.id.button_team);

        EditText tvname = (EditText) findViewById(R.id.team_info_name2);
        TextView tvbirth = (TextView) findViewById(R.id.team_info_makedate2);
        EditText tvmanager = (EditText) findViewById(R.id.team_info_manager2);
        EditText tvcoch = (EditText) findViewById(R.id.team_info_coch2);
        ivlogo = (ImageView) findViewById(R.id.team_info_image);

        Spinner spinner = (Spinner) findViewById(R.id.team_info_home2);//send.xml의 스피너 아이디
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.area, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);


        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                area = adapterView.getItemAtPosition(i).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        tvbirth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar calendar = Calendar.getInstance(Locale.KOREA);
                int mYear = calendar.get(Calendar.YEAR);
                int mMonth = calendar.get(calendar.MONTH);
                int mDate = calendar.get(Calendar.DATE);


                DatePickerDialog datePickerDialog = new DatePickerDialog(Team_add.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker datepicker, int year, int month, int day) {
                                String pickerY = String.valueOf(year);
                                String pickerM = String.valueOf(month + 1);
                                String pickerD = String.valueOf(day);
                                Log.i("월", pickerM);

                                tvbirth.setText(pickerY + "." + pickerM + "." + pickerD);

                            }
                        }, mYear, mMonth, mDate);

                //Call show() to simply show the dialog
                datePickerDialog.show();
            }
        });
        ivlogo.setOnClickListener(new View.OnClickListener() {
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
                String naem = tvname.getText().toString();
                String manager = tvmanager.getText().toString();
                String coch = tvcoch.getText().toString();
                String temabirth = tvbirth.getText().toString();

                if (uri == null) {
                    Toast.makeText(Team_add.this, "사진을 입력해주세요", Toast.LENGTH_SHORT).show();
                } else if(naem.length()==0){
                    Toast.makeText(Team_add.this, "팀이름을 입력해주세요", Toast.LENGTH_SHORT).show();

                }else if(manager.length()==0){
                    Toast.makeText(Team_add.this, "감독 이름을 입력해주세요", Toast.LENGTH_SHORT).show();

                }else if(coch.length()==0){
                    Toast.makeText(Team_add.this, "코치 이름을 입력해주세요", Toast.LENGTH_SHORT).show();

                }else if(temabirth.length()==0){
                    Toast.makeText(Team_add.this, "창단일을 선택택해주세요", Toast.LENGTH_SHORT).show();

                }else if(area.equals("지역")){
                    Toast.makeText(Team_add.this, "연고 지역을 선택택해주세요", Toast.LENGTH_SHORT).show();

                }



                else {


                    Intent intent1 = new Intent();

                    intent1.putExtra("naem", naem);
                    intent1.putExtra("area", area);
                    intent1.putExtra("manager", manager);
                    intent1.putExtra("coch", coch);
                    intent1.putExtra("temabirth", temabirth);
                    intent1.putExtra("uri", uri);

                    setResult(RESULT_OK, intent1);
                    finish();
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                Glide.with(this)
                        .load(data.getData())
                        .into(ivlogo);
                uri = data.getDataString();
            }
        }
    }
}
