package com.developer.rohithragav.anchor;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class SuccessCompliant extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_success_compliant);
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(this,DailyFeed.class));
    }
}
