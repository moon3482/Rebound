package com.example.rebound.Adapter;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.rebound.R;
import com.example.rebound.Team_info;
import com.example.rebound.data.Team_data;

import java.util.ArrayList;

public class Team_Adapter extends RecyclerView.Adapter<Team_Adapter.CustomViewHolder> {
    private ArrayList<Team_data> arrayList;
    Team_Adapter.CustomViewHolder holder;
    Team_data team_data;
    public Team_Adapter(ArrayList<Team_data> arrayList) {
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public Team_Adapter.CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.tema_rcv, parent, false);
        holder = new Team_Adapter.CustomViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull Team_Adapter.CustomViewHolder holder, int position) {
        team_data = arrayList.get(position);
        holder.name.setText(team_data.getNaem());
        Glide.with(holder.itemView.getContext()).load(team_data.getUri()).into(holder.logo);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(view.getContext(), Team_info.class);
                intent.putExtra("naem",arrayList.get(position).getNaem());
                intent.putExtra("area",arrayList.get(position).getArea());
                intent.putExtra("temabirth",arrayList.get(position).getTemabirth());
                intent.putExtra("manager",arrayList.get(position).getManager());
                intent.putExtra("coch",arrayList.get(position).getCoch());
                intent.putExtra("uri",arrayList.get(position).getUri());
                ((Activity)view.getContext()).startActivity(intent);

            }
        });
    }

    @Override
    public int getItemCount() {
        return (null!= arrayList ? arrayList.size() : 0);

    }


    public class CustomViewHolder extends RecyclerView.ViewHolder {
        TextView name;
        ImageView logo;

        public CustomViewHolder(@NonNull View itemView) {
            super(itemView);
            this.name = (TextView) itemView.findViewById(R.id.search_seoulteam1text);
            this.logo = (ImageView) itemView.findViewById(R.id.search_seoulteam1);
        }
    }
}
