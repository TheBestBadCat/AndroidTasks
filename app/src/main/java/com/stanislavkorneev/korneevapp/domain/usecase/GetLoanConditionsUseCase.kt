package com.stanislavkorneev.korneevapp.domain.usecase

import com.stanislavkorneev.korneevapp.data.repository.LoanRepositoryImpl
import com.stanislavkorneev.korneevapp.di.FragmentScope
import com.stanislavkorneev.korneevapp.domain.entities.LoanConditions
import javax.inject.Inject

@FragmentScope
class GetLoanConditionsUseCase @Inject constructor(private val repository: LoanRepositoryImpl) {

    suspend operator fun invoke(token: String): LoanConditions =
        repository.getLoanConditions(token)
}