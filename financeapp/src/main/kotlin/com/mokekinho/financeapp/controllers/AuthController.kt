package com.mokekinho.financeapp.controllers

import com.mokekinho.financeapp.dto.UserDto
import com.mokekinho.financeapp.dto.LoginRequest
import com.mokekinho.financeapp.dto.LoginResponse
import com.mokekinho.financeapp.entities.User
import com.mokekinho.financeapp.services.AuthService
import com.mokekinho.financeapp.services.FinanceService
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
    private val authService: AuthService
) {

    @GetMapping("/me")
    fun getLoginInfo(): String{
        return "ok" // isso aqui é apenas pra pdoer testar se o oken esta vivo ou não
    }

    @PostMapping("/add")
    fun addNewUser(
        @RequestBody user: User // criar dto para isso
    ){
        authService.addNewUser(user)
    }

    @PostMapping("/login")
    fun logIn(
        @RequestBody loginRequest: LoginRequest
    ): LoginResponse {
        
       return authService.login(loginRequest)

    }

    @PostMapping("/logout")
    fun logOut(
        @RequestHeader("Authorization") authHeader: String
    ) {
    }
}