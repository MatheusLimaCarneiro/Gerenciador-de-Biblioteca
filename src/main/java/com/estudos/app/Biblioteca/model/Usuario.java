package com.estudos.app.Biblioteca.model;

import java.util.ArrayList;
import java.util.List;

public class Usuario {
    private String id;
    private String nome;
    private String email;
    List<Emprestimo> historicoEmprestimo;


    public Usuario(String id, String nome, String email) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.historicoEmprestimo = new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Emprestimo> getHistoricoEmprestimo() {
        return historicoEmprestimo;
    }

    public void setHistoricoEmprestimo(List<Emprestimo> historicoEmprestimo) {
        this.historicoEmprestimo = historicoEmprestimo;
    }
}
