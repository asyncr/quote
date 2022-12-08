package com.example.quote.domain.usecase

import com.example.quote.data.model.LoginRequest
import com.example.quote.data.remote.UserLoginResponse
import com.example.quote.domain.UserRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class LoginUserUseCase @Inject constructor(private val userRepository: UserRepository) {
    suspend fun login(loginRequest: LoginRequest): Flow<UserLoginResponse>? = userRepository.login(loginRequest)
}
