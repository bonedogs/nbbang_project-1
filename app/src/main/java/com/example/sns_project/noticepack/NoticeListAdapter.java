package com.example.sns_project.noticepack;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.sns_project.R;

import java.util.List;

public class NoticeListAdapter extends BaseAdapter {

    private Context context;
    private List<com.example.sns_project.noticepack.Notice> noticeList; //Notice 목록들이 들어갈 list

    public NoticeListAdapter(Context context, List<com.example.sns_project.noticepack.Notice> noticeList) {
        this.context = context;
        this.noticeList = noticeList;
    }

    @Override
    public int getCount() {
        return noticeList.size();
    }

    @Override
    public Object getItem(int position) {
        return noticeList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v = View.inflate(context, R.layout.item_notice, null);
        TextView noticeText = (TextView)v.findViewById(R.id.noticeItem);
        TextView nameText = (TextView)v.findViewById(R.id.nameText);
        TextView dateText = (TextView)v.findViewById(R.id.dateText);

        noticeText.setText(noticeList.get(position).getNotice());
        nameText.setText(noticeList.get(position).getName());
        dateText.setText(noticeList.get(position).getDate());

        v.setTag(noticeList.get(position).getNotice());
        return v;
    }
}
