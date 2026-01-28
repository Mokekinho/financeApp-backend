package com.mokekinho.financeapp.configuration

import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer


//@Configuration
class MvcConfig : WebMvcConfigurer{

    override fun addViewControllers(registry: ViewControllerRegistry) {
        registry.addViewController("/home").setViewName("home") // vai procurar o arquivo home na pasta template, isso elimina a necessidade de get complexos pq a gente quer so mostrar um html estatico
        registry.addViewController("/").setViewName("home")
        registry.addViewController("/hello").setViewName("hello")
        registry.addViewController("/login").setViewName("login")
    }
}