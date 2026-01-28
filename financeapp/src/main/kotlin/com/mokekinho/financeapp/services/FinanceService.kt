package com.mokekinho.financeapp.services

import com.mokekinho.financeapp.repositories.FinanceRepository
import com.mokekinho.financeapp.dto.LoginRequest
import com.mokekinho.financeapp.dto.LoginResponse
import com.mokekinho.financeapp.temp.*
import org.springframework.stereotype.Service



@Service
class FinanceService(
    private val financeRepository: FinanceRepository,
    private val fakePassWordEconder: FakePassWordEconder,
    private val fakeTokenService: FakeTokenService
){

    fun login(
        loginRequest: LoginRequest
    ): LoginResponse{

        val user = financeRepository.findUserByName(loginRequest.name) ?: throw RuntimeException("Invalid credentials")

        if(!fakePassWordEconder.matches(loginRequest.password ,user.password)){
            throw RuntimeException("Invalid credentials")
        }

        return LoginResponse(
            token = fakeTokenService.generateToken(user)
        )
    }


}