package com.example.rebound.Adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.example.rebound.Login.LoginActivity;
import com.example.rebound.R;
import com.example.rebound.data.Reple_Data;

import java.util.ArrayList;

public class Reply_adapter extends RecyclerView.Adapter<Reply_adapter.CustomViewHolder> {
    private ArrayList<Reple_Data> arrayList;
    private Context context;
    Reply_adapter.CustomViewHolder holder;
    Reple_Data reple_data;
    int REQUSTCODE = 900;
    String gg;


    public Reply_adapter(ArrayList<Reple_Data> arrayList) {
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public Reply_adapter.CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.reply_rcv_item, parent, false);

        holder = new Reply_adapter.CustomViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final Reply_adapter.CustomViewHolder holder, final int position) {
        reple_data = arrayList.get(position);
        holder.reply_text.setText(reple_data.getReply());
        holder.id.setText(reple_data.getId());
        holder.date.setText(reple_data.getDate());
        if (LoginActivity.user_id.equals("admin")) {
            holder.edit.setVisibility(View.VISIBLE);
            holder.delete.setVisibility(View.VISIBLE);
        } else if (arrayList.get(position).getId().equals(LoginActivity.user_id)) {
            holder.edit.setVisibility(View.VISIBLE);
            holder.delete.setVisibility(View.VISIBLE);


        } else {
            holder.edit.setVisibility(View.INVISIBLE);
            holder.delete.setVisibility(View.INVISIBLE);

        }
        holder.edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String rl = arrayList.get(position).getReply();
                Log.i("포지션", String.valueOf(position));
                holder.reply_text.setVisibility(View.GONE);
                holder.edit_reply.setVisibility(View.VISIBLE);
                holder.edit_reply.setText(rl);
                holder.edit.setVisibility(View.GONE);
                holder.edit_don.setVisibility(View.VISIBLE);

            }
        });
        holder.edit_don.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gg = holder.edit_reply.getText().toString();
                String id = arrayList.get(position).getId();
                String dd = arrayList.get(position).getDate();
                holder.edit.setVisibility(View.VISIBLE);
                holder.edit_don.setVisibility(View.GONE);

                holder.edit_reply.setVisibility(View.GONE);
                holder.reply_text.setVisibility(View.VISIBLE);
                Reple_Data reple_data = new Reple_Data(gg,id,dd);
                Log.i("포지션", String.valueOf(arrayList.get(position)));

                arrayList.set(position, reple_data);
                notifyItemChanged(position);

            }
        });
        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                androidx.appcompat.app.AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                builder.setTitle("삭제 확인");
                builder.setMessage("댓글을 삭제하시겠습니까?");
                builder.setNegativeButton("예", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {


                        arrayList.remove(position);
                        notifyDataSetChanged();
                    }
                });
                builder.setPositiveButton("아니오", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });
                builder.show();
            }
        });


    }

    @Override
    public int getItemCount() {

        return (null != arrayList ? arrayList.size() : 0);
    }

    public class CustomViewHolder extends RecyclerView.ViewHolder {
        TextView reply_text;
        TextView id;
        TextView date;
        ImageView edit;
        ImageView edit_don;
        EditText edit_reply;
        ImageView delete;

        public CustomViewHolder(@NonNull View itemView) {
            super(itemView);
            this.reply_text = (TextView) itemView.findViewById(R.id.reply_text);
            this.id = (TextView) itemView.findViewById(R.id.reply_id);
            this.date = (TextView) itemView.findViewById(R.id.reply_time);
            this.edit = (ImageView) itemView.findViewById(R.id.reply_edit);
            this.edit_reply = (EditText) itemView.findViewById(R.id.edit_rtply);
            this.edit_don = (ImageView) itemView.findViewById(R.id.reply_edit2);
            this.delete = (ImageView) itemView.findViewById(R.id.reply_delete);

        }
    }


}
