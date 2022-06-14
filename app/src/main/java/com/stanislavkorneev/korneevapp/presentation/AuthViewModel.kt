package com.stanislavkorneev.korneevapp.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.stanislavkorneev.korneevapp.data.repository.AuthRepositoryImpl
import com.stanislavkorneev.korneevapp.domain.entities.Auth

class AuthViewModel : ViewModel() {

    private val repository = AuthRepositoryImpl()

    private val _authData = MutableLiveData<Unit>()
    val authData: LiveData<Unit> = _authData

    private val _result = MutableLiveData<String>()
    val result: LiveData<String> = _result

    fun login(login: String, password: String) {
        if (hasUserData(login, password)) {
            val body = Auth(login, password)
            repository.login(body)
            Thread.sleep(1000)
            handleResponseCode(repository.getResponseCode())
        }
        else {
            _authData.value = Unit
        }
    }

    fun registration(login: String, password: String) {
        if (hasUserData(login, password)) {
            val body = Auth(login, password)
            repository.registration(body)
            Thread.sleep(1000)
            handleResponseCode(repository.getResponseCode())
        }
        else {
            _authData.value = Unit
        }
    }

    fun handleResponseCode(responseCode: Int) {
        if (responseCode in 200..299) {
            _result.value = "Все хорошо"
        }
        else if (responseCode in 400..499) {
            _result.value = "Что-то пошло не так"
        }
        else {
            _result.value = ""
        }
    }

    private fun hasUserData(login: String, password: String): Boolean {
        val isEmptyLogin = login.isNotEmpty()
        val isEmptyPassword = password.isNotEmpty()
        return isEmptyLogin && isEmptyPassword
    }

}