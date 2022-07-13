package com.stanislavkorneev.korneevapp.domain.usecase

import com.stanislavkorneev.korneevapp.di.FragmentScope
import com.stanislavkorneev.korneevapp.domain.entities.Loan
import com.stanislavkorneev.korneevapp.domain.entities.LoanRequest
import com.stanislavkorneev.korneevapp.domain.repository.LoanRepository
import javax.inject.Inject

@FragmentScope
class CreateLoanUseCase @Inject constructor(private val repository: LoanRepository) {

    suspend operator fun invoke(
        token: String,
        amount: Int,
        percent: Double,
        period: Int,
        firstName: String,
        lastName: String,
        phoneNumber: String
    ): Loan {
        return if (isValidUserData(amount, percent, period, firstName, lastName, phoneNumber)) {
            repository.createLoan(token, LoanRequest(amount, firstName, lastName, percent, period, phoneNumber))
        } else {
            throw IllegalArgumentException()
        }
    }

    private fun isValidUserData(
        amount: Int,
        percent: Double,
        period: Int,
        firstName: String,
        lastName: String,
        phoneNumber: String,
    ) = amount > 0 && percent > 0.0 && period > 0 && firstName.isNotEmpty() && lastName.isNotEmpty() && phoneNumber.isNotEmpty()
}