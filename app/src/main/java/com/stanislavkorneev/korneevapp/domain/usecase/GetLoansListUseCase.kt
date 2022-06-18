package com.stanislavkorneev.korneevapp.domain.usecase

import com.stanislavkorneev.korneevapp.data.repository.AuthRepositoryImpl
import com.stanislavkorneev.korneevapp.domain.entities.Loan

class GetLoansListUseCase {

    private val repository = AuthRepositoryImpl()

    suspend operator fun invoke(token: String): List<Loan> =
        repository.getLoansList(token)

}