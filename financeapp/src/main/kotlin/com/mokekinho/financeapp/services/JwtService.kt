package com.mokekinho.financeapp.services

import io.jsonwebtoken.Jwts
import io.jsonwebtoken.security.Keys
import io.jsonwebtoken.security.SignatureAlgorithm
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Service
import java.util.Date

@Service
class JwtService {
    private val EXPIRATION_TIME = 1000 * 60 * 5 // 5 minutos
    private val SECRET_KEY_STRING = "MINHA_CHAVE_SUPER_SECRETA_1234567890123456"

    private val secretKey = Keys.hmacShaKeyFor(SECRET_KEY_STRING.toByteArray()) // isso aqui não pode ir pro github, mas vou deixar ir por fins didaticos

    fun generateToken(userDetails: UserDetails): String{
        return Jwts.builder()
            .subject(userDetails.username) // define o dono do token, aqui seria o username do usuario
            .claim("role", userDetails.authorities.map{ it.authority }) //Todo nao entedi essa parte
            .issuedAt(Date()) // define a data do toke, por padrao o Date(  ja retorna o Syste.CurrenTimeInMillis
            .expiration(Date(System.currentTimeMillis() + EXPIRATION_TIME))
            .audience().add("mobile-app").and()
            .signWith(secretKey)
            .compact()
    }

    fun extractUsername(token: String): String{

        return Jwts.parser()
            .verifyWith(secretKey) // aqui eu estou definindo a chave que eu vou verificar
            .build() // aqui eu vou criar o parser configurado, entao acima disso vai ser como eu vou verificar o token
            .parseSignedClaims(token) // aqui eu to verificando o token com as configurações la de cima
            .payload.subject // esse payload é o que a gente construiu la em cima, ele vai ter subject, o expiration, o issueat // aqui eu to pegando o username


    }
}