package com.martinez.data.mapper

import com.martinez.data.domain.LamguagesErrorCode
import com.martinez.data.domain.LanguageDomainModel
import com.martinez.data.domain.LanguagesErrorDomainModel
import com.martinez.data.model.LanguageModel
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