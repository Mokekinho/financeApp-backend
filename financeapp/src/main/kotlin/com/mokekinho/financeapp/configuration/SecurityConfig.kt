package com.mokekinho.financeapp.configuration

import com.mokekinho.financeapp.filter.JwtAuthFilter
import com.mokekinho.financeapp.services.UserService
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.AuthenticationProvider
import org.springframework.security.authentication.dao.DaoAuthenticationProvider
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter

@Configuration
@EnableWebSecurity
class SecurityConfig(
    private val jwtAuthFilter: JwtAuthFilter,
    private val userService: UserService
) {

    @Bean
    fun filterChain(http: HttpSecurity): SecurityFilterChain{

        //TODO Estudar o objeto HttpSecurity

        http
            .authorizeHttpRequests {
                it.requestMatchers("/auth/login").permitAll() //todos podem ver a tela de login
                it.requestMatchers("/admin/**").hasRole("ADMIN") //aqui eu tenho que aprender, mas aqui vai poder acessar so quem for admin
                it.anyRequest().authenticated() // as outras requizições precisa de autenticação

            }
            .sessionManagement {
                it.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            }
            .authenticationProvider(authenticationProvider())
            // Essa opção de form não me é muito util
            //.formLogin { //tem como criar uma tela personalizada, mais pra frente ver como funciona }
            .csrf { it.disable() } // to desabilitando pq sim,
            .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter::class.java) // o Spring security tem um fila de filtros, eu to falando que quero colocar o meu filtro na frente do filtro De UsernamePasswordAuthenticationFilter

            return http.build()
    }

    @Bean
    fun passwordEncoder(): PasswordEncoder{
        return BCryptPasswordEncoder()
    }

    @Bean
    fun authenticationProvider(): AuthenticationProvider{

        val provider = DaoAuthenticationProvider(userService)

        provider.setPasswordEncoder(passwordEncoder())

        return provider

    }

    @Bean
    @Throws(Exception::class)
    fun authenticationManager(config: AuthenticationConfiguration): AuthenticationManager{
        return config.authenticationManager
    }

}

/*
1. Gerenciamento de Autorização (authorizeHttpRequests)
É aqui que você define quem pode acessar o quê.

requestMatchers(): Define os padrões de URL (ex: /admin/**, /public/api).

hasRole() / hasAuthority(): Restringe o acesso a usuários com papéis ou permissões específicas.

permitAll(): Libera o acesso total (comum para login ou documentação API).

authenticated(): Exige que o usuário esteja logado, independente do cargo.

anyRequest(): Uma regra "pega-tudo" para garantir que nada fique exposto por erro.

2. Autenticação (Login e Logout)
formLogin(): Habilita o formulário de login padrão do Spring ou permite que você defina uma loginPage() personalizada.

httpBasic(): Habilita a autenticação via cabeçalho HTTP Basic (comum em APIs simples).

logout(): Configura o comportamento de saída, como a URL de logout e a limpeza de cookies/sessão.

oauth2Login(): Para autenticação via Google, GitHub, etc.

3. Proteções de Segurança (CSRF e CORS)
csrf(): O Cross-Site Request Forgery vem habilitado por padrão. Em APIs REST que usam Tokens (JWT), é comum desabilitá-lo via .disable().

cors(): Configura o Cross-Origin Resource Sharing, necessário se o seu frontend estiver em um domínio/porta diferente do backend.

4. Gerenciamento de Sessão (sessionManagement)
Fundamental para definir como a aplicação se comporta em relação ao estado:

sessionCreationPolicy():

STATELESS: Não cria sessão no servidor (usado com JWT).

IF_REQUIRED: Cria apenas se necessário.

ALWAYS: Sempre cria sessão.

maximumSessions(): Controla quantos logins simultâneos um usuário pode ter.

5. Configurações de Cabeçalhos e Exceções
headers(): Permite configurar proteções como o FrameOptions (necessário para o H2 Console funcionar, por exemplo).

exceptionHandling(): Permite definir o que acontece em erros de segurança:

authenticationEntryPoint(): Quando o usuário não está autenticado.

accessDeniedHandler(): Quando o usuário está logado, mas não tem permissão.
*/