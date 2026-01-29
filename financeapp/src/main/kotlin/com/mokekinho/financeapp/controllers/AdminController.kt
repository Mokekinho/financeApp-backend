package com.mokekinho.financeapp.controllers

import com.mokekinho.financeapp.entities.Role
import com.mokekinho.financeapp.entities.User
import com.mokekinho.financeapp.services.FinanceService
import com.mokekinho.financeapp.temp.FakeSecurityContext
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("/admin")
class AdminController(
    val financeService: FinanceService
) {
    private fun isAdmin(user: User): Boolean{
        return user.role == Role.ADMIN
    }

    @GetMapping
    fun getAdmin(): String{
        val user: User = FakeSecurityContext.getUser() ?: throw RuntimeException("Not Authenticated")
        if(!isAdmin(user)){
            throw RuntimeException("Forbidden")
        }

        return "ADMIN"
    }


}