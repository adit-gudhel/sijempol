package com.aditgudhel.sijempol.data.network

import com.aditgudhel.sijempol.data.network.responses.UserResponse
import okhttp3.ResponseBody
import retrofit2.http.GET
import retrofit2.http.POST

interface UserApi {

    @GET("api/user")
    suspend fun getUser() : UserResponse

    @POST("api/logout")
    suspend fun logout() : ResponseBody

}