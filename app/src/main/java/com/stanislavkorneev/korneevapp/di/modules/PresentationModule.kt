package com.stanislavkorneev.korneevapp.di.modules

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.stanislavkorneev.korneevapp.di.ViewModelKey
import com.stanislavkorneev.korneevapp.presentation.*
import dagger.Module
import dagger.Binds
import dagger.multibindings.IntoMap

@Module
interface PresentationModule {
    @Binds
    fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(AuthViewModel::class)
    fun bindAuthViewModel(viewModel: AuthViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(LoanCreateViewModel::class)
    fun bindLoanCreateViewModel(viewModel: LoanCreateViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(LoanInfoViewModel::class)
    fun bindLoanLInfoViewModel(viewModel: LoanInfoViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(LoansListViewModel::class)
    fun bindLoansListViewModel(viewModel: LoansListViewModel): ViewModel
}
