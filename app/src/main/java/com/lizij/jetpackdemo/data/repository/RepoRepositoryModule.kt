package com.lizij.jetpackdemo.data.repository

import dagger.Module
import dagger.Provides

@Module
class RepoRepositoryModule {
    @Provides
    fun provideRepoRepository(): RepoRepository {
        return RepoRepository()
    }
}