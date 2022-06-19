package com.stanislavkorneev.korneevapp.ui

import android.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.stanislavkorneev.korneevapp.R
import com.stanislavkorneev.korneevapp.databinding.ActivityMainBinding
import com.stanislavkorneev.korneevapp.prefs
import com.stanislavkorneev.korneevapp.presentation.AuthViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel : AuthViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel = ViewModelProvider(this)[AuthViewModel::class.java]
        setHomeFragment()
        initNavigationBarListener()
    }

    fun changeFragment(fragment: Fragment){
        supportFragmentManager
            .beginTransaction()
            .replace(binding.fragmentContainer.id, fragment)
            .commit()
    }

    fun showExceptionMessage(exceptionSimpleName: String) {
        when (exceptionSimpleName) {
            "HttpException" -> showHttpExceptionDialog()
            "UnknownHostException" -> showUnknownHostExceptionDialog()
            "IllegalArgumentException" -> showInputExceptionDialog()
            else -> showUnknownExceptionDialog()
        }
    }

    fun showRegistrationSuccessDialog() {
        AlertDialog.Builder(this)
            .setTitle(R.string.registration_success_title)
            .setMessage(R.string.registration_success_message)
            .setNeutralButton(R.string.dialog_neutral_button) { dialog, id ->
                dialog.dismiss()
            }.show()
    }

    fun showCreateLoanSuccessDialog() {
        AlertDialog.Builder(this)
            .setTitle(R.string.create_loan_success_title)
            .setMessage(R.string.create_loan_success_message)
            .setNeutralButton(R.string.create_loan_success_button) { dialog, id ->
                dialog.dismiss()
                changeFragment(LoanInfoFragment.newInstance())
            }.show()
    }

    private fun showInputExceptionDialog() {
        AlertDialog.Builder(this)
            .setTitle(R.string.input_exception_title)
            .setMessage(R.string.input_exception_message)
            .setNeutralButton(R.string.dialog_neutral_button) { dialog, id ->
                dialog.dismiss()
            }.show()
    }

    private fun showUnknownHostExceptionDialog() {
        AlertDialog.Builder(this)
            .setTitle(R.string.host_exception_title)
            .setMessage(R.string.host_exception_message)
            .setNeutralButton(R.string.dialog_neutral_button) { dialog, id ->
                dialog.dismiss()
            }.show()
    }

    private fun showHttpExceptionDialog() {
        AlertDialog.Builder(this)
            .setTitle(R.string.http_exception_title)
            .setMessage(R.string.host_exception_message)
            .setNeutralButton(R.string.dialog_neutral_button) { dialog, id ->
                dialog.dismiss()
            }.show()
    }

    private fun showUnknownExceptionDialog() {
        AlertDialog.Builder(this)
            .setTitle(R.string.unknown_exception_title)
            .setMessage(R.string.input_exception_message)
            .setNeutralButton(R.string.dialog_neutral_button) { dialog, id ->
                dialog.dismiss()
            }.show()
    }

    private fun setHomeFragment() {
        if (!isLoginUser())
            changeFragment(LoansListFragment.newInstance())
        else
            changeFragment(AuthFragment.newInstance())
    }

    private fun initNavigationBarListener() {
        binding.bottomNavigation.setOnItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.home -> {
                    setHomeFragment()
                    return@setOnItemSelectedListener true
                }
                R.id.loan -> {
                    if (isLoginUser()) {
                        return@setOnItemSelectedListener false
                    }
                    changeFragment(LoanCreateFragment.newInstance())
                    return@setOnItemSelectedListener true
                }
                R.id.profile -> {
                    if (isLoginUser()) {
                        return@setOnItemSelectedListener false
                    }
                    //changeFragment(ProfileFragment.newInstance())
                    return@setOnItemSelectedListener true
                }
            }
            return@setOnItemSelectedListener false
        }
    }

    private fun isLoginUser() = prefs.tokenPreferences.isNullOrEmpty()
}