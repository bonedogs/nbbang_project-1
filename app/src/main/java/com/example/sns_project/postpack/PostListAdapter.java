package com.example.sns_project.postpack;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.sns_project.R;
import com.example.sns_project.WriteInfo;
import com.example.sns_project.chattest.ChatActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;

public class PostListAdapter extends BaseAdapter {
//    final private ArrayList<WriteInfo> mDataset;
//    private Activity activity;
    private Context context;
    private ArrayList<WriteInfo> postList;

    public PostListAdapter(Context context, ArrayList<WriteInfo> postList) {
        this.context = context;
        this.postList = postList;
    }

    @Override
    public int getCount() {
        return postList.size();
    }

    @Override
    public Object getItem(int position) {
        return postList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = View.inflate(context, R.layout.item_post, null);

        TextView title = (TextView)v.findViewById(R.id.title);
        TextView publisher = (TextView)v.findViewById(R.id.publisher);
        TextView writedate = (TextView)v.findViewById(R.id.writedate);
        TextView deaddate = (TextView)v.findViewById(R.id.deaddate);
        TextView minnum = (TextView)v.findViewById(R.id.minnum);
        TextView region = (TextView)v.findViewById(R.id.region);
        TextView store = (TextView)v.findViewById(R.id.store);
        TextView category = (TextView)v.findViewById(R.id.category);
        TextView object = (TextView)v.findViewById(R.id.object);
        TextView contents = (TextView)v.findViewById(R.id.contents);

        title.setText(postList.get(position).getTitle());
        publisher.setText(postList.get(position).getPublisher());
        writedate.setText(postList.get(position).getWritedate());
        deaddate.setText(postList.get(position).getDeaddate());
        minnum.setText(String.valueOf(postList.get(position).getMinnum()));
        region.setText(postList.get(position).getRegion());
        store.setText(postList.get(position).getStore());
        category.setText(postList.get(position).getCategory());
        object.setText(postList.get(position).getObject());
        contents.setText(postList.get(position).getContents());

        v.findViewById(R.id.btn_enter_chat).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

                Intent intent = new Intent(context, ChatActivity.class);
                intent.putExtra("chatName", title.getText());
                intent.putExtra("userName", user.getUid());
                context.startActivity(intent);
            }
        });

        v.setTag(postList.get(position).getTitle());
        return v;
    }


//    public static class GalleryViewHolder extends RecyclerView.ViewHolder{
//        public CardView cardView;
//        public GalleryViewHolder(CardView v){
//            super(v);
//            cardView = v;
//        }
//    }
//
//    public PostListAdapter(Activity activity, ArrayList<WriteInfo> myDataset){
//        mDataset = myDataset;
//        this.activity = activity;
//    }
//
//    @NonNull
//    public PostListAdapter.GalleryViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
//        CardView cardView = (CardView) LayoutInflater.from(parent.getContext()).inflate(R.layout.item_post2, parent, false);
//
//        final GalleryViewHolder galleryViewHolder = new GalleryViewHolder(cardView);
//        cardView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//            }
//        });
//
//        return galleryViewHolder;
//    }
//
//    public void onBindViewHolder(final GalleryViewHolder holder, int position) {
//        CardView cardView = holder.cardView;
//        TextView textView = cardView.findViewById(R.id.textView2);
//        textView.setText(mDataset.get(position).getTitle());
//    }
//
//    public int getItemCount(){
//        return mDataset.size();
//    }

}
