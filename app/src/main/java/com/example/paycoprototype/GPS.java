package com.example.paycoprototype;

import android.Manifest;
import android.app.DownloadManager;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.PackageManagerCompat;

public class GPS extends AppCompatActivity {
    private TextView textViewlongitude, textViewlatitude, textViewcoord;
    private LocationManager locationManager;

    public String glocation;
    public String lg;
    public String lt;
    //static int REQUEST_CODE=1;
    public int requestCode = 101;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gps);

        textViewlatitude = findViewById(R.id.latitude);
        textViewlongitude = findViewById(R.id.longitude);
        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);

        textViewcoord = findViewById(R.id.coord);

        if (ContextCompat.checkSelfPermission(GPS.this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
            ContextCompat.checkSelfPermission(GPS.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED)
        {
            ActivityCompat.requestPermissions(GPS.this, new String[]{Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION},101);
        }
        if(requestCode == 101) {

            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 10, 100, new LocationListener() {
                @Override
                public void onLocationChanged(@NonNull Location location) {

                    textViewlongitude.setText(String.valueOf(location.getLongitude()));
                    textViewlatitude.setText(String.valueOf(location.getLatitude()));

                    lg = String.valueOf(location.getLongitude());
                    lt = String.valueOf(location.getLatitude());

                    //lt=textViewlongitude.getText().toString();
                    //glocation = "E" + lg + "\n" + "S" + lt; //Location's string
                    Toast.makeText(GPS.this, glocation, Toast.LENGTH_SHORT).show();
                /*//Sending from GPS Activity to Description Activity
                Intent lintent= new Intent(getApplicationContext(),DescriptionActivity.class);
                lintent.putExtra("theLocation",glocation);
                startActivity(lintent);*/

                    textViewcoord.setText(String.valueOf(glocation));
                }
//For some reason, the gps goes to the following address
// Google Building 40, Amphitheatre Parkway, Mountain View, CA 94043, United States of America
            });
        }
    }
}
