package com.lizij.jetpackdemo.db.dao


import android.arch.persistence.room.*
import android.arch.persistence.room.OnConflictStrategy.REPLACE
import com.lizij.jetpackdemo.model.Repo

@Dao
interface RepoDao {

    @Insert(onConflict = REPLACE)
    fun insert(repo: Repo)

    @Query("select * from repos where owner_name = :ownerName")
    fun loadWithUsername(ownerName: String): List<Repo>

    @Update
    fun update(repo: Repo)

    @Delete
    fun delete(repo: Repo)
}