package com.example.sns_project.activity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.sns_project.R;
import com.example.sns_project.WriteInfo;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class WritePostActivity extends AppCompatActivity {
    private static final String TAG = "WritePostActivity";
    final static int CODE = 1;

    private FirebaseUser user;

    long now = System.currentTimeMillis();
    Date date = new Date(now);
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    private String spinnerresult;

    private TextView store;
    private String Deadline;

    Calendar myCalendar = Calendar.getInstance();

    DatePickerDialog.OnDateSetListener myDatePicker = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            myCalendar.set(Calendar.YEAR, year);
            myCalendar.set(Calendar.MONTH, month);
            myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            updateLabel();
        }
    };

    private void updateLabel() {
        String myFormat = "yyyy/MM/dd";    // 출력형식   2018/11/28
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.KOREA);

        EditText deaddate = (EditText) findViewById(R.id.deadEditText);
        deaddate.setText(sdf.format(myCalendar.getTime()));
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_writepost);

        store = (TextView)findViewById(R.id.storeEditText);
        EditText deadDate = (EditText) findViewById(R.id.deadEditText);
        deadDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(WritePostActivity.this, myDatePicker,
                        myCalendar.get(Calendar.YEAR), myCalendar.get(Calendar.MONTH), myCalendar.get(Calendar.DAY_OF_MONTH)).show();
                Deadline = deadDate.getText().toString();
            }
        });

        findViewById(R.id.writeButton).setOnClickListener(onClickListener);
        findViewById(R.id.findButton).setOnClickListener(onClickListener);

        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id){
                spinnerresult = parent.getItemAtPosition(position).toString();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent){

            }
        });
    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.writeButton:
                    writePost();
                    break;
                case R.id.findButton:
                    findStore();
                    break;
            }
        }
    };

    private void writePost() {
        final String title = ((EditText) findViewById(R.id.titleEditText)).getText().toString();
        final int minnum = Integer.parseInt(((EditText) findViewById(R.id.minnumEditText)).getText().toString());
        final String region = ((EditText) findViewById(R.id.regionEditText)).getText().toString();
        final String store = ((TextView)findViewById(R.id.storeEditText)).getText().toString();
        final String category = spinnerresult;
//        final String category = ((EditText) findViewById(R.id.categoryEditText)).getText().toString();
        final String object = ((EditText) findViewById(R.id.objectEditText)).getText().toString();
        final String contents = ((EditText) findViewById(R.id.contentsEditText)).getText().toString();


        if (title.length() > 0 &&  minnum > 0 && region.length() > 0 && store.length() > 0 && category.length() > 0 && object.length() > 0 && contents.length() > 0) {
            user = FirebaseAuth.getInstance().getCurrentUser();
            WriteInfo writeinfo = new WriteInfo(title,user.getUid(),sdf.format(date), Deadline, minnum, region, store, category, object, contents);
            uploader(writeinfo);
        }else{
            startToast("내용을 입력하세요.");
        }
    }

    private void findStore() {

        Intent intent = new Intent(WritePostActivity.this, MapActivity.class);
        intent.putExtra("storename", store.getText().toString());
        startActivityForResult(intent,CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case 1:
                if (resultCode == RESULT_OK) {
                    String edtstore = data.getStringExtra("storename");
                    store.setText(edtstore);
                } else {
                }
                break;
        }
    }

    private void uploader(WriteInfo writeInfo){
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("posts").add(writeInfo).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
            @Override
            public void onSuccess(DocumentReference documentReference) {
                Log.d(TAG, "DocumentSnapshot written with ID: "+documentReference.getId());
                startToast("게시되었습니다.");
                startActivity(MainActivity.class);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.w(TAG, "Error adding document",e);
            }
        });
    }

    private void startToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    private void startActivity(Class c){
        Intent intent = new Intent(this, c);
        startActivity(intent);
    }
}
