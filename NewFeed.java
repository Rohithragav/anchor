package com.developer.rohithragav.anchor;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class NewFeed extends AppCompatActivity {

    static String date;
    static String time;
    static String desc;
    static String title;
    static String place;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_feed);
        ((TextView) findViewById(R.id.title)).setText(title);
        ((TextView) findViewById(R.id.desc)).setText(desc);
        ((TextView) findViewById(R.id.time)).setText(time);
        ((TextView) findViewById(R.id.date)).setText(date);
        ((TextView) findViewById(R.id.place)).setText(place);
    }


    @Override
    public void onBackPressed() {
        startActivity(new Intent(this,DailyFeed.class));
    }
}
