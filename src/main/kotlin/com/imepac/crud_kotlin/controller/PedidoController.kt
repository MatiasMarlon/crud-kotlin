package com.imepac.crud_kotlin.controller

import com.imepac.crud_kotlin.model.Pedido
import com.imepac.crud_kotlin.service.PedidoService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/pedidos")
class PedidoController(private val pedidoService: PedidoService) {

    @GetMapping
    fun listarTodos(): List<Pedido> = pedidoService.listarTodos()

    @GetMapping("/usuario/{usuarioId}")
    fun listarPorUsuario(@PathVariable usuarioId: Long): List<Pedido> = pedidoService.listarPorUsuario(usuarioId)

    @GetMapping("/{id}")
    fun buscarPorId(@PathVariable id: Long): ResponseEntity<Pedido> {
        val pedido = pedidoService.buscarPorId(id)
        return if (pedido != null) ResponseEntity.ok(pedido) else ResponseEntity.notFound().build()
    }

    @PostMapping
    fun salvar(@RequestBody pedido: Pedido): Pedido = pedidoService.salvar(pedido)

    @PutMapping("/{id}")
    fun atualizar(@PathVariable id: Long, @RequestBody pedido: Pedido): ResponseEntity<Pedido> {
        val pedidoAtualizado = pedidoService.atualizar(id, pedido)
        return if (pedidoAtualizado != null) ResponseEntity.ok(pedidoAtualizado) else ResponseEntity.notFound().build()
    }

    @DeleteMapping("/{id}")
    fun deletar(@PathVariable id: Long): ResponseEntity<Void> {
        pedidoService.deletar(id)
        return ResponseEntity.noContent().build()
    }
}
