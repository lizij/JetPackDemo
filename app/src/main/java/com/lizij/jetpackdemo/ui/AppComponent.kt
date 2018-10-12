package com.lizij.jetpackdemo.ui

import com.lizij.jetpackdemo.network.api.GithubApiModule
import com.lizij.jetpackdemo.data.repository.RepoRepository
import com.lizij.jetpackdemo.db.GithubDatabaseModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, GithubApiModule::class, GithubDatabaseModule::class])
interface AppComponent {
    fun inject(application: MyApplication)

    fun inject(repoRepository: RepoRepository)
}