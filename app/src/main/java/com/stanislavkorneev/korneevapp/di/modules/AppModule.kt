package com.stanislavkorneev.korneevapp.di.modules

import android.content.Context
import com.stanislavkorneev.korneevapp.app.App
import dagger.Binds
import dagger.Module
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Module(includes = [AndroidSupportInjectionModule::class])
interface AppModule {
    @Singleton
    @Binds
    fun provideContext(app: App): Context
}