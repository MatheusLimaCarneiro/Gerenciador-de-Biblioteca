package com.estudos.app.Biblioteca;

import com.estudos.app.Biblioteca.exception.BibliotecaException;
import com.estudos.app.Biblioteca.model.Emprestimo;
import com.estudos.app.Biblioteca.model.Livro;
import com.estudos.app.Biblioteca.model.Usuario;
import com.estudos.app.Biblioteca.repository.*;
import com.estudos.app.Biblioteca.service.BibliotecaService;

import java.util.List;

public class Main {
    public static void main(String[] args) {

        LivroRepository livroRepository = new LivroRepositoryEmMemoria();
        UsuarioRepository usuarioRepository = new UsuarioRepositoryEmMemoria();
        EmprestimoRepository emprestimoRepository = new EmprestimoRepositoryEmMemoria();


        BibliotecaService bibliotecaService = new BibliotecaService(livroRepository, usuarioRepository, emprestimoRepository);


        Livro livro1 = new Livro("123", "Java POO", "Autor 1", 2024);
        Usuario usuario1 = new Usuario("1", "João", "joao@email.com");

        livroRepository.salvarLivro(livro1);
        usuarioRepository.salvarUsuario(usuario1);


        try {

            Emprestimo emprestimo = bibliotecaService.realizarEmprestimo("123", "1");
            System.out.println("Empréstimo realizado com sucesso!");
            System.out.println("Data de devolução: " + emprestimo.getDataDevolucao());


            List<Emprestimo> emprestimosAtivos = bibliotecaService.consultarEmprestimosAtivos("1");
            System.out.println("Empréstimos ativos: " + emprestimosAtivos.size());


            bibliotecaService.devolverLivro(emprestimo.getId());
            System.out.println("Livro devolvido com sucesso!");

        } catch (BibliotecaException e) {
            System.err.println("Erro: " + e.getMessage());
        }
    }

}

