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
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ServerValue;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

//public class MessageActivity extends AppCompatActivity {
//    private String destinationUid;
//    private Button button;
//    private EditText editText;
//
//    private String uid;
//    private String chatRoomUid;
//
//    private RecyclerView recyclerView;
//
//    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy.MM.dd HH:mm");
//
//    private UserModel destinationUserModel;
//    private DatabaseReference databaseReference;
//    private ValueEventListener valueEventListener;
//    int peopleCount = 0;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState){
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_message);
//        uid = FirebaseAuth.getInstance().getCurrentUser().getUid(); //채팅을 요구하는 아이디, 즉 단말기에 로그인 된 UID
//        destinationUid = getIntent().getStringExtra("destinationUid"); //채팅을 받는 아이디
//        button = (Button)findViewById(R.id.btn_msg);
//        editText = (EditText)findViewById(R.id.editText_msg);
//
//        recyclerView = (RecyclerView)findViewById(R.id.recyclerview);
//        button.setOnClickListener(new View.OnClickListener(){
//            @Override
//            public void onClick(View view) {
//                ChatModel chatModel = new ChatModel();
//                chatModel.users.put(uid, true);
//                chatModel.users.put(destinationUid, true);
//
//                if (chatRoomUid == null) {
//                    FirebaseDatabase.getInstance().getReference().child("chatrooms").push().setValue(chatModel).addOnSuccessListener(new OnSuccessListener<Void>() {
//                        @Override
//                        public void onSuccess(Void aVoid) {
//                            checkChatRoom();
//                        }
//                    });
//                }else{
//                    ChatModel.Comment comment = new ChatModel.Comment();
//                    comment.uid=uid;
//                    comment.message = editText.getText().toString();
//                    comment.timestamp = ServerValue.TIMESTAMP;
//                    FirebaseDatabase.getInstance().getReference().child("chatrooms").child(chatRoomUid).child("comments").push().setValue(comment).addOnCompleteListener(new OnCompleteListener<Void>() {
//                        @Override
//                        public void onComplete(@NonNull Task<Void> task) {
//                            editText.setText("");
//                        }
//                    });
//                }
//            }
//        });
//        checkChatRoom();
//    }
////    void sendGcm(){
////        Gson gson = new Gson();
////
////        NotificationModel notificationModel = new NotificationModel();
////        notificationModel.to = destinationUserModel.pushToken;
////        notificationModel.notification.title = "보낸이 아이디";
////        notificationModel.notification.text = editText.getText().toString();
////
////        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf8"),gson.toJson(notificationModel));
////
////        Request request = new Request.Builder()
////                .header("Content-Type","application/json")
////                .addHeader("Authorization","key=AIzaSyDNFs9vhpZ")
////    }
//
//
//    void checkChatRoom(){
//        FirebaseDatabase.getInstance().getReference().child("chatrooms").orderByChild("users/"+uid).equalTo(true).addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                for(DataSnapshot item : dataSnapshot.getChildren()){
//                    ChatModel chatModel = item.getValue(ChatModel.class);
//                    if(chatModel.users.containsKey(destinationUid)&& chatModel.users.size()==2){
//                        chatRoomUid = item.getKey();
//                        button.setEnabled(true);
//                        recyclerView.setLayoutManager(new LinearLayoutManager(MessageActivity.this));
//                        recyclerView.setAdapter(new RecyclerViewAdapter());
//                    }
//                }
//            }
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });
//    }
//    class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
//        List<ChatModel.Comment> comments;
//
//        public RecyclerViewAdapter(){
//            comments = new ArrayList<>();
//            FirebaseDatabase.getInstance().getReference().child("users").child(destinationUid).addListenerForSingleValueEvent(new ValueEventListener() {
//                @Override
//                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                    destinationUserModel = dataSnapshot.getValue(UserModel.class);
//                    getMessageList();
//                }
//
//                @Override
//                public void onCancelled(@NonNull DatabaseError error) {
//                }
//            });
//        }
//
//        void getMessageList(){
//            databaseReference = FirebaseDatabase.getInstance().getReference().child("chatrooms").child(chatRoomUid).child("comments");
//            valueEventListener = databaseReference.addValueEventListener(new ValueEventListener() {
//                @Override
//                public void onDataChange(DataSnapshot dataSnapshot) {
//                    comments.clear();
//                    Map<String, Object> readUsersMap = new HashMap<>();
//
//                    for (DataSnapshot item : dataSnapshot.getChildren()) {
//                        String key = item.getKey();
//                        ChatModel.Comment comment_origin = item.getValue(ChatModel.Comment.class);
//                        ChatModel.Comment comment_motify = item.getValue(ChatModel.Comment.class);
//                        comment_motify.readUsers.put(uid, true);
//
//                        readUsersMap.put(key, comment_motify);
//                        comments.add(comment_origin);
//                    }
//                    if (!comments.get(comments.size() - 1).readUsers.containsKey(uid)) {
//
//                        FirebaseDatabase.getInstance().getReference().child("chatrooms").child(chatRoomUid).child("comments")
//                                .updateChildren(readUsersMap).addOnCompleteListener(new OnCompleteListener<Void>() {
//                            @Override
//                            public void onComplete(@NonNull Task<Void> task) {
//                                notifyDataSetChanged();
//                                recyclerView.scrollToPosition(comments.size() - 1);
//                            }
//                        });
//                    } else {
//                        notifyDataSetChanged();
//                        recyclerView.scrollToPosition(comments.size() - 1);
//                    }
//                    //메세지 갱신
//                }
//                @Override
//                public void onCancelled(@NonNull DatabaseError error) {
//
//                }
//            });
//        }
//
//        @Override
//        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
//            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_message,parent,false);
//            return new MessageViewHolder(view);
//        }
//
//        @Override
//        public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
//            MessageViewHolder messageViewHolder = ((MessageViewHolder) holder);
//            //내가 보낸 메세지
//            if (comments.get(position).uid.equals(uid)) {
//                messageViewHolder.textView_message.setText(comments.get(position).message);
//                messageViewHolder.textView_message.setBackgroundResource(R.drawable.rightbubble);
//                messageViewHolder.destination.setVisibility(View.INVISIBLE);
//                messageViewHolder.linearLayout_main.setGravity(Gravity.RIGHT);
//                setReadCounter(position, messageViewHolder.readCounter_left);
//            //상대가 보낸 메세지
//            }else{
//                Glide.with(holder.itemView.getContext())
//                        .load(destinationUserModel.profileImageUrl)
//                        .apply(new RequestOptions().circleCrop())
//                        .into(messageViewHolder.imageView_profile);
//                messageViewHolder.textView_name.setText(destinationUserModel.userName);
//                messageViewHolder.destination.setVisibility(View.VISIBLE);
//                messageViewHolder.textView_message.setBackgroundResource(R.drawable.leftbubble);
//                messageViewHolder.textView_message.setText(comments.get(position).message);
//                messageViewHolder.textView_message.setTextSize(25);
//                messageViewHolder.linearLayout_main.setGravity(Gravity.LEFT);
//                setReadCounter(position, messageViewHolder.readCounter_right);
//            }
//            long unixTime = (long)comments.get(position).timestamp;
//            Date date = new Date(unixTime);
//            simpleDateFormat.setTimeZone(TimeZone.getTimeZone("Asia/Seoul"));
//            String time = simpleDateFormat.format(date);
//            messageViewHolder.textView_timestamp.setText(time);
//        }
//        void setReadCounter(final int position, TextView textView){
//            if(peopleCount ==0) {
//                FirebaseDatabase.getInstance().getReference().child("chatrooms").child(chatRoomUid).child("users").addListenerForSingleValueEvent(new ValueEventListener() {
//                    @Override
//                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                        Map<String, Boolean> users = (Map<String, Boolean>) dataSnapshot.getValue();
//                        peopleCount = users.size();
//                        int count = users.size() - comments.get(position).readUsers.size();
//                        if (count > 0) {
//                            textView.setVisibility(View.VISIBLE);
//                            textView.setText(String.valueOf(count));
//                        } else {
//                            textView.setVisibility(View.INVISIBLE);
//                        }
//                    }
//
//                    @Override
//                    public void onCancelled(@NonNull DatabaseError error) {
//
//                    }
//                });
//            }else{
//                int count = peopleCount - comments.get(position).readUsers.size();
//                if(count>0){
//                    textView.setVisibility(View.VISIBLE);
//                    textView.setText(String.valueOf(count));
//                }else{
//                    textView.setVisibility(View.INVISIBLE);
//                }
//            }
//        }
//
//        @Override
//        public int getItemCount() {
//            return comments.size();
//        }
//
//        public class MessageViewHolder extends RecyclerView.ViewHolder{
//            public TextView textView_message;
//            public TextView textView_name;
//            public ImageView imageView_profile;
//            public LinearLayout destination;
//            public LinearLayout linearLayout_main;
//            public TextView textView_timestamp;
//            public TextView readCounter_left;
//            public TextView readCounter_right;
//
//            public MessageViewHolder(View view){
//                super(view);
//                textView_message = (TextView)view.findViewById(R.id.textView_message);
//                textView_name = (TextView)view.findViewById(R.id.textView_name);
//                imageView_profile = (ImageView)view.findViewById(R.id.imageView_profile);
//                destination = (LinearLayout)view.findViewById(R.id.destination);
//                linearLayout_main = (LinearLayout)view.findViewById(R.id.messageItem_linearlayout_main);
//                textView_timestamp = (TextView)view.findViewById(R.id.textview_timestamp);
//                readCounter_left = (TextView)view.findViewById(R.id.textview_readCounter_left);
//                readCounter_right = (TextView)view.findViewById(R.id.textview_readCounter_right);
//            }
//        }
//    }
//    @Override
//    public void onBackPressed(){
////        super.onBackPressed();
//        databaseReference.removeEventListener(valueEventListener);
//        finish();
//        overridePendingTransition(R.anim.fromleft, R.anim.toright);
//    }
//}
