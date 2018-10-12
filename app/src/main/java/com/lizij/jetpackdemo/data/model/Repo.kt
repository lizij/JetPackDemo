package com.lizij.jetpackdemo.data.model

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Embedded
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

import com.google.gson.annotations.SerializedName

import java.io.Serializable

@Entity(tableName = "repos")
class Repo : Serializable {

    @PrimaryKey
    @ColumnInfo(name = "id")
    @SerializedName("id")
    var id: Long = 0

    @ColumnInfo(name = "name")
    @SerializedName("name")
    var name: String? = null

    @ColumnInfo(name = "full_name")
    @SerializedName("full_name")
    var fullName: String? = null

    @ColumnInfo(name = "description")
    @SerializedName("description")
    var description: String? = null

    @ColumnInfo(name = "html_url")
    @SerializedName("html_url")
    var htmlUrl: String? = null

    @ColumnInfo(name = "language")
    @SerializedName("language")
    var language: String? = null

    @Embedded
    @SerializedName("owner")
    var owner: Owner? = null
}
