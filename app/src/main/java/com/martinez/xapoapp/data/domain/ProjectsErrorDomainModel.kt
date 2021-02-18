package com.martinez.xapoapp.data.domain


data class ProjectsErrorDomainModel(
    val errorCode: ProjectsErrorCode,
    val message: String? = null
)

sealed class ProjectsErrorCode {
    object ServerNotReachable : ProjectsErrorCode()
    object Unknown : ProjectsErrorCode()
}
