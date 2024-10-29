package com.estudos.app.Biblioteca.repository;


import com.estudos.app.Biblioteca.model.Usuario;

import java.util.Optional;

public interface UsuarioRepository {
    Optional<Usuario> acharPorId(String id);
    void salvarUsuario(Usuario usuario);
}
