package com.stanislavkorneev.korneevapp.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.stanislavkorneev.korneevapp.databinding.FragmentAuthBinding
import com.stanislavkorneev.korneevapp.presentation.AuthViewModel
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.stanislavkorneev.korneevapp.app.prefs
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

class AuthFragment: Fragment() {

    private var _binding: FragmentAuthBinding? = null
    private val binding get() = _binding!!

    companion object {
        fun newInstance() = AuthFragment()
    }

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val viewModel by viewModels<AuthViewModel> { viewModelFactory }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        AndroidSupportInjection.inject(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentAuthBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initListeners()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
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
                    (context as MainActivity).showLoginSuccessDialog()
                    (context as MainActivity).changeFragment(LoansListFragment.newInstance())
                }
            }
        }

        viewModel.exception.observe(viewLifecycleOwner) { exceptionSimpleName ->
            (context as MainActivity).showExceptionMessage(exceptionSimpleName)
        }
    }
}