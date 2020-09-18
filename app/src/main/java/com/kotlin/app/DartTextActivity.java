package com.kotlin.app;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.kotlin.app.utils.ThemeHelper;

public class DartTextActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dart_text);
        if (ThemeHelper.Companion.isNightMode(getApplicationContext()))
            ThemeHelper.Companion.applyTheme(ThemeHelper.DARK_MODE);
    }
}
