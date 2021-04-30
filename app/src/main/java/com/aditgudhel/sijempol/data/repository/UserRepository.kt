package com.aditgudhel.sijempol.data.repository

import com.aditgudhel.sijempol.data.network.UserApi

class UserRepository(
    private val api: UserApi
) : BaseRepository() {

    suspend fun getUser() = safeApiCall {
        api.getUser()
    }

}