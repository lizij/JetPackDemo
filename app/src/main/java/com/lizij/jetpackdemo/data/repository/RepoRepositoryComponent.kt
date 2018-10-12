package com.lizij.jetpackdemo.data.repository

import com.lizij.jetpackdemo.ui.viewmodel.RepoViewModel
import dagger.Component

@Component(modules = [RepoRepositoryModule::class])
interface RepoRepositoryComponent {
    fun inject(repoViewModel: RepoViewModel)
}