package com.example.quote.data.remote

import com.example.quote.data.model.LoginRequest
import kotlinx.coroutines.flow.Flow


interface UserRemoteDataSource {
    suspend fun login(loginRequest: LoginRequest): Flow<UserLoginResponse>
}
