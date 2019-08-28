package com.kotlin.replugin;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button mClickBt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        mClickBt = (Button) findViewById(R.id.bt_click);
        mClickBt.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_click:
                // TODO 19/08/28
                Toast.makeText(getApplicationContext(), "测试", Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
    }
}
