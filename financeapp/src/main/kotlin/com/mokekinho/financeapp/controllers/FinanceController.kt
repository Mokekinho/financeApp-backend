package com.mokekinho.financeapp.controllers

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("/finance")
class FinanceController {

    @GetMapping("/dashboard")
    fun dashboard(): String{
        return "ACESSO LIBERADO"
    }
}