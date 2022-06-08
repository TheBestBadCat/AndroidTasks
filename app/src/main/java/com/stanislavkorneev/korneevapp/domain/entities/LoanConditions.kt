package com.stanislavkorneev.korneevapp.domain.entities

data class LoanConditions(
    val maxAmount: Double,
    val percent: Double,
    val period: Int,
)