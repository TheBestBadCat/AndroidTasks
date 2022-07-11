package com.stanislavkorneev.korneevapp.presentation.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.stanislavkorneev.korneevapp.domain.entities.Loan
import com.stanislavkorneev.korneevapp.domain.usecase.GetLoanInfoUseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

class LoanInfoViewModel @Inject constructor(
    private val getLoanInfoUseCase: GetLoanInfoUseCase,
    private val coroutineDispatcher: CoroutineDispatcher
): ViewModel() {

    lateinit var loan: LiveData<Loan>
    private val _exception =  MutableLiveData<String>()
    val exception: LiveData<String> = _exception

    fun getLoanInfo(token: String, id: Int) {
        loan = liveData (coroutineDispatcher) {
            try {
                emit(getLoanInfoUseCase(token, id))
                _exception.postValue("")
            } catch (e: Exception) {
                _exception.postValue(e.javaClass.simpleName)
            }
        }
    }
}