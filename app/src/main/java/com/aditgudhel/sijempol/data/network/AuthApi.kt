package com.aditgudhel.sijempol.data.network

import com.aditgudhel.sijempol.data.network.responses.LoginResponse
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface AuthApi {

    @FormUrlEncoded
    @POST("api/login")
    suspend fun login(
        @Field("username") username: String,
        @Field("password") password: String
    ) : LoginResponse

}