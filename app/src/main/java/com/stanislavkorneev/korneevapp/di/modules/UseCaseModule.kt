package com.stanislavkorneev.korneevapp.di.modules

import com.stanislavkorneev.korneevapp.domain.repository.AuthRepository
import com.stanislavkorneev.korneevapp.domain.repository.LoanRepository
import com.stanislavkorneev.korneevapp.domain.usecase.*
import dagger.Module
import dagger.Provides

@Module
class UseCaseModule {

    @Provides
    fun provideCreateLoanUseCase(repository: LoanRepository) : CreateLoanUseCase {
        return CreateLoanUseCase(repository)
    }

    @Provides
    fun provideGetLoanConditionsUseCase(repository: LoanRepository) : GetLoanConditionsUseCase {
        return GetLoanConditionsUseCase(repository)
    }

    @Provides
    fun provideGetLoanInfoUseCase(repository: LoanRepository) : GetLoanInfoUseCase {
        return GetLoanInfoUseCase(repository)
    }

    @Provides
    fun provideGetLoansListUseCase(repository: LoanRepository) : GetLoansListUseCase {
        return GetLoansListUseCase(repository)
    }

    @Provides
    fun provideLoginUseCase(repository: AuthRepository) : LoginUseCase {
        return LoginUseCase(repository)
    }

    @Provides
    fun provideRegistrationUseCase(repository: AuthRepository) : RegistrationUseCase {
        return RegistrationUseCase(repository)
    }
}