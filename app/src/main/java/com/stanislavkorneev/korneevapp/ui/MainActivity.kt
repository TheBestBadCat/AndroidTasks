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
            .setTitle("Регистрация прошла успешно")
            .setMessage("Осуществите вход с данными указанными при регистрации для дальнейшей работы с приложением")
            .setNeutralButton("Ок") { dialog, id ->
                dialog.dismiss()
            }.show()
    }

    fun showCreateLoanSuccessDialog() {
        AlertDialog.Builder(this)
            .setTitle("Заявка успешно оформлена")
            .setMessage("Ознакомтесь с инструкцией и выберите предпочтительный способ получения займа")
            .setNeutralButton("Посмотреть инструкцию") { dialog, id ->
                dialog.dismiss()
                changeFragment(LoanInfoFragment.newInstance())
            }.show()
    }

    private fun showInputExceptionDialog() {
        AlertDialog.Builder(this)
            .setTitle("Недостаточно данных")
            .setMessage("Корректно заполните все поля и попробуйте отправить запрос повторно")
            .setNeutralButton("Ок") { dialog, id ->
                dialog.dismiss()
            }.show()
    }

    private fun showUnknownHostExceptionDialog() {
        AlertDialog.Builder(this)
            .setTitle("Отсутствует подключение к интернету")
            .setMessage("Проверьте соединение и попробуйте отправить запрос повторно")
            .setNeutralButton("Ок") { dialog, id ->
                dialog.dismiss()
            }.show()
    }

    private fun showHttpExceptionDialog() {
        AlertDialog.Builder(this)
            .setTitle("Ошибка при работе с сервером")
            .setMessage("Проверьте корректность введенных данных или попробуйте повторить запрос позже")
            .setNeutralButton("Ок") { dialog, id ->
                dialog.dismiss()
            }.show()
    }

    private fun showUnknownExceptionDialog() {
        AlertDialog.Builder(this)
            .setTitle("Неизвестная ошибка")
            .setMessage("На данный момент причина возникновения данной ошибки неизвестна. Спасибо, за понимание.")
            .setNeutralButton("Ок") { dialog, id ->
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
        binding.bottomNavigation.setOnItemSelectedListener {
            when(it.itemId) {
                R.id.home -> {
                    setHomeFragment()
                    return@setOnItemSelectedListener true
                }
                R.id.loan -> {
                    if (!isLoginUser()) {
                        changeFragment(LoanCreateFragment.newInstance())
                        return@setOnItemSelectedListener true
                    } else {
                        return@setOnItemSelectedListener false
                    }
                }
                /*R.id.profile -> {
                    if (!isLoginUser()) {
                        changeFragment(ProfileFragment.newInstance())
                        return@setOnItemSelectedListener true
                    } else {
                        return@setOnItemSelectedListener false
                    }
                }*/
                else -> return@setOnItemSelectedListener false
            }
        }
    }

    private fun isLoginUser() = prefs.tokenPreferences.isNullOrEmpty()
}