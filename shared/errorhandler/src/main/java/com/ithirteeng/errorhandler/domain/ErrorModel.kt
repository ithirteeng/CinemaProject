package com.ithirteeng.errorhandler.domain

data class ErrorModel(
    val errorCode: Int,
    val errorDescription: String? = null,
    val errorDescriptionId: Int? = null
)
