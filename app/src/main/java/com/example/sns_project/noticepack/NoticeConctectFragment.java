package com.example.sns_project.noticepack;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.sns_project.R;


public class NoticeConctectFragment extends Fragment {

    public NoticeConctectFragment() {
        // Required empty public constructor
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_notice_conctect, container, false);
    }


    @Override
    public void onStart() {
        super.onStart();

        Bundle args = getArguments();
        if (args != null) {
            int pos = args.getInt("position");
            updateDefinitionView(pos);
        }
    }

    public void updateDefinitionView(int position) {
        TextView def = (TextView) getView().findViewById(R.id.definition);
        def.setText(NoticeManager.Contents[position]);
    }


}