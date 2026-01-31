package com.mokekinho.financeapp.repositories

import com.mokekinho.financeapp.entities.Role
import org.springframework.stereotype.Repository
import com.mokekinho.financeapp.entities.User
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder

@Repository
class FinanceRepository(

){

    private val encoder = BCryptPasswordEncoder()

    private val users = listOf<User>(
        User(
            id = 1,
            name = "user1",
            password = encoder.encode("1234")!!,
        ),
        User(
            id = 2,
            name = "user2",
            password = encoder.encode("1234")!!,
            role = Role.ADMIN
        ),
        User(
            id = 3,
            name = "user3",
            password = encoder.encode("1234")!!
        ),
        User(
            id = 4,
            name = "user4",
            password = encoder.encode("1234")!!
        ),
    )

    fun findUserByName(
        name: String
    ): User? {

        return users.find { 
            name == it.name
        }
    }
}

