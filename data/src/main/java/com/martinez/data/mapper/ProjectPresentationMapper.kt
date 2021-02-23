package com.martinez.data.mapper


import com.martinez.data.domain.ContributorDomainModel
import com.martinez.data.domain.ProjectDomainModel
import com.martinez.data.domain.ProjectsErrorCode
import com.martinez.data.domain.ProjectsErrorDomainModel
import com.martinez.data.model.ContributorModel
import com.martinez.data.model.ProjectModel
import java.io.IOException

class ProjectPresentationMapper {

    fun toDomainModel(project: ProjectModel): ProjectDomainModel {
        return ProjectDomainModel(
            author = project.author,
            name = project.name,
            avatar = project.avatar,
            url = project.url,
            stars = project.stars,
            forks = project.forks,
            language = project.language,
            description = project.description,
            currentPeriodStars = project.currentPeriodStars,
            languageColor = project.languageColor,
            builtBy = project.builtBy.map { toContribuitorDomainModel(it) }
        )
    }

    private fun toContribuitorDomainModel(contributorModel: ContributorModel): ContributorDomainModel {
        return ContributorDomainModel(
            username = contributorModel.username,
            avatar = contributorModel.avatar,
            href = contributorModel.href
        )
    }

    fun toErrorDomainModel(error: String?): ProjectsErrorDomainModel {
        return ProjectsErrorDomainModel(ProjectsErrorCode.Unknown, error)
    }

    fun toErrorDomainModel(exception: Exception): ProjectsErrorDomainModel {
        val errorCode = when (exception) {
            is IOException -> ProjectsErrorCode.ServerNotReachable
            else -> ProjectsErrorCode.Unknown
        }
        return ProjectsErrorDomainModel(errorCode, exception.message)
    }
}