package com.stanislavkorneev.korneevapp.domain.repository

import com.stanislavkorneev.korneevapp.domain.entities.Auth
import com.stanislavkorneev.korneevapp.domain.entities.User

interface AuthRepository {

    fun login(auth: Auth) : String

    fun registration(auth: Auth) : User

}