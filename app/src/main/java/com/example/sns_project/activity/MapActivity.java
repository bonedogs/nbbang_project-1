package com.example.sns_project.activity;

import android.app.AlertDialog;
import android.app.FragmentManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.example.sns_project.R;
import com.example.sns_project.Store;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class MapActivity extends AppCompatActivity implements OnMapReadyCallback {
    final String TAG = "MapActivity";

    private FragmentManager fragmentManager;
    private MapFragment mapFragment;
    ArrayList<Store> arrayList;
    String selectStore = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        fragmentManager = getFragmentManager();
        mapFragment = (MapFragment)fragmentManager.findFragmentById(R.id.google_map);
        mapFragment.getMapAsync(this);

        arrayList = xml_parse();
        Log.d(TAG, String.valueOf(arrayList.size()));
//
        Intent intent = getIntent();
        String storename = intent.getStringExtra("storename");
        selectStore = storename;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        LatLng location = new LatLng(37.283593762082944, 127.04643539611467);

        for(int i=1; i< arrayList.size();i++){
           double lat = Double.parseDouble(arrayList.get(i).getREFINE_WGS84_LAT());
           double logt = Double.parseDouble(arrayList.get(i).getREFINE_WGS84_LOGT());
           LatLng store = new LatLng(lat,logt);
           MarkerOptions markerOptions = new MarkerOptions();
           markerOptions.position(store);
           markerOptions.title(arrayList.get(i).getMARKET_NM());
           markerOptions.snippet(arrayList.get(i).getFACLT_DIV_NM());
           googleMap.addMarker(markerOptions);
        }
        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.title("아주대학교");
        markerOptions.snippet("대학교");
        markerOptions.position(location);
        googleMap.addMarker(markerOptions);

        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(location,16));

        googleMap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener(){
            @Override
            public void onInfoWindowClick(Marker marker){
                String marker_number = null;
                for(int i=0; i< arrayList.size(); i++){
                    if(arrayList.get(i).findIndex(marker.getTitle())!=null){
                        marker_number = arrayList.get(i).findIndex(marker.getTitle());
                    }
                }
                final int marker_ID_number = Integer.parseInt(marker_number);
                AlertDialog.Builder builder = new AlertDialog.Builder(MapActivity.this);
                builder.setTitle("가게정보");
                builder.setMessage(
                        "가게이름 : "+arrayList.get(marker_ID_number).getMARKET_NM()+
                                "\n전화번호 : "+arrayList.get(marker_ID_number).getTELNO()+
                                "\n주소 : "+arrayList.get(marker_ID_number).getREFINE_ROADNM_ADDR());
                builder.setPositiveButton("선택", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        selectStore = arrayList.get(marker_ID_number).getMARKET_NM();
                        Intent intent = new Intent();
                        intent.putExtra("storename", selectStore);
                        setResult(RESULT_OK,intent);
                        finish();
                    }
                });
                builder.setNegativeButton("돌아가기", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
        });
    }

    private ArrayList<Store> xml_parse() {
        ArrayList<Store> storeList = new ArrayList<Store>();
        InputStream inputStream = getResources().openRawResource(R.raw.store);
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
        BufferedReader reader = new BufferedReader(inputStreamReader);

        XmlPullParserFactory xmlPullParserFactory = null;
        XmlPullParser xmlPullParser = null;

        try{
            xmlPullParserFactory = XmlPullParserFactory.newInstance();
            xmlPullParser = xmlPullParserFactory.newPullParser();
            xmlPullParser.setInput(reader);

            Store store = null;
            int eventType = xmlPullParser.getEventType();

            while(eventType != XmlPullParser.END_DOCUMENT){
                switch(eventType){
                    case XmlPullParser.START_DOCUMENT:
                        Log.i(TAG,"xml START");
                        break;
                    case XmlPullParser.START_TAG:
                        String startTag = xmlPullParser.getName();
                        Log.i(TAG,"Start TAG : " + startTag);
                        if(startTag.equals("row")){
                            store = new Store();
                            Log.d(TAG, "store 추가");
                        }
                        else if(startTag.equals("SIGUN_NM")){
                            store.setSIGUN_NM(xmlPullParser.nextText());
                        }
                        else if(startTag.equals("SIGUN_CD")){
                            store.setSIGUN_CD(xmlPullParser.nextText());
                        }
                        else if(startTag.equals("FACLT_DIV_NM")){
                            store.setFACLT_DIV_NM(xmlPullParser.nextText());
                        }
                        else if(startTag.equals("MARKET_NM")){
                            store.setMARKET_NM(xmlPullParser.nextText());
                        }
                        else if(startTag.equals("TELNO")){
                            store.setTELNO(xmlPullParser.nextText());
                        }
                        else if(startTag.equals("REFINE_LOTNO_ADDR")){
                            store.setREFINE_LOTNO_ADDR(xmlPullParser.nextText());
                        }
                        else if(startTag.equals("REFINE_ROADNM_ADDR")){
                            store.setREFINE_ROADNM_ADDR(xmlPullParser.nextText());
                        }
                        else if(startTag.equals("REFINE_ZIP_CD")){
                            store.setREFINE_ZIP_CD(xmlPullParser.nextText());
                        }
                        else if(startTag.equals("REFINE_WGS84_LOGT")){
                            store.setREFINE_WGS84_LOGT(xmlPullParser.nextText());
                        }
                        else if(startTag.equals("REFINE_WGS84_LAT")){
                            store.setREFINE_WGS84_LAT(xmlPullParser.nextText());
                        }
                        break;
                    case XmlPullParser.END_TAG:
                        String endTag = xmlPullParser.getName();
                        Log.i(TAG,"End TAG : " + endTag);
                        if(endTag.equals("row")){
                            storeList.add(store);
                        }
                        break;
                } try {
                    eventType = xmlPullParser.next();
                } catch(IOException e){
                    e.printStackTrace();
                }
            }
        }catch(XmlPullParserException e){
            e.printStackTrace();
        }catch(IOException e){
            e.printStackTrace();
        }finally{
            try{
                if(reader!=null) reader.close();
                if(inputStreamReader != null) inputStreamReader.close();
                if(inputStream != null) inputStream.close();
            } catch(Exception e2){
                e2.printStackTrace();
            }
        }
        return storeList;
    }
}