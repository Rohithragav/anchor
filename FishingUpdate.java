package com.developer.rohithragav.anchor;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.developer.rohithragav.anchor.DB.*;
import com.developer.rohithragav.anchor.Storage.*;

import java.util.ArrayList;
import java.util.List;

public class FishingUpdate extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    String resp;
    Handler handler;
    Spinner sp;
    static String fishtype;
    EditText sdate, sdesc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fishing_update);
        setTitle("Fishing Update");
        handler = new Handler();
        sdate = (EditText) findViewById(R.id.sdate);
        sdesc = (EditText) findViewById(R.id.quantity);
        sp = (Spinner) findViewById(R.id.fishtype);

        List<String> fishes = new ArrayList<String>();
        fishes.add("Alfonsino");
        fishes.add("Dusky shark");
        fishes.add("Black ruff");
        fishes.add("Golden roughy");
        fishes.add("Hilsa kelee");
        fishes.add("Keeltail");
        fishes.add("sora");

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, fishes);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp.setAdapter(dataAdapter);
        sp.setOnItemSelectedListener(this);

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long l) {
        fishtype = parent.getItemAtPosition(position).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
    public void sendDetails(View v){
        final String date = sdate.getText().toString();
        final String desc = sdesc.getText().toString();
        final String ftype =fishtype;
        final ProgressDialog progressDoalog = new ProgressDialog(this);
        progressDoalog.setMessage("Update Fishing ");
        progressDoalog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDoalog.show();
        Thread th = new Thread(){
            @Override
            public void run() {
                try {
                    String fid = LocalStorage.getUsername(getApplication());
                    resp = fishingup.setSurvey(date,fid,ftype,desc);
                } catch (Exception e) {}
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        progressDoalog.dismiss();
                        Toast.makeText(getApplication(),resp,Toast.LENGTH_SHORT).show();
                        if (resp.equals("ok")){
                            finish();
                            startActivity(new Intent(getApplication(),SurveyAdd.class));
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
