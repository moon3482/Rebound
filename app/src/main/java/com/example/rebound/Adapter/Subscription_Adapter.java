package com.example.rebound.Adapter;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.rebound.Login.LoginActivity;
import com.example.rebound.R;
import com.example.rebound.data.Post_Data2;
import com.example.rebound.post.Post_View;
import com.example.rebound.user_profile;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class Subscription_Adapter extends RecyclerView.Adapter<Subscription_Adapter.CustomViewHolder> {
    private ArrayList<Post_Data2> arrayList;
    Subscription_Adapter.CustomViewHolder holder;
    Post_Data2 post_data2;
    private Context context;
    RequestOptions options;
    int co;
    String no;

    public Subscription_Adapter(ArrayList<Post_Data2> arrayList) {
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public Subscription_Adapter.CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.subscription_item, parent, false);
        holder = new Subscription_Adapter.CustomViewHolder(view);
        return holder;
    }

    public Subscription_Adapter(Context context, ArrayList<Post_Data2> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
        options = new RequestOptions().centerCrop().placeholder(R.drawable.ball).error(R.drawable.basket);
    }

    @Override
    public void onBindViewHolder(@NonNull final CustomViewHolder holder, final int position) {
        post_data2 = arrayList.get(position);

        holder.id.setText(post_data2.getId());
        holder.birth.setText(post_data2.getBirthday());
        holder.height.setText(post_data2.getHeight());
        holder.weight.setText(post_data2.getWeight());
        holder.position.setText(post_data2.getPosition());
//holder.button.setVisibility(View.INVISIBLE);


        Glide.with(holder.itemView.getContext()).load(Uri.parse(post_data2.getUri())).into(holder.profile);

        if (Post_View.post_id.equals(LoginActivity.user_id)) {
            holder.button.setVisibility(View.VISIBLE);
        } else {
            holder.button.setVisibility(View.INVISIBLE);
        }
        if (arrayList.get(position).getReply().equals("1")) {
            holder.itemView.setBackgroundResource(R.drawable.border2);
            holder.button.setBackgroundColor(Color.parseColor("#6200EE"));
            holder.button.setBackgroundResource(R.drawable.coner_layout3);
            holder.button.setText("선택완료");
            holder.button.setEnabled(false);
            holder.id.setTextColor(Color.parseColor("#FFFFFF"));
            holder.id1.setTextColor(Color.parseColor("#FFFFFF"));
            holder.birth.setTextColor(Color.parseColor("#FFFFFF"));
            holder.birth1.setTextColor(Color.parseColor("#FFFFFF"));
            holder.height1.setTextColor(Color.parseColor("#FFFFFF"));
            holder.weight.setTextColor(Color.parseColor("#FFFFFF"));
            holder.height.setTextColor(Color.parseColor("#FFFFFF"));
            holder.position.setTextColor(Color.parseColor("#FFFFFF"));
            holder.position1.setTextColor(Color.parseColor("#FFFFFF"));
            co = 1;
        } else if (co == 1) {
            holder.button.setVisibility(View.INVISIBLE);
        } else {
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), user_profile.class);
                intent.putExtra("id", arrayList.get(position).getId());
                intent.putExtra("birth", arrayList.get(position).getBirthday());
                intent.putExtra("height", arrayList.get(position).getHeight());
                intent.putExtra("weight", arrayList.get(position).getWeight());
                intent.putExtra("team", arrayList.get(position).getTeam());
                intent.putExtra("uri", arrayList.get(position).getUri());
                ((Activity) view.getContext()).startActivity(intent);

            }
        });
        // 게스트 수락 버튼 바뀌는 특성
        holder.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (co == 0) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                    builder.setTitle("선택 확인");
                    builder.setMessage("선택 하시겠습니까?\n(한번 선택후 변경 할 수 없습니다.)\n(신중하게 선택해주세요.)");
                    builder.setNegativeButton("예", new DialogInterface.OnClickListener() {
                        @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            holder.itemView.setBackgroundResource(R.drawable.border2);
                            holder.button.setBackgroundColor(Color.parseColor("#6200EE"));
                            holder.button.setBackgroundResource(R.drawable.coner_layout3);
                            holder.button.setText("선택완료");
                            holder.button.setEnabled(false);
                            holder.id.setTextColor(Color.parseColor("#FFFFFF"));
                            holder.id1.setTextColor(Color.parseColor("#FFFFFF"));
                            holder.birth.setTextColor(Color.parseColor("#FFFFFF"));
                            holder.birth1.setTextColor(Color.parseColor("#FFFFFF"));
                            holder.height1.setTextColor(Color.parseColor("#FFFFFF"));
                            holder.weight.setTextColor(Color.parseColor("#FFFFFF"));
                            holder.height.setTextColor(Color.parseColor("#FFFFFF"));
                            holder.position.setTextColor(Color.parseColor("#FFFFFF"));
                            holder.position1.setTextColor(Color.parseColor("#FFFFFF"));
                            holder.height2.setTextColor(Color.parseColor("#FFFFFF"));
                            holder.weight2.setTextColor(Color.parseColor("#FFFFFF"));
                            arrayList.get(position).setReply("1");
                            Log.i("홀더 스트링", holder.po);
                            co = 1;
//                            SharedPreferences sharedPreferences;
//                            SharedPreferences.Editor editor;
//                            sharedPreferences = view.getContext().getSharedPreferences("Guest_rcv", Context.MODE_PRIVATE);
//                            editor = sharedPreferences.edit();
//                            try {
//                                    JSONArray jsonArray = new JSONArray();
//                                    JSONObject jsonObject = new JSONObject();
//                                    jsonObject.put("id", arrayList.get(position).getId());
//                                    jsonObject.put("postid", Post_View.post_id);
//                                    jsonArray.put(jsonObject);
//
//
//                                    String nono = jsonArray.toString();
//                                    editor.putString("notify", nono);
//                                    editor.apply();
//                                    Log.i("노티2", nono);
//
//
//                                } catch (Exception e) {
//                                    e.printStackTrace();
//                                }

                            SharedPreferences sharedPreferences;
                            SharedPreferences.Editor editor;

                            sharedPreferences = view.getContext().getSharedPreferences("Guest_rcv", Context.MODE_PRIVATE);
                            editor = sharedPreferences.edit();

                            no = sharedPreferences.getString("notify", "");

                            if (no.length() != 0) {
                                try {
                                    JSONArray jsonArray = new JSONArray(no);
                                    JSONObject jsonObject = new JSONObject();
                                    jsonObject.put("id", arrayList.get(position).getId());
                                    jsonObject.put("postid", Post_View.post_id);
                                    jsonArray.put(jsonObject);
                                    String nono = jsonArray.toString();
                                    editor.putString("notify", nono);
                                    editor.apply();
                                    Log.i("노티1", nono);

                                } catch (Exception e) {
                                    e.printStackTrace();
                                }


                            } else {
                                try {
                                    JSONArray jsonArray = new JSONArray();
                                    JSONObject jsonObject = new JSONObject();
                                    jsonObject.put("id", arrayList.get(position).getId());
                                    jsonObject.put("postid", Post_View.post_id);
                                    jsonArray.put(jsonObject);


                                    String nono = jsonArray.toString();
                                    editor.putString("notify", nono);
                                    editor.apply();
                                    Log.i("노티2", nono);


                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }

//                               try{
//                                   JSONArray jsonArray1 = new JSONArray();
//
//
//
//
//                                   JSONObject jsonObject2 = new JSONObject();
//                                   jsonObject2.put("id", arrayList.get(position).getId());
//                                   jsonObject2.put("postid", Post_View.post_id);
//
//                                   jsonArray1.put(jsonObject2);
//                                   String nono = jsonArray1.toString();
//                                   Log.i("노티 데이터", nono);
//                                   editor.putString("notify", nono);
//                                   editor.apply();
//                               }catch(Exception g){
//                                 g.printStackTrace();
//                               }


                        }


                    });
                    builder.setPositiveButton("아니오", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                        }
                    });

                    builder.show();
                } else {

                    androidx.appcompat.app.AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                    builder.setTitle("선택 불가");
                    builder.setMessage("이미 선택된 사람이 있습니다.");
                    builder.setNegativeButton("확인", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    });

                    builder.show();
                }
                notifyDataSetChanged();

            }
        });
    }

    @Override
    public int getItemCount() {
        return (null != arrayList ? arrayList.size() : 0);
    }


    public class CustomViewHolder extends RecyclerView.ViewHolder {
        TextView id;
        TextView birth;
        TextView height;
        TextView weight;
        TextView position;
        TextView id1;
        TextView birth1;
        TextView height1;
        TextView weight2;
        TextView height2;
        ImageView profile;
        String po;
        int count;
        TextView position1;
        Button button;

        public CustomViewHolder(@NonNull View itemView) {
            super(itemView);
            this.id = (TextView) itemView.findViewById(R.id.subscription_id);
            this.birth = (TextView) itemView.findViewById(R.id.subscription_birth);
            this.height = (TextView) itemView.findViewById(R.id.aab);
            this.weight = (TextView) itemView.findViewById(R.id.aac);
            this.position = (TextView) itemView.findViewById(R.id.subscription_position);
            this.id1 = (TextView) itemView.findViewById(R.id.subscription_id1);
            this.birth1 = (TextView) itemView.findViewById(R.id.subscription_birth1);
            this.height1 = (TextView) itemView.findViewById(R.id.aab1);
            this.profile = (ImageView) itemView.findViewById(R.id.imageView2);
            this.position1 = (TextView) itemView.findViewById(R.id.subscription_position1);
            this.button = (Button) itemView.findViewById(R.id.button_subscription);
            this.po = new String();
            this.height2 = (TextView) itemView.findViewById(R.id.aab2);
            this.weight2 = (TextView) itemView.findViewById(R.id.aac2);

            int count = this.count;

        }
    }
}
