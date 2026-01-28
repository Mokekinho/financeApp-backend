package com.mokekinho.financeapp.repositories

import org.springframework.stereotype.Repository
import com.mokekinho.financeapp.entities.User



@Repository
class FinanceRepository(
){

    private val users = listOf<User>(
        User(
            id = 1,
            name = "user1",
            password = "ENC(1234)"
        ),
        User(
            id = 2,
            name = "user2",
            password = "ENC(1234)"
        ),
        User(
            id = 3,
            name = "user3",
            password = "ENC(1234)"
        ),
        User(
            id = 4,
            name = "user4",
            password = "ENC(1234)"
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

