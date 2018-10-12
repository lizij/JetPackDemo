package com.lizij.jetpackdemo.data.repository

import android.arch.lifecycle.MutableLiveData
import bolts.Continuation
import bolts.Task
import com.lizij.jetpackdemo.network.api.GithubApi
import com.lizij.jetpackdemo.data.model.Repo
import com.lizij.jetpackdemo.db.dao.RepoDao
import com.lizij.jetpackdemo.ui.MyApplication
import javax.inject.Inject

class RepoRepository @Inject constructor() {

    @Inject
    lateinit var repoDao: RepoDao
    @Inject
    lateinit var githubApi: GithubApi

    init {
        MyApplication.appComponent.inject(this)
    }

    fun loadRepos(repos: MutableLiveData<List<Repo>>, username: String) {
        Task.callInBackground {
            var repoList = repoDao.loadWithUsername(username)
            if (repoList.isNotEmpty()) {
                repoList = repoList.sortedBy { repo: Repo -> repo.name }
                repos.postValue(repoList)
            }
            loadFromNetwork(repos, username)
        }
    }

    private fun loadFromNetwork(repos: MutableLiveData<List<Repo>>, username: String) {
        githubApi.listRepos(username).continueWith(Continuation<List<Repo>, Void?> {
            repos.postValue(it.result)
            for (repo in it.result) {
                repoDao.insert(repo)
            }
            return@Continuation null
        })
    }
}
