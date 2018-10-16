package com.lizij.jetpackdemo.ui.viewmodel

import android.arch.lifecycle.ViewModelProviders
import com.lizij.jetpackdemo.ui.activity.RepoActivity
import dagger.Module
import dagger.Provides

@Module
class RepoViewModelModule {
    @Provides
    fun provideViewModel(repoActivity: RepoActivity): RepoViewModel {
        return ViewModelProviders.of(repoActivity).get(RepoViewModel::class.java)
    }
}