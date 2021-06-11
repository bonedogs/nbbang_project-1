package com.example.sns_project.noticepack;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.sns_project.R;
import com.example.sns_project.activity.MainActivity;

import java.util.ArrayList;
import java.util.List;

public class NoticeFragment extends Fragment {


    public NoticeFragment() {
        // Required empty public constructor
    }


    private ListView noticeListView;
    private com.example.sns_project.noticepack.NoticeListAdapter adapter;
    private List<com.example.sns_project.noticepack.Notice> noticeList;


    @Override
    public void onActivityCreated(@Nullable Bundle b) {
        super.onActivityCreated(b);

        noticeListView = (ListView)getView().findViewById(R.id.noticeListView);
        noticeList = new ArrayList<Notice>();
        noticeList.add(new com.example.sns_project.noticepack.Notice("5월 첫째 주 블랙리스트 명단입니다.", "뼈개들", "2021-05-27"));
        noticeList.add(new com.example.sns_project.noticepack.Notice("이벤트 안내입니다.", "뼈개들", "2021-04-28"));
        noticeList.add(new com.example.sns_project.noticepack.Notice("앱 출시 공지입니다.", "뼈개들", "2020-04-20"));
        adapter = new com.example.sns_project.noticepack.NoticeListAdapter(getContext().getApplicationContext(), noticeList);
        noticeListView.setAdapter(adapter);

    }


    public interface OnNoticeSelectedListener {
        public void onNoticeSelected(int position);
    }

    OnNoticeSelectedListener mNoticeSelListener;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_notice, container, false);
        ListView noticeListView = view.findViewById(R.id.noticeListView);

        mNoticeSelListener = (MainActivity) getActivity();
        noticeListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id){
                mNoticeSelListener.onNoticeSelected(position);
            }
        });
        return view;
    }


}

