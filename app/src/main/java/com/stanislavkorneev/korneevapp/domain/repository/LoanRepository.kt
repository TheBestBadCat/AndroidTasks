package com.stanislavkorneev.korneevapp.domain.repository

import com.stanislavkorneev.korneevapp.domain.entities.Loan
import com.stanislavkorneev.korneevapp.domain.entities.LoanConditions
import com.stanislavkorneev.korneevapp.domain.entities.LoanRequest

interface LoanRepository {

    suspend fun createLoan(request: LoanRequest) : Loan

    suspend fun getLoan(id: Int) : Loan

    suspend fun getAllLoans() : List<Loan>

    suspend fun getLoansConditions() : LoanConditions

}