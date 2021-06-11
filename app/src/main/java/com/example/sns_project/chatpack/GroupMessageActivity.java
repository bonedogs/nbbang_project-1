package com.example.sns_project.chatpack;

import android.os.Bundle;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.sns_project.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ServerValue;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.NotNull;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

public class GroupMessageActivity extends AppCompatActivity {
//    Map<String, UserModel> users = new HashMap<>();
//    String destinationRoom;
//    String uid;
//    EditText editText;
//
//    private DatabaseReference databaseReference;
//    private ValueEventListener valueEventListener;
//    private RecyclerView recyclerView;
//    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy.MM.dd HH:mm");
//
//    List<ChatModel.Comment> comments = new ArrayList<>();
//    int peopleCount =0;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState){
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_message);
//        destinationRoom = getIntent().getStringExtra("destinationRoom");
//        uid = FirebaseAuth.getInstance().getCurrentUser().getUid();
//        editText = (EditText)findViewById(R.id.groupMessageActivity_editText);
//        FirebaseDatabase.getInstance().getReference().child("users").addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                for(DataSnapshot item : dataSnapshot.getChildren()) {
//                    users.put(item.getKey(),item.getValue(UserModel.class));
//                }
//                init();
//                recyclerView = (RecyclerView)findViewById(R.id.groupMessageActivity_recyclerview);
//                recyclerView.setAdapter(new GroupMessageRecyclerViewAdapter());
//                recyclerView.setLayoutManager(new LinearLayoutManager(GroupMessageActivity.this));
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });
//    }
//    void init() {
//        //버튼 다시생성
////        Button button = (Button) findViewById(R.id.);
////        button.setOnClickListener(new View.OnClickListener() {
////            @Override
////            public void onClick(View view) {
//                ChatModel.Comment comment = new ChatModel.Comment();
//                comment.uid = uid;
//                comment.message = editText.getText().toString();
//                comment.timestamp = ServerValue.TIMESTAMP;
//                FirebaseDatabase.getInstance().getReference().child("chatrooms").child(destinationRoom).child("comments").push().setValue(comment).addOnCompleteListener(new OnCompleteListener<Void>() {
//                    @Override
//                    public void onComplete(@NonNull @NotNull Task<Void> task) {
//                        editText.setText("");
//                        FirebaseDatabase.getInstance().getReference().child("chatrooms").child(destinationRoom).child("users").addListenerForSingleValueEvent(new ValueEventListener() {
//                            @Override
//                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                                Map<String, Boolean> map = (Map<String, Boolean>) dataSnapshot.getValue();
//                                for (String item : map.keySet()) {
//                                    if (item.equals(uid)) {
//                                        continue;
//                                    }
////                                    sendGcm(users.get(item).pushToken);
//                                }
//                                editText.setText("");
//                            }
//                            @Override
//                            public void onCancelled(@NonNull DatabaseError error) {
//                            }
//                        });
//                    }
//                });
////            }
////        });
//    }
//
//
////    void sendGcm(String pushToken){
////        Gson gson = new Gson();
////
////        String userName = FirebaseAuth.getInstance().getCurrentUser().getDisplayName();
////        NotificationModel notificationModel = new NotificationModel();
////        notificationModel.to = pushToken;
////        notificationModel.notification.title = userName;
////        notificationModel.notification.text = editText.getText().toString();
////        notificationModel.data.text = editText.getText().toString();
////        notificationModel.data.title = userName;
////
////        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf8"),gson.toJson(notificationModel));
////
////        Request request = new Request.Builder()
////                .header("Content-Type","application/json")
////                .addHeader("Authorization","key=AIzaSyDNFs9vhpZ")
////    }
//class GroupMessageRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
//
//    public GroupMessageRecyclerViewAdapter(){
//        getMessageList();
//    }
//    void getMessageList(){
//        databaseReference = FirebaseDatabase.getInstance().getReference().child("chatrooms").child(destinationRoom).child("comments");
//        valueEventListener = databaseReference.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                comments.clear();
//                Map<String, Object> readUsersMap = new HashMap<>();
//
//                for (DataSnapshot item : dataSnapshot.getChildren()) {
//                    String key = item.getKey();
//                    ChatModel.Comment comment_origin = item.getValue(ChatModel.Comment.class);
//                    ChatModel.Comment comment_motify = item.getValue(ChatModel.Comment.class);
//                    comment_motify.readUsers.put(uid, true);
//
//                    readUsersMap.put(key, comment_motify);
//                    comments.add(comment_origin);
//                }
//                if (!comments.get(comments.size() - 1).readUsers.containsKey(uid)) {
//                    FirebaseDatabase.getInstance().getReference().child("chatrooms").child(destinationRoom).child("comments")
//                            .updateChildren(readUsersMap).addOnCompleteListener(new OnCompleteListener<Void>() {
//                        @Override
//                        public void onComplete(@NonNull Task<Void> task) {
//                            notifyDataSetChanged();
//                            recyclerView.scrollToPosition(comments.size() - 1);
//                        }
//                    });
//                } else {
//                    notifyDataSetChanged();
//                    recyclerView.scrollToPosition(comments.size() - 1);
//                }
//                //메세지 갱신
//            }
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });
//    }
//    @Override
//    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
//        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_message,parent,false);
//        return new GroupMessageViewHolder(view);
//    }
//
//    @Override
//    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position){
//        GroupMessageViewHolder messageViewHolder = ((GroupMessageViewHolder) holder);
//        //내가 보낸 메세지
//        if (comments.get(position).uid.equals(uid)) {
//            messageViewHolder.textView_message.setText(comments.get(position).message);
//            messageViewHolder.textView_message.setBackgroundResource(R.drawable.rightbubble);
//            messageViewHolder.destination.setVisibility(View.INVISIBLE);
//            messageViewHolder.linearLayout_main.setGravity(Gravity.RIGHT);
//            setReadCounter(position, messageViewHolder.readCounter_left);
//            //상대가 보낸 메세지
//        }else{
//            Glide.with(holder.itemView.getContext())
//                    .load(users.get(comments.get(position).uid).profileImageUrl)
//                    .apply(new RequestOptions().circleCrop())
//                    .into(messageViewHolder.imageView_profile);
//            messageViewHolder.textView_name.setText(users.get(comments.get(position).uid).userName);
//            messageViewHolder.destination.setVisibility(View.VISIBLE);
//            messageViewHolder.textView_message.setBackgroundResource(R.drawable.leftbubble);
//            messageViewHolder.textView_message.setText(comments.get(position).message);
//            messageViewHolder.textView_message.setTextSize(25);
//            messageViewHolder.linearLayout_main.setGravity(Gravity.LEFT);
//            setReadCounter(position, messageViewHolder.readCounter_right);
//        }
//        long unixTime = (long)comments.get(position).timestamp;
//        Date date = new Date(unixTime);
//        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("Asia/Seoul"));
//        String time = simpleDateFormat.format(date);
//        messageViewHolder.textView_timestamp.setText(time);
//    }
//    void setReadCounter(final int position, TextView textView){
//        if(peopleCount ==0) {
//            FirebaseDatabase.getInstance().getReference().child("chatrooms").child(destinationRoom).child("users").addListenerForSingleValueEvent(new ValueEventListener() {
//                @Override
//                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                    Map<String, Boolean> users = (Map<String, Boolean>) dataSnapshot.getValue();
//                    peopleCount = users.size();
//                    int count = users.size() - comments.get(position).readUsers.size();
//                    if (count > 0) {
//                        textView.setVisibility(View.VISIBLE);
//                        textView.setText(String.valueOf(count));
//                    } else {
//                        textView.setVisibility(View.INVISIBLE);
//                    }
//                }
//                @Override
//                public void onCancelled(@NonNull DatabaseError error) {
//
//                }
//            });
//        }else{
//            int count = peopleCount - comments.get(position).readUsers.size();
//            if(count>0){
//                textView.setVisibility(View.VISIBLE);
//                textView.setText(String.valueOf(count));
//            }else{
//                textView.setVisibility(View.INVISIBLE);
//            }
//        }
//    }
//
//    @Override
//    public int getItemCount(){
//        return comments.size();
//    }
//
//    private class GroupMessageViewHolder extends RecyclerView.ViewHolder{
//        public TextView textView_message;
//        public TextView textView_name;
//        public ImageView imageView_profile;
//        public LinearLayout destination;
//        public LinearLayout linearLayout_main;
//        public TextView textView_timestamp;
//        public TextView readCounter_left;
//        public TextView readCounter_right;
//
//        public GroupMessageViewHolder(View view){
//            super(view);
//
//            textView_message = (TextView)view.findViewById(R.id.textView_message);
//            textView_name = (TextView)view.findViewById(R.id.textView_name);
//            imageView_profile = (ImageView)view.findViewById(R.id.imageView_profile);
//            destination = (LinearLayout)view.findViewById(R.id.destination);
//            linearLayout_main = (LinearLayout)view.findViewById(R.id.messageItem_linearlayout_main);
//            textView_timestamp = (TextView)view.findViewById(R.id.textview_timestamp);
//            readCounter_left = (TextView)view.findViewById(R.id.textview_readCounter_left);
//            readCounter_right = (TextView)view.findViewById(R.id.textview_readCounter_right);
//
//        }
//    }
//}
}
