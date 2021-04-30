package com.aditgudhel.sijempol.ui.auth

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.aditgudhel.sijempol.data.network.Resource
import com.aditgudhel.sijempol.data.network.responses.LoginResponse
import com.aditgudhel.sijempol.data.repository.AuthRepository
import com.aditgudhel.sijempol.ui.base.BaseViewModel
import kotlinx.coroutines.launch

class AuthViewModel(
    private val repository: AuthRepository
) : BaseViewModel(repository) {

    private val _loginResponse : MutableLiveData<Resource<LoginResponse>> = MutableLiveData()
    val loginResponse: LiveData<Resource<LoginResponse>>
        get() = _loginResponse

    fun login(
        username: String,
        password: String
    ) = viewModelScope.launch {
        _loginResponse.value = Resource.Loading
        _loginResponse.value = repository.login(username, password)
    }

    suspend fun saveAuthToken(token: String) {
        repository.saveAuthToken(token)
    }

}