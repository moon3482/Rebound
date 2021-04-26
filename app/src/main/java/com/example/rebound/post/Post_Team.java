package com.example.rebound.post;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.icu.text.SimpleDateFormat;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentManager;

import com.example.rebound.Main.MainTeam;
import com.example.rebound.R;
import com.example.rebound.data.Address;
import com.naver.maps.geometry.LatLng;
import com.naver.maps.map.CameraUpdate;
import com.naver.maps.map.MapFragment;
import com.naver.maps.map.NaverMap;
import com.naver.maps.map.OnMapReadyCallback;
import com.naver.maps.map.overlay.InfoWindow;
import com.naver.maps.map.overlay.Marker;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class Post_Team extends AppCompatActivity implements OnMapReadyCallback {

    EditText title;
    EditText content;
    String date;
    String titleedit;
    String contentedit;
    String dateedit;
    String area;
    String needposi;
    int j;
    int num;
    String title_theme;
    public double lat;
    public double lon;
    Button map_btn;
    EditText map_src;
    public String road;
    public String add;
    public String json;
    String dd;
    double la;
    double lo;
    Button end_time;
    Button end_date;
    private List<Address> list = null;
    private int mYear, mMonth, mDate;
    int alarmHour = 0;
    int alarmMiute = 0;
    String a;
    TextView time;
    TextView dayd;
    String pickerY = "0000";
    String pickerM = "00";
    String pickerD = "00";
    String pickerH = "00";
    String pickerMM = "00";
    Date date11 = null;
    Date today = null;
    int timeminer;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.post);
        title = findViewById(R.id.editTextTextPersonName7);
        content = findViewById(R.id.editpost);
        map_btn = findViewById(R.id.map_button);
        map_src = findViewById(R.id.map_search);
        end_date = findViewById(R.id.date_pick);
        end_time = findViewById(R.id.time_pick);
        time = findViewById(R.id.end_time);
        dayd = findViewById(R.id.end_date);
        dayd.setText(pickerY + "년" + pickerM + "월" + pickerD + "일");
        time.setText(pickerH + "시" + pickerMM + "분");

        Intent intent = getIntent();
        date = intent.getStringExtra("date");
        titleedit = intent.getStringExtra("titleedit");
        contentedit = intent.getStringExtra("contentedit");
        dateedit = intent.getStringExtra("dateedit");
        num = intent.getIntExtra("number", 0);
        area = intent.getStringExtra("area");
        needposi = intent.getStringExtra("needposi");
        title.setText(titleedit);
        content.setText(contentedit);

        FragmentManager fm = getSupportFragmentManager();
        MapFragment mapFragment = (MapFragment) fm.findFragmentById(R.id.map);
        if (mapFragment == null) {
            mapFragment = MapFragment.newInstance();
            fm.beginTransaction().add(R.id.map, mapFragment).commit();
        }

        mapFragment.getMapAsync(this);

        Toolbar toolbar = findViewById(R.id.team_info_toolbar);

        Spinner spinner = (Spinner) findViewById(R.id.category);//send.xml의 스피너 아이디
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.team, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Spinner spinner1 = (Spinner) findViewById(R.id.spinner_area);//send.xml의 스피너 아이디
        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this, R.array.area, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Spinner spinner2 = (Spinner) findViewById(R.id.needposi1);//send.xml의 스피너 아이디
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this, R.array.position, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner1.setAdapter(adapter1);
        spinner2.setAdapter(adapter2);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                title_theme = adapterView.getItemAtPosition(i).toString();


            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                area = adapterView.getItemAtPosition(i).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                needposi = adapterView.getItemAtPosition(i).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("팀원 구인 게시판 글쓰기");
        Button button = (Button) findViewById(R.id.post_guest_application2);
        end_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar calendar = Calendar.getInstance(Locale.KOREA);
                mYear = calendar.get(Calendar.YEAR);
                mMonth = calendar.get(calendar.MONTH);
                mDate = calendar.get(Calendar.DATE);


                DatePickerDialog datePickerDialog = new DatePickerDialog(Post_Team.this,
                        AlertDialog.THEME_HOLO_LIGHT, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datepicker, int year, int month, int day) {
                        pickerY = String.valueOf(year);
                        pickerM = String.valueOf(month + 1);
                        pickerD = String.valueOf(day);
                        Log.i("월", pickerM);

                        dayd.setText(pickerY + "년" + pickerM + "월" + pickerD + "일");

                    }
                }, mYear, mMonth, mDate);

                //Call show() to simply show the dialog
                datePickerDialog.show();
            }
        });
        end_time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TimePickerDialog timePickerDialog = new TimePickerDialog(Post_Team.this,
                        android.R.style.Theme_Holo_Light_Dialog,
                        new TimePickerDialog.OnTimeSetListener() {
                            @RequiresApi(api = Build.VERSION_CODES.N)
                            @Override
                            public void onTimeSet(TimePicker timePicker, int i, int i1) {
                                alarmHour = i;
                                alarmMiute = i1;

                                if (alarmHour < 10) {
                                    pickerH = "0" + String.valueOf(alarmHour);
                                    Log.d("디버그태그", String.valueOf(a));
                                } else {
                                    pickerH = String.valueOf(alarmHour);
                                }
                                if (alarmMiute < 10) {
                                    pickerMM = "0" + String.valueOf(alarmMiute);
                                } else {
                                    pickerMM = String.valueOf(alarmMiute);

                                }

                                time.setText(pickerH + "시" + pickerMM + "분");

                            }
                        },
                        0,
                        0,
                        true);
                timePickerDialog.show();
            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View view) {
                String titleText = title.getText().toString();
                String contentText = content.getText().toString();
                SimpleDateFormat dfDate = new SimpleDateFormat("yyyy-MM-dd/HH:mm:ss.SSS", Locale.KOREA);
                try {
                    String gg = dfDate.format(new Date());

                    date11 = dfDate.parse(pickerY + "-" + pickerM + "-" + pickerD + "/" + pickerH + ":" + pickerMM + ":00.000");
                    today = dfDate.parse(gg);
                    timeminer = date11.compareTo(today);

                    Log.i("날짜계산후", String.valueOf(timeminer));


                } catch (ParseException e) {
                    e.printStackTrace();
                }
                if (titleText.length() == 0) {
                    Toast.makeText(Post_Team.this, "제목을 입력해주십쇼", Toast.LENGTH_SHORT).show();
                } else if (contentText.length() == 0) {
                    Toast.makeText(Post_Team.this, "내용을 입력해주십쇼", Toast.LENGTH_SHORT).show();

                } else if (title_theme.equals("말머리")) {
                    Toast.makeText(Post_Team.this, "말머리를 선택해주십쇼", Toast.LENGTH_SHORT).show();
                } else if (area.equals("지역")) {
                    Toast.makeText(Post_Team.this, "지역을 선택해주십쇼", Toast.LENGTH_SHORT).show();
                } else if (needposi.equals("포지션")) {
                    Toast.makeText(Post_Team.this, "포지션을 선택해주십쇼", Toast.LENGTH_SHORT).show();


                } else if (timeminer < 0) {
                    Toast.makeText(Post_Team.this, "날짜를 정확히 선택해주십쇼", Toast.LENGTH_SHORT).show();


                } else if (la == 0.0 && lo == 0.0) {
                    Toast.makeText(Post_Team.this, "장소를 선택해주십쇼", Toast.LENGTH_SHORT).show();

                }else {
                    String map_a = map_src.getText().toString();


                    Intent intent = new Intent(Post_Team.this, MainTeam.class);

                    intent.putExtra("title", titleText);
                    intent.putExtra("content", contentText);
                    intent.putExtra("date", dateedit);
                    intent.putExtra("title_theme", "[" + title_theme + "]");
                    intent.putExtra("number", num);
                    intent.putExtra("area", area);
                    intent.putExtra("needposi", needposi);
                    intent.putExtra("x", la);
                    intent.putExtra("y", lo);
                    intent.putExtra("end_date", pickerY + "-" + pickerM + "-" + pickerD + "/" + pickerH + ":" + pickerMM + ":00.000");

                    Log.i("넘버", String.valueOf(num));
                    String pass;


                    setResult(RESULT_OK, intent);
                    Log.i("데이터", "게스트에서 전달했음");

                    finish();


                }
            }

        });
    }

    @Override
    public void onMapReady(@NonNull NaverMap naverMap) {
        Marker marker = new Marker();
        map_btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                String map_a = map_src.getText().toString();
//                Geocoder geocoder = new Geocoder(Post_Guest.this, Locale.KOREA);


                Thread thread = new Thread() {
                    @Override
                    public void run() {
                        try {
                            main2(map_a);
                            main();

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                };
                thread.start();
                try {
                    thread.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                try {


                    JSONObject jsonObject = new JSONObject(json);
                    String aa = jsonObject.getString("addresses");
                    JSONArray jsonArray = new JSONArray(aa);
                    JSONObject jsonObject1 = jsonArray.getJSONObject(0);
                    dd = jsonObject1.getString("roadAddress");
                    if (dd.length() == 0) {
                        dd = jsonObject1.getString("jibunAddress");
                        lo = jsonObject1.getDouble("x");
                        la = jsonObject1.getDouble("y");
                        Log.i("json파싱~~~~~", "la=" + la + "lo=" + lo + "dd=" + dd);
                    } else {
                    }
                    lo = jsonObject1.getDouble("x");
                    la = jsonObject1.getDouble("y");
                    Log.i("json파싱~~~~~", "la=" + la + "lo=" + lo + "dd=" + dd);

                } catch (JSONException e) {
                    e.printStackTrace();
                    Log.e("test", "입출력 오류 - 서버에서 주소변환시 에러발생");
                }
                InfoWindow infoWindow = new InfoWindow();
                infoWindow.setAdapter(new InfoWindow.DefaultTextAdapter(Post_Team.this) {
                    @NonNull
                    @Override
                    public CharSequence getText(@NonNull InfoWindow infoWindow) {
                        return dd;
                    }
                });
                LatLng latLng = new LatLng(la, lo);

                marker.setPosition(new LatLng(la, lo));

                marker.setMap(naverMap);
                infoWindow.open(marker);
                CameraUpdate cameraUpdate = CameraUpdate.scrollAndZoomTo(new LatLng(la, lo), 15);


                naverMap.moveCamera(cameraUpdate);
            }


        });
    }
    public void main2(String b) {

        String clientId = "@string/@string/NaverSearchAPI_ID"; //애플리케이션 클라이언트 아이디값"
        String clientSecret = "@string/NaverSearchAPI_key"; //애플리케이션 클라이언트 시크릿값"

        String text = null;
        try {
            text = URLEncoder.encode(b, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("검색어 인코딩 실패", e);
        }

        String apiURL = "https://openapi.naver.com/v1/search/local?query=" + text;    // json 결과
        //String apiURL = "https://openapi.naver.com/v1/search/blog.xml?query="+ text; // xml 결과

        Map<String, String> requestHeaders = new HashMap<>();
        requestHeaders.put("X-Naver-Client-Id", clientId);
        requestHeaders.put("X-Naver-Client-Secret", clientSecret);
        String responseBody = get(apiURL, requestHeaders);

        Log.i("json파싱222", responseBody.toString());
        try {
            JSONObject jsonObject = new JSONObject(responseBody);
            String item = jsonObject.getString("items");
            JSONArray jsonArray = new JSONArray(item);
            JSONObject jsonObject1 = jsonArray.getJSONObject(0);
            road = jsonObject1.getString("address");


        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private static String get(String apiUrl, Map<String, String> requestHeaders) {
        HttpURLConnection con = connect(apiUrl);
        try {
            con.setRequestMethod("GET");
            for (Map.Entry<String, String> header : requestHeaders.entrySet()) {
                con.setRequestProperty(header.getKey(), header.getValue());
            }

            int responseCode = con.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) { // 정상 호출
                return readBody(con.getInputStream());
            } else { // 에러 발생
                return readBody(con.getErrorStream());
            }
        } catch (IOException e) {
            throw new RuntimeException("API 요청과 응답 실패", e);
        } finally {
            con.disconnect();
        }
    }

    private static HttpURLConnection connect(String apiUrl) {
        try {
            URL url = new URL(apiUrl);
            return (HttpURLConnection) url.openConnection();
        } catch (MalformedURLException e) {
            throw new RuntimeException("API URL이 잘못되었습니다. : " + apiUrl, e);
        } catch (IOException e) {
            throw new RuntimeException("연결이 실패했습니다. : " + apiUrl, e);
        }
    }

    private static String readBody(InputStream body) {
        InputStreamReader streamReader = new InputStreamReader(body);

        try (BufferedReader lineReader = new BufferedReader(streamReader)) {
            StringBuilder responseBody = new StringBuilder();

            String line;
            while ((line = lineReader.readLine()) != null) {
                responseBody.append(line);
            }

            return responseBody.toString();
        } catch (IOException e) {
            throw new RuntimeException("API 응답을 읽는데 실패했습니다.", e);
        }
    }

    public void main() {
        String clientId = "@string/NaverMapAPI_ID";//애플리케이션 클라이언트 아이디값";
        String clientSecret = "@string/NaverMapAPI_key";//애플리케이션 클라이언트 시크릿값";
        try {

            String query = URLEncoder.encode(road, "UTF-8");
            String apiURL = "https://naveropenapi.apigw.ntruss.com/map-geocode/v2/geocode?query=" + query;
            URL url = new URL(apiURL);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("X-NCP-APIGW-API-KEY-ID", clientId);
            con.setRequestProperty("X-NCP-APIGW-API-KEY", clientSecret);
            int responseCode = con.getResponseCode();
            Log.i("리스폰스코드", String.valueOf(responseCode));

            BufferedReader br;
            if (responseCode == 200) { // 정상 호출
                br = new BufferedReader(new InputStreamReader(con.getInputStream()));
            } else {  // 에러 발생
                br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
            }
            String inputLine;
            StringBuffer response = new StringBuffer();
            while ((inputLine = br.readLine()) != null) {
                response.append(inputLine);
            }

            br.close();

            json = response.toString();
            Log.i("json파싱", response.toString());


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}