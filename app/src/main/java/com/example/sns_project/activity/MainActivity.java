package com.example.sns_project.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.sns_project.R;
import com.example.sns_project.chattest.StartFragment;
import com.example.sns_project.frag3;
import com.example.sns_project.noticepack.NoticeConctectFragment;
import com.example.sns_project.noticepack.NoticeFragment;
import com.example.sns_project.postpack.PostFragment;
import com.example.sns_project.setting.SettingFragment;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements NoticeFragment.OnNoticeSelectedListener {
    private static final String TAG = "MainActivity";
    private String username;

    private BottomNavigationView bottomNavigationView; //바텀 네비게이션 뷰
    private FragmentManager fm;
    private FragmentTransaction ft;
    private NoticeFragment frag1;
    private PostFragment frag2;
    private frag3 frag3;
    private StartFragment frag4;
    private SettingFragment frag5;
    private TextView user_name;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        if(user == null) {
            //메인으로 시작했는데, 로그인 되어있지 않으면 로그인액티비티 실행
            startActivity(LoginActivity.class);
        }else{
            //로그인 했을 시,
//            startActivity(PostTableActivity.class);
            FirebaseFirestore db = FirebaseFirestore.getInstance();
            DocumentReference docRef = db.collection("users").document(user.getUid());
            docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                    if (task.isSuccessful()) {
                        DocumentSnapshot document = task.getResult();
                        if(document != null){
                            if (document.exists()) {
                                Log.d(TAG, "DocumentSnapshot data: " + document.getData());
                            } else {
                                Log.d(TAG, "No such document");
                                startActivity(MemberInitActivity.class);
                            }
                        }
                    } else {
                        Log.d(TAG, "get failed with ", task.getException());
                    }
                }
            });
        }
        findViewById(R.id.logoutButton).setOnClickListener(onClickListener);


        bottomNavigationView = findViewById(R.id.bottom_nav);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.action_1:
                        setFrag(0);
                        break;
                    case R.id.action_2:
                        setFrag(1);
                        break;
                    case R.id.action_4:
                        setFrag(3);
                        break;
                    case R.id.action_5:
                        setFrag(4);
                        break;
                }
                return true;
            }
        });

        frag1 = new NoticeFragment();
        frag2 = new PostFragment();
        frag3 = new frag3();
        frag4 = new StartFragment();
        frag5 = new SettingFragment();
        setFrag(0); //가장 처음 fragment를 지정
//
//        getRestOfTheUserAttributes();
//        user_name.setText(username);
    }

//    public void getRestOfTheUserAttributes() {
//        DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();
//        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
//        DatabaseReference additionalUserInfoRef = rootRef.child("users");
//        Query userQuery = additionalUserInfoRef.orderByChild("user_id").equalTo(user.getUid());
//        ValueEventListener valueEventListener = new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                for (DataSnapshot ds : dataSnapshot.getChildren()) {
//                    Map<String, Object> map = new HashMap<>();
//                    username = ds.child("name").getValue(String.class);
//                }
//            }
//
//            @Override
//            public void onCancelled(@NonNull @NotNull DatabaseError error) {
//            }
//        };
//    }

            // fragment 교체되는 함수
    private void setFrag(int n){
        fm = getSupportFragmentManager();
        ft = fm.beginTransaction(); //실질적인 fragment 교체하려는 행위
        switch (n) {
            case 0:
                ft.replace(R.id.main_frame, frag1);
                ft.commit(); //저장
                break;
            case 1:
                ft.replace(R.id.main_frame, frag2);
                ft.commit(); //저장
                break;
            case 3:
                ft.replace(R.id.main_frame, frag4);
                ft.commit(); //저장
                break;
            case 4:
                ft.replace(R.id.main_frame, frag5);
                ft.commit(); //저장
                break;
        }
    }


    public void onNoticeSelected(int position) {
        if (findViewById(R.id.main_frame) != null) {
            NoticeConctectFragment newFragment = new NoticeConctectFragment();
            Bundle args = new Bundle();
            args.putInt("position", position);
            newFragment.setArguments(args);

            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.main_frame, newFragment);
            transaction.addToBackStack(null);
            transaction.commit();
        }
    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch(v.getId()){
                case R.id.logoutButton:
                    FirebaseAuth.getInstance().signOut();
                    startActivity(LoginActivity.class);
                    break;
            }
        }
    };

    private void startActivity(Class c){
        Intent intent = new Intent(this, c);
        startActivity(intent);
    }

}