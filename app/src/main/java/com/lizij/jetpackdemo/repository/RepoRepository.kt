package com.lizij.jetpackdemo.repository

import android.arch.lifecycle.MutableLiveData
import com.lizij.jetpackdemo.api.GithubApi
import com.lizij.jetpackdemo.application.MyApplication
import com.lizij.jetpackdemo.db.dao.RepoDao
import com.lizij.jetpackdemo.model.Repo
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers

class RepoRepository {

    private val mRepoDao: RepoDao? = MyApplication.getDataBase()?.repoDao()

    fun loadRepos(repos: MutableLiveData<List<Repo>>, username: String) {
        Observable.just(username)
                .observeOn(Schedulers.io())
                .subscribe {
                    val repoList = mRepoDao?.loadWithUsername(it)
                    if (repoList != null && repoList.isNotEmpty()) {
                        repos.postValue(repoList)
                        return@subscribe
                    }
                    loadFromNetwork(repos, username)
                }
    }

    private fun loadFromNetwork(repos: MutableLiveData<List<Repo>>, username: String) {
        GithubApi.api
                .listRepos(username)
                .subscribeOn(Schedulers.newThread())
                .observeOn(Schedulers.io())
                .subscribe { repoList ->
                    repos.postValue(repoList)
                    for (repo in repoList) {
                        mRepoDao?.insert(repo)
                    }
                }
    }
}
