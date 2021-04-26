package com.example.rebound.Adapter;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.example.rebound.Login.LoginActivity;
import com.example.rebound.data.Post_Data_NC;
import com.example.rebound.post.Post_news;
import com.example.rebound.post.Post_view2;
import com.example.rebound.R;

import java.util.ArrayList;

public class Post_Adapter_NC extends RecyclerView.Adapter<Post_Adapter_NC.CustomViewHolder> {
    private ArrayList<Post_Data_NC> arrayList;
    private ArrayList<Post_Data_NC> unarrayList;
    Post_Adapter_NC.CustomViewHolder holder;
    Post_Data_NC post_data_nc;
    int REQUEST_CODE_SUJUNG = 302;
    String posinum;







    public interface OnItemCliclistener {
        void onItemClick(View view, int pos);
    }

    private Post_Adapter_NC.OnItemCliclistener mListener = null;

    public void setOnItemCliklistener(Post_Adapter_NC.OnItemCliclistener listener) {
        this.mListener = listener;
    }


//    @Override
//    public void onCreateContextMenu(ContextMenu contextMenu, View view, ContextMenu.ContextMenuInfo contextMenuInfo) {
//        MenuItem update = contextMenu.add(Menu.NONE, 100, 1, "수정");
//        MenuItem delete = contextMenu.add(Menu.NONE, 101, 2, "삭제");
//        update.setOnMenuItemClickListener(onEditMenu);
//
//    }
//
//    private final MenuItem.OnMenuItemClickListener onEditMenu = new MenuItem.OnMenuItemClickListener() {
//        @Override
//        public boolean onMenuItemClick(final MenuItem menuItem) {
//            switch (menuItem.getItemId()) {
//                case 100:
//                    Intent intent = new Intent(context,Post_Guest.class);
//                    View view = LayoutInflater.from(context).inflate(R.layout.post, null, false);
//
//
//                    final Button button = (Button) view.findViewById(R.id.post_guest_application);
//                    final EditText editTexttitle = (EditText) view.findViewById(R.id.editTextTextPersonName7);
//                    final EditText editTextcontent = (EditText) view.findViewById(R.id.editpost);
//                    editTexttitle.setText(arrayList.get(itemposition).getTitle());
//                    editTextcontent.setText(arrayList.get(itemposition).getContent());
//
//                    button.setOnClickListener(new View.OnClickListener() {
//                        @RequiresApi(api = Build.VERSION_CODES.N)
//                        @Override
//                        public void onClick(View view) {
//                            SimpleDateFormat dfDate = new SimpleDateFormat("MM-dd", Locale.KOREA);
//                            String TODAY = dfDate.format(new Date());
//                            String updatetitle = editTexttitle.getText().toString();
//                            String updatecontent = editTextcontent.getText().toString();
//                            arrayList.set(itemposition, new Post_Data(updatetitle, updatecontent, "","수정일 : "+TODAY));
//                            notifyItemChanged(itemposition);
//
//
//
//                        }
//                    });
//
//                    break;
//            }
//            return true;
//        }
//    };


    public static class OnItemClickEvent {
        public OnItemClickEvent(int position, int id) {
            this.position = position;
            this.id = id;
        }

        int position;
        int id;
    }

    interface onItemClickListener {
        void onItemClick(View view, int position);
    }


//////////////////////////////////////////////////////////////////여기부터 중요


    public Post_Adapter_NC(ArrayList<Post_Data_NC> item) {
        arrayList = item;
        unarrayList = new ArrayList<>(item);
    }

    @NonNull
    @Override
    public Post_Adapter_NC.CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rclv_item, parent, false);
        Log.i("데이터", "onCreateView");
        holder = new Post_Adapter_NC.CustomViewHolder(view);


        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull Post_Adapter_NC.CustomViewHolder holder, final int position) {
        post_data_nc = arrayList.get(position);
        holder.title.setText(post_data_nc.getTitle());
//        holder.content.setText(post_data_nc.getContent());
        holder.date.setText(post_data_nc.getDate().substring(5));
        holder.wright.setText(post_data_nc.getWrighter());
        holder.title_theme.setText(post_data_nc.getTitletheme());
        if (LoginActivity.user_id.equals("admin")||arrayList.get(position).getId().equals(LoginActivity.user_id)) {
            holder.edit.setVisibility(View.VISIBLE);
            holder.delete.setVisibility(View.VISIBLE);


        } else {
            holder.edit.setVisibility(View.INVISIBLE);
            holder.delete.setVisibility(View.INVISIBLE);

        }


        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                builder.setTitle("삭제 확인");
                builder.setMessage("삭제하시겠습니까?");
                builder.setNegativeButton("예", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        SharedPreferences sharedPreferences;
                        SharedPreferences.Editor editor;
                        sharedPreferences = view.getContext().getSharedPreferences("News_rcv", Context.MODE_PRIVATE);
                        editor = sharedPreferences.edit();
                        editor.remove(arrayList.get(position).getDate());
                        arrayList.remove(position);
                        notifyItemRemoved(position);
                        Toast.makeText(view.getContext().getApplicationContext(),"삭제가 완료되었습니다.",Toast.LENGTH_SHORT).show();
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
        holder.edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(view.getContext(), "수정", Toast.LENGTH_SHORT).show();
                String edittile = arrayList.get(position).getTitle().toString();
                String editcontent = arrayList.get(position).getContent().toString();
                String editdate = arrayList.get(position).getDate().toString();
                String uri = arrayList.get(position).getUri();
                Post_Data_NC a = arrayList.get(position);
                Log.i("수정", edittile);
                Log.i("수정", editcontent);
                Log.i("수정", editdate);
                Intent intent = new Intent(view.getContext(), Post_news.class);
                intent.putExtra("titleedit", edittile);
                intent.putExtra("contentedit", editcontent);
                intent.putExtra("dateedit", editdate);
                intent.putExtra("number", position);
                intent.putExtra("uri",uri);

                ((Activity) view.getContext()).startActivityForResult(intent, REQUEST_CODE_SUJUNG);

                posinum = arrayList.get(position).toString();
                Log.i("정보태그", String.valueOf(position));

            }
        });
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), Post_view2.class);

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
                intent.putExtra("uri",arrayList.get(position).getUri());

                Log.i("뷰클릭 제발", arrayList.get(position).getId());

                Log.i("뷰클릭 숫자", String.valueOf(position));

                ((Activity) view.getContext()).startActivity(intent);
                Log.i("뷰클릭 제발", arrayList.get(position).getId());


            }
        });

//        holder.itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(view.getContext(), Post_view2.class);
//                intent.putExtra("number", position);
//                intent.putExtra("title", post_data_nc.getTitle());
//                intent.putExtra("content", post_data_nc.getContent());
//                intent.putExtra("name", post_data_nc.getName());
//                intent.putExtra("birth", post_data_nc.getBirthday());
//                intent.putExtra("height", post_data_nc.getHeight());
//                intent.putExtra("weight", post_data_nc.getWeight());
//                intent.putExtra("team", post_data_nc.getTeam());
//                intent.putExtra("date", post_data_nc.getDate());
//                intent.putExtra("title_theme",post_data_nc.getTitletheme());
//                view.getContext().startActivity(intent);
//            }
//        });


        //    holder.title.setText(arrayList.get(position).getTitle());
//        holder.content.setText(arrayList.get(position).getContent());


//        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
//            @Override
//            public boolean onLongClick(View view) {
//                itemposition = holder.getAdapterPosition();
//                return false;
//            }
//        });


//
    }

    @Override
    public int getItemCount() {
        Log.i("데이터", "getItem");

        return arrayList.size();
//                (null != arrayList ? arrayList.size() : 0);
    }

    public void remove(int position) {
        try {
            arrayList.remove(position);
            notifyItemRemoved(position);
        } catch (IndexOutOfBoundsException ex) {
            ex.printStackTrace();
        }
    }

    public class CustomViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        TextView content;
        TextView date;
        TextView wright;
        TextView title_theme;
        ImageView edit;
        ImageView delete;

        public CustomViewHolder(@NonNull View itemView) {
            super(itemView);
            this.title = (TextView) itemView.findViewById(R.id.post_titl);
//            this.content = (TextView) itemView.findViewById(R.id.post_position1);
            this.date = (TextView) itemView.findViewById(R.id.post_date);
            this.wright = (TextView) itemView.findViewById(R.id.post_content);
            this.title_theme = (TextView) itemView.findViewById(R.id.title_theme);
            this.edit = (ImageView) itemView.findViewById(R.id.post_edit_button);
            this.delete = (ImageView) itemView.findViewById(R.id.post_delete_button);


            itemView.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) {
                        Log.d("위치확인 ", "" + arrayList.get(position).getTitle() + "위치" + position);
                        Log.d("위치확인 ", "" + arrayList.get(position).getContent() + "위치" + position);
                        Log.d("위치확인 ", "" + arrayList.get(position).getDate() + "위치" + position);
//                        Intent intent = new Intent(view.getContext(), Post_view2.class);
//                        intent.putExtra("number", position);
//                        intent.putExtra("title", post_data_nc.getTitle());
//                        intent.putExtra("content", post_data_nc.getContent());
//                        intent.putExtra("name", post_data_nc.getName());
//                        intent.putExtra("birth", post_data_nc.getBirthday());
//                        intent.putExtra("height", post_data_nc.getHeight());
//                        intent.putExtra("weight", post_data_nc.getWeight());
//                        intent.putExtra("team", post_data_nc.getTeam());
//                        intent.putExtra("date", post_data_nc.getDate());
//                        intent.putExtra("title_theme", post_data_nc.getTitletheme());
//                        view.getContext().startActivity(intent);

                        if (mListener != null) {
                            mListener.onItemClick(view, position);
                            Log.d("위치확인2 ", "" + arrayList.get(position).getTitle().toString());
                            Log.d("위치확인2 ", "" + arrayList.get(position).getContent().toString());
                            Log.d("위치확인2 ", "" + arrayList.get(position).getDate().toString());

                        }
                    }
                }

            });
//            area.findViewById(R.id.post_position1);
//            date.findViewById(R.id.post_date);

        }


    }

}








