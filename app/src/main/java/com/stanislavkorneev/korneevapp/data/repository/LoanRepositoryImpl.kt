package com.stanislavkorneev.korneevapp.data.repository

import com.stanislavkorneev.korneevapp.data.api.LoanApi
import com.stanislavkorneev.korneevapp.domain.entities.*
import com.stanislavkorneev.korneevapp.domain.repository.LoanRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LoanRepositoryImpl @Inject constructor(
    private val api: LoanApi
) : LoanRepository {

    override suspend fun login(auth: Auth): String =
        api.login(auth)

    override suspend fun registration(auth: Auth): User =
        api.registration(auth)

    override suspend fun createLoan(token: String, request: LoanRequest): Loan =
        api.createLoan(token, request)

    override suspend fun getLoan(token: String, id: Int): Loan =
        api.getLoan(token, id)

    override suspend fun getLoansList(token: String): List<Loan> =
        api.getLoansList(token)

    override suspend fun getLoanConditions(token: String): LoanConditions =
        api.getLoanConditions(token)

}