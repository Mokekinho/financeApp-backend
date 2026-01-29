package com.mokekinho.financeapp.temp

import com.mokekinho.financeapp.entities.User

object FakeSecurityContext {
    private val currentUser = ThreadLocal<User?>() // vou salver o usuário numa trhead local

    fun setUser(user: User) {
        currentUser.set(user) //aqui eu adiciono o usuario na Thead
    }

    fun getUser(): User? {
        return currentUser.get() //pega os dados do usuaário
    }

    fun clear(){ // limpa a thread para os dados do usuario não vazarem para o proximo qe for usar
        currentUser.remove()
    }
}