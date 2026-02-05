package com.mokekinho.financeapp.services

import com.mokekinho.financeapp.repositories.FinanceRepository
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class FinanceService(
    private val financeRepository: FinanceRepository,
    private val passwordEncoder: PasswordEncoder // o jeito certo é injetar a interface
){

}