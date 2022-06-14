package com.stanislavkorneev.korneevapp.data.api

import com.stanislavkorneev.korneevapp.domain.entities.Auth
import com.stanislavkorneev.korneevapp.domain.entities.User
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface UserApi {

    @POST("/login")
    fun login(
        @Body auth: Auth
    ) : Call<String>

    @POST("/registration")
    fun registration(
        @Body auth: Auth
    ) : Call<User>

}