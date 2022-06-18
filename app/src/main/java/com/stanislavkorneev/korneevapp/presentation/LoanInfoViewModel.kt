package com.stanislavkorneev.korneevapp.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.stanislavkorneev.korneevapp.domain.entities.Loan
import com.stanislavkorneev.korneevapp.domain.usecase.GetLoanInfoUseCase
import kotlinx.coroutines.Dispatchers

class LoanInfoViewModel: ViewModel() {

    private val getLoanInfoUseCase = GetLoanInfoUseCase()
    lateinit var loan: LiveData<Loan>

    private val _exception =  MutableLiveData<String>()
    val exception: LiveData<String> = _exception

    fun getLoanInfo(token: String, id: Int) {
        loan = liveData (Dispatchers.IO) {
            try {
                emit(getLoanInfoUseCase(token, id))
                _exception.postValue("")
            } catch (e: Exception) {
                _exception.postValue(e.javaClass.simpleName)
            }
        }
    }
}