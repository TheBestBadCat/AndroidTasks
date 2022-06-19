package com.stanislavkorneev.korneevapp.ui

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.stanislavkorneev.korneevapp.R
import com.stanislavkorneev.korneevapp.databinding.FragmentLoanCreateBinding
import com.stanislavkorneev.korneevapp.domain.entities.LoanConditions
import com.stanislavkorneev.korneevapp.prefs
import com.stanislavkorneev.korneevapp.presentation.LoanCreateViewModel

class LoanCreateFragment: Fragment() {

    private lateinit var binding: FragmentLoanCreateBinding
    private lateinit var loanConditions: LoanConditions

    companion object {
        fun newInstance() = LoanCreateFragment()
    }

    private val viewModel: LoanCreateViewModel by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        setHasOptionsMenu(true)
        binding = FragmentLoanCreateBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initListeners()
        initExceptionObserver()
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
            initConditions()
        }

        binding.createNewLoanButton.setOnClickListener {
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

    private fun initConditions() {
        val amount = "${loanConditions.maxAmount} ₽"
        val percent = "${loanConditions.percent} %"
        val period = "${loanConditions.period} дней"
        binding.amountLoanConditionText.text = amount
        binding.percentLoanConditionText.text = percent
        binding.periodLoanConditionText.text = period
    }
}