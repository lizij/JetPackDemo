package com.lizij.jetpackdemo.ui.viewmodel

import android.arch.lifecycle.ViewModelProviders
import android.support.v4.app.Fragment
import dagger.Module
import dagger.Provides
import javax.inject.Named

@Module
class ViewModelModule {

    @Provides
    @Named("repo")
    fun provideRepoViewModel(activity: Fragment) : RepoViewModel {
        return ViewModelProviders.of(activity).get(RepoViewModel::class.java)
    }
}