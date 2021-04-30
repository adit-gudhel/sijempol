package com.aditgudhel.sijempol.ui.auth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import com.aditgudhel.sijempol.data.network.AuthApi
import com.aditgudhel.sijempol.data.network.Resource
import com.aditgudhel.sijempol.data.repository.AuthRepository
import com.aditgudhel.sijempol.databinding.FragmentLoginBinding
import com.aditgudhel.sijempol.ui.base.BaseFragment
import com.aditgudhel.sijempol.ui.enable
import com.aditgudhel.sijempol.ui.handleApiError
import com.aditgudhel.sijempol.ui.home.HomeActivity
import com.aditgudhel.sijempol.ui.startNewActivity
import com.aditgudhel.sijempol.ui.visible
import kotlinx.coroutines.launch

class LoginFragment : BaseFragment<AuthViewModel, FragmentLoginBinding, AuthRepository>() {

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        binding.buttonLogin.enable(false)

        viewModel.loginResponse.observe(viewLifecycleOwner, Observer {
            binding.progressbar.visible(it is Resource.Loading)
            when (it) {
                is Resource.Success -> {
                    lifecycleScope.launch {
                        viewModel.saveAuthToken(it.value.data.access_token!!)
                        requireActivity().startNewActivity(HomeActivity::class.java)
                    }
                }
                is Resource.Failure -> handleApiError(it){
                    login()
                }
            }
        })

        binding.etPassword.addTextChangedListener {
            val username = binding.etUsername.text.toString().trim()
            binding.buttonLogin.enable(username.isNotEmpty() && it.toString().isNotEmpty())
        }

        binding.buttonLogin.setOnClickListener {
            login()
        }
    }

    private fun login() {
        val username = binding.etUsername.text.toString().trim()
        val password = binding.etPassword.text.toString().trim()
        viewModel.login(username, password)
    }

    override fun getViewModel() = AuthViewModel::class.java

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentLoginBinding.inflate(inflater, container, false)

    override fun getFragmentRepository() =
        AuthRepository(remoteDataSource.buildApi(AuthApi::class.java), userPreferences)

}