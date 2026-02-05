package com.mokekinho.financeapp.entities

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table


@Entity
@Table(
    name = "users"
)
class UserEntity(
    @Id
    @GeneratedValue(
        strategy = GenerationType.IDENTITY
    )
    val id: Long = 0 ,

    @Column(
        nullable = false,
        unique = true
    ) val name: String = "",

    @Column(
        nullable = false
    ) val password: String = "",

    @Enumerated(EnumType.STRING)
    @Column(
        nullable = false
    ) val role: Role = Role.USER
)

enum class Role(){
    USER,
    ADMIN,
    MANAGER
}