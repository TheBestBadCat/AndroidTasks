package com.stanislavkorneev.korneevapp.domain.usecase

import com.stanislavkorneev.korneevapp.di.FragmentScope
import com.stanislavkorneev.korneevapp.domain.entities.LoanConditions
import com.stanislavkorneev.korneevapp.domain.repository.LoanRepository
import javax.inject.Inject

@FragmentScope
class GetLoanConditionsUseCase @Inject constructor(private val repository: LoanRepository) {

    suspend operator fun invoke(token: String): LoanConditions =
        repository.getLoanConditions(token)
}