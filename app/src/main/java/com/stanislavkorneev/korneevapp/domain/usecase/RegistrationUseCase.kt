package com.stanislavkorneev.korneevapp.domain.usecase

import com.stanislavkorneev.korneevapp.data.repository.LoanRepositoryImpl
import com.stanislavkorneev.korneevapp.domain.entities.Auth

class RegistrationUseCase {

    private val repository = LoanRepositoryImpl()

    suspend operator fun invoke(login: String, password: String) {
        if (isValidUserData(login, password)) {
            repository.registration(Auth(login, password))
        } else {
            throw IllegalArgumentException()
        }
    }

    private fun isValidUserData(login: String, password: String) =
        login.isNotEmpty() && password.isNotEmpty()
}