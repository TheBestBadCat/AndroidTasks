package com.stanislavkorneev.korneevapp.di.modules

import com.stanislavkorneev.korneevapp.di.FragmentScope
import com.stanislavkorneev.korneevapp.ui.fragments.AuthFragment
import com.stanislavkorneev.korneevapp.ui.fragments.LoanCreateFragment
import com.stanislavkorneev.korneevapp.ui.fragments.LoanInfoFragment
import com.stanislavkorneev.korneevapp.ui.fragments.LoansListFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
interface FragmentModule {
    @FragmentScope
    @ContributesAndroidInjector(modules = [PresentationModule::class])
    fun injectAuthFragment(): AuthFragment

    @FragmentScope
    @ContributesAndroidInjector(modules = [PresentationModule::class])
    fun injectLoanCreateFragment(): LoanCreateFragment

    @FragmentScope
    @ContributesAndroidInjector(modules = [PresentationModule::class])
    fun injectLoanInfoFragment(): LoanInfoFragment

    @FragmentScope
    @ContributesAndroidInjector(modules = [PresentationModule::class])
    fun injectLoansListFragment(): LoansListFragment
}