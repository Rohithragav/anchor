package com.developer.rohithragav.anchor;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class YourAdded extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_your_added);
    }

    @Override
    public void onBackPressed() {
        finish();
        startActivity(new Intent(this,Login.class));
    }
}
