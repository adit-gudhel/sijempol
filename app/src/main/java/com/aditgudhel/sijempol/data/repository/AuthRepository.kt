package com.aditgudhel.sijempol.data.repository

import com.aditgudhel.sijempol.data.UserPreferences
import com.aditgudhel.sijempol.data.network.AuthApi

class AuthRepository(
    private val api: AuthApi,
    private val preferences: UserPreferences
) : BaseRepository() {

    suspend fun login(
        username: String,
        password: String
    ) = safeApiCall {
        api.login(username, password)
    }

    suspend fun saveAuthToken(token: String) {
        preferences.saveAuthToken(token)
    }

}