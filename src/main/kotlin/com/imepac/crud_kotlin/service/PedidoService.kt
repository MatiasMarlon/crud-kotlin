package com.imepac.crud_kotlin.service

import com.imepac.crud_kotlin.model.Pedido
import com.imepac.crud_kotlin.repository.PedidoRepository
import com.imepac.crud_kotlin.repository.UsuarioRepository
import org.springframework.stereotype.Service

@Service
class PedidoService(private val pedidoRepository: PedidoRepository, private val usuarioRepository: UsuarioRepository) {

    fun listarTodos(): List<Pedido> = pedidoRepository.findAll()

    fun listarPorUsuario(usuarioId: Long): List<Pedido> = pedidoRepository.findByUsuarioId(usuarioId)

    fun buscarPorId(id: Long): Pedido? = pedidoRepository.findById(id).orElse(null)

    fun salvar(pedido: Pedido): Pedido {
        val usuario = usuarioRepository.findById(pedido.usuario.id!!).orElseThrow {
            throw RuntimeException("Usuário não encontrado")
        }
        pedido.usuario = usuario  // Garante que o usuário está completo antes de salvar
        pedido.calcularValorTotal()
        return pedidoRepository.save(pedido)
    }


    fun atualizar(id: Long, pedidoAtualizado: Pedido): Pedido? {
        return if (pedidoRepository.existsById(id)) {
            val pedido = pedidoAtualizado.copy(id = id)
            pedido.calcularValorTotal()
            pedidoRepository.save(pedido)
        } else {
            null
        }
    }

    fun deletar(id: Long) {
        if (pedidoRepository.existsById(id)) {
            pedidoRepository.deleteById(id)
        }
    }
}
