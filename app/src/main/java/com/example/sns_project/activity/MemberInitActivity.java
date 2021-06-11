package com.example.sns_project.activity;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.bumptech.glide.Glide;
import com.example.sns_project.MemberInfo;
import com.example.sns_project.R;
import com.example.sns_project.activity.CameraActivity;
import com.example.sns_project.adaptor.GalleryAdapter;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;


public class MemberInitActivity extends AppCompatActivity {
    private static final String TAG="MemberInitActivity";
    private ImageView profileImageView;
    private String profilePath;
    FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_member_init);

        profileImageView = findViewById(R.id.profileImageView);
        profileImageView.setOnClickListener(onClickListener);

        findViewById(R.id.checkButton).setOnClickListener(onClickListener);
        findViewById(R.id.pictureButton).setOnClickListener(onClickListener);
        findViewById(R.id.galleryButton).setOnClickListener(onClickListener);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case 0: {
                if(resultCode==Activity.RESULT_OK) {
                    profilePath = data.getStringExtra("profilePath");
                    Glide.with(this).load(profilePath).centerCrop().override(500).into(profileImageView);
                }
                break;
            }
        }
    }

    View.OnClickListener onClickListener = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.checkButton:
                    profileUpdate();
                    break;
                case R.id.profileImageView:
                    CardView cardView = findViewById(R.id.buttonCardView);
                    if (cardView.getVisibility() == View.VISIBLE) {
                        cardView.setVisibility(View.GONE);
                    } else {
                        cardView.setVisibility(View.VISIBLE);
                    }
                    break;
                case R.id.pictureButton:
                    startActivity(CameraActivity.class);
                    break;
                case R.id.galleryButton:
                    if (ContextCompat.checkSelfPermission(MemberInitActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                        ActivityCompat.requestPermissions(MemberInitActivity.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 1);
                        if (ActivityCompat.shouldShowRequestPermissionRationale(MemberInitActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE)) {
                        } else {
                            startToast("권한을 허용해주세요.");
                        }
                    }else{
                        startActivity(GalleryActivity.class);
                    }
                    break;
            }
        }
    };

//    @Override
//    public void onRequestPermissionResult(int requestCode, String permissions[], int[] grantResults) {
//        switch (requestCode) {
//            case 1: {
//                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//                    startActivity(GalleryActivity.class);
//                } else {
//                    startToast("권한을 허용하세요.");
//                }
//            }
//        }
//    }

    private void profileUpdate() {
        final String name = ((EditText) findViewById(R.id.nameEditText)).getText().toString();
        final String phone = ((EditText) findViewById(R.id.phoneEditText)).getText().toString();
        final String birth = ((EditText) findViewById(R.id.birthEditText)).getText().toString();
        final String address = ((EditText) findViewById(R.id.addressEditText)).getText().toString();

        if (name.length() > 0 && phone.length() > 9 && birth.length() > 5 && address.length() > 0) {
            FirebaseStorage storage = FirebaseStorage.getInstance();
            StorageReference storageRef = storage.getReference();
            user = FirebaseAuth.getInstance().getCurrentUser();
            final StorageReference mountainImagesRef = storageRef.child("users/"+user.getUid()+"/profileImage.jpg");
            if(profilePath==null) {
                MemberInfo memberinfo = new MemberInfo(user.getUid(), name,phone,birth,address);
                uploader(memberinfo);
//                mInformation(memberinfo);
            }else{
                try {
                    InputStream stream = new FileInputStream(new File(profilePath));
                    UploadTask uploadTask = mountainImagesRef.putStream(stream);
                    uploadTask.continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
                        @Override
                        public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
                            if (!task.isSuccessful()) {
                                throw task.getException();
                            }
                            return mountainImagesRef.getDownloadUrl();
                        }
                    }).addOnCompleteListener(new OnCompleteListener<Uri>() {
                        @Override
                        public void onComplete(@NonNull Task<Uri> task) {
                            if (task.isSuccessful()) {
                                Uri downloadUri = task.getResult();

                                MemberInfo memberinfo = new MemberInfo(user.getUid(), name,phone,birth,address,downloadUri.toString());
                                uploader(memberinfo);
                            } else {
                                startToast("회원정보를 보내는데 실패하였습니다.");
                            }
                        }
                    });
                } catch(java.io.FileNotFoundException e){
                    Log.e("로그", "에러: "+e.toString());
                }
            }
        } else {
            startToast("회원정보를 입력해주세요.");
        }
    }

    private void startToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    private void startActivity(Class c){
        Intent intent = new Intent(this, c);
        startActivityForResult(intent,0);
    }

    private void uploader(MemberInfo memberInfo){
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("users").document(user.getUid()).set(memberInfo).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                startToast("회원정보가 등록되었습니다.");
                finish();
            }
        })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        startToast("회원정보등록에 실패하였습니다.");
                        Log.w(TAG, "Error writing document", e);
                    }
                });
    }
}