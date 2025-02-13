package com.imepac.crud_kotlin.controller

import com.imepac.crud_kotlin.model.Usuario
import com.imepac.crud_kotlin.service.UsuarioService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/usuarios")
class UsuarioController(private val usuarioService: UsuarioService) {

    @GetMapping
    fun listarTodos(): List<Usuario> = usuarioService.listarTodos()

    @GetMapping("/{id}")
    fun buscarPorId(@PathVariable id: Long): ResponseEntity<Usuario> {
        val usuario = usuarioService.buscarPorId(id)
        return if (usuario != null) ResponseEntity.ok(usuario) else ResponseEntity.notFound().build()
    }

    @PostMapping
    fun salvar(@RequestBody usuario: Usuario): Usuario = usuarioService.salvar(usuario)

    @PutMapping("/{id}")
    fun atualizar(@PathVariable id: Long, @RequestBody usuario: Usuario): ResponseEntity<Usuario> {
        val usuarioAtualizado = usuarioService.atualizar(id, usuario)
        return if (usuarioAtualizado != null) ResponseEntity.ok(usuarioAtualizado) else ResponseEntity.notFound().build()
    }

    @DeleteMapping("/{id}")
    fun deletar(@PathVariable id: Long): ResponseEntity<Void> {
        usuarioService.deletar(id)
        return ResponseEntity.noContent().build()
    }
}
