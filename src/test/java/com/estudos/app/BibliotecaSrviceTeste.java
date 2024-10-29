package com.estudos.app;

import com.estudos.app.Biblioteca.repository.LivroRepository;
import com.estudos.app.Biblioteca.repository.UsuarioRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


@ExtendWith(MockitoExtension.class)
public class BibliotecaSrviceTeste {
    @Mock
    private LivroRepository livroRepository;

    @Mock
    private UsuarioRepository usuarioRepository;


    @Test
    void EncontrarUsuarioLivro(){

    }
}
