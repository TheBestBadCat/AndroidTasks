package com.stanislavkorneev.korneevapp.domain.repository

import com.stanislavkorneev.korneevapp.domain.entities.*

interface LoanRepository {

    suspend fun createLoan(token: String, request: LoanRequest): Loan

    suspend fun getLoan(token: String, id: Int): Loan

    suspend fun getLoansList(token: String): List<Loan>

    suspend fun getLoanConditions(token: String): LoanConditions

}