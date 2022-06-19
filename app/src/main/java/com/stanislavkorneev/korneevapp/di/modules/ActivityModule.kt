package com.stanislavkorneev.korneevapp.di.modules

import com.stanislavkorneev.korneevapp.di.ActivityScope
import com.stanislavkorneev.korneevapp.ui.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
interface ActivityModule {
    @ActivityScope
    @ContributesAndroidInjector(modules = [FragmentModule::class])
    fun injectMainActivity(): MainActivity
}