package com.mokekinho.financeapp.temp

import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter

/**
@Component
class FakeAuthFilter : OncePerRequestFilter() {

    @Autowired // eu poderia fazer o constructor injection ao inves disso
    private lateinit var tokenService: FakeTokenService

    override fun doFilterInternal( // toda vez que chega um requisição o Spring chama esse método
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        val path = request.requestURI //o requeste tem tudo que o usuario vai mandar

        if (path.startsWith("/auth")) {
            filterChain.doFilter(request, response) // aqui eu to chamando os controllers
            return
        }

        val authHeader = request.getHeader("Authorization") // vou epgar o header de autenticação

        if (authHeader == null || !authHeader.startsWith("Bearer ")) { //vejo se o header existe e se ee segue o padrão começando com Bearer
            response.status = HttpServletResponse.SC_UNAUTHORIZED // se entrar no If é pq nao exite ou nao segue o padrao
            return
        }

        val token = authHeader.removePrefix("Bearer ") // vou remover o prefixo Bearer
        //esse Bearer serve pra seguir o padrao, o headear authetication pode ser basic, nesse caso todas requições teriam usario e senha o que seria pessimo

        if(!tokenService.isValid(token)){
            response.status = HttpServletResponse.SC_UNAUTHORIZED // se o token não for valiido ele da erro
            return
        }

        val user = tokenService.getUser(token) // vou descobrir o usuario pra passar pra requisição

        if (user != null){
            FakeSecurityContext.setUser(user) // agora o user esta no contexto e a aplicação toda pode utilizá-lo.
        }

        try {
            filterChain.doFilter(request, response)
        } finally {
            FakeSecurityContext.clear() // evita vazamento entre requests
        }

    }
}
        **/