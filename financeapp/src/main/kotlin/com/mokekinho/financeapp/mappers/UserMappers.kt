package com.mokekinho.financeapp.mappers

import com.mokekinho.financeapp.dto.UserDto
import com.mokekinho.financeapp.entities.UserEntity

fun UserDto.toEntity(): UserEntity{
    return UserEntity(
        name = this.name,
        password = this.password,
        email = this.email
    )
}

fun UserEntity.toDto(): UserDto{
    return UserDto(
        name = this.name,
        password = this.password,
        email = this.email
    )
}