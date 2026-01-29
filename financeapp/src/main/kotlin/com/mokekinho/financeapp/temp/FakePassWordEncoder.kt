package com.mokekinho.financeapp.temp

import org.springframework.stereotype.Component

interface PasswordEncoder{
    fun encode(raw: String): String
    fun matches(raw: String, encoded: String): Boolean
}

@Component
class FakePassWordEncoder : PasswordEncoder
{
    override fun encode(raw: String): String{
        return "ENC($raw)" //simulando uma codificação
    }

    override fun matches(raw: String, encoded: String): Boolean{
        return encode(raw) == encoded
    }

}