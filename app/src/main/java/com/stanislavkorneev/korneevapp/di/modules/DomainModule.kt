package com.stanislavkorneev.korneevapp.di.modules

import com.stanislavkorneev.korneevapp.data.repository.AuthRepositoryImpl
import com.stanislavkorneev.korneevapp.data.repository.LoanRepositoryImpl
import com.stanislavkorneev.korneevapp.domain.repository.AuthRepository
import com.stanislavkorneev.korneevapp.domain.repository.LoanRepository
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
interface DomainModule {

    @Singleton
    @Binds
    fun bindAuthRepository(repositoryImpl: AuthRepositoryImpl) : AuthRepository

    @Singleton
    @Binds
    fun bindLoanRepository(repository: LoanRepositoryImpl) : LoanRepository
}