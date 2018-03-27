package com.developer.rohithragav.anchor;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.developer.rohithragav.anchor.DB.*;
import com.developer.rohithragav.anchor.Storage.*;

public class complaints extends AppCompatActivity {

    Handler handler ;
    String resp;
    EditText title, desc, date;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complaints);
        setTitle("New Complaints");
        handler = new Handler();
        title = (EditText) findViewById(R.id.ctitle);
        desc = (EditText) findViewById(R.id.cdesc);
        date = (EditText) findViewById(R.id.dat);
    }

    public void createComplaint(View v){
        final String ctitle = title.getText().toString();
        final String cdesc = desc.getText().toString();
        final String cdate = date.getText().toString();

        final ProgressDialog progressDoalog = new ProgressDialog(this);
        progressDoalog.setMessage("Sending Complaint ");
        progressDoalog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDoalog.show();
        String fid = LocalStorage.getUsername(getApplication());
        Toast.makeText(getApplication(),ctitle+" "+cdesc+" "+cdate+" "+fid,Toast.LENGTH_SHORT).show();
        Thread th = new Thread(){
            @Override
            public void run() {
                try {
                    String fid = LocalStorage.getUsername(getApplication());
                    resp = AddComplaint.addComplaint(cdate,fid,ctitle,cdesc);
                } catch (Exception e) {}
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        progressDoalog.dismiss();
                        Toast.makeText(getApplication(),resp,Toast.LENGTH_SHORT).show();
                        if (resp.equals("ok")){
                            finish();
                            startActivity(new Intent(getApplication(), SuccessCompliant.class));
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
