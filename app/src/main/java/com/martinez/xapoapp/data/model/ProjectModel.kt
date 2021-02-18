package com.martinez.xapoapp.data.model

import com.google.gson.annotations.SerializedName

data class ProjectModel(
    @SerializedName("author")
    val author: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("avatar")
    val avatar: String,
    @SerializedName("url")
    val url: String,
    @SerializedName("stars")
    val stars: Int,
    @SerializedName("forks")
    val forks: Int,
    @SerializedName("language")
    val language: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("currentPeriodStars")
    val currentPeriodStars: Int,
    @SerializedName("languageColor")
    val languageColor: String,
    @SerializedName("builtBy")
    val  builtBy: List<ContributorModel>
    )
