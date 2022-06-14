package com.stanislavkorneev.korneevapp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.stanislavkorneev.korneevapp.databinding.FragmentAuthBinding
import com.stanislavkorneev.korneevapp.presentation.AuthViewModel
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer

class AuthFragment: Fragment() {

    private lateinit var binding: FragmentAuthBinding

    companion object {
        fun newInstance() = AuthFragment()
    }

    private val viewModel: AuthViewModel by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentAuthBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initListeners()
    }

    private fun initListeners() {
        binding.loginButton.setOnClickListener {
            viewModel.login(
                login = binding.login.text.toString(),
                password = binding.password.text.toString()
            )
        }

        binding.registrationButton.setOnClickListener {
            viewModel.registration(
                login = binding.login.text.toString(),
                password = binding.password.text.toString()
            )
        }

        viewModel.authData.observe(viewLifecycleOwner) {
            showMessage("Введите все необходимые данные")
        }

        viewModel.result.observe(viewLifecycleOwner) {
            showMessage(it)
        }

    }

    private fun showMessage(message: String) {
        Toast
            .makeText(context, message, Toast.LENGTH_SHORT)
            .show()
    }

}