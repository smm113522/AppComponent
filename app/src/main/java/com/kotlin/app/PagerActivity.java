package com.kotlin.app;

import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class PagerActivity extends AppCompatActivity {

    private ViewPager mViewpager;
    private ViewPagerAdaper adaper;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pager);
        initViews();

    }

    private void initViews() {
        mViewpager = findViewById(R.id.viewpager);
        adaper = new ViewPagerAdaper();

        ImageView imageView = new ImageView(getApplicationContext());
        Glide.with(this).load("https://tpc.googlesyndication.com/simgad/1004724400346658062?sqp=4sqPyQQ7QjkqNxABHQAAtEIgASgBMAk4A0DwkwlYAWBfcAKAAQGIAQGdAQAAgD-oAQGwAYCt4gS4AV_FAS2ynT4&rs=AOga4qkpWmrFUda8-4f_v03Xm4TcspbJBw").into(imageView);

        ImageView imageView1 = new ImageView(getApplicationContext());
        Glide.with(this).load("https://pic.cnblogs.com/face/739173/20160831042505.png").into(imageView1);


        ArrayList<View> list = new ArrayList<>();
        list.add(imageView);
        adaper.setList(list);

        mViewpager.setAdapter(adaper);
    }

    class ViewPagerAdaper extends PagerAdapter {
        private ArrayList<View> list;

        public void setList(ArrayList<View> list) {
            this.list = list;
            notifyDataSetChanged();
        }

        @Override
        public int getCount() {
            if (list == null) {
                return 0;
            } else {
                return list.size();
            }
        }

        @NonNull
        @Override
        public Object instantiateItem(@NonNull ViewGroup container, int position) {
            container.addView(list.get(position));
            return list.get(position);
        }

        @Override
        public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
            return view == object;
        }

        @Override
        public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
            container.removeView(list.get(position));
        }

        @Override
        public int getItemPosition(@NonNull Object object) {
            return super.getItemPosition(object);
        }

    }

}
