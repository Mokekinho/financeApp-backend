package com.mokekinho.financeapp.services

import com.mokekinho.financeapp.dto.LoginRequest
import com.mokekinho.financeapp.dto.LoginResponse
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service

@Service
class AuthService(
    private val authenticationManager: AuthenticationManager,
    private val jwtService: JwtService
) {

    fun login(
        request: LoginRequest
    ): LoginResponse{

        //aqui basicamente acontece o seguinte:
        // 1- UsernamePasswordAuthentcationToken chama uma Dao
        // 2 - Essa Dao chama o loadUserByName que a gente sobrescreveu
        // 3 - a Dado verifica se bate senha e o restante
        val auth = authenticationManager.authenticate( // essa função vai retornar um usuario autenticado
            UsernamePasswordAuthenticationToken( // aqui eu to criando um objeto authentication que recebe usuario e senha, provavelmente deve ter outras formas de fazer isso
                request.name,
                request.password
            )
        )

        if(!auth.isAuthenticated){
            throw UsernameNotFoundException("User Not Found") // isso aqui é pra caso ele nao tenha lançado exceções la dentro
        }

        val token = jwtService.generateToken(
            auth.principal as UserDetails // o .principal é o objeto que representa o usuario, estou fazendo o cast pra UserDetails
        )

        return LoginResponse(
            token // estou retornando o token
        )
    }
}