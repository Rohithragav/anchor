package com.developer.rohithragav.anchor;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.location.Location;
import android.media.MediaPlayer;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class Fishing extends AppCompatActivity {

    static Boolean isNotificationRecieved = false;
    static Boolean serviceStop = true;
    private static final int REQUEST_PERMISSIONS = 100;
    boolean boolean_permission;

    static Double latitude,longitude;

    TextView Lat , Lan , dis ,zone;
    MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fishing);

        zone = (TextView) findViewById(R.id.zone);
        Lat = (TextView) findViewById(R.id.lat);
        Lan = (TextView) findViewById(R.id.lan);
        dis = (TextView) findViewById(R.id.dis);
        Intent intent = new Intent(getApplicationContext(), GoogleService.class);
        startService(intent);
        mediaPlayer = MediaPlayer.create(this, R.raw.lb);
        if (isNotificationRecieved) {
            serviceStop = false;
        }

        fn_permission();
    }
    private void fn_permission() {
        if ((ContextCompat.checkSelfPermission(getApplicationContext(), android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED)) {

            if ((ActivityCompat.shouldShowRequestPermissionRationale(Fishing.this, android.Manifest.permission.ACCESS_FINE_LOCATION))) {


            } else {
                ActivityCompat.requestPermissions(Fishing.this, new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION

                        },
                        REQUEST_PERMISSIONS);

            }
        } else {
            boolean_permission = true;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode) {
            case REQUEST_PERMISSIONS: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    boolean_permission = true;

                } else {
                    Toast.makeText(getApplicationContext(), "Please allow the permission", Toast.LENGTH_LONG).show();

                }
            }
        }
    }

    private BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {

            latitude = Double.valueOf(intent.getStringExtra("latutide"));
            longitude = Double.valueOf(intent.getStringExtra("longitude"));

            Location myLoc = new Location("");
            myLoc.setLatitude(latitude);
            myLoc.setLongitude(longitude);

            Lat.setText(latitude+"");
            Lan.setText(longitude+"");
            Location Linearline = new Location("");
            Linearline.setLatitude(LinearLine.getLatDistance());
            Linearline.setLongitude(LinearLine.getLanDistance());

            float distanceKm = myLoc.distanceTo(Linearline)/1000f;
            if(distanceKm<0.5001){
                dis.setTextColor(getResources().getColor(R.color.red));
                zone.setText("You are in Danger Zone");
                mediaPlayer.start();
            }
            else{
                dis.setTextColor(getResources().getColor(R.color.green));
                zone.setText("You are in safe Zone");
                mediaPlayer.pause();
            }

            dis.setText(String.format("%.4f km",distanceKm));
            //tv_status.getText();


        }
    };

        /*private void sendServer()
        {
            postMethod pm = new postMethod();
            try
            {
                pm.send(latitude+"", longitude+"");
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }*/


    @Override
    protected void onResume() {
        super.onResume();
        registerReceiver(broadcastReceiver, new IntentFilter(GoogleService.str_receiver));

    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(broadcastReceiver);
    }


}
