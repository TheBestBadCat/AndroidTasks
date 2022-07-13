package com.stanislavkorneev.korneevapp.domain.repository

import com.stanislavkorneev.korneevapp.domain.entities.Auth
import com.stanislavkorneev.korneevapp.domain.entities.User

interface AuthRepository {

    suspend fun login(auth: Auth): String

    suspend fun registration(auth: Auth): User

}