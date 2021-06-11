package com.example.sns_project.setting;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.sns_project.R;

import java.util.List;

public class SettingListAdapter extends BaseAdapter {

    private Context context;
    private List<Setting> settingList; //Post 목록들이 들어갈 list

    public SettingListAdapter(Context context, List<Setting> settingList) {
        this.context = context;
        this.settingList = settingList;
    }

    @Override
    public int getCount() {
        return settingList.size();
    }

    @Override
    public Object getItem(int position) {
        return settingList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v = View.inflate(context, R.layout.item_setting, null);
        TextView settingText = (TextView)v.findViewById(R.id.settingText);

        settingText.setText(settingList.get(position).getSetting());
        v.setTag(settingList.get(position).getSetting());
        return v;

    }


}
