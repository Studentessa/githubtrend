package com.martinez.xapoapp.data.domain


data class LanguagesErrorDomainModel(
    val errorCode: LamguagesErrorCode,
    val message: String? = null
)

sealed class LamguagesErrorCode {
    object ServerNotReachable : LamguagesErrorCode()
    object Unknown : LamguagesErrorCode()
}
