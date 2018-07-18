package com.lizij.jetpackdemo.viewmodel

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel

import com.lizij.jetpackdemo.model.Repo
import com.lizij.jetpackdemo.repository.RepoRepository

class RepoViewModel : ViewModel() {

    val ownerName: MutableLiveData<String> = MutableLiveData()
    val repos: MutableLiveData<List<Repo>> = MutableLiveData()
    private val mRepoRepository: RepoRepository = RepoRepository()

    fun loadRepos() {
        mRepoRepository.loadRepos(repos, ownerName.value?:"")
    }
}
