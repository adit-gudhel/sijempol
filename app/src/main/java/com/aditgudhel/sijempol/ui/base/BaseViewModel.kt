package com.aditgudhel.sijempol.ui.base

import androidx.lifecycle.ViewModel
import com.aditgudhel.sijempol.data.network.UserApi
import com.aditgudhel.sijempol.data.repository.BaseRepository

abstract class BaseViewModel(
        private val repository: BaseRepository
) : ViewModel() {

    suspend fun logout(api: UserApi) = repository.logout(api)
}