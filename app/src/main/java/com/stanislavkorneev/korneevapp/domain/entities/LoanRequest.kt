package com.stanislavkorneev.korneevapp.domain.entities

data class LoanRequest (
    val amount: Double,
    val firstName: String,
    val lastName: String,
    val percent: Double,
    val period: Int,
    val phoneNumber: String,
)