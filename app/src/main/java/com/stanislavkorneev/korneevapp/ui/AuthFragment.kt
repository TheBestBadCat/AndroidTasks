package com.stanislavkorneev.korneevapp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.stanislavkorneev.korneevapp.databinding.FragmentAuthBinding
import com.stanislavkorneev.korneevapp.presentation.AuthViewModel
import androidx.fragment.app.viewModels
import com.stanislavkorneev.korneevapp.prefs

class AuthFragment: Fragment() {

    private lateinit var binding: FragmentAuthBinding

    companion object {
        fun newInstance() = AuthFragment()
    }

    private val viewModel: AuthViewModel by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentAuthBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initListeners()
    }

    private fun initListeners() {

        binding.registrationButton.setOnClickListener {
            viewModel.registration(
                login = binding.loginEditText.text.toString(),
                password = binding.passwordEditText.text.toString()
            )

            viewModel.registrationSuccess.observe(viewLifecycleOwner) { registrationSuccess ->
                if (registrationSuccess) {
                    (context as MainActivity).showRegistrationSuccessDialog()
                }
            }
        }

        binding.loginButton.setOnClickListener {
            viewModel.login(
                login = binding.loginEditText.text.toString(),
                password = binding.passwordEditText.text.toString()
            )

            viewModel.token.observe(viewLifecycleOwner) { token ->
                if (token.isNotEmpty()) {
                    prefs.tokenPreferences = token
                    (context as MainActivity).changeFragment(LoanCreateFragment.newInstance())
                }
            }
        }


        viewModel.exception.observe(viewLifecycleOwner) { exceptionSimpleName ->
            (context as MainActivity).showExceptionMessage(exceptionSimpleName)
        }
    }
}