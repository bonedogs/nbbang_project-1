package com.example.sns_project.setting;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.sns_project.R;

import java.util.ArrayList;
import java.util.List;


public class SettingFragment extends Fragment {
    private ListView settingListView;
    private com.example.sns_project.setting.SettingListAdapter adapter;
    private List<com.example.sns_project.setting.Setting> settingList;

    public SettingFragment() {
        // Required empty public constructor
    }



    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        settingListView = (ListView)getView().findViewById(R.id.postListView);
        settingList = new ArrayList<com.example.sns_project.setting.Setting>();
        settingList.add(new com.example.sns_project.setting.Setting("문의"));
        settingList.add(new com.example.sns_project.setting.Setting("버전 정보"));
        settingList.add(new com.example.sns_project.setting.Setting("알림 설정"));
        settingList.add(new com.example.sns_project.setting.Setting("기타"));

        adapter = new com.example.sns_project.setting.SettingListAdapter(getContext().getApplicationContext(), settingList);
        settingListView.setAdapter(adapter);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_setting, container, false);
    }
}