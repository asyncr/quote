package com.example.quote.presentation.ui.fragments.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.quote.data.model.LoginRequest
import com.example.quote.databinding.FragmentLoginBinding
import com.example.quote.presentation.ui.utils.Messages
import com.example.quote.presentation.ui.utils.Utils
import com.example.quote.presentation.viewmodel.DataStoreViewModel
import com.example.quote.presentation.viewmodel.LoginViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch

@AndroidEntryPoint
class Login : Fragment() {
    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!
    private val loginVM: LoginViewModel by viewModels()
    private val dataStoreVM: DataStoreViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        observer()
        with(binding) {
            edtUser.requestFocus()
            btnLogin.setOnClickListener { process() }
            return binding.root
        }
    }

    private fun process() {
        with(binding) {
            when {
                Utils.isValidField(edtUser).not() -> {
                    showMessage(Messages.INVALID_USERNAME.message)
                    return@process
                }
                Utils.isValidField(edtPassword).not() -> {
                    showMessage(Messages.INVALID_PASSWORD.message)
                    return@process
                }
                else -> login()
            }
        }
    }

    private fun login() = with(binding) {
        val account = edtUser.text.toString()
        val password = edtPassword.text.toString()
        lifecycleScope.launch {
            loginVM.login(LoginRequest(account, password))
        }
    }

    private fun observer() {
        lifecycleScope.launch {
            loginVM.user?.collect { auth ->
                if (auth.message.isNotEmpty()) {
                    Utils.tempAlert(requireContext(), Messages.ERROR_SERVER.message, auth.message)
                        .show()
                    return@collect
                }
                if (auth.success) {
                    val token = auth.data
                    dataStoreVM.let { it.saveToken(token) }
                    Utils.tempAlert(requireContext(), Messages.LOGIN_SUCCESS.message, auth.message)
                        .show()
                    this.cancel()
                    activity?.onBackPressed()
                    cleanFields()
                }
            }
        }
    }

    private fun showMessage(msg: String) = Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()

    private fun cleanFields() = with(binding) {
        edtUser.text.clear()
        edtPassword.text.clear()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}