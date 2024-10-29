package com.estudos.app.Biblioteca.service;

import com.estudos.app.Biblioteca.exception.BibliotecaException;
import com.estudos.app.Biblioteca.model.Emprestimo;
import com.estudos.app.Biblioteca.model.Livro;
import com.estudos.app.Biblioteca.model.Status;
import com.estudos.app.Biblioteca.model.Usuario;
import com.estudos.app.Biblioteca.repository.EmprestimoRepository;
import com.estudos.app.Biblioteca.repository.LivroRepository;
import com.estudos.app.Biblioteca.repository.UsuarioRepository;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class BibliotecaService {
    private LivroRepository livroRepository;
    private UsuarioRepository usuarioRepository;
    private EmprestimoRepository emprestimoRepository;

    public BibliotecaService(LivroRepository livroRepository, UsuarioRepository usuarioRepository, EmprestimoRepository emprestimoRepository) {
        this.livroRepository = livroRepository;
        this.usuarioRepository = usuarioRepository;
        this.emprestimoRepository = emprestimoRepository;
    }

    public Emprestimo realizarEmprestimo(String isbnLivro, String idUsuario){
        Livro livro = livroRepository.acharPorIsbn(isbnLivro)
                .orElseThrow(() -> new BibliotecaException("Livro não encontrado!"));

        Usuario usuario = usuarioRepository.acharPorId(idUsuario)
                .orElseThrow(() -> new BibliotecaException("Usuario não encontrado!"));


        if (!livro.isDisponivel()){
            throw new BibliotecaException("Livro não esta mais disponivel");
        }

        String idEmpresimo = UUID.randomUUID().toString();
        Emprestimo emprestimo = new Emprestimo(idEmpresimo, livro, usuario);

        livro.setDisponivel(false);

        emprestimoRepository.salvar(emprestimo);

        usuario.getHistoricoEmprestimo().add(emprestimo);

        return emprestimo;
    }


    public void devolverLivro(String idEmprestimo){
        Emprestimo emprestimo = emprestimoRepository.acharPorId(idEmprestimo)
                .orElseThrow(() -> new BibliotecaException("Empréstimo não encontrado!"));

        if (emprestimo.getStatus()== Status.DEVOLVIDO){
            throw new BibliotecaException("Livro já foi devolvido");
        }

        emprestimo.setStatus(Status.DEVOLVIDO);
        emprestimo.getLivro().setDisponivel(true);
    }

    public List<Livro> buscarLivrosDisponiveis(){
        return livroRepository.acharTodos().stream()
                .filter(Livro::isDisponivel)
                .collect(Collectors.toList());
    }

    public List<Emprestimo> consultarEmprestimosAtivos(String idUsuario){
        return emprestimoRepository.achaPorUsuario(idUsuario).stream()
                .filter(emprestimo -> emprestimo.getStatus() == Status.ATIVO)
                .collect(Collectors.toList());
    }

}