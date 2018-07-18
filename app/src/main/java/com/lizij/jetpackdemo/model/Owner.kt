package com.lizij.jetpackdemo.model

import android.arch.persistence.room.ColumnInfo

import com.google.gson.annotations.SerializedName

import java.io.Serializable

class Owner : Serializable {

    @SerializedName("login")
    @ColumnInfo(name = "owner_name")
    var username: String? = null

    @SerializedName("id")
    @ColumnInfo(name = "owner_id")
    var uid: Long = 0

    @SerializedName("html_url")
    @ColumnInfo(name = "owner_html_url")
    var userHtmlUrl: String? = null
}
