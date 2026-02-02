package com.mokekinho.financeapp.entities


//@Entity//Todo criar um banco de dados pra guardar isso
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