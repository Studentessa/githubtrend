package com.martinez.xapoapp.data.remote

import com.martinez.xapoapp.data.model.LanguageModel
import com.martinez.xapoapp.data.model.ProjectModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ProjectService {

    @GET("repositories")
    suspend fun getProjectList(
        @Query("language") language: String,
        @Query("since") since: String
    ): Response<ArrayList<ProjectModel>>

    @GET("languages")
    suspend fun getLanguages(): Response<ArrayList<LanguageModel>>
}