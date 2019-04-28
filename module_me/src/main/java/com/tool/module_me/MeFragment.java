package com.tool.module_me;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.tool.module_me.model.BannerEntity;
import com.tool.module_me.net.ApiService;
import com.tool.net.RxSubcriber;

import java.util.List;

import androidx.fragment.app.Fragment;

@Route(path = "/me/fragment")
public class MeFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        TextView textView = new TextView(getActivity());
        textView.setText("我的");

        ApiService.getMeData(new RxSubcriber<List<BannerEntity>>(getActivity()) {
                    @Override
                    public void onSuccess(List<BannerEntity> bannerEntities) {
                        Log.d("print", "->" + bannerEntities.toString());
                        textView.setText(bannerEntities.toString());
                    }
                });

        return textView;
    }
}