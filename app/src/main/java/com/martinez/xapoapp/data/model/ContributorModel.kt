package com.martinez.xapoapp.data.model

import com.google.gson.annotations.SerializedName

data class ContributorModel(
    @SerializedName("username")
    val username: String,
    @SerializedName("avatar")
    val avatar: String,
    @SerializedName("href")
    val href: String
)
