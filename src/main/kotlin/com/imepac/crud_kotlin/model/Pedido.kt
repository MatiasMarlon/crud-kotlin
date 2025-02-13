package com.imepac.crud_kotlin.model

import jakarta.persistence.*
import java.time.LocalDate

@Entity
data class Pedido(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    val valorCompra: Double,
    val valorDesconto: Double,
    var valorTotal: Double = 0.0,

    @Enumerated(EnumType.STRING)
    val status: StatusPedido = StatusPedido.PENDENTE,

    //val dataCriacao: LocalDate = LocalDate.now(),

    val metodoPagamento: String,

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    var usuario: Usuario
) {
    fun calcularValorTotal() {
        this.valorTotal = valorCompra - valorDesconto
    }
}

enum class StatusPedido {
    PENDENTE, PAGO, CANCELADO
}

