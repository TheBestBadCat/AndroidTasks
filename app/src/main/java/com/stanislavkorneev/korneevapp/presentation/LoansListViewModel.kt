package com.stanislavkorneev.korneevapp.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.stanislavkorneev.korneevapp.domain.entities.Loan
import com.stanislavkorneev.korneevapp.domain.usecase.GetLoansListUseCase
import kotlinx.coroutines.Dispatchers
import java.lang.Exception

class LoansListViewModel: ViewModel() {

    private val getLoansListUseCase = GetLoansListUseCase()
    lateinit var loansList: LiveData<List<Loan>>

    private val _exception =  MutableLiveData<String>()
    val exception: LiveData<String> = _exception

    fun getLoansList(token: String) {
        loansList = liveData (Dispatchers.IO) {
            try {
                emit(getLoansListUseCase(token))
                _exception.postValue("")
            } catch (e: Exception) {
                emit(emptyList())
                _exception.postValue(e.javaClass.simpleName)
            }
        }
    }
}