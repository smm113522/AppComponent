package com.kotlin.main.adapter;


import android.databinding.BindingAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.kotlin.code.adapter.BaseDBRVAdapter;
import com.kotlin.main.BR;
import com.kotlin.main.R;
import com.kotlin.main.bean.MainHome;
import com.kotlin.main.databinding.ItemMainBinding;

import kotlin.jvm.JvmStatic;

public class MainAdatpters extends BaseDBRVAdapter<MainHome, ItemMainBinding> {

    public MainAdatpters() {
        super(R.layout.item_main, BR.bean);
    }

    /**
     * 绑定Adapter的ImageView
     *
     * @param imageView
     * @param url       图片地址
     */
    @BindingAdapter({"imageUrl"})
    public static void loadImage(ImageView imageView, String url) {
        Glide.with(imageView.getContext()).load(url).into(imageView);
    }

    @BindingAdapter("app:imageUrl")
    @JvmStatic
    public void srcCompat(ImageView imageView, String url) {
        Glide.with(imageView.getContext()).load(url).into(imageView);
    }
    @BindingAdapter("app:imageUrl")
    @JvmStatic
    public void srcCompat(ImageView imageView, Integer url) {
        imageView.setBackgroundResource(url);
    }

}
