package com.stanislavkorneev.korneevapp.domain.usecase

import com.stanislavkorneev.korneevapp.data.repository.AuthRepositoryImpl
import com.stanislavkorneev.korneevapp.domain.entities.Auth

class LoginUseCase {

    private val repository = AuthRepositoryImpl()

    suspend operator fun invoke(login: String, password: String): String {
        return if (isValidUserData(login, password))
            repository.login(Auth(login, password))
        else
            throw IllegalArgumentException()
    }

    private fun isValidUserData(login: String, password: String) =
        login.isNotEmpty() && password.isNotEmpty()
}