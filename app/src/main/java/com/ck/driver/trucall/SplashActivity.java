package com.ck.driver.trucall;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.ck.driver.code.utils.MapUtils;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        MapUtils.startMap(SplashActivity.this);
    }



}
