package com.kocerlabs.mvvmdatabinding.ui.auth

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.kocerlabs.mvvmdatabinding.R
import com.kocerlabs.mvvmdatabinding.data.network.model.UserResponse
import com.kocerlabs.mvvmdatabinding.databinding.FragmentLoginBinding
import com.kocerlabs.mvvmdatabinding.ui.home.HomeActivity
import com.kocerlabs.mvvmdatabinding.util.snackbar
import com.kocerlabs.mvvmdatabinding.util.visibility


class LoginFragment : Fragment(), AuthListener {
    private lateinit var binding: FragmentLoginBinding
    private val TAG = "LoginFragment"
    private var viewModel: AuthViewModel? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(layoutInflater, R.layout.fragment_login, container, false)

        return binding.root
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
        // MANUEL DI
        viewModel =
            (requireActivity() as LoginActivity).appContainer.authContainer?.authViewModelFactory?.create()

        binding.viewmodel = viewModel

        viewModel?.authListener = this

        viewModel?.getLoggedInUser()?.observe(viewLifecycleOwner, Observer { user ->
            if (user != null) {
                val intent = Intent(requireActivity(), HomeActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(intent)
            }
        })
    }

    override fun onStarted() {
        binding.rootLogin.snackbar("Login Started")
        binding.progressBar.visibility(true)
    }

    override fun onSuccess(body: UserResponse?) {
        binding.progressBar.visibility(false)
    }

    override fun onFailure(message: String) {
        binding.rootLogin.snackbar("Error: $message")
        binding.progressBar.visibility(false)
    }

}