package com.kotlin.retrofit;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;

import com.kotlin.demo.net.retrofit.bean.BannerBean;
import com.kotlin.demo.net.retrofit.bean.BaseResponse;
import com.kotlin.retrofit.databinding.ActivityRetrofitBinding;
import com.kotlin.retrofit.livadeta.LiveApi;
import com.kotlin.retrofit.live.daolai.BodyBean;
import com.kotlin.retrofit.live.daolai.UserInfo;

import java.util.List;

public class RetrofitActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityRetrofitBinding dataBinding = ActivityRetrofitBinding.inflate(getLayoutInflater());
        setContentView(dataBinding.getRoot());
//        setContentView(R.layout.activity_retrofit);
        // 道来异常处理。
        // 把 数据转换成live data 数据 。还得报错正确和错误的数据不一致的情况
//        LiveData<BodyBean<UserInfo>> liveData = LiveApi.Companion.get().login("15122805490",
//                "", "2", "123456");
//        liveData.observe(this, userInfoBodyBean -> {
//            if (userInfoBodyBean.getReturnCode().equals("200")) {
//                System.out.println(userInfoBodyBean.getReturnData().getNickname());
//            }else {
//                System.out.println(userInfoBodyBean.getReturnMsg());
//            }
//        });

        // refrofit 转 live bean 数据
        LiveData<BaseResponse<List<BannerBean>>> liveData = LiveApi.Companion.get().bannerLiveList();
        liveData.observe(this, listBaseResponse -> {
            System.out.println(listBaseResponse.getErrorMsg());
        });


        // 道来，异常处理，自定义获取数据，然后返回来数据
        // 默认形式不可以用，因为 错误数据和正确的数据，格式不一样所以需要自己重新定义factory来解析数据
        // 正确数据在onResponse 中
        // 错误数据在onFailure 总 t.getMessage()就是提示，还有一个code 是后台返回来 的。

//        Call<BodyBean<String>> vcodeCall = DaolaiApi.Companion.get().getVcode("15122805490");
//        vcodeCall.enqueue(new Callback<BodyBean<String>>() {
//            @Override
//            public void onResponse(Call<BodyBean<String>> call, Response<BodyBean<String>> response) {
//                System.out.println(response.body().toString());
//            }
//
//            @Override
//            public void onFailure(Call<BodyBean<String>> call, Throwable t) {
//
//            }
//        });

//        DaolaiApi.Companion.get().login("15122805490", "", "2", "123456")
//                .enqueue(new Callback<BodyBean<UserInfo>>() {
//            @Override
//            public void onResponse(Call<BodyBean<UserInfo>> call, Response<BodyBean<UserInfo>> response) {
//                System.out.println(response.body().getReturnData().getRemark());
//            }
//
//            @Override
//            public void onFailure(Call<BodyBean<UserInfo>> call, Throwable t) {
//                System.out.println(t.getMessage());
//            }
//        });

//        EditText view = findViewById(R.id.et_code);
//        findViewById(R.id.bt_get).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Call<BodyBean<String>> register = DaolaiApi.Companion.get().register("15122805490",
//                        "",
//                        "123456");
//                register.enqueue(new Callback<BodyBean<String>>() {
//                    @Override
//                    public void onResponse(Call<BodyBean<String>> call, Response<BodyBean<String>> response) {
//                        System.out.println(response.body().toString());
//                    }
//
//                    @Override
//                    public void onFailure(Call<BodyBean<String>> call, Throwable t) {
//                        System.out.println(t.getMessage());
//                    }
//                });
//
//
//            }
//        });

        // 简单的refrofit 的使用。。。。call 是 默认形式
//        Call<BaseResponse<List<BannerBean>>> responseCall = WanApi.Companion.get().bannerList();
//        responseCall.enqueue(new Callback<BaseResponse<List<BannerBean>>>() {
//            @Override
//            public void onResponse(Call<BaseResponse<List<BannerBean>>> call, Response<BaseResponse<List<BannerBean>>> response) {
//                System.out.println(response.body().toString());
//            }
//
//            @Override
//            public void onFailure(Call<BaseResponse<List<BannerBean>>> call, Throwable t) {
//                System.out.println(t.getMessage());
//            }
//        });


    }
}
