package com.kotlin.demo_tools.work;

import android.app.job.JobParameters;
import android.app.job.JobService;
import android.os.Build;
import android.util.Log;

import androidx.annotation.RequiresApi;

import com.blankj.utilcode.util.TimeUtils;


@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
public class MyService extends JobService {

    @Override
    public boolean onStartJob(JobParameters params) {
        Log.d(
                "xxx",
                "SecondWork onStartJob  " + TimeUtils.millis2String(System.currentTimeMillis())
        );
        return false;
    }

    @Override
    public boolean onStopJob(JobParameters params) {
        Log.d(
                "xxx",
                "SecondWork onStopJob  " + TimeUtils.millis2String(System.currentTimeMillis())
        );
        return false;
    }
}
