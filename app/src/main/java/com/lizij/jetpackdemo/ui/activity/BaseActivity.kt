package com.lizij.jetpackdemo.ui.activity

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import androidx.work.Data
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.WorkStatus
import com.lizij.jetpackdemo.work.MyWorker
import dagger.android.AndroidInjection

abstract class BaseActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        initWorkManager()
    }

    private fun initWorkManager() {
        // 创建Request
        val workRequest = OneTimeWorkRequestBuilder<MyWorker>()
                // 设置输入数据
                .setInputData(Data.Builder()
                        .putString(MyWorker.COMPONENT_NAME, javaClass.simpleName)
                        .build())
                .build()

        // 添加workRequest
        WorkManager.getInstance().enqueue(workRequest)

        // 添加监控，获取结果
        WorkManager.getInstance().getStatusById(workRequest.id)
                .observe(this, Observer<WorkStatus> { it ->
                    it.let {
                        Log.d(javaClass.simpleName, "status: %s, msg: %s".format(it?.state.toString(), it?.outputData?.getString(MyWorker.MESSAGE) ?: "null"))
                    }
                })
    }
}