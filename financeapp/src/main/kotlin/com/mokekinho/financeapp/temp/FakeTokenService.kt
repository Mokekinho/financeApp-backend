package com.mokekinho.financeapp.temp

import com.mokekinho.financeapp.entities.User
import org.springframework.stereotype.Component

interface TokenService{
    fun generateToken(user: User): String
}

@Component
class FakeTokenService: TokenService{
    override fun generateToken(user: User): String{
        return "TOKEN_${user.id}_${System.currentTimeMillis()}"
    }
}