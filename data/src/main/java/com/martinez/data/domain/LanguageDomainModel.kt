package com.martinez.data.domain

import com.google.gson.annotations.SerializedName

data class LanguageDomainModel(
    @SerializedName("urlParam")
    val urlParam: String,
    @SerializedName("name")
    val name: String
)
