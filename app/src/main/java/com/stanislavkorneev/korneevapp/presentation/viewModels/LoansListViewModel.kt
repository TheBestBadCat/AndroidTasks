package com.stanislavkorneev.korneevapp.presentation.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.stanislavkorneev.korneevapp.domain.entities.Loan
import com.stanislavkorneev.korneevapp.domain.usecase.GetLoansListUseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import java.lang.Exception
import javax.inject.Inject

class LoansListViewModel @Inject constructor(
    private val getLoansListUseCase: GetLoansListUseCase,
    private val coroutineDispatcher: CoroutineDispatcher
): ViewModel() {

    lateinit var loansList: LiveData<List<Loan>>
    private val _exception =  MutableLiveData<String>()
    val exception: LiveData<String> = _exception

    fun getLoansList(token: String) {
        loansList = liveData (coroutineDispatcher) {
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