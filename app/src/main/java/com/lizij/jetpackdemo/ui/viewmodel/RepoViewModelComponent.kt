package com.lizij.jetpackdemo.ui.viewmodel

import com.lizij.jetpackdemo.data.repository.RepoRepositoryModule
import dagger.Component

@Component(modules = [RepoRepositoryModule::class])
interface RepoViewModelComponent {
    fun inject(repoViewModel: RepoViewModel)
}