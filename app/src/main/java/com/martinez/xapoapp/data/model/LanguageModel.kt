package com.martinez.xapoapp.data.model

import com.google.gson.annotations.SerializedName

data class LanguageModel(
    @SerializedName("urlParam")
    val urlParam: String,
    @SerializedName("name")
    val name: String
)
