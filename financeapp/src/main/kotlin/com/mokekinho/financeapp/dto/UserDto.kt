package com.mokekinho.financeapp.dto

import com.mokekinho.financeapp.entities.Role

data class UserDto(
    val name: String,
    val password: String,
    val email: String
)