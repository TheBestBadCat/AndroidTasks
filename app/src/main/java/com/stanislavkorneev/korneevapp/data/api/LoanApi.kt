package com.stanislavkorneev.korneevapp.data.api

import com.stanislavkorneev.korneevapp.domain.entities.Loan
import com.stanislavkorneev.korneevapp.domain.entities.LoanConditions
import retrofit2.http.GET
import retrofit2.http.POST

interface LoanApi {

    @POST("/loans")
    suspend fun createLoan(

    ) : Loan

    @GET("/loans/{id}")
    suspend fun getLoan(

    ) : Loan

    @GET("/loans/all")
    suspend fun getAllLoans() : List<Loan>

    @GET("/loans/conditions")
    suspend fun getLoansConditions() : LoanConditions

}