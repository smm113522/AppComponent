package com.kotlin.replugin;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;


public class MainActivity extends Activity {

    private Button mClickBt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        initView();
    }

    private void initView() {
//        mClickBt = (Button) findViewById(R.id.bt_click);
//        mClickBt.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(MainActivity.this, "测试", Toast.LENGTH_SHORT).show();
//            }
//        });

//        getSupportFragmentManager().beginTransaction().add(R.id.content, new DemoFragment()).commit();//添加Fragment到UI

//        LinearLayout contentView = RePlugin.fetchViewByLayoutName("replugin", "main_fragment", null);
//        if (contentView == null) {
//            Toast.makeText(this, "from_demo1 Not Found", Toast.LENGTH_SHORT).show();
//            return;
//        }

    }


}
