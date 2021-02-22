package com.martinez.xapoapp.presentation.paymentList


import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.martinez.xapoapp.data.repository.ProjectRepository
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.martinez.xapoapp.base.util.EspressoIdlingResource
import com.martinez.xapoapp.data.domain.ProjectDomainModel
import com.martinez.xapoapp.data.domain.DomainResponse
import com.martinez.xapoapp.data.domain.LanguageDomainModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ProjectListDetailViewModel @ViewModelInject
constructor(
    private val projectRepository:  ProjectRepository
):ViewModel(){

    private val arrayRange = arrayOf("daily", "weekly", "monthly")
    private var since: String = arrayRange[0]
    var codelanguageCode: String = ""

    private val _projectListLiveData = MutableStateFlow<ProjectListViewModelState>(ProjectListViewModelState.Empty)

    val state : StateFlow<ProjectListViewModelState>
        get() = _projectListLiveData

    private var projectJob: Job ?= null
    var projectSelected: ProjectDomainModel ?= null
    var languages: List<LanguageDomainModel> ?= null

    init {
        getLanguages()
    }

    private fun getLanguages() {
        EspressoIdlingResource.increment()
        _projectListLiveData.value = (ProjectListViewModelState.LoadingLanguages)
        viewModelScope.launch {
            val response = projectRepository.getLanguges()
            when (response){
                is DomainResponse.Success -> {
                    if(!response.model.isNullOrEmpty()){
                        languages = response.model
                        _projectListLiveData.value =(ProjectListViewModelState.LanguageSuccess)
                    }
                }
                is DomainResponse.Error -> _projectListLiveData.value =(ProjectListViewModelState.Error)
            }
            EspressoIdlingResource.decrement()
        }
    }

    private fun getProjects() {
        EspressoIdlingResource.increment()
        _projectListLiveData.value =(ProjectListViewModelState.Loading(codelanguageCode))

        if(projectJob?.isActive == true){
            projectJob?.cancel()
            EspressoIdlingResource.decrement()
        }
        projectJob = viewModelScope.launch {
            when (val response = projectRepository.getProjectList(codelanguageCode.toLowerCase(), since)){
                is DomainResponse.Success -> {
                    if(response.model.isEmpty()){
                        _projectListLiveData.value =(ProjectListViewModelState.Empty)
                    }else {
                        _projectListLiveData.value =(ProjectListViewModelState.Success(response.model))
                    }
                }
                is DomainResponse.Error -> _projectListLiveData.value =(ProjectListViewModelState.Error)
            }
            EspressoIdlingResource.decrement()
        }
    }

    fun rangeHasChange(sincePosition: Int) {
        since = arrayRange[sincePosition]
        getProjects()
    }

    fun codeLanguageHasChange(codelanguageCode: LanguageDomainModel) {
        this.codelanguageCode = codelanguageCode.name
        getProjects()

    }

    fun projectSelected(projectSelected: ProjectDomainModel) {
        this.projectSelected = projectSelected
    }
}