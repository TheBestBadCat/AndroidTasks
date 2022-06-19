package com.stanislavkorneev.korneevapp.ui

import android.content.Context
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.stanislavkorneev.korneevapp.R
import com.stanislavkorneev.korneevapp.databinding.FragmentLoanInfoBinding
import com.stanislavkorneev.korneevapp.domain.entities.LoanState
import com.stanislavkorneev.korneevapp.app.prefs
import com.stanislavkorneev.korneevapp.presentation.LoanInfoViewModel
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

class LoanInfoFragment: Fragment() {

    private var _binding: FragmentLoanInfoBinding? = null
    private val binding get() = _binding!!

    companion object {
        fun newInstance() = LoanInfoFragment()
    }

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val viewModel by viewModels<LoanInfoViewModel> { viewModelFactory }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        AndroidSupportInjection.inject(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        setHasOptionsMenu(true)
        _binding = FragmentLoanInfoBinding.inflate(inflater, container, false)
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
            val amount = "${loan.amount}₽"
            val percent = "${loan.percent}%"
            val period = "${loan.period} дней"
            binding.firstNameText.text = loan.firstName
            binding.lastNameText.text = loan.lastName
            binding.phoneText.text = loan.phoneNumber
            binding.dateText.text = loan.date
            binding.amountText.text = amount
            binding.percentText.text = percent
            binding.periodText.text = period
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