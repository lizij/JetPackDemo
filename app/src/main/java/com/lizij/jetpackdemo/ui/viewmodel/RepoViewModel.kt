package com.lizij.jetpackdemo.ui.viewmodel

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel

import com.lizij.jetpackdemo.data.model.Repo
import com.lizij.jetpackdemo.data.repository.RepoRepository
import javax.inject.Inject

class RepoViewModel : ViewModel() {

    val ownerName: MutableLiveData<String> = MutableLiveData()
    val repos: MutableLiveData<List<Repo>> = MutableLiveData()

    @Inject
    lateinit var mRepoRepository: RepoRepository

    init {
        DaggerRepoViewModelComponent.create().inject(this)
    }

    fun loadRepos() {
        mRepoRepository.loadRepos(repos, ownerName.value?:"")
    }
}
