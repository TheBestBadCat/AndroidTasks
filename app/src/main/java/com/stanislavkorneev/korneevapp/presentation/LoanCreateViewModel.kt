package com.stanislavkorneev.korneevapp.presentation

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.liveData
import com.stanislavkorneev.korneevapp.domain.entities.Loan
import com.stanislavkorneev.korneevapp.domain.entities.LoanConditions
import com.stanislavkorneev.korneevapp.domain.usecase.CreateLoanUseCase
import com.stanislavkorneev.korneevapp.domain.usecase.GetLoanConditionsUseCase
import kotlinx.coroutines.Dispatchers
import java.lang.Exception

class LoanCreateViewModel(application: Application): AndroidViewModel(application) {

    private val getLoanConditionsUseCase = GetLoanConditionsUseCase()
    private val createLoanUseCase = CreateLoanUseCase()
    private val _exception =  MutableLiveData<String>()
    val exception: LiveData<String> = _exception
    lateinit var conditions: LiveData<LoanConditions>
    lateinit var loan: LiveData<Loan>

    fun getLoanConditions(token: String) {
        conditions = liveData (Dispatchers.IO) {
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