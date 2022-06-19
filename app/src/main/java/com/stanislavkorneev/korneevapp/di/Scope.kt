package com.stanislavkorneev.korneevapp.di

import javax.inject.Scope

@Scope
@MustBeDocumented
@Retention(value = AnnotationRetention.RUNTIME)
annotation class ActivityScope

@Scope
@MustBeDocumented
@Retention(value = AnnotationRetention.RUNTIME)
annotation class FragmentScope