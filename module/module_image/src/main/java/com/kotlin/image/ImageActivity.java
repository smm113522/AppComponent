package com.kotlin.image;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;

import androidx.recyclerview.widget.ConcatAdapter;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.kotlin.image.adapter.GrideAdapter;
import com.kotlin.image.adapter.ImageAdapter;

@Route(path = "/image/activity")
public class ImageActivity extends AppCompatActivity {

    private RecyclerView mRecyclerview;
    private GrideAdapter adapter1;
    private ImageAdapter adapter2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);
        initViews();
    }

    private void initViews() {
        mRecyclerview = findViewById(R.id.recyclerview);
        adapter1 = new GrideAdapter();
        adapter2 = new ImageAdapter();
        ConcatAdapter adapter = new ConcatAdapter(adapter1,adapter2);
        mRecyclerview.setAdapter(adapter);
    }
}
