package com.aditya.handyman;
import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.provider.Settings;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.wangjie.rapidfloatingactionbutton.RapidFloatingActionButton;

public class MainActivity extends AppCompatActivity {

    private static final int REQUEST_LOCATION = 1;
    FloatingActionButton carpenter_btn;
    FloatingActionButton plumber_btn;
    FloatingActionButton electrician_btn;
    LocationManager locationManager;
    String lattitude,longitude;
    TextView txt1, txt2, txt3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_LOCATION);

        carpenter_btn = (FloatingActionButton)findViewById(R.id.carpenter_call);
        plumber_btn = (FloatingActionButton) findViewById(R.id.plumber_call);
        electrician_btn = (FloatingActionButton) findViewById(R.id.electrician_call);

        txt1 = findViewById(R.id.txtCarp);
        txt2 = findViewById(R.id.txtElec);
        txt3 = findViewById(R.id.txtPlumb);

        carpenter_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
                if (!locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
                    buildAlertMessageNoGps();

                } else if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
                    String data = getLocation();
                    System.out.println(data);
                    //api call send the data

                    Intent intent = new Intent(MainActivity.this, PricingActivity.class);
                    intent.putExtra("Location_Data", data);
                    intent.putExtra("Service",txt1.getText().toString());
                    startActivity(intent);
                }
            }
        });

        plumber_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
                if (!locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
                    buildAlertMessageNoGps();

                } else if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
                    String data = getLocation();
                    System.out.println(data);
                    //api call send the data

                    Intent intent = new Intent(MainActivity.this, PricingActivity.class);
                    intent.putExtra("Location_Data", data);
                    intent.putExtra("Service",txt3.getText().toString());
                    startActivity(intent);
                }
            }
        });

        electrician_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
                if (!locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
                    buildAlertMessageNoGps();

                } else if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
                    String data = getLocation();
                    System.out.println(data);
                    //api call send the data

                    Intent intent = new Intent(MainActivity.this, PricingActivity.class);
                    intent.putExtra("Location_Data", data);
                    intent.putExtra("Service",txt2.getText().toString());
                    startActivity(intent);
                }
            }
        });

    }

    private String getLocation() {
        if (ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission
                (MainActivity.this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_LOCATION);

        } else {
            Location location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);

            Location location1 = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);

            Location location2 = locationManager.getLastKnownLocation(LocationManager. PASSIVE_PROVIDER);

            if (location != null) {
                double latti = location.getLatitude();
                double longi = location.getLongitude();
                lattitude = String.valueOf(latti);
                longitude = String.valueOf(longi);

                //Toast.makeText(this,lattitude+" "+longitude,Toast.LENGTH_SHORT).show();
                return lattitude+" "+longitude;

            } else  if (location1 != null) {
                double latti = location1.getLatitude();
                double longi = location1.getLongitude();
                lattitude = String.valueOf(latti);
                longitude = String.valueOf(longi);

                //Toast.makeText(this,lattitude+" "+longitude,Toast.LENGTH_SHORT).show();
                return lattitude+" "+longitude;


            } else  if (location2 != null) {
                double latti = location2.getLatitude();
                double longi = location2.getLongitude();
                lattitude = String.valueOf(latti);
                longitude = String.valueOf(longi);

                //Toast.makeText(this,lattitude+" "+longitude,Toast.LENGTH_SHORT).show();
                return lattitude+" "+longitude;

            }else{

                Toast.makeText(this,"Unble to Trace your location",Toast.LENGTH_SHORT).show();

            }
        }

        return lattitude+" "+longitude;
    }

    protected void buildAlertMessageNoGps() {

        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Please Turn ON your GPS Connection")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(final DialogInterface dialog, final int id) {
                        startActivity(new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS));
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(final DialogInterface dialog, final int id) {
                        dialog.cancel();
                    }
                });
        final AlertDialog alert = builder.create();
        alert.show();
    }

}