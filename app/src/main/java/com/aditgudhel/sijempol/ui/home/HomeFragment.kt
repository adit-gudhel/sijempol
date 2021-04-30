package com.aditgudhel.sijempol.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.aditgudhel.sijempol.data.network.Resource
import com.aditgudhel.sijempol.data.network.UserApi
import com.aditgudhel.sijempol.data.network.responses.UserResponse
import com.aditgudhel.sijempol.data.repository.UserRepository
import com.aditgudhel.sijempol.databinding.FragmentHomeBinding
import com.aditgudhel.sijempol.ui.base.BaseFragment
import com.aditgudhel.sijempol.ui.visible
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking

class HomeFragment : BaseFragment<HomeViewModel, FragmentHomeBinding, UserRepository>() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.progressbar.visible(false)

        viewModel.getUser()

        viewModel.user.observe(viewLifecycleOwner, Observer {
            when(it) {
                is Resource.Success -> {
                    binding.progressbar.visible(false)
                    updateUI(it.value)
                }
                is Resource.Loading -> {
                    binding.progressbar.visible(true)
                }
            }
        })

        binding.buttonLogout.setOnClickListener {
            logout()
        }
    }

    private fun updateUI(user: UserResponse) {
        with(binding) {
            textViewId.text = user.akses_id.toString()
            textViewName.text = user.nama
            textViewEmail.text = user.email
        }
    }

    override fun getViewModel() = HomeViewModel::class.java

    override fun getFragmentBinding(
            inflater: LayoutInflater,
            container: ViewGroup?
    ) = FragmentHomeBinding.inflate(inflater, container, false)

    override fun getFragmentRepository(): UserRepository {
        val token = runBlocking { userPreferences.authToken.first() }
        val api = remoteDataSource.buildApi(UserApi::class.java, token)

        return UserRepository(api)
    }

}