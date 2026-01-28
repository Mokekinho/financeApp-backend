package com.mokekinho.financeapp.controllers

import com.mokekinho.financeapp.dto.UserDto
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("/auth")
class AuthController {

    private val users = listOf<UserDto>(
        UserDto(
            name = "user1",
            password = "1234"
        ),
        UserDto(
            name = "user2",
            password = "1234"
        ),
        UserDto(
            name = "user3",
            password = "1234"
        ),
        UserDto(
            name = "user4",
            password = "1234"
        ),
    )


    @PostMapping
    fun logIn(
        @RequestBody user: UserDto
    ){




    }
}