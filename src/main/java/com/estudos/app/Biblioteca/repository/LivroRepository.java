package com.estudos.app.Biblioteca.repository;

import com.estudos.app.Biblioteca.model.Livro;


import java.util.List;
import java.util.Optional;

public interface LivroRepository {
    Optional<Livro> acharPorIsbn(String isbn);
    List<Livro> acharTodos();
    void salvarLivro(Livro livro);
}
