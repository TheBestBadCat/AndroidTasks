package com.stanislavkorneev.korneevapp.ui.fragments

import android.content.Context
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.stanislavkorneev.korneevapp.R
import com.stanislavkorneev.korneevapp.databinding.FragmentLoanCreateBinding
import com.stanislavkorneev.korneevapp.domain.entities.LoanConditions
import com.stanislavkorneev.korneevapp.app.prefs
import com.stanislavkorneev.korneevapp.presentation.viewModels.LoanCreateViewModel
import com.stanislavkorneev.korneevapp.ui.MainActivity
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

class LoanCreateFragment: Fragment() {

    private var _binding: FragmentLoanCreateBinding? = null
    private val binding get() = _binding!!
    private lateinit var loanConditions: LoanConditions

    companion object {
        fun newInstance() = LoanCreateFragment()
    }

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val viewModel by viewModels<LoanCreateViewModel> { viewModelFactory }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        AndroidSupportInjection.inject(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        setHasOptionsMenu(true)
        _binding = FragmentLoanCreateBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initListeners()
        initExceptionObserver()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.top_menu_refresh, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        initListeners()
        return super.onOptionsItemSelected(item)
    }

    private fun initListeners() {
        prefs.tokenPreferences?.let { token ->
            viewModel.getLoanConditions(token)
        }

        viewModel.conditions.observe(viewLifecycleOwner) { conditions ->
            loanConditions = conditions
            loadConditions()
        }

        binding.createNewLoanButton.setOnClickListener {
            createLoan()

            viewModel.loan.observe(viewLifecycleOwner) {
                (context as MainActivity).showCreateLoanSuccessDialog()
            }
        }
    }

    private fun initExceptionObserver() {
        viewModel.exception.observe(viewLifecycleOwner) { exceptionSimpleName ->
            if (exceptionSimpleName.isNotEmpty())
                (context as MainActivity).showExceptionMessage(exceptionSimpleName)
        }
    }

    private fun loadConditions() {
        val amount = "${loanConditions.maxAmount} ₽"
        val percent = "${loanConditions.percent} %"
        val period = "${loanConditions.period} дней"
        binding.amountLoanConditionText.text = amount
        binding.percentLoanConditionText.text = percent
        binding.periodLoanConditionText.text = period
    }

    private fun createLoan() {
        prefs.tokenPreferences?.let { token ->
            viewModel.createLoan(
                token = token,
                amount = loanConditions.maxAmount,
                percent = loanConditions.percent,
                period = loanConditions.period,
                firstName = binding.firstNameEditText.text.toString(),
                lastName = binding.lastNameEditText.text.toString(),
                phoneNumber = binding.phoneNumberEditText.text.toString()
            )
        }
    }
}