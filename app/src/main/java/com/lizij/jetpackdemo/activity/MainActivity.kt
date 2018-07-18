package com.lizij.jetpackdemo.activity

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import com.lizij.jetpackdemo.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        requestPermissions()

        liveDataDemo.setOnClickListener {
            val intent = Intent(this@MainActivity, RepoActivity::class.java)
            startActivity(intent)
        }
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
