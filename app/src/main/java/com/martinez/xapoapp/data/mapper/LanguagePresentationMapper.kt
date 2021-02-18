package com.martinez.xapoapp.data.mapper

import com.martinez.xapoapp.data.domain.*
import com.martinez.xapoapp.data.model.LanguageModel
import com.martinez.xapoapp.data.model.ProjectModel
import java.io.IOException

class LanguagePresentationMapper {

    fun toDomainModel(language: LanguageModel): LanguageDomainModel {
        return LanguageDomainModel(
            urlParam = language.urlParam,
            name = language.name
        )
    }

    fun toErrorDomainModel(error: String?): LanguagesErrorDomainModel {
        return LanguagesErrorDomainModel(LamguagesErrorCode.Unknown, error)
    }

    fun toErrorDomainModel(exception: Exception): LanguagesErrorDomainModel {
        val errorCode = when (exception) {
            is IOException -> LamguagesErrorCode.ServerNotReachable
            else -> LamguagesErrorCode.Unknown
        }
        return LanguagesErrorDomainModel(errorCode, exception.message)
    }
}