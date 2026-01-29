package com.mokekinho.financeapp.temp

import com.mokekinho.financeapp.entities.User
import org.springframework.stereotype.Component

interface TokenService{
    fun generateToken(user: User): String
    fun isValid(token: String): Boolean
}

@Component
class FakeTokenService: TokenService{
    private val validTokens = mutableMapOf<String, TokenInfo>()
    private val EXPIRATION_TIME = 1000 * 60 * 5 // 5 minutos

    override fun generateToken(user: User): String{


        val token = "TOKEN_${user.id}_${System.currentTimeMillis()}"

        val tokenInfo = TokenInfo(
            token = token,
            user = user,
            expiresAt = System.currentTimeMillis() + EXPIRATION_TIME
        )

        validTokens[token] = tokenInfo
        return token
    }

    override fun isValid(token: String): Boolean {
        val info = validTokens[token] ?: return false
        return info.expiresAt > System.currentTimeMillis()

    }

    fun getUser(token: String): User? {
        return validTokens[token]?.user
    }
    fun invalidate(token: String) {
        validTokens.remove(token)
    }
}

data class TokenInfo(
    val token: String,
    val user: User,
    val expiresAt: Long
)
