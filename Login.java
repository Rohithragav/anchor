package com.developer.rohithragav.anchor;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.developer.rohithragav.anchor.DB.*;
import com.developer.rohithragav.anchor.Storage.LocalStorage;

import org.json.JSONArray;
import org.json.JSONObject;

public class Login extends AppCompatActivity implements View.OnClickListener {

    EditText us,ps;
    Button login;
    String resp;

    Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        setTitle("Login");
        SharedPreferences prefs = this.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        String status = prefs.getString("loginKey", "");
        if (status.equals("logged"))
            startActivity(new Intent(this,DailyFeed.class));
        handler = new Handler();
        us = (EditText) findViewById(R.id.loginid);
        ps = (EditText) findViewById(R.id.loginpass);
        login = (Button) findViewById(R.id.loginbtn);
        login.setOnClickListener(this);
    }

    public void createAccount(View v){
        Intent i = new Intent(this,CreateAccount.class);
        startActivity(i);
    }

    @Override
    public void onClick(View view) {
        final String user = us.getText().toString();
        final String pass = ps.getText().toString();
        Toast.makeText(getApplication(),user+" "+pass,Toast.LENGTH_SHORT).show();
        final ProgressDialog progressDoalog = new ProgressDialog(this);
        progressDoalog.setMessage("Making Deal...");
        progressDoalog.setCancelable(false);
        progressDoalog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDoalog.show();
        Thread th = new Thread(){
            @Override
            public void run() {
                try {
                    resp = signin.login(user,pass);
                } catch (Exception e) {}
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        progressDoalog.dismiss();
                        //Toast.makeText(getApplication(),resp,Toast.LENGTH_SHORT).show();
                        if (!resp.equals("error")) {
                        try {
                            JSONArray ja = new JSONArray(resp);
                            for (int i=0;i<ja.length();i++){
                                JSONObject jo = ja.getJSONObject(i);
                                String fid = jo.getString("fid");
                                String name= jo.getString("name");
                                String boatno = jo.getString("boatno");
                                String age = jo.getString("age");
                                String place = jo.getString("place");
                                String phno = jo.getString("phno");
                                String password = jo.getString("password");
                                LocalStorage.setFid(fid,password,getApplication());
                            }
                            startActivity(new Intent(getApplication(), DailyFeed.class));
                        }catch (Exception e){

                        }

                        }


                    }
                });
            }
        };
        th.start();
    }
}
