package com.stanislavkorneev.korneevapp.di.modules

import com.stanislavkorneev.korneevapp.data.repository.LoanRepositoryImpl
import com.stanislavkorneev.korneevapp.domain.usecase.*
import dagger.Module
import dagger.Provides

@Module
class DomainModule {

    @Provides
    fun provideCreateLoanUseCase(repositoryImpl: LoanRepositoryImpl) : CreateLoanUseCase {
        return CreateLoanUseCase(repositoryImpl)
    }

    @Provides
    fun provideGetLoanConditionsUseCase(repositoryImpl: LoanRepositoryImpl) : GetLoanConditionsUseCase {
        return GetLoanConditionsUseCase(repositoryImpl)
    }

    @Provides
    fun provideGetLoanInfoUseCase(repositoryImpl: LoanRepositoryImpl) : GetLoanInfoUseCase {
        return GetLoanInfoUseCase(repositoryImpl)
    }

    @Provides
    fun provideGetLoansListUseCase(repositoryImpl: LoanRepositoryImpl) : GetLoansListUseCase {
        return GetLoansListUseCase(repositoryImpl)
    }

    @Provides
    fun provideLoginUseCase(repositoryImpl: LoanRepositoryImpl) : LoginUseCase {
        return LoginUseCase(repositoryImpl)
    }

    @Provides
    fun provideRegistrationUseCase(repositoryImpl: LoanRepositoryImpl) : RegistrationUseCase {
        return RegistrationUseCase(repositoryImpl)
    }
}