package com.example.quote.presentation.ui.fragments.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.quote.databinding.FragmentLoginBinding
import com.example.quote.presentation.ui.utils.CustomMessages
import com.example.quote.presentation.ui.utils.Utils
import com.example.quote.presentation.viewmodel.LoginViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class Login : Fragment() {
    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!
    private val loginVM: LoginViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        with(binding) {
            btnLogin.setOnClickListener { process() }
            return binding.root
        }
    }

    private fun process() {
        with(binding) {
            when {
                Utils.isValidField(edtUser).not() -> {
                    showMessage(CustomMessages.INVALID_USERNAME.message)
                    return@process
                }
                Utils.isValidField(edtPassword).not() -> {
                    showMessage(CustomMessages.INVALID_PASSWORD.message)
                    return@process
                }
                else -> login()
            }
        }
    }

    private fun login() = with(binding) {
        val user = edtUser.text.toString()
        val password = edtPassword.text.toString()
        showMessage(CustomMessages.LOGIN_SUCCESS.message)
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