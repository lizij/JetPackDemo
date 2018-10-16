package com.lizij.jetpackdemo.ui.activity

import com.lizij.jetpackdemo.ui.viewmodel.RepoViewModelModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityModule {

    @ActivityScope
    @ContributesAndroidInjector(modules = [RepoViewModelModule::class])
    abstract fun contributeRepoActivityInjector(): RepoActivity
}