package com.stanislavkorneev.korneevapp.domain.entities

data class LoanConditions(
    val maxAmount: Int,
    val percent: Double,
    val period: Int,
)