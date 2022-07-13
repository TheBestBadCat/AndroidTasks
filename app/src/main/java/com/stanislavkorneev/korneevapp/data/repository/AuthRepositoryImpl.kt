package com.stanislavkorneev.korneevapp.data.repository

import com.stanislavkorneev.korneevapp.data.api.LoanApi
import com.stanislavkorneev.korneevapp.domain.entities.Auth
import com.stanislavkorneev.korneevapp.domain.entities.User
import com.stanislavkorneev.korneevapp.domain.repository.AuthRepository
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(private val api: LoanApi) : AuthRepository {

    override suspend fun login(auth: Auth): String =
        api.login(auth)

    override suspend fun registration(auth: Auth): User =
        api.registration(auth)
}