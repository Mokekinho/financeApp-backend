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
    var id: Long = 0 ,

    @Column(
        nullable = false,
        unique = true
    ) var name: String = "",

    @Column(
        nullable = false
    ) var password: String = "",

    @Enumerated(EnumType.STRING)
    @Column(
        nullable = false
    ) var role: Role = Role.USER,

    // User information

    @Column(
        nullable = false,
        unique = true
    ) var email: String = "",

)

enum class Role(){
    USER,
    ADMIN,
    MANAGER
}