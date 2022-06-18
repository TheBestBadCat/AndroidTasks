package com.stanislavkorneev.korneevapp.ui

import android.os.Bundle
import android.view.*

import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.stanislavkorneev.korneevapp.R
import com.stanislavkorneev.korneevapp.databinding.FragmentLoansListBinding
import com.stanislavkorneev.korneevapp.prefs
import com.stanislavkorneev.korneevapp.presentation.LoansListAdapter
import com.stanislavkorneev.korneevapp.presentation.LoansListViewModel

class LoansListFragment : Fragment() {

    private lateinit var binding: FragmentLoansListBinding
    private val adapter = LoansListAdapter { id ->
        prefs.loanIdPreferences = id
        (context as MainActivity).changeFragment(LoanInfoFragment.newInstance())
    }

    companion object {
        fun newInstance() = LoansListFragment()
    }

    private val viewModel: LoansListViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        setHasOptionsMenu(true)
        binding = FragmentLoansListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.loansList.adapter = adapter
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
            viewModel.getLoansList(token)
        }

        viewModel.loansList.observe(viewLifecycleOwner) { loans ->
            adapter.loans = loans
            binding.loansList.adapter = adapter
        }
    }

    private fun initExceptionObserver() {
        viewModel.exception.observe(viewLifecycleOwner) { exceptionSimpleName ->
            if (exceptionSimpleName.isNotEmpty())
                (context as MainActivity).showExceptionMessage(exceptionSimpleName)
        }
    }
}