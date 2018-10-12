package com.lizij.jetpackdemo.ui

import android.app.Application
import com.frogermcs.androiddevmetrics.AndroidDevMetrics
import com.lizij.jetpackdemo.db.GithubDatabase
import com.lizij.jetpackdemo.db.GithubDatabaseModule
import javax.inject.Inject

class MyApplication : Application() {
    @Inject lateinit var githubDatabase: GithubDatabase
//    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        AndroidDevMetrics.initWith(this@MyApplication)

        appComponent = DaggerAppComponent.builder()
                .appModule(AppModule(this))
                .githubDatabaseModule(GithubDatabaseModule())
                .build()

        appComponent.inject(this@MyApplication)
    }

    override fun onTerminate() {
        super.onTerminate()
        githubDatabase.close()
    }

    companion object {
        lateinit var appComponent: AppComponent
    }
}
