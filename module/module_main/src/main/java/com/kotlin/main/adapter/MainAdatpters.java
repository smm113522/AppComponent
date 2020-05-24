package com.kotlin.main.adapter;


import android.databinding.BindingAdapter;
import android.support.annotation.NonNull;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.kotlin.code.adapter.BaseDBRVAdapter;
import com.kotlin.code.adapter.BaseDBRVHolder;
import com.kotlin.main.BR;
import com.kotlin.main.R;
import com.kotlin.main.bean.MainHome;
import com.kotlin.main.databinding.ItemMainBinding;

import kotlin.jvm.JvmStatic;

public class MainAdatpters extends BaseDBRVAdapter<MainHome, ItemMainBinding> {

    public MainAdatpters() {
        super(R.layout.item_main, BR.bean);
    }

    @Override
    public void onBindViewHolder(@NonNull BaseDBRVHolder holder, int position) {
        super.onBindViewHolder(holder, position);
        EditText et = holder.itemView.findViewById(R.id.et_template);
        et.addTextChangedListener(textWatcher);
        et.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (b){
                    
                }else {

                }
            }
        });
    }

    private TextWatcher textWatcher = new TextWatcher() {

        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void afterTextChanged(Editable editable) {

        }
    };

    /**
     * 绑定Adapter的ImageView
     *
     * @param imageView
     * @param url       图片地址
     */
    @BindingAdapter("app:imageUrl")
    @JvmStatic
    public static void loadImage(ImageView imageView, String url) {
        Glide.with(imageView.getContext()).load(url).into(imageView);
    }
    @BindingAdapter("app:imageUrl")
    @JvmStatic
    public static void loadImage(ImageView imageView, Integer url) {
        imageView.setBackgroundResource(url);
    }

}
