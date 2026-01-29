package com.mokekinho.financeapp.entities

data class User(
    val id: Long,
    val name: String,
    val password: String,
    val role: Role = Role.USER
)

enum class Role(){
    USER,
    ADMIN,
    MANAGER
}