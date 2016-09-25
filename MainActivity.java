package com.example.avantijoshi.speedometer;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements LocationListener {
    private final int x = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
           ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    x);
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {

        switch (requestCode) {
            case x: {
                // If request is cancelled, the result arrays are empty.
                    if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                        LocationManager locMan = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
                        try {
                            locMan.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this);
                            this.onLocationChanged(null);
                        } catch (SecurityException e) {
                            throw new SecurityException("wrong");
                        }
                    } else {
                        return;
                    }

                }

        }
    }

    @Override
    public void onLocationChanged(Location location) {
        TextView text = (TextView) this.findViewById(R.id.textView5);
        if (location == null) {
            text.setText("-- m/s");
        } else {
            float currentSpeed = location.getSpeed();
            text.setText("" + currentSpeed);
        }


    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }
}
