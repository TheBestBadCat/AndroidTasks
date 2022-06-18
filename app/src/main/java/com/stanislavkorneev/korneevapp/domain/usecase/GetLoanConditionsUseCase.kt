package com.stanislavkorneev.korneevapp.domain.usecase

import com.stanislavkorneev.korneevapp.data.repository.AuthRepositoryImpl
import com.stanislavkorneev.korneevapp.domain.entities.LoanConditions

class GetLoanConditionsUseCase {

    private val repository = AuthRepositoryImpl()

    suspend operator fun invoke(token: String): LoanConditions =
        repository.getLoanConditions(token)

}