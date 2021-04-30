package com.aditgudhel.sijempol.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.aditgudhel.sijempol.data.network.Resource
import com.aditgudhel.sijempol.data.network.responses.UserResponse
import com.aditgudhel.sijempol.data.repository.UserRepository
import com.aditgudhel.sijempol.ui.base.BaseViewModel
import kotlinx.coroutines.launch

class HomeViewModel(
    private val repository: UserRepository
) : BaseViewModel(repository) {

    private val _user: MutableLiveData<Resource<UserResponse>> = MutableLiveData()
    val user : LiveData<Resource<UserResponse>>
        get() = _user

    fun getUser() = viewModelScope.launch {
        _user.value = Resource.Loading
        _user.value = repository.getUser()
    }
}