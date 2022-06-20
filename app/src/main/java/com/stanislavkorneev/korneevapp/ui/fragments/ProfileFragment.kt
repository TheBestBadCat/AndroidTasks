package com.stanislavkorneev.korneevapp.ui.fragments

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import com.stanislavkorneev.korneevapp.R
import com.stanislavkorneev.korneevapp.databinding.FragmentProfileBinding
import com.stanislavkorneev.korneevapp.ui.MainActivity

class ProfileFragment: Fragment() {

    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!

    companion object {
        fun newInstance() = ProfileFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        setHasOptionsMenu(true)
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
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
        inflater.inflate(R.menu.top_menu_logout, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        (context as MainActivity).showLogoutDialog()
        return super.onOptionsItemSelected(item)
    }

    private fun initListeners() {
        binding.startGuideButton.setOnClickListener {
            (context as MainActivity).showLoginSuccessDialog()
        }

        binding.startCashGuideButton.setOnClickListener {
            (context as MainActivity).showGetLoanCashGuide()
        }

        binding.startCardGuideButton.setOnClickListener {
            (context as MainActivity).showGetLoanCardGuide()
        }
    }
}