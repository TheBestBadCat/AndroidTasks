package com.stanislavkorneev.korneevapp.domain.usecase

import android.util.Log
import com.stanislavkorneev.korneevapp.data.repository.AuthRepositoryImpl
import com.stanislavkorneev.korneevapp.domain.entities.Auth

class RegistrationUseCase {

    private val repository = AuthRepositoryImpl()

    suspend operator fun invoke(login: String, password: String) {
        if (isValidUserData(login, password)) {
            Log.d("BACKEND", "login $login pass $password")
            repository.registration(Auth(login, password))
        } else {
            throw IllegalArgumentException()
        }
    }

    private fun isValidUserData(login: String, password: String) =
        login.isNotEmpty() && password.isNotEmpty()

}