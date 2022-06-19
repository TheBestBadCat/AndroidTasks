package com.stanislavkorneev.korneevapp.app

import android.app.Application
import com.stanislavkorneev.korneevapp.data.Preferences
import com.stanislavkorneev.korneevapp.di.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject

val prefs: Preferences by lazy {
    App.prefs!!
}

class App: Application(), HasAndroidInjector {

    @Inject
    lateinit var activityInjector: DispatchingAndroidInjector<Any>

    companion object {
        var prefs: Preferences? = null
        lateinit var instance: App
            private set
    }

    override fun onCreate() {
        DaggerAppComponent.factory().create(this).inject(this)
        super.onCreate()
        instance = this
        prefs = Preferences(applicationContext)
    }

    override fun androidInjector(): AndroidInjector<Any> =
        activityInjector
}