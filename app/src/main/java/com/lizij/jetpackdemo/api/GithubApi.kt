package com.lizij.jetpackdemo.api

import bolts.Task
import com.lizij.jetpackdemo.BuildConfig
import com.lizij.jetpackdemo.model.Repo
import com.lizij.jetpackdemo.retrofit.adapter.TaskCallAdapterFactory

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path

object GithubApi {

    private const val BASE_URL = "https://api.github.com/"
    val api: Api

    init {
        val client = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(TaskCallAdapterFactory.create())
                .build()
        api = client.create(Api::class.java)
    }

    interface Api {
        @Headers("Authorization:token ${BuildConfig.GITHUB_ACCESS_TOKEN}")
        @GET("users/{user}/repos")
        fun listRepos(@Path("user") user: String): Task<List<Repo>>
    }
}
