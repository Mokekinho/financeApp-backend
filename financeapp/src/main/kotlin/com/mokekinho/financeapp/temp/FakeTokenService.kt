package com.mokekinho.financeapp.temp

import com.mokekinho.financeapp.entities.User
import org.springframework.stereotype.Component

interface TokenService{
    fun generateToken(user: User): String
    fun isValid(token: String): Boolean
}

@Component
class FakeTokenService: TokenService{
    private val validTokens = mutableSetOf<String>()

    override fun generateToken(user: User): String{
        val token = "TOKEN_${user.id}_${System.currentTimeMillis()}"

        validTokens.add(token)
        return token
    }

    override fun isValid(token: String): Boolean {
        return validTokens.contains(token)
    }
}