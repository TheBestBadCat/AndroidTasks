package com.stanislavkorneev.korneevapp.presentation.viewModels

import androidx.lifecycle.*
import com.stanislavkorneev.korneevapp.domain.usecase.LoginUseCase
import com.stanislavkorneev.korneevapp.domain.usecase.RegistrationUseCase
import kotlinx.coroutines.Dispatchers
import java.lang.Exception
import javax.inject.Inject

class AuthViewModel @Inject constructor(
    private val registrationUseCase: RegistrationUseCase,
    private val loginUseCase: LoginUseCase
) : ViewModel() {

    lateinit var token: LiveData<String>
    lateinit var registrationSuccess: LiveData<Boolean>
    private val _exception =  MutableLiveData<String>()
    val exception: LiveData<String> = _exception

    fun login(login: String, password: String) {
        token = liveData (Dispatchers.IO) {
            try {
                emit(loginUseCase(login, password))
            } catch (e: Exception) {
                emit("")
                _exception.postValue(e.javaClass.simpleName)
            }
        }
    }

    fun registration(login: String, password: String) {
        registrationSuccess = liveData (Dispatchers.IO) {
            try {
                registrationUseCase(login, password)
                emit(true)
            } catch (e: Exception) {
                emit(false)
                _exception.postValue(e.javaClass.simpleName)
            }
        }
    }
}