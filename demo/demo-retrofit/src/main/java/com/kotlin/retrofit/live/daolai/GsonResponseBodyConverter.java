package com.kotlin.retrofit.live.daolai;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;

import java.io.IOException;
import java.lang.reflect.Type;

import okhttp3.ResponseBody;
import retrofit2.Converter;

class GsonResponseBodyConverter<T> implements Converter<ResponseBody, T> {

    private Gson mGson;
    private TypeAdapter<T> mAdapter;
    private Type mType;

    GsonResponseBodyConverter(Gson gson, TypeAdapter<T> adapter, Type type) {
        this.mGson = gson;
        this.mAdapter = adapter;
        this.mType = type;
    }

    @Override
    public T convert(ResponseBody value) throws IOException {
        String response = value.string();
        try {
            Log.d("Network", "response>>" + response);
            //ResultResponse 只解析result字段
            ResultResponse resultResponse = mGson.fromJson(response, ResultResponse.class);
            if (resultResponse.getReturnCode().equals("200")) {
                //result==0表示成功返回，继续用本来的Model类解析
                return mGson.fromJson(response, mType);
            } else {
                //ErrResponse 将msg解析为异常消息文本
//                ErrResponse errResponse = mGson.fromJson(response, ErrResponse.class);
                throw new ResultException(resultResponse.getReturnCode(), resultResponse.getReturnMsg());
            }
        } finally {
            value.close();
        }
    }

}