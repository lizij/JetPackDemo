package com.lizij.jetpackdemo.activity

import android.Manifest
import android.arch.lifecycle.Observer
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.Button
import androidx.work.*
import com.lizij.jetpackdemo.R
import kotlinx.android.synthetic.main.activity_main.*
import work.MyWorker
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {
    private val TAG = javaClass.simpleName
    lateinit var mLifeCycleDemo: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        requestPermissions()

        mLifeCycleDemo = life_cycle_demo
        mLifeCycleDemo.setOnClickListener {
            val intent = Intent(this@MainActivity, RepoActivity::class.java)
            startActivity(intent)
        }

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
                        Log.d(TAG, "status: %s, msg: %s".format(it?.state.toString(), it?.outputData?.getString(MyWorker.MESSAGE) ?: "null"))
                    }
                })
    }

    private fun requestPermissions() {
        for (i in PERMISSIONS.indices) {
            if (ContextCompat.checkSelfPermission(this@MainActivity, PERMISSIONS[i]) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this@MainActivity, arrayOf(PERMISSIONS[i]), i)
            }
        }
    }

    companion object {
        private val PERMISSIONS = arrayOf(Manifest.permission.INTERNET)
    }
}
