package com.mokekinho.financeapp.services

import com.mokekinho.financeapp.repositories.FinanceRepository
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service

@Service
class UserService(
    private val financeRepository: FinanceRepository,
) : UserDetailsService {

    override fun loadUserByUsername(username: String): UserDetails {

        val user = financeRepository.findByName(username) // aqui eu to dizendo como o Spring vai construir meu usuario
            ?: throw UsernameNotFoundException("User Not Found")

        return User.builder()
            .username(user.name)
            .password(user.password)
            .roles(user.role.name)
            .build()
    }
}