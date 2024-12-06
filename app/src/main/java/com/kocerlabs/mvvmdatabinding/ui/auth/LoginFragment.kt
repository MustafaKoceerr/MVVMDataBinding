package com.kocerlabs.mvvmdatabinding.ui.auth

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.kocerlabs.mvvmdatabinding.R
import com.kocerlabs.mvvmdatabinding.data.database.AppDatabase
import com.kocerlabs.mvvmdatabinding.data.network.MyApi
import com.kocerlabs.mvvmdatabinding.data.network.model.NetworkConnectionInterceptor
import com.kocerlabs.mvvmdatabinding.data.network.model.UserResponse
import com.kocerlabs.mvvmdatabinding.data.repository.UserRepository
import com.kocerlabs.mvvmdatabinding.databinding.FragmentLoginBinding
import com.kocerlabs.mvvmdatabinding.ui.home.HomeActivity
import com.kocerlabs.mvvmdatabinding.util.snackbar
import com.kocerlabs.mvvmdatabinding.util.visibility
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class LoginFragment : Fragment(), AuthListener {
    private val viewModel: AuthViewModel by viewModels()
    private lateinit var binding: FragmentLoginBinding

//    @Inject lateinit var networkConnectionInterceptor: NetworkConnectionInterceptor

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_login, container, false)

        // Inflate the layout for this fragment
        return binding.rootLoginFragment
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

     /*
        val networkConnectionInterceptor = NetworkConnectionInterceptor(requireContext())
        val api = MyApi(networkConnectionInterceptor)
        val database = AppDatabase(requireContext())
        val repository = UserRepository(api, database)
        val factory = AuthViewModelFactory(repository)
        viewModel = ViewModelProvider(requireActivity(), factory).get(AuthViewModel::class.java)
      */


//        viewModel = ViewModelProvider(this).get(AuthViewModel::class.java)
        viewModel.authListener = this
        binding.viewmodel = viewModel


        viewModel.getLoggedInUser().observe(viewLifecycleOwner, Observer { user ->
            if (user != null) {
                Intent(requireContext(), HomeActivity::class.java).also {
                    it.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    startActivity(it)
                }
            }
        })
    }

    override fun onStarted() {
        binding.rootLoginFragment.snackbar("Login Started")
        binding.progressBar.visibility(true)
    }

    override fun onSuccess(body: UserResponse?) {
        binding.progressBar.visibility(false)
    }

    override fun onFailure(message: String) {
        binding.rootLoginFragment.snackbar("Error: $message")
        binding.progressBar.visibility(false)
    }
}