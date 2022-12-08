package com.example.quote.presentation.viewmodel


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.quote.data.model.LoginRequest
import com.example.quote.data.remote.UserLoginResponse
import com.example.quote.domain.usecase.LoginUserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.random.Random

@HiltViewModel
class LoginViewModel
@Inject constructor(
    private val loginUserUseCase: LoginUserUseCase
) : ViewModel() {

    private var _user = MutableStateFlow(
        UserLoginResponse(false, "", "")
    )

    val user: StateFlow<UserLoginResponse>? = _user

    fun login(loginRequest: LoginRequest) {
        viewModelScope.launch {
            val loginResponse = loginUserUseCase.login(loginRequest)?.first()
            loginResponse.let {
                if (!(it!!.success)) { // Si no se pudo loguear
                    it!!.data = it!!.data + Random.nextInt(1, 99999)
                }
                _user.value = it // Actualiza el valor del StateFlow
            }
        }
    }

}