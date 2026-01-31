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
    private val passwordEncoder: PasswordEncoder // o jeito certo é injetar a interface
){

}