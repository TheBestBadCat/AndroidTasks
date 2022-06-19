package com.stanislavkorneev.korneevapp.domain.usecase

import com.stanislavkorneev.korneevapp.data.repository.LoanRepositoryImpl
import com.stanislavkorneev.korneevapp.domain.entities.Loan

class GetLoanInfoUseCase {

    private val repository = LoanRepositoryImpl()

    suspend operator fun invoke(token: String, id: Int): Loan =
        repository.getLoan(token, id)
}