package com.mokekinho.financeapp.controllers

import com.mokekinho.financeapp.dto.UserDto
import com.mokekinho.financeapp.entities.Role
import com.mokekinho.financeapp.entities.UserEntity
import com.mokekinho.financeapp.services.AdminService
import com.mokekinho.financeapp.services.FinanceService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("/admin")
class AdminController(
    val adminService: AdminService
) {
    private fun isAdmin(user: UserEntity): Boolean{
        return user.role == Role.ADMIN
    }

    @GetMapping
    fun getAdmin(): String{

        return "ADMIN"
    }

    @GetMapping("/userList")
    fun getUserList(): List<UserDto>{
        return adminService.getUserList()
    }


}