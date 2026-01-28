package com.mokekinho.financeapp.configuration

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.provisioning.InMemoryUserDetailsManager
import org.springframework.security.web.SecurityFilterChain


@Configuration
@EnableWebSecurity
class WebSecurityConfig {

    @Bean
    fun securityFilterChain(http: HttpSecurity): SecurityFilterChain {

        http.
            // Kotlin security extensions allow concise DSL instead of builder-style lambdas
            authorizeHttpRequests { auth ->
                auth
                    .requestMatchers("/", "/home").permitAll() //procura essas rotas, para as rotas eu permito todas as pessoas
                    .anyRequest().authenticated()// agora qualquer outro end point diferente dos de cima é necessario estar authenticado
            }
            .formLogin {form ->
                form
                    .loginPage("/login")
                    .permitAll()
            }
            .logout { logout ->
                logout.permitAll()
            }


        return http.build()

    }

    @Bean
    fun passwordEncoder(): PasswordEncoder {
        return BCryptPasswordEncoder() // para codificar a senha
    }

    @Bean
    fun userDetailsService(): UserDetailsService {
        val encoder = BCryptPasswordEncoder()
        val user = User.builder()
            .username("user")
            .password(encoder.encode("password"))
            .roles("USER")
            .build()

        return InMemoryUserDetailsManager(user)
    }


}