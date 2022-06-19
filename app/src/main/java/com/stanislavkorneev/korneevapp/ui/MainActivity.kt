package com.stanislavkorneev.korneevapp.ui

import android.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.stanislavkorneev.korneevapp.R
import com.stanislavkorneev.korneevapp.databinding.ActivityMainBinding
import com.stanislavkorneev.korneevapp.app.prefs
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject

class MainActivity : AppCompatActivity(), HasAndroidInjector {

    @Inject
    lateinit var fragmentInjector: DispatchingAndroidInjector<Any>

    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setHomeFragment()
        initNavigationBarListener()
    }

    override fun androidInjector(): AndroidInjector<Any> =
        fragmentInjector


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
            .setNeutralButton(R.string.dialog_neutral_button) { dialog, _ ->
                dialog.dismiss()
            }.show()
    }

    fun showLoginSuccessDialog() {
        AlertDialog.Builder(this)
            .setTitle(R.string.login_success_title)
            .setMessage(R.string.login_success_message)
            .setPositiveButton(R.string.dialog_positive_button) { dialog, _ ->
                dialog.dismiss()
                showHomeGuideDialog()
            }.show()
    }

    fun showCreateLoanSuccessDialog() {
        AlertDialog.Builder(this)
            .setTitle(R.string.create_loan_success_title)
            .setMessage(R.string.create_loan_success_message)
            .setPositiveButton(R.string.dialog_neutral_button) { dialog, _ ->
                dialog.dismiss()
            }.show()
    }

    fun showLogoutDialog() {
        AlertDialog.Builder(this)
            .setTitle(R.string.profile_logout_title)
            .setMessage(R.string.profile_logout_message)
            .setPositiveButton(R.string.profile_logout_positive_button) { dialog, _ ->
                dialog.dismiss()
                logout()
            }
            .setNegativeButton(R.string.profile_logout_negative_button) { dialog, _ ->
                dialog.dismiss()
            }.show()
    }

    private fun showHomeGuideDialog() {
        AlertDialog.Builder(this)
            .setIcon(R.drawable.ic_baseline_home_24)
            .setTitle(R.string.home_guide_title)
            .setMessage(R.string.home_guide_message)
            .setPositiveButton(R.string.dialog_positive_button) { dialog, _ ->
                dialog.dismiss()
                showLoanGuideDialog()
            }.show()
    }

    private fun showLoanGuideDialog() {
        AlertDialog.Builder(this)
            .setIcon(R.drawable.ic_baseline_credit_card_24)
            .setTitle(R.string.loan_guide_title)
            .setMessage(R.string.loan_guide_message)
            .setPositiveButton(R.string.dialog_positive_button) { dialog, _ ->
                dialog.dismiss()
                showProfileGuideDialog()
            }.show()
    }

    private fun showProfileGuideDialog() {
        AlertDialog.Builder(this)
            .setIcon(R.drawable.ic_baseline_person_24)
            .setTitle(R.string.profile_guide_title)
            .setMessage(R.string.profile_guide_message)
            .setPositiveButton(R.string.dialog_neutral_button) { dialog, _ ->
                dialog.dismiss()
            }.show()
    }

    private fun showInputExceptionDialog() {
        AlertDialog.Builder(this)
            .setTitle(R.string.input_exception_title)
            .setMessage(R.string.input_exception_message)
            .setNeutralButton(R.string.dialog_neutral_button) { dialog, _ ->
                dialog.dismiss()
            }.show()
    }

    private fun showUnknownHostExceptionDialog() {
        AlertDialog.Builder(this)
            .setTitle(R.string.host_exception_title)
            .setMessage(R.string.host_exception_message)
            .setNeutralButton(R.string.dialog_neutral_button) { dialog, _ ->
                dialog.dismiss()
            }.show()
    }

    private fun showHttpExceptionDialog() {
        AlertDialog.Builder(this)
            .setTitle(R.string.http_exception_title)
            .setMessage(R.string.http_exception_message)
            .setNeutralButton(R.string.dialog_neutral_button) { dialog, _ ->
                dialog.dismiss()
            }.show()
    }

    private fun showUnknownExceptionDialog() {
        AlertDialog.Builder(this)
            .setTitle(R.string.unknown_exception_title)
            .setMessage(R.string.input_exception_message)
            .setNeutralButton(R.string.dialog_neutral_button) { dialog, _ ->
                dialog.dismiss()
            }.show()
    }

    private fun isLoginUser() = prefs.tokenPreferences.isNullOrEmpty()

    private fun setHomeFragment() {
        if (!isLoginUser())
            changeFragment(LoansListFragment.newInstance())
        else
            changeFragment(AuthFragment.newInstance())
    }

    private fun logout() {
        prefs.tokenPreferences = ""
        setHomeFragment()
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
                    changeFragment(ProfileFragment.newInstance())
                    return@setOnItemSelectedListener true
                }
            }
            return@setOnItemSelectedListener false
        }
    }
}