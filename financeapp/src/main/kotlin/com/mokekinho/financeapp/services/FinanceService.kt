package com.mokekinho.financeapp.services

import com.mokekinho.financeapp.repositories.FinanceRepository
import com.mokekinho.financeapp.dto.LoginRequest
import com.mokekinho.financeapp.dto.LoginResponse
import com.mokekinho.financeapp.temp.*
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class FinanceService(
    private val financeRepository: FinanceRepository,
    private val fakeTokenService: FakeTokenService,
    private val passwordEncoder: PasswordEncoder // o jeito certo é injetar a interface
){

    fun login(
        loginRequest: LoginRequest
    ): LoginResponse{

        val user = financeRepository.findUserByName(loginRequest.name) ?: throw RuntimeException("Invalid credentials")

        if(
            passwordEncoder.matches(
                loginRequest.password,user.password
                )
            ){
            throw RuntimeException("Invalid credentials")
        }

        return LoginResponse(
            token = fakeTokenService.generateToken(user)
        )
    }

    fun logout(
        authHeader: String
    ){
        val token = authHeader.removePrefix("Bearer ")
        fakeTokenService.invalidate(token)
    }


}