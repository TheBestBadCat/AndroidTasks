package com.stanislavkorneev.korneevapp.di

import com.stanislavkorneev.korneevapp.app.App
import com.stanislavkorneev.korneevapp.di.modules.*
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [
    ActivityModule::class,
    AppModule::class,
    DataModule::class,
    DomainModule::class,
    PresentationModule::class,
    AndroidSupportInjectionModule::class
])

interface AppComponent : AndroidInjector<App> {
    @Component.Factory
    interface Factory : AndroidInjector.Factory<App>
}
