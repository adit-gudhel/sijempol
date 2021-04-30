package com.aditgudhel.sijempol.data.network.responses

data class Data(
    val access_token: String?,
    val expires_in: Int,
    val refresh_token: String,
    val token_type: String,
    val user: User
)