package com.imepac.crud_kotlin.model

import com.fasterxml.jackson.annotation.JsonIgnore
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id


import jakarta.persistence.*

@Entity
data class Usuario(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,
    var nome: String = "",  // Valor padrão para evitar erro
    var email: String = "",

    @JsonIgnore
    @OneToMany(mappedBy = "usuario", cascade = [CascadeType.ALL], orphanRemoval = true)
    val pedidos: MutableList<Pedido> = mutableListOf()
)
