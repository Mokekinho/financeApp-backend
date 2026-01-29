package com.mokekinho.financeapp.controllers

import com.mokekinho.financeapp.dto.UserDto
import com.mokekinho.financeapp.dto.LoginRequest
import com.mokekinho.financeapp.dto.LoginResponse
import com.mokekinho.financeapp.services.FinanceService
import com.mokekinho.financeapp.temp.FakeTokenService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("/auth")
class AuthController(
    private val financeService: FinanceService,
) {

    @GetMapping
    fun getLoginInfo(): String{

        return "Login"
    }

    @PostMapping("/login")
    fun logIn(
        @RequestBody loginRequest: LoginRequest
    ): LoginResponse {
        
       return financeService.login(loginRequest)

    }

    @PostMapping("/logout")
    fun logOut(
        @RequestHeader("Authorization") authHeader: String
    ) {
        financeService.logout(authHeader)
    }
}