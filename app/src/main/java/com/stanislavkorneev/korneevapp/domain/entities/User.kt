package com.stanislavkorneev.korneevapp.domain.entities

data class User(
    val name: String,
    val role: UserRole
)

enum class UserRole {
    ADMIN,
    USER
}