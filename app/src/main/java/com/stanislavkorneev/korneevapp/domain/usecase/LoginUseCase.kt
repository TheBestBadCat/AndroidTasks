package com.stanislavkorneev.korneevapp.domain.usecase

import com.stanislavkorneev.korneevapp.di.FragmentScope
import com.stanislavkorneev.korneevapp.domain.entities.Auth
import com.stanislavkorneev.korneevapp.domain.repository.AuthRepository
import javax.inject.Inject

@FragmentScope
class LoginUseCase @Inject constructor(private val repository: AuthRepository) {

    suspend operator fun invoke(login: String, password: String): String {
        return if (isValidUserData(login, password))
            repository.login(Auth(login, password))
        else
            throw IllegalArgumentException()
    }

    private fun isValidUserData(login: String, password: String) =
        login.isNotEmpty() && password.isNotEmpty()
}