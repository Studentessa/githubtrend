package com.martinez.xapoapp.presentation.paymentList

import com.martinez.xapoapp.data.domain.ProjectDomainModel

sealed class ProjectListViewModelState {
    data class Loading(val language: String): ProjectListViewModelState()
    object Empty : ProjectListViewModelState()
    data class Success(val list: List<ProjectDomainModel>): ProjectListViewModelState()
    object LoadingLanguages: ProjectListViewModelState()
    object LanguageSuccess: ProjectListViewModelState()
    object Error : ProjectListViewModelState()
}