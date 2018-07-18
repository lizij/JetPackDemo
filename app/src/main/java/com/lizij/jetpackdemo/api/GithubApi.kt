package com.lizij.jetpackdemo.api

import com.lizij.jetpackdemo.model.Repo

import io.reactivex.Observable
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path

object GithubApi {

    private const val BASE_URL = "https://api.github.com/"
    private const val AUTH_KEY = "token 4c8b311ec19763cb62fc2ef77dc1ded048ef53da"
    val api: Api

    init {
        val client = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
        api = client.create(Api::class.java)
    }

    interface Api {
        @Headers("Authorization:$AUTH_KEY")
        @GET("users/{user}/repos")
        fun listRepos(@Path("user") user: String): Observable<List<Repo>>
    }
}
