package com.mokekinho.financeapp.services

import com.mokekinho.financeapp.dto.UserDto
import com.mokekinho.financeapp.mappers.toDto
import com.mokekinho.financeapp.repositories.FinanceRepository
import org.springframework.stereotype.Service

@Service
class AdminService(
    private val financeRepository: FinanceRepository
) {
    fun getUserList(): List<UserDto>{
        return financeRepository.findAll().map {
            it.toDto()
        }
    }

}