package com.lizij.jetpackdemo.db

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase

import com.lizij.jetpackdemo.db.dao.RepoDao
import com.lizij.jetpackdemo.data.model.Repo

@Database(entities = [(Repo::class)], version = 1, exportSchema = false)
abstract class GithubDatabase : RoomDatabase() {
    abstract fun repoDao(): RepoDao
}
