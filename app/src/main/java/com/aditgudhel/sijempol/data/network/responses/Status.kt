package com.aditgudhel.sijempol.data.network.responses

data class Status(
    val code: Int,
    val error: Boolean,
    val error_message: List<Any>,
    val message: String
)