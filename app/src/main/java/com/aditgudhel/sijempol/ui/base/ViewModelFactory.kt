package com.aditgudhel.sijempol.ui.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.aditgudhel.sijempol.data.repository.AuthRepository
import com.aditgudhel.sijempol.data.repository.BaseRepository
import com.aditgudhel.sijempol.data.repository.UserRepository
import com.aditgudhel.sijempol.ui.auth.AuthViewModel
import com.aditgudhel.sijempol.ui.home.HomeViewModel

@Suppress("UNCHECKED_CAST")
class ViewModelFactory(
    private val repository: BaseRepository
) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(AuthViewModel::class.java) -> AuthViewModel(repository as AuthRepository) as T
            modelClass.isAssignableFrom(HomeViewModel::class.java) -> HomeViewModel(repository as UserRepository) as T
            else -> throw IllegalArgumentException("ViewModelClass Not Found")
        }
    }
}