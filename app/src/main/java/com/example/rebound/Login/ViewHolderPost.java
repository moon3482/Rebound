//package com.example.rebound;
//
//import android.view.View;
//import android.widget.TextView;
//
//import androidx.annotation.NonNull;
//import androidx.recyclerview.widget.RecyclerView;
//
//public class ViewHolderPost extends RecyclerView.ViewHolder {
//TextView post_title1;
//    TextView post_title2;
//    TextView post_title3;
//    TextView post_title4;
//
//
//    public ViewHolderPost(@NonNull View itemView) {
//        super(itemView);
//        post_title1 = itemView.findViewById(R.id.post_title);
//        post_title2 = itemView.findViewById(R.id.post_content);
//        post_title3 = itemView.findViewById(R.id.post_position1);
//        post_title4 = itemView.findViewById(R.id.post_date);
//    }
//    public void onBind(Post_Data data){
//        post_title1.setText(data.getTitle());
//        post_title2.setText(data.getContent());
//        post_title3.setText(data.getArea());
//        post_title4.setText(data.getDate());
//    }
//}
