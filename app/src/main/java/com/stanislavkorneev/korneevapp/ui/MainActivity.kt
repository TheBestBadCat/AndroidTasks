package com.stanislavkorneev.korneevapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.stanislavkorneev.korneevapp.presentation.DialogManager
import com.stanislavkorneev.korneevapp.R
import com.stanislavkorneev.korneevapp.databinding.ActivityMainBinding
import com.stanislavkorneev.korneevapp.app.prefs
import com.stanislavkorneev.korneevapp.ui.fragments.AuthFragment
import com.stanislavkorneev.korneevapp.ui.fragments.LoanCreateFragment
import com.stanislavkorneev.korneevapp.ui.fragments.LoansListFragment
import com.stanislavkorneev.korneevapp.ui.fragments.ProfileFragment
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
        DialogManager.showExceptionMessage(this, exceptionSimpleName)
    }

    fun showRegistrationSuccessDialog() {
        DialogManager.showRegistrationSuccessDialog(this)
    }

    fun showCreateLoanSuccessDialog() {
        DialogManager.showCreateLoanSuccessDialog(this)
    }

    fun showLogoutDialog() {
        DialogManager.showLogoutDialog(this) { logout() }
    }

    fun showLoginSuccessDialog() {
        DialogManager.showLoginSuccessDialog(this)
    }

    fun showGetLoanCashGuide() {
        DialogManager.showGetLoanCashGuide(this)
    }

    fun showGetLoanCardGuide() {
        DialogManager.showGetLoanCardGuide(this)
    }

    private fun isLoginUser() = prefs.tokenPreferences.isNullOrEmpty()

    private fun setHomeFragment() {
        if (!isLoginUser()) changeFragment(LoansListFragment.newInstance())
        else changeFragment(AuthFragment.newInstance())
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