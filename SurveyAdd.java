package com.developer.rohithragav.anchor;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class SurveyAdd extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_survey_add);
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(this,DailyFeed.class));
    }
}
