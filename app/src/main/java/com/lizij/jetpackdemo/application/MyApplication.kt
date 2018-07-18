package com.lizij.jetpackdemo.application

import android.app.Application
import android.arch.persistence.room.Room
import com.lizij.jetpackdemo.db.database.GithubDatabase

class MyApplication : Application() {

    companion object {
        private const val DATABASE_NAME = "github"
        var githubDatabase: GithubDatabase? = null
        fun getDataBase(): GithubDatabase? {
            return githubDatabase
        }
    }

    override fun onCreate() {
        super.onCreate()
        githubDatabase = Room.databaseBuilder(applicationContext, GithubDatabase::class.java, DATABASE_NAME).build()
    }

    override fun onTerminate() {
        super.onTerminate()
        githubDatabase?.close()
    }
}
