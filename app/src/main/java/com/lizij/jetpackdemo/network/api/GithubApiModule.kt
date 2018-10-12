package com.lizij.jetpackdemo.network.api

import android.app.Application
import com.lizij.jetpackdemo.R
import com.lizij.jetpackdemo.network.adapter.TaskCallAdapterFactory
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class GithubApiModule {

    @Provides
    @Singleton
    fun provideRest(application: Application): Retrofit {
        return Retrofit.Builder()
                .baseUrl(application.resources.getString(R.string.github_api_url))
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(TaskCallAdapterFactory.create())
                .build()
    }

    @Provides
    @Singleton
    fun provideGithubApi(retrofit: Retrofit): GithubApi {
        return retrofit.create(GithubApi::class.java)
    }
}
