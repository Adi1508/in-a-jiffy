package com.aditya.handyman;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ProtocolException;
import java.net.URL;
import java.util.List;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;

public class PricingActivity extends AppCompatActivity {

    Button requestService;
    String lat, longi, service, servicev1;
    private RequestQueue rQueue;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pricing_activity);

        String received_data = getIntent().getStringExtra("Location_Data");

        String[] arr = received_data.split(" ");
        lat = arr[0];
        longi = arr[1];

        service = getIntent().getStringExtra("Service");
        String[] arr1 = service.split(" ");
        servicev1 = arr1[arr1.length - 1].toLowerCase();

        System.out.println("Pricing Activity: " + received_data);

        requestService = findViewById(R.id.request_service);

        requestService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(PricingActivity.this, lat + " " + longi + " " + servicev1, Toast.LENGTH_SHORT).show();

                //postService(lat, longi, servicev1);
                String url = "https://handymanv1.herokuapp.com/api/" + servicev1 + "/" + lat + "/" + longi; //insert api url

                StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String s) {
                        System.out.println("Response " + s);
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        Log.d("Error", volleyError.toString());
                    }
                });

                getRequestQueue().add(request);
            }
        });

    }

    public RequestQueue getRequestQueue() {
        if (rQueue == null) {
            rQueue = Volley.newRequestQueue(getApplicationContext());
        }
        return rQueue;
    }

//    public void postService(String lat, String longi, String servicev1) throws IOException {
//
//
//    }
}
