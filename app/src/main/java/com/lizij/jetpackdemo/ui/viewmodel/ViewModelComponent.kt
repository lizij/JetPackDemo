package com.lizij.jetpackdemo.ui.viewmodel

import com.lizij.jetpackdemo.ui.activity.RepoActivity
import dagger.Component

@Component(modules = [ViewModelModule::class])
interface ViewModelComponent {
    fun inject(repoActivity: RepoActivity)
}