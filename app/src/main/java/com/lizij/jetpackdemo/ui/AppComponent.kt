package com.lizij.jetpackdemo.ui

import com.lizij.jetpackdemo.network.api.GithubApiModule
import com.lizij.jetpackdemo.data.repository.RepoRepository
import com.lizij.jetpackdemo.db.GithubDatabaseModule
import com.lizij.jetpackdemo.ui.activity.ActivityModule
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [
    AppModule::class,
    ActivityModule::class,
    GithubApiModule::class,
    GithubDatabaseModule::class,
    AndroidInjectionModule::class,
    AndroidSupportInjectionModule::class
])
interface AppComponent {
    fun inject(application: MyApplication)

    fun inject(repoRepository: RepoRepository)
}