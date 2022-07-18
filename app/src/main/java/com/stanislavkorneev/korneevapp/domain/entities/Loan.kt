package com.stanislavkorneev.korneevapp.domain.entities

data class Loan(
    val amount: Int,
    val date: String,
    val firstName: String,
    val id: Int,
    val lastName: String,
    val percent: Double,
    val period: Int,
    val phoneNumber: String,
    val state: LoanState
)

//TODO fix hardcoded text
enum class LoanState(val description: String) {
    APPROVED("Одобрен"),
    REGISTERED("Зарегистрирован"),
    REJECTED("Отклонен");
}
