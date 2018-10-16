package com.lizij.jetpackdemo.ui.activity

import android.arch.lifecycle.Observer
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.lizij.jetpackdemo.R
import com.lizij.jetpackdemo.data.model.Repo
import com.lizij.jetpackdemo.ui.viewmodel.RepoViewModel
import kotlinx.android.synthetic.main.activity_repo.*
import kotlinx.android.synthetic.main.item_repo.view.*
import javax.inject.Inject

class RepoActivity : BaseActivity(){

    @Inject
    lateinit var mRepoViewModel: RepoViewModel

    private var mRepoAdapter: RepoAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_repo)

        mRepoViewModel.ownerName.observe(this, Observer { mRepoViewModel.loadRepos() })

        mRepoViewModel.repos.observe(this, Observer { repoList ->
            if (repoList != null) {
                // 创建RepoAdapter或更新adapter中的repoList
                if (mRepoAdapter == null) {
                    mRepoAdapter = RepoAdapter(this@RepoActivity, mRepoViewModel.repos.value)
                    this@RepoActivity.mRepoListView?.adapter = mRepoAdapter
                    val layoutManager = LinearLayoutManager(this@RepoActivity)
                    layoutManager.stackFromEnd = true
                    this@RepoActivity.mRepoListView?.layoutManager = layoutManager
                } else {
                    mRepoAdapter?.updateRepos(repoList)
                }
            }
        })

        search.setOnClickListener {
            val ownerName:String = mGithubOwnerName?.editableText.toString()
            if (!ownerName.equals(mRepoViewModel.ownerName.value)) {
                mRepoViewModel.ownerName.value = ownerName
            }
        }

    }

    inner class RepoAdapter(mContext: Context, private var mRepoList: List<Repo>?) : RecyclerView.Adapter<RepoAdapter.ViewHolder>() {
        private val mLayoutInflater: LayoutInflater = LayoutInflater.from(mContext)

        fun updateRepos(repoList: List<Repo>?) {
            mRepoList = repoList
            notifyDataSetChanged()
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            return ViewHolder(mLayoutInflater.inflate(R.layout.item_repo, parent, false))
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) =
                holder.bindTo(mRepoList!![position])

        override fun getItemCount(): Int = mRepoList?.size ?: 0

        inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

            fun bindTo(repo: Repo) {
                itemView.repoTitle?.text = repo.name
                itemView.repoDescription?.text = repo.description
                itemView.setOnClickListener {
                    val intent = Intent(this@RepoActivity, WebActivity::class.java)
                    intent.putExtra("url", repo.htmlUrl)
                    startActivity(intent)
                }
            }
        }
    }
}
