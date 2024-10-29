package com.estudos.app.Biblioteca.repository;

import com.estudos.app.Biblioteca.model.Emprestimo;
import com.estudos.app.Biblioteca.model.Usuario;

import java.util.List;
import java.util.Optional;

public interface EmprestimoRepository {
    void salvar (Emprestimo emprestimo);
    List<Emprestimo> achaPorUsuario(String idUsuario);
    Optional<Emprestimo> acharPorId(String id);
}
