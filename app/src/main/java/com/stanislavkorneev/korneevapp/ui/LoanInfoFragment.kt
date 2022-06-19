package com.stanislavkorneev.korneevapp.ui

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.stanislavkorneev.korneevapp.R
import com.stanislavkorneev.korneevapp.databinding.FragmentLoanInfoBinding
import com.stanislavkorneev.korneevapp.domain.entities.LoanState
import com.stanislavkorneev.korneevapp.prefs
import com.stanislavkorneev.korneevapp.presentation.LoanInfoViewModel

class LoanInfoFragment: Fragment() {

    private lateinit var binding: FragmentLoanInfoBinding

    companion object {
        fun newInstance() = LoanInfoFragment()
    }

    private val viewModel: LoanInfoViewModel by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        setHasOptionsMenu(true)
        binding = FragmentLoanInfoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initListeners()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.top_menu_close, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        (context as MainActivity).changeFragment(LoansListFragment.newInstance())
        return super.onOptionsItemSelected(item)
    }

    private fun initListeners() {
        prefs.tokenPreferences?.let { token ->
            viewModel.getLoanInfo(token, prefs.loanIdPreferences)
        }

        viewModel.loan.observe(viewLifecycleOwner) { loan ->
            binding.firstNameText.text = loan.firstName
            binding.lastNameText.text = loan.lastName
            binding.phoneText.text = loan.phoneNumber
            binding.dateText.text = loan.date
            binding.amountText.text = loan.amount.toString()
            binding.percentText.text = loan.percent.toString()
            binding.periodText.text = loan.period.toString()
            binding.stateText.text = when (loan.state) {
                LoanState.APPROVED -> "Одобрен"
                LoanState.REJECTED -> "Отклонен"
                LoanState.REGISTERED -> "Зарегистрирован"
            }
        }

        viewModel.exception.observe(viewLifecycleOwner) { exceptionSimpleName ->
            if (exceptionSimpleName.isNotEmpty())
                (context as MainActivity).showExceptionMessage(exceptionSimpleName)
        }
    }
}