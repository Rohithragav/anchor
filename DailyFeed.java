package com.developer.rohithragav.anchor;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.developer.rohithragav.anchor.DB.GetFeed;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class DailyFeed extends AppCompatActivity implements View.OnClickListener,AdapterView.OnItemClickListener  {

    ListView lv;
    Handler handler;
    String resp;
    CustomAdapter1 customAdapter1;
    ArrayList<Feeds> feeds = new ArrayList<Feeds>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daily_feed);
        handler = new Handler();
        customAdapter1 = new CustomAdapter1(feeds);
        lv = (ListView) findViewById(R.id.list);
        lv.setOnItemClickListener(this);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDalog();
            }
        });
        final ProgressDialog progressDoalog = new ProgressDialog(this);
        progressDoalog.setMessage("Loging...");
        progressDoalog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDoalog.setCancelable(false);
        progressDoalog.show();
        progressDoalog.dismiss();
        Thread th2 = new Thread(){
            @Override
            public void run() {
                try {
                    resp = GetFeed.getFeed();
                } catch (Exception e) {}
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                       try {
                            JSONArray ja = new JSONArray(resp);
                            for (int i=0;i<ja.length();i++){
                                JSONObject jo = ja.getJSONObject(i);
                                String date = jo.getString("date");
                                String time = jo.getString("time");
                                String title = jo.getString("title");
                                String desc = jo.getString("desc");
                                String place = jo.getString("place");
                                feeds.add(new Feeds(date,time,title,desc,place));
                                customAdapter1.notifyDataSetChanged();
                            }
                            lv.setAdapter(customAdapter1);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                });
            }
        };
        th2.start();


    }

    private void showDalog() {
        final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.customdialog);
        // set the custom dialog components - text, image and button
        TextView updatefishing = (TextView) dialog.findViewById(R.id.updatefishing);
        TextView makecomp = (TextView) dialog.findViewById(R.id.makecomp);
        TextView logout = (TextView) dialog.findViewById(R.id.logout);
        TextView fishing = (TextView) dialog.findViewById(R.id.fishing);
        TextView myacc = (TextView) dialog.findViewById(R.id.myacc);
        // if button is clicked, close the custom dialog
        updatefishing.setOnClickListener(this);
        makecomp.setOnClickListener(this);
        logout.setOnClickListener(this);
        fishing.setOnClickListener(this);
        myacc.setOnClickListener(this);
        dialog.show();
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()){
            case R.id.updatefishing: startActivity(new Intent(this,FishingUpdate.class)); break;
            case R.id.makecomp: startActivity(new Intent(this,complaints.class)); break;
            case R.id.fishing: startActivity(new Intent(this,Fishing.class)); break;
            case R.id.logout : this.finish(); System.exit(0);
        }
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Feeds feed = feeds.get(i);
        NewFeed.date=feed.getDate();
        NewFeed.title=feed.getTitle();
        NewFeed.desc=feed.getDesc();
        NewFeed.place=feed.getPlace();
        NewFeed.time=feed.getTime();
        startActivity(new Intent(this,NewFeed.class));
    }

    @Override
    public void onBackPressed() {
        System.exit(0);
    }
}
