package com.stanislavkorneev.korneevapp.data.repository

import com.stanislavkorneev.korneevapp.data.api.ApiService
import com.stanislavkorneev.korneevapp.data.api.LoanApi
import com.stanislavkorneev.korneevapp.domain.entities.*
import com.stanislavkorneev.korneevapp.domain.repository.LoanRepository

class LoanRepositoryImpl : LoanRepository {

    private val api = ApiService()
    private val retrofit = api.retrofit.create(LoanApi::class.java)

    override suspend fun login(auth: Auth): String =
        retrofit.login(auth)

    override suspend fun registration(auth: Auth): User =
        retrofit.registration(auth)

    override suspend fun createLoan(token: String, request: LoanRequest): Loan =
        retrofit.createLoan(token, request)

    override suspend fun getLoan(token: String, id: Int): Loan =
        retrofit.getLoan(token, id)

    override suspend fun getLoansList(token: String): List<Loan> =
        retrofit.getLoansList(token)

    override suspend fun getLoanConditions(token: String): LoanConditions =
        retrofit.getLoanConditions(token)

}