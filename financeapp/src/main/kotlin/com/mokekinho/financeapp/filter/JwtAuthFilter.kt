package com.mokekinho.financeapp.filter

import com.mokekinho.financeapp.services.JwtService
import com.mokekinho.financeapp.services.UserService
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter

@Component
class JwtAuthFilter(
    private val jwtService: JwtService,
    private val userService: UserService,
): OncePerRequestFilter() {

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {

        // eu nao rpeciso verificar se ele ta na rota certa pq o Configuration já vai fazer isso pra mim
        val authHeader = request.getHeader("Authorization") //vou pegar o token

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response) // eu vou deixar a requisição passar por que ai o spring security que vai cuidar de quando pode ou não acessar
            return
        }

        val token = authHeader.removePrefix("Bearer ")// pegando o token

        val userName = jwtService.extractUsername(token) // pego o nome do usario, aqui ele valida tambem se o tonken esta correto

        val userDetails = userService.loadUserByUsername(userName) //pego os dados do usuario

        val authToken = UsernamePasswordAuthenticationToken( // aqui ele vai retornar um usuario autenticado
            userDetails,
            null,
            userDetails.authorities // esse emtodo com 3 parametros nao pede senha ele ja confia no token
            // colocar as autorithies aqui também
        )

        SecurityContextHolder.getContext().authentication = authToken // aqui o usuário é logado

        filterChain.doFilter(request, response) // com o usuário logado podemos continuar com a reqisição que o Spring Security não vai mais barrar
    }
}