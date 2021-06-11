package com.example.sns_project.postpack;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.sns_project.R;
import com.example.sns_project.WriteInfo;
import com.example.sns_project.activity.WritePostActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class PostFragment extends Fragment {
    private static final String TAG = "PostFragment";


    public PostFragment() {
        // Required empty public constructor
    }



    private ListView postListView;
    private PostListAdapter adapter;
    private ArrayList<WriteInfo> postList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View postpage = inflater.inflate(R.layout.fragment_post, container, false);
        postListView = postpage.findViewById(R.id.postListView);
        postList=new ArrayList<>();

        //        게시글 쓰기 버튼 클릭
        postpage.findViewById(R.id.floatingActionButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), WritePostActivity.class);
                startActivity(intent);
            }
        });


//        postpage.findViewById(R.id.btn_enter_chat).setOnClickListener(new View.OnClickListener(){
//            @Override
//            public void onClick(View view){
//                Intent intent = new Intent(getActivity(), MessageActivity.class);
//                startActivity(intent);
//            }
//        });

        FirebaseFirestore db = FirebaseFirestore.getInstance();

        db.collection("posts")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Log.d(TAG, document.getId() + " => " + document.getData());
                                postList.add(new WriteInfo(
                                        document.getData().get("title").toString(),
                                        document.getData().get("publisher").toString(),
                                        document.getData().get("writedate").toString(),
                                        document.getData().get("deaddate").toString(),
                                        Integer.parseInt(document.getData().get("minnum").toString()),
                                        document.getData().get("region").toString(),
                                        document.getData().get("store").toString(),
                                        document.getData().get("category").toString(),
                                        document.getData().get("object").toString(),
                                        document.getData().get("contents").toString()));
                            }
                            adapter = new PostListAdapter(getActivity(), postList);
                            postListView.setAdapter(adapter);
                        } else {
                            Log.d(TAG, "Error getting documents: ", task.getException());
                        }
                    }
                });



        return postpage;
    }
   // 게시글 읽기용에 붙여넣기
//    View.OnClickListener onClickListener = v -> {
//        switch (v.getId()) {
//            case R.id.btn_enter_chat:
//                Intent intent = new Intent(v.getContext(), MessageActivity.class);
//                startActivity(intent);
//                break;
//        }
//    };
}