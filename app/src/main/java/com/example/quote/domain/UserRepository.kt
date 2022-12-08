package com.example.quote.domain

import com.example.quote.data.model.LoginRequest
import com.example.quote.data.remote.UserLoginResponse
import kotlinx.coroutines.flow.Flow

interface  UserRepository {
    suspend fun login(loginRequest: LoginRequest): Flow<UserLoginResponse>?
}