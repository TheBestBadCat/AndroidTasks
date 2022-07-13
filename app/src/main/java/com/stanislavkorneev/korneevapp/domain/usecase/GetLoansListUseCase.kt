package com.stanislavkorneev.korneevapp.domain.usecase

import com.stanislavkorneev.korneevapp.di.FragmentScope
import com.stanislavkorneev.korneevapp.domain.entities.Loan
import com.stanislavkorneev.korneevapp.domain.repository.LoanRepository
import javax.inject.Inject

@FragmentScope
class GetLoansListUseCase @Inject constructor(private val repository: LoanRepository) {

    suspend operator fun invoke(token: String): List<Loan> =
        repository.getLoansList(token)
}