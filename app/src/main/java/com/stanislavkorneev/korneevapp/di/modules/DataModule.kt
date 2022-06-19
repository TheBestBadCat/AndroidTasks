package com.stanislavkorneev.korneevapp.di.modules

import com.stanislavkorneev.korneevapp.data.api.ApiService
import com.stanislavkorneev.korneevapp.data.api.LoanApi
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DataModule {
    @Provides
    @Singleton
    fun provideLoanApi(apiService: ApiService): LoanApi {
        return apiService.retrofit.create(LoanApi::class.java)
    }
}