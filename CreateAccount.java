package com.developer.rohithragav.anchor;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Handler;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.developer.rohithragav.anchor.DB.AddComplaint;
import com.developer.rohithragav.anchor.DB.CreateAcc;
import com.developer.rohithragav.anchor.Storage.LocalStorage;

public class CreateAccount extends AppCompatActivity {

    EditText ad , name , pass , age , plc , bno , phn;
    String resp;
    Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);
        setTitle("Create Account");
        handler = new Handler();
        ad = (EditText) findViewById(R.id.aadhar);
        name = (EditText) findViewById(R.id.name);
        pass = (EditText) findViewById(R.id.pass);
        age = (EditText) findViewById(R.id.age);
        plc = (EditText) findViewById(R.id.place);
        bno = (EditText) findViewById(R.id.boatno);
        phn = (EditText) findViewById(R.id.phno);
    }

    public void creatAccount(View v){
        final String aadhar = ad.getText().toString();
        final String nam = name.getText().toString();
        final String password = pass.getText().toString();
        final String ag = age.getText().toString();
        final String place = plc.getText().toString();
        final String bn = bno.getText().toString();
        final String ph = phn.getText().toString();
        final ProgressDialog progressDoalog = new ProgressDialog(this);
        progressDoalog.setMessage("Creating Account ");
        progressDoalog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDoalog.show();
        Thread th = new Thread(){
            @Override
            public void run() {
                try {
                    resp = CreateAcc.createAccount(aadhar,nam,bn,ag,place,ph,password);
                } catch (Exception e) {}
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        progressDoalog.dismiss();
                        Toast.makeText(getApplication(),resp,Toast.LENGTH_SHORT).show();
                        if (resp.equals("ok")){
                            finish();
                             startActivity(new Intent(getApplication(),YourAdded.class));
                        }
                        else
                            Toast.makeText(getApplication(),"something went wrong",Toast.LENGTH_SHORT).show();
                    }
                });
            }
        };
        th.start();
    }
}
