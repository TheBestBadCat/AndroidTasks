package com.stanislavkorneev.korneevapp.di.modules

import dagger.Module
import dagger.Provides
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

@Module
class DispatchersModule {

    @Provides
    fun providesCoroutineDispatcherIO() : CoroutineDispatcher = Dispatchers.IO
}