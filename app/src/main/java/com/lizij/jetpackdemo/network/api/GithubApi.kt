package com.lizij.jetpackdemo.network.api

import bolts.Task
import com.lizij.jetpackdemo.BuildConfig
import com.lizij.jetpackdemo.data.model.Repo
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path

interface GithubApi {
    @Headers("Authorization:token ${BuildConfig.GITHUB_ACCESS_TOKEN}")
    @GET("/users/{user}/repos")
    fun listRepos(@Path("user") user: String): Task<List<Repo>>
}