package com.stanislavkorneev.korneevapp.domain.repository

import com.stanislavkorneev.korneevapp.domain.entities.Loan
import com.stanislavkorneev.korneevapp.domain.entities.LoanConditions
import com.stanislavkorneev.korneevapp.domain.entities.LoanRequest

interface LoanRepository {

    fun createLoan(request: LoanRequest) : Loan

    fun getLoan(id: Int) : Loan

    fun getAllLoans() : List<Loan>

    fun getLoansConditions() : LoanConditions

}