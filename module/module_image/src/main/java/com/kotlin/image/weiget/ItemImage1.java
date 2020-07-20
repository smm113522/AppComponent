package com.kotlin.image.weiget;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.widget.LinearLayout;

import com.kotlin.image.R;

public class ItemImage1 extends LinearLayout {

    public ItemImage1(Context context) {
        super(context);
        init();
    }

    private void init() {
        LayoutInflater.from(getContext()).inflate(R.layout.item_image_1,this);
    }
}
