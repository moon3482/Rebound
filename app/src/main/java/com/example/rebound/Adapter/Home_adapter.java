package com.example.rebound.Adapter;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.rebound.data.Post_Data;
import com.example.rebound.post.Post_View;
import com.example.rebound.R;

import java.util.ArrayList;

import static android.content.Intent.FLAG_GRANT_PERSISTABLE_URI_PERMISSION;

public class Home_adapter extends RecyclerView.Adapter<Home_adapter.CustomViewHolder> {
    private ArrayList<Post_Data> arrayList;
    Home_adapter.CustomViewHolder holder;
    Post_Data post_data;

    public Home_adapter(ArrayList<Post_Data> arrayList) {
        this.arrayList = arrayList;
    }

    public class CustomViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        TextView id;
        TextView date;

        public CustomViewHolder(@NonNull View itemView) {
            super(itemView);
            this.title = (TextView) itemView.findViewById(R.id.title_home);
            this.id = (TextView) itemView.findViewById(R.id.id_home);
            this.date = (TextView) itemView.findViewById(R.id.date_home);
        }
    }

    @NonNull
    @Override
    public Home_adapter.CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.home_post, parent, false);
        holder = new Home_adapter.CustomViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull Home_adapter.CustomViewHolder holder, int position) {
        post_data = arrayList.get(position);
        holder.title.setText(post_data.getTitle());
        holder.id.setText(post_data.getId());
        holder.date.setText(post_data.getDate().substring(5));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), Post_View.class);

                intent.putExtra("number", position);
                intent.putExtra("title", arrayList.get(position).getTitle());
                intent.putExtra("content", arrayList.get(position).getContent());
                intent.putExtra("name", arrayList.get(position).getName());
                intent.putExtra("birth", arrayList.get(position).getBirthday());
                intent.putExtra("height", arrayList.get(position).getHeight());
                intent.putExtra("weight", arrayList.get(position).getWeight());
                intent.putExtra("team", arrayList.get(position).getTeam());
                intent.putExtra("date", arrayList.get(position).getDate());
                intent.putExtra("title_theme", arrayList.get(position).getTitletheme());
                intent.putExtra("id", arrayList.get(position).getId());
                intent.putExtra("aoa",arrayList.get(position).getUri());
                intent.putExtra("area",arrayList.get(position).getArea());
                intent.putExtra("needposi",arrayList.get(position).getPosition());
                intent.putExtra("x",arrayList.get(position).getX());
                intent.putExtra("y",arrayList.get(position).getY());
                intent.putExtra("end",arrayList.get(position).getEnd());
                intent.putExtra("end_date",arrayList.get(position).getEnd_date());
                intent.addFlags(FLAG_GRANT_PERSISTABLE_URI_PERMISSION);
                intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                intent.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);


                Log.i("뷰클릭 uri", arrayList.get(position).getUri());
                Log.i("게시판정보", post_data.getId());
                Log.i("뷰클릭 숫자", String.valueOf(position));

                ((Activity) view.getContext()).startActivity(intent.addFlags(FLAG_GRANT_PERSISTABLE_URI_PERMISSION));
                Log.i("뷰클릭 제발", arrayList.get(position).getId());
            }
        });
    }

    @Override
    public int getItemCount() {
        if (arrayList != null) {
            return Math.min(arrayList.size(), 13);
        } else {
            return 0;
        }
    }


}
