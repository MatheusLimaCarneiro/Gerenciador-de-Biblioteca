package com.estudos.app.Biblioteca.repository;

import com.estudos.app.Biblioteca.model.Usuario;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UsuarioRepositoryEmMemoria implements  UsuarioRepository{

    private List<Usuario> usuarios = new ArrayList<>();

    @Override
    public Optional<Usuario> acharPorId(String id) {
        return usuarios.stream().filter(usuario -> usuario.getId()
                .equals(id))
                .findFirst();
    }

    @Override
    public void salvarUsuario(Usuario usuario) {
        usuarios.add(usuario);
    }
}
