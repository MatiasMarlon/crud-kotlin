package com.imepac.crud_kotlin.service

import com.imepac.crud_kotlin.model.Usuario
import com.imepac.crud_kotlin.repository.UsuarioRepository
import org.springframework.stereotype.Service

@Service
class UsuarioService(private val usuarioRepository: UsuarioRepository) {

    fun listarTodos(): List<Usuario> = usuarioRepository.findAll()

    fun buscarPorId(id: Long): Usuario? = usuarioRepository.findById(id).orElse(null)

    fun salvar(usuario: Usuario): Usuario {
        return usuarioRepository.save(usuario)
    }

    fun atualizar(id: Long, usuarioAtualizado: Usuario): Usuario? {
        return if (usuarioRepository.existsById(id)) {
            val usuario = usuarioAtualizado.copy(id = id)
            usuarioRepository.save(usuario)
        } else {
            null
        }
    }

    fun deletar(id: Long) {
        if (usuarioRepository.existsById(id)) {
            usuarioRepository.deleteById(id)
        }
    }
}
