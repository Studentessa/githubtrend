package com.martinez.data.repository

import com.martinez.data.domain.*
import com.martinez.data.mapper.LanguagePresentationMapper
import com.martinez.data.mapper.ProjectPresentationMapper
import com.martinez.data.remote.ProjectService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.IOException
import javax.inject.Inject

class ProjectRepository @Inject constructor(
    private val apiService: ProjectService,
    private val projectsMapper: ProjectPresentationMapper,
    private val languagePresentationMapper: LanguagePresentationMapper
){
    suspend fun getProjectList(
        language: String,
        since: String
    ): DomainResponse<List<ProjectDomainModel>, ProjectsErrorDomainModel> = withContext(Dispatchers.IO) {
        try {
            val response = apiService.getProjectList(language, since)
            if (response.isSuccessful && response.body() != null){
                val domainList = response.body()!!.map { projectsMapper.toDomainModel(it) }
                DomainResponse.Success(domainList)
            }else{
                DomainResponse.Error(projectsMapper.toErrorDomainModel(response.message()))
            }
        }catch  (e: IOException) {
            DomainResponse.Error(projectsMapper.toErrorDomainModel(e))
        }

    }

    suspend fun getLanguges(): DomainResponse<List<LanguageDomainModel>, LanguagesErrorDomainModel> =
        withContext(Dispatchers.IO) {
        try {
            val response = apiService.getLanguages()
            if (response.isSuccessful && !response.body().isNullOrEmpty()){
                val domainList = response.body()!!.map { languagePresentationMapper.toDomainModel(it) }
                DomainResponse.Success(domainList)
            }else{
                DomainResponse.Error(languagePresentationMapper.toErrorDomainModel(response.message()))
            }
        }catch  (e: IOException) {
            DomainResponse.Error(languagePresentationMapper.toErrorDomainModel(e))
        }

    }
}