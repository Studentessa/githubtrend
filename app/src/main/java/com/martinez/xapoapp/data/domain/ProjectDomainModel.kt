package com.martinez.xapoapp.data.domain


data class ProjectDomainModel(
    val author: String,
    val name: String,
    val avatar: String,
    val url: String,
    val stars: Int,
    val forks: Int,
    val language: String?,
    val description: String,
    val currentPeriodStars: Int,
    val languageColor: String?,
    val builtBy: List<ContributorDomainModel>
    )

