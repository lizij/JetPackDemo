package com.lizij.jetpackdemo.repository

import android.arch.lifecycle.MutableLiveData
import bolts.Continuation
import bolts.Task
import com.lizij.jetpackdemo.api.GithubApi
import com.lizij.jetpackdemo.application.MyApplication
import com.lizij.jetpackdemo.db.dao.RepoDao
import com.lizij.jetpackdemo.model.Repo
import io.reactivex.schedulers.Schedulers

class RepoRepository {

    private val mRepoDao: RepoDao? = MyApplication.getDataBase()?.repoDao()

    fun loadRepos(repos: MutableLiveData<List<Repo>>, username: String) {
        Task.callInBackground {
            val repoList = mRepoDao?.loadWithUsername(username)
            if (repoList != null && repoList.isNotEmpty()) {
                repos.postValue(repoList)
            }
            loadFromNetwork(repos, username)
        }
    }

    private fun loadFromNetwork(repos: MutableLiveData<List<Repo>>, username: String) {
        GithubApi.api.listRepos(username).continueWith(Continuation<List<Repo>, Void?> {
            repos.postValue(it.result)
            for (repo in it.result) {
                mRepoDao?.insert(repo)
            }
            return@Continuation null
        })
    }
}
