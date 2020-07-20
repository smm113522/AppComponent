package com.kotlin.image.weiget;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.LinearLayout;

import com.kotlin.image.R;

public class ItemImage2 extends LinearLayout {
    public ItemImage2(Context context) {
        super(context);
        init();
    }

    private void init() {
        LayoutInflater.from(getContext()).inflate(R.layout.item_image_2,this);
    }
}
