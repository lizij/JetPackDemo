package com.lizij.jetpackdemo.db

import android.app.Application
import android.arch.persistence.room.Room
import com.lizij.jetpackdemo.R
import com.lizij.jetpackdemo.db.dao.RepoDao
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class GithubDatabaseModule {
    @Provides
    @Singleton
    fun provideGithubDatabase(application: Application): GithubDatabase {
        return Room.databaseBuilder(
                application,
                GithubDatabase::class.java,
                application.resources.getString(R.string.github_database_name)).build()
    }

    @Provides
    @Singleton
    fun provideRepoDao(githubDatabase: GithubDatabase): RepoDao {
        return githubDatabase.repoDao()
    }
}