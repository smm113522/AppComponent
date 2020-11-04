package com.kotlin.demo_tools.work;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

import com.blankj.utilcode.util.TimeUtils;

import java.sql.Date;


public class MyWork extends Worker {

    public MyWork(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
    }

    @NonNull
    @Override
    public Result doWork() {
        Log.d("xxx", "thread id = " + Thread.currentThread().getId());
        Log.d("xxx", "启动了");
        Log.d("xxx","SecondWork 启动时间：  "+ TimeUtils.millis2String(System.currentTimeMillis()));
        return Result.success();
    }
}
