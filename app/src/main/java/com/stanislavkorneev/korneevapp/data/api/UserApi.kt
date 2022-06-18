package com.stanislavkorneev.korneevapp.data.api

import com.stanislavkorneev.korneevapp.domain.entities.*
import retrofit2.http.*

interface UserApi {

    @POST("/login")
    suspend fun login(@Body auth: Auth): String

    @POST("/registration")
    suspend fun registration(@Body auth: Auth): User

    @POST("/loans")
    suspend fun createLoan(
        @Header("Authorization") token: String,
        @Body loanRequest: LoanRequest
    ): Loan

    @GET("/loans/{id}")
    suspend fun getLoan(
        @Header("Authorization") token: String,
        @Path("id") loanId: Int
    ): Loan

    @GET("/loans/all")
    suspend fun getLoansList(@Header("Authorization") token: String): List<Loan>

    @GET("/loans/conditions")
    suspend fun getLoanConditions(@Header("Authorization") token: String): LoanConditions

}