package com.lizij.jetpackdemo.db.database

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase

import com.lizij.jetpackdemo.application.MyApplication
import com.lizij.jetpackdemo.db.dao.RepoDao
import com.lizij.jetpackdemo.model.Repo

@Database(entities = [(Repo::class)], version = 1, exportSchema = false)
abstract class GithubDatabase : RoomDatabase() {

    abstract fun repoDao(): RepoDao
}
