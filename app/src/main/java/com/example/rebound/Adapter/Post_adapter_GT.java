package com.example.rebound.Adapter;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Handler;
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
import com.example.rebound.data.Post_Data;
import com.example.rebound.post.Post_Guest;
import com.example.rebound.post.Post_View;
import com.example.rebound.R;

import java.util.ArrayList;

import static android.content.Intent.FLAG_GRANT_PERSISTABLE_URI_PERMISSION;


public class Post_adapter_GT extends RecyclerView.Adapter<Post_adapter_GT.CustomViewHolder> {
    LoginActivity loginActivity;
    String button;
    private ArrayList<Post_Data> arrayList;
    private ArrayList<Post_Data> unarrayList;
    Post_adapter_GT.CustomViewHolder holder;
    int REQUEST_CODE_SUJUNG = 156;
    String posinum;
    Post_Data post_data;
    int posi;
    Handler handler = new Handler();


    public interface OnClickListener {
        void onClick(int pos);
    }

    public interface OnItemClickListener {
        void onItemClick(View v, int position);
    }

    private Post_adapter_GT.OnItemClickListener mListener = null;

//    // OnItemClickListener 리스너 객체 참조를 어댑터에 전달하는 메서드
//    public void setOnItemClickListener(OnItemClickListener listener) {
//        this.mListener = listener ;
//    }
//    public void setArrayList(ArrayList<Post_Data> list, Context context) {
//        this.arrayList = list;
//        this.context = context;
//
//    }


//    @Override
//    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {  // 3. 메뉴 추가
//
//
//        MenuItem Edit = menu.add(Menu.NONE, 1001, 1, "편집");
//        MenuItem Delete = menu.add(Menu.NONE, 1002, 2, "삭제");
//        Edit.setOnMenuItemClickListener(onEditMenu);
//        Delete.setOnMenuItemClickListener(onEditMenu);
//
//    }

//    // 4. 캔텍스트 메뉴 클릭시 동작을 설정
//    private final MenuItem.OnMenuItemClickListener onEditMenu = new MenuItem.OnMenuItemClickListener() {
//        @Override
//        public boolean onMenuItemClick(MenuItem item) {
//
//
//            switch (item.getItemId()) {
//                case 1001:
//
//                    AlertDialog.Builder builder = new AlertDialog.Builder(context);
//                    View view = LayoutInflater.from(context)
//                            .inflate(R.layout.edit_box, null, false);
//                    builder.setView(view);
//                    final Button ButtonSubmit = (Button) view.findViewById(R.id.button_dialog_submit);
//                    final EditText editTextID = (EditText) view.findViewById(R.id.edittext_dialog_id);
//                    final EditText editTextEnglish = (EditText) view.findViewById(R.id.edittext_dialog_endlish);
//
//
//                    editTextID.setText(arrayList.get(getAdapterPosition()).getId());
//                    editTextEnglish.setText(arrayList.get(getAdapterPosition()).getEnglish());
//
//
//                    final AlertDialog dialog = builder.create();
//                    ButtonSubmit.setOnClickListener(new View.OnClickListener() {
//                        public void onClick(View v) {
//                            String strID = editTextID.getText().toString();
//                            String strEnglish = editTextEnglish.getText().toString();
//
//
//                            Post_Data post_data = new Post_Data(strID, strEnglish,  );
//
//                            arrayList.set(getAdapterPosition(), dict);
//                            notifyItemChanged(getAdapterPosition());
//
//                            dialog.dismiss();
//                        }
//                    });
//
//                    dialog.show();
//
//                    break;
//
//                case 1002:
//
//                    arrayList.remove(getAdapterPosition());
//                    notifyItemRemoved(getAdapterPosition());
//                    notifyItemRangeChanged(getAdapterPosition(), arrayList.size());
//
//                    break;
//
//            }
//            return true;
//        }
//    };


//    private MenuItem.OnMenuItemClickListener onEditMenu = new MenuItem.OnMenuItemClickListener() {
//        @Override
//        public boolean onMenuItemClick( MenuItem menuItem) {
//            switch (menuItem.getItemId()) {
//                case R.id.edit_jung:
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
//                case R.id.delete:
//                    arrayList.remove(itemposition);
//
//
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


    public Post_adapter_GT(ArrayList<Post_Data> arrayList) {
        this.arrayList = arrayList;

    }

    @NonNull
    @Override
    public Post_adapter_GT.CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rclv_item, parent, false);


        holder = new Post_adapter_GT.CustomViewHolder(view);


        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull Post_adapter_GT.CustomViewHolder holder, final int position) {
        post_data = arrayList.get(position);
        holder.title.setText(post_data.getTitle());
//        holder.content.setText(post_data.getContent());
        holder.date.setText(post_data.getDate().substring(5));
        holder.wright.setText(post_data.getWrighter());
        holder.title_theme.setText(post_data.getTitletheme());




        if (LoginActivity.user_id.equals("admin") || arrayList.get(position).getId().equals(LoginActivity.user_id)) {
            holder.edit.setVisibility(View.VISIBLE);
            holder.delete.setVisibility(View.VISIBLE);


        } else {
            holder.edit.setVisibility(View.INVISIBLE);
            holder.delete.setVisibility(View.INVISIBLE);

        }


        if (post_data.getTitletheme().equals("[급 구]")) {

            holder.title_theme.setTextColor(Color.parseColor("#FF0000"));

        } else {
            holder.title_theme.setTextColor(Color.parseColor("#000000"));
        }

        if(post_data.getEnd()==1){
            holder.end.setVisibility(View.VISIBLE);
            holder.title_theme.setVisibility(View.GONE);
        }
        else if(post_data.getEnd()==0) {
            holder.end.setVisibility(View.GONE);
            holder.title_theme.setVisibility(View.VISIBLE);
        }
        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {

                androidx.appcompat.app.AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
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
        holder.edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(view.getContext(), "수정", Toast.LENGTH_SHORT).show();
                String edittile = arrayList.get(position).getTitle().toString();
                String editcontent = arrayList.get(position).getContent().toString();
                String editdate = arrayList.get(position).getDate().toString();
                String editarea = arrayList.get(position).getArea().toString();
                String editposi = arrayList.get(position).getPosition().toString();

                Post_Data a = arrayList.get(position);
                Log.i("수정", edittile);
                Log.i("수정", editcontent);
                Log.i("수정", editdate);
                Intent intent = new Intent(view.getContext(), Post_Guest.class);
                intent.putExtra("titleedit", edittile);
                intent.putExtra("contentedit", editcontent);
                intent.putExtra("dateedit", editdate);
                intent.putExtra("number", position);
                intent.putExtra("editarea", editarea);
                intent.putExtra("editposi", editposi);
                ((Activity) view.getContext()).startActivityForResult(intent, REQUEST_CODE_SUJUNG);

                posinum = arrayList.get(position).toString();
                Log.i("정보태그", String.valueOf(position));

            }
        });
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
                intent.putExtra("aoa", arrayList.get(position).getUri());
                intent.putExtra("area", arrayList.get(position).getArea());
                intent.putExtra("needposi", arrayList.get(position).getPosition());
                intent.putExtra("x", arrayList.get(position).getX());
                intent.putExtra("y", arrayList.get(position).getY());
                intent.putExtra("end",arrayList.get(position).getEnd());
                intent.putExtra("end_date",arrayList.get(position).getEnd_date());

                intent.addFlags(FLAG_GRANT_PERSISTABLE_URI_PERMISSION);
                intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                intent.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);


                Log.i("뷰클릭 uri", arrayList.get(position).getUri());

                Log.i("뷰클릭 숫자", String.valueOf(position));

                ((Activity) view.getContext()).startActivity(intent.addFlags(FLAG_GRANT_PERSISTABLE_URI_PERMISSION));
                Log.i("뷰클릭 제발", arrayList.get(position).getId());
            }
        });



    }


//


    @Override
    public int getItemCount() {
        Log.i("데이터", "getItem");

//        return arrayList.size();
        return (null != arrayList ? arrayList.size() : 0);
    }

//    public void remove(int position) {
//
//        try {setOnItemClickListener(new OnItemClickListener() {
//            @Override
//            public void onItemClick(View v, int position) {
//                arrayList.remove(position);
//                notifyItemRemoved(position);
//            }
//        });
//
//        } catch (IndexOutOfBoundsException ex) {
//            ex.printStackTrace();
//        }
//    }

    public class CustomViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        TextView content;
        TextView date;
        TextView wright;
        TextView title_theme;
        ImageView edit;
        ImageView delete;
        TextView end;
        TextView area;

        public CustomViewHolder(@NonNull View itemView) {
            super(itemView);
            this.title = (TextView) itemView.findViewById(R.id.post_titl);
//            this.content = (TextView) itemView.findViewById(R.id.post_position1);
            this.date = (TextView) itemView.findViewById(R.id.post_date);
            this.wright = (TextView) itemView.findViewById(R.id.post_content);
            this.title_theme = (TextView) itemView.findViewById(R.id.title_theme);
            this.edit = (ImageView) itemView.findViewById(R.id.post_edit_button);
            this.delete = (ImageView) itemView.findViewById(R.id.post_delete_button);
            this.end = (TextView) itemView.findViewById(R.id.title_theme2);

//            if (arrayList.get(posi).getId().equals(LoginActivity.user_id)){
//                edit.se
//
//            }
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = getAdapterPosition();
                    if (pos != RecyclerView.NO_POSITION) {

                        if (mListener != null) {
                            mListener.onItemClick(v, pos);
                        }
                    }
                }
            });
//            area.findViewById(R.id.post_position1);
//            date.findViewById(R.id.post_date);

        }
    }


//        @Override
//        public void onCreateContextMenu(ContextMenu contextMenu, View view, ContextMenu.ContextMenuInfo contextMenuInfo) {
//            MenuItem Edit = contextMenu.add(Menu.NONE, 101, 1, "수정");
//            MenuItem delete = contextMenu.add(Menu.NONE, 102, 2, "삭제");
//            Edit.setOnMenuItemClickListener(onEditmenu);
//            delete.setOnMenuItemClickListener(onEditmenu);


//        private final MenuItem.OnMenuItemClickListener onEditmenu = new MenuItem.OnMenuItemClickListener() {
//            @Override
//            public boolean onMenuItemClick(MenuItem menuItem) {
//                switch (menuItem.getItemId()) {
//                    case 101:
//                        AlertDialog.Builder builder = new AlertDialog.Builder(context);
//                        View view = LayoutInflater.from(context).inflate(R.layout.dial, null, false);
//                        builder.setView(view);
//                        final Button ButtonSubmit = (Button) view.findViewById(R.id.post_guest_application);
//                        final EditText editTextID = (EditText) view.findViewById(R.id.edittext_dialog_id);
//                        final EditText editTextEnglish = (EditText) view.findViewById(R.id.edittext_dialog_endlish);
//
//                        editTextID.setText(arrayList.get(getAdapterPosition()).getTitle());
//                        editTextEnglish.setText(arrayList.get(getAdapterPosition()).getContent());
//
//                        final AlertDialog dialog = builder.create();
//                        ButtonSubmit.setOnClickListener(new View.OnClickListener() {
//                            @Override
//                            public void onClick(View view) {
//                                String strti = editTextID.getText().toString();
//                                String strco = editTextEnglish.getText().toString();
//
//                                Post_Data post_data = new Post_Data(strti, strco, "");
//                                notifyItemChanged(getAdapterPosition());
//                                dialog.dismiss();
//
//
//                            }
//
//                        });
//                        dialog.show();
//                        break;
//                    case 102:
//                        new AlertDialog.Builder(context)
//                                .setTitle("Delete entry")
//                                .setMessage("정말 이걸 삭제할건가요?")
//                                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
//                                    public void onClick(DialogInterface dialog, int which) {
//                                        arrayList.remove(getAdapterPosition());
//                                        notifyItemRemoved(getAdapterPosition());
//                                        notifyItemRangeChanged(getAdapterPosition(), arrayList.size());
//
//
//                                    }
//                                })
//                                .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
//                                    public void onClick(DialogInterface dialog, int which) {
//                                        // 아무일도 안 일어남
//                                    }
//                                })
//                                .setIcon(android.R.drawable.ic_dialog_alert)
//                                .show();
//
//
//                }
//                return true;
//            }
//        };

//    public View getView(int pos, View convertView, ViewGroup parent){
//        ImageView imageView = (ImageView)convertView.findViewById(R.id.post_edit_button);
//        ImageView imageView1 = (ImageView)convertView.findViewById(R.id.post_delete_button);
//
//
//
//
//    }

}






