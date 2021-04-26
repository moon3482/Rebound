package com.example.rebound.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.rebound.R;
import com.example.rebound.data.Post_Data;

import java.util.ArrayList;

public class Search_user_Adapter extends RecyclerView.Adapter<Search_user_Adapter.CustomViewHolder> {
    private ArrayList<Post_Data> arrayList;
    private ArrayList<Post_Data> unarrayList;
    Search_user_Adapter.CustomViewHolder holder;
    Post_Data post_data;


    public Search_user_Adapter(ArrayList<Post_Data> arrayList) {
        this.arrayList = arrayList;

    }
    @NonNull
    @Override
    public Search_user_Adapter.CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.search_user, parent, false);


        holder = new Search_user_Adapter.CustomViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull Search_user_Adapter.CustomViewHolder holder, int position) {
        post_data = arrayList.get(position);
holder.name.setText(post_data.getName());
holder.area.setText(post_data.getArea());
holder.area.setText(post_data.getPosition());
if(post_data.getPosition().equals("포인트 가드")){
    holder.position.setText("PG");
}else if (post_data.getPosition().equals("슈팅 가드")){
    holder.position.setText("SG");
}else if (post_data.getPosition().equals("슈팅 가드")){
    holder.position.setText("SG");
}else if (post_data.getPosition().equals("스몰 포워드")){
    holder.position.setText("SF");
}else if (post_data.getPosition().equals("파워 포워드")){
    holder.position.setText("PF");
}else {
    holder.position.setText("C");
}
        Glide.with(holder.itemView.getContext()).load(post_data.getUri()).into(holder.ivprofile);
    }

    @Override
    public int getItemCount() {
        return (null != arrayList ? arrayList.size() : 0);
    }
    public class CustomViewHolder extends RecyclerView.ViewHolder {
        ImageView ivprofile;
        TextView name;
        TextView area;
        TextView position;

        public CustomViewHolder(@NonNull View itemView) {
            super(itemView);
            this.ivprofile = (ImageView) itemView.findViewById(R.id.search_useriv);
            this.name = (TextView) itemView.findViewById(R.id.name);
            this.area = (TextView) itemView.findViewById(R.id.area_us);
            this.position = (TextView) itemView.findViewById(R.id.user_posi);

        }
    }
}
