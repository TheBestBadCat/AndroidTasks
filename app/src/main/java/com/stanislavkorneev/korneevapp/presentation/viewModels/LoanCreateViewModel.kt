package com.stanislavkorneev.korneevapp.presentation.viewModels

import androidx.lifecycle.*
import com.stanislavkorneev.korneevapp.domain.entities.Loan
import com.stanislavkorneev.korneevapp.domain.entities.LoanConditions
import com.stanislavkorneev.korneevapp.domain.usecase.CreateLoanUseCase
import com.stanislavkorneev.korneevapp.domain.usecase.GetLoanConditionsUseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import java.lang.Exception
import javax.inject.Inject

class LoanCreateViewModel @Inject constructor(
    private val getLoanConditionsUseCase: GetLoanConditionsUseCase,
    private val createLoanUseCase: CreateLoanUseCase,
    private val coroutineDispatcher: CoroutineDispatcher
): ViewModel() {

    private val _exception =  MutableLiveData<String>()
    val exception: LiveData<String> = _exception
    lateinit var conditions: LiveData<LoanConditions>
    lateinit var loan: LiveData<Loan>

    fun getLoanConditions(token: String) {
        conditions = liveData (coroutineDispatcher) {
            try {
                emit(getLoanConditionsUseCase(token))
            } catch (e: Exception) {
                _exception.postValue(e.javaClass.simpleName)
            }
        }
    }

    fun createLoan(token: String, amount: Int, percent: Double, period: Int,
                   firstName: String, lastName: String, phoneNumber: String) {
        loan = liveData(Dispatchers.IO) {
            try {
                emit(createLoanUseCase(token, amount, percent, period,
                    firstName, lastName, phoneNumber))
                _exception.postValue("")
            } catch (e: Exception) {
                _exception.postValue(e.javaClass.simpleName)
            }
        }
    }
}