package com.lizij.jetpackdemo.ui

import android.app.Activity
import android.app.Application
import com.frogermcs.androiddevmetrics.AndroidDevMetrics
import com.lizij.jetpackdemo.db.GithubDatabase
import com.lizij.jetpackdemo.db.GithubDatabaseModule
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import javax.inject.Inject

class MyApplication : Application(), HasActivityInjector {
    @Inject lateinit var githubDatabase: GithubDatabase
    @Inject lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Activity>

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

    override fun activityInjector(): AndroidInjector<Activity> {
        return dispatchingAndroidInjector
    }

    companion object {
        lateinit var appComponent: AppComponent
    }
}
