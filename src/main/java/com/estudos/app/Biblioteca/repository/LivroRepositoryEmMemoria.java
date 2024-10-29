package com.estudos.app.Biblioteca.repository;

import com.estudos.app.Biblioteca.model.Livro;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class LivroRepositoryEmMemoria implements LivroRepository{

    private List<Livro> livros = new ArrayList<>();

    @Override
    public Optional<Livro> acharPorIsbn(String isbn) {
        return livros.stream().filter(livro -> livro.getIsbn()
                .equals(isbn))
                .findFirst();
    }

    @Override
    public List<Livro> acharTodos() {
        return new ArrayList<>(livros);
    }

    @Override
    public void salvarLivro(Livro livro) {
        livros.add(livro);
    }
}
