package com.estudos.app.Biblioteca.repository;

import com.estudos.app.Biblioteca.model.Emprestimo;
import com.estudos.app.Biblioteca.model.Usuario;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class EmprestimoRepositoryEmMemoria implements EmprestimoRepository{
    private List<Emprestimo> emprestimos = new ArrayList<>();


    @Override
    public void salvar(Emprestimo emprestimo) {
        emprestimos.add(emprestimo);
    }

    @Override
    public List<Emprestimo> achaPorUsuario(String idUsuario) {
        return emprestimos.stream().filter(emprestimo -> emprestimo.getUsuario()
                        .getId()
                        .equals(idUsuario))
                .collect(Collectors.toList());
    }


    @Override
    public Optional<Emprestimo> acharPorId(String id) {
        return emprestimos.stream().filter(emprestimo -> emprestimo.getId().equals(id)).findFirst();
    }
}
