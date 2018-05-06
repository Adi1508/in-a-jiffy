package com.aditya.handyman;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

public class FinalPage extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final);

        ProgressDialog pd = new ProgressDialog(FinalPage.this);
        pd.setMessage("Finding your Service Shortly......");
        pd.show();


    }
}
