package com.kotlin.demo_tools

import android.app.job.JobInfo
import android.app.job.JobScheduler
import android.content.ComponentName
import android.content.Context
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.lifecycle.Observer
import androidx.work.PeriodicWorkRequest
import androidx.work.WorkManager
import com.blankj.utilcode.util.TimeUtils
import com.kotlin.demo_tools.adapter.BaseAdapter
import com.kotlin.demo_tools.databinding.ActivityToolsBinding
import com.kotlin.demo_tools.view.ToolsView
import com.kotlin.demo_tools.viewholder.ToolViewModel
import com.kotlin.demo_tools.work.MyService
import com.kotlin.demo_tools.work.MyWork
import java.util.concurrent.TimeUnit


class ToolsActivity : BaseMvvmActivity<ToolViewModel, ActivityToolsBinding>() {

    override fun getLayoutId(): Int = R.layout.activity_tools

    class Tools2Adapter : BaseAdapter<String, ToolsView>() {
        override fun getItemView(context: Context?): ToolsView {
            return ToolsView(context!!)
        }

        override fun refreshItemView(itemView: ToolsView, data: String) {
            itemView.setData(data)
        }
    }

//    private val adapter: ToolsAdapter by lazy {
//        ToolsAdapter()
//    }

    private val adapter: Tools2Adapter by lazy {
        Tools2Adapter()
    }
    var page = 1

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun afterCreate() {
        mViewDataBind.adapter = adapter
        mViewModel.list.observe(this, Observer {
            adapter.refreshData(it)
        })

        mViewModel.refreshData(page);
        adapter.setOnclckListener {

            /*val secondWork = OneTimeWorkRequest.Builder(MyWork::class.java)
                .setInitialDelay(5000, TimeUnit.MILLISECONDS) // 在满足约束条件的前提下，初始延迟时间为5S
                .setBackoffCriteria(
                    BackoffPolicy.LINEAR,
                    10,
                    TimeUnit.SECONDS
                ) // 重试间隔时间为：curTime + 10 * 重试次数
                .build()
            WorkManager.getInstance().enqueue(secondWork)
            Log.d(
                "xxx",
                "SecondWork 添加到系统  " + TimeUtils.millis2String(System.currentTimeMillis())
            )*/

//            报错了。。。。

            val request =
                PeriodicWorkRequest.Builder(MyWork::class.java, 5, TimeUnit.SECONDS).build()
            WorkManager.getInstance().enqueue(request);

//            val scheduler: JobScheduler =
//                getSystemService(Context.JOB_SCHEDULER_SERVICE) as JobScheduler
//            val jobService = ComponentName(applicationContext, MyService::class.java)
//            // 注意jobId
//            val builder = JobInfo.Builder(1000, jobService)
//            val jobInfo = builder
//                .setMinimumLatency(3 * 1000.toLong())
//                .setOverrideDeadline(4 * 1000.toLong())
//                .build()
//            scheduler.schedule(jobInfo)
            Log.d(
                "xxx",
                "SecondWork 添加到系统  " + TimeUtils.millis2String(System.currentTimeMillis())
            )
        }
    }

}