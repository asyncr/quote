package com.example.quote.data.remote


import com.example.quote.data.model.LoginRequest
import com.google.gson.JsonObject
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface UserApiInterface {
    @Headers("Content-Type: application/json; charset=utf-8","Accept: */*; charset=utf-8")
    @POST("api/v1/users/Login")
    suspend fun login(@Body requestLogin: LoginRequest): JsonObject

}