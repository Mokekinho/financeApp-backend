package com.mokekinho.financeapp.controllers

import com.mokekinho.financeapp.entities.Role
import com.mokekinho.financeapp.entities.UserEntity
import com.mokekinho.financeapp.services.FinanceService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("/admin")
class AdminController(
    val financeService: FinanceService
) {
    private fun isAdmin(user: UserEntity): Boolean{
        return user.role == Role.ADMIN
    }

    @GetMapping
    fun getAdmin(): String{

        return "ADMIN"
    }


}