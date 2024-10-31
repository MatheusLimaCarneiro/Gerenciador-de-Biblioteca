package com.estudos.app;

import com.estudos.app.Biblioteca.exception.BibliotecaException;
import com.estudos.app.Biblioteca.model.Emprestimo;
import com.estudos.app.Biblioteca.model.Livro;
import com.estudos.app.Biblioteca.model.Status;
import com.estudos.app.Biblioteca.model.Usuario;
import com.estudos.app.Biblioteca.repository.EmprestimoRepository;
import com.estudos.app.Biblioteca.repository.LivroRepository;
import com.estudos.app.Biblioteca.repository.UsuarioRepository;
import com.estudos.app.Biblioteca.service.BibliotecaService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@ExtendWith(MockitoExtension.class)
public class BibliotecaServiceTeste {
    @Mock
    private LivroRepository livroRepository;

    @Mock
    private UsuarioRepository usuarioRepository;

    @Mock
    private EmprestimoRepository emprestimoRepository;

    @InjectMocks
    private BibliotecaService bibliotecaService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    void verificarRealizarEmprestimo() {
        String isbn = "12345";
        String idUsuario = "456422";
        Livro livro = new Livro(isbn, "titulo 1", "autor 1", 2005);
        Usuario usuario = new Usuario(idUsuario, "Matheus", "matheus@email.com");

        livro.setDisponivel(true);

        Mockito.when(livroRepository.acharPorIsbn(isbn)).thenReturn(Optional.of(livro));
        Mockito.when(usuarioRepository.acharPorId(idUsuario)).thenReturn(Optional.of(usuario));

        Emprestimo emprestimo = bibliotecaService.realizarEmprestimo(isbn, idUsuario);

        Assertions.assertNotNull(emprestimo);
        Assertions.assertEquals(livro, emprestimo.getLivro());
        Assertions.assertEquals(usuario, emprestimo.getUsuario());
        Assertions.assertEquals(Status.ATIVO, emprestimo.getStatus());
        Assertions.assertFalse(livro.isDisponivel());

        Mockito.verify(emprestimoRepository, Mockito.times(1)).salvar(emprestimo);
        Mockito.verify(livroRepository, Mockito.times(1)).acharPorIsbn(isbn);
    }

    @Test
    void verificarDevolverLivro() {
        String idEmprestimo = "987";
        Livro livro = new Livro("4321", "titulo 1", "autor 1", 2005);
        Usuario usuario = new Usuario("42233", "Matheus", "matheus@email.com");
        Emprestimo emprestimo = new Emprestimo(idEmprestimo, livro, usuario);

        livro.setDisponivel(false);

        Mockito.when(emprestimoRepository.acharPorId(idEmprestimo)).thenReturn(Optional.of(emprestimo));

        bibliotecaService.devolverLivro(idEmprestimo);

        Assertions.assertEquals(Status.DEVOLVIDO, emprestimo.getStatus());
        Assertions.assertTrue(livro.isDisponivel());
    }

    @Test
    void verificarBuscarLivrosDisponiveis() {

        bibliotecaService.buscarLivrosDisponiveis();

        Livro livro1 = new Livro("111", "Livro Disponível 1", "Autor 1", 2020);
        Livro livro2 = new Livro("222", "Livro Não Disponível", "Autor 2", 2018);
        Livro livro3 = new Livro("333", "Livro Disponível 2", "Autor 3", 2021);

        livro1.setDisponivel(true);
        livro2.setDisponivel(false);
        livro3.setDisponivel(true);

        List<Livro> todosOsLivros = Arrays.asList(livro1, livro2, livro3);

        Mockito.when(livroRepository.acharTodos()).thenReturn(todosOsLivros);

        List<Livro> livrosDisponiveis = bibliotecaService.buscarLivrosDisponiveis();

        List<Livro> expectedLivrosDisponiveis = todosOsLivros.stream()
                .filter(Livro::isDisponivel)
                .collect(Collectors.toList());

        Assertions.assertEquals(livrosDisponiveis.size(), expectedLivrosDisponiveis.size());
        Assertions.assertTrue(livrosDisponiveis.containsAll(expectedLivrosDisponiveis));
    }

    @Test
    void verificarEmprestimosAtivos() {
        Livro livro1 = new Livro("4321", "titulo 1", "autor 1", 2005);
        Livro livro2 = new Livro("4322", "titulo 2", "autor 2", 2006);
        Livro livro3 = new Livro("4323", "titulo 3", "autor 3", 2007);
        Livro livro4 = new Livro("4324", "titulo 4", "autor 4", 2008);
        Usuario usuario = new Usuario("42233", "Matheus", "matheus@email.com");
        Emprestimo emprestimo1 = new Emprestimo("234", livro1, usuario);
        Emprestimo emprestimo2 = new Emprestimo("235", livro2, usuario);
        Emprestimo emprestimo3 = new Emprestimo("236", livro3, usuario);
        Emprestimo emprestimo4 = new Emprestimo("237", livro4, usuario);

        emprestimo1.setStatus(Status.ATIVO);
        emprestimo2.setStatus(Status.ATRASADO);
        emprestimo3.setStatus(Status.ATIVO);
        emprestimo4.setStatus(Status.ATIVO);

        List<Emprestimo> todosEmprestimos = Arrays.asList(emprestimo1, emprestimo2, emprestimo3, emprestimo4);

        Mockito.when(bibliotecaService.consultarEmprestimosAtivos("42233")).thenReturn(todosEmprestimos);

        List<Emprestimo> emprestimosAtivos = bibliotecaService.consultarEmprestimosAtivos("42233");

        List<Emprestimo> expectedEmprestimosAtivos = todosEmprestimos.stream()
                .filter(e -> e.getStatus() == Status.ATIVO)
                .collect(Collectors.toList());


        Assertions.assertEquals(expectedEmprestimosAtivos.size(), emprestimosAtivos.size());
        Assertions.assertTrue(emprestimosAtivos.containsAll(expectedEmprestimosAtivos));
    }

    @Test
    void verificarRealizarEmprestimo_LivroNaoEncontrado(){
        String isbn = "1234";
        String idUsuario = "321";

        Mockito.when(livroRepository.acharPorIsbn(isbn)).thenReturn(Optional.empty());

        BibliotecaException exception = Assertions.assertThrows(BibliotecaException.class, () -> {
            bibliotecaService.realizarEmprestimo(isbn, idUsuario);
        });
        Assertions.assertEquals("Livro não encontrado!", exception.getMessage());
    }

    @Test
    void verificarRealizarEmprestimo_UsuarioNaoEncontrado(){
        String isbn = "1234";
        String idUsuario = "321";
        Livro livro = new Livro(isbn,"titulo 1", "autor 1", 2012);
        livro.setDisponivel(true);

        Mockito.when(livroRepository.acharPorIsbn(isbn)).thenReturn(Optional.of(livro));
        Mockito.when(usuarioRepository.acharPorId(idUsuario)).thenReturn(Optional.empty());

        BibliotecaException exception = Assertions.assertThrows(BibliotecaException.class, () -> {
            bibliotecaService.realizarEmprestimo(isbn,idUsuario);
        });
        Assertions.assertEquals("Usuario não encontrado!", exception.getMessage());
    }


    @Test
    void verificarRealizarEmprestimo_LivroIndisponivel(){
        String isbn = "12345";
        String idUsuario = "456422";
        Livro livro = new Livro(isbn, "titulo 1", "autor 1", 2005);
        Usuario usuario = new Usuario(idUsuario, "Matheus", "matheus@email.com");

        livro.setDisponivel(false);


        Mockito.when(livroRepository.acharPorIsbn(isbn)).thenReturn(Optional.of(livro));
        Mockito.when(usuarioRepository.acharPorId(idUsuario)).thenReturn(Optional.of(usuario));

        BibliotecaException exception = Assertions.assertThrows(BibliotecaException.class, () ->{
            bibliotecaService.realizarEmprestimo(isbn, idUsuario);
        });
        Assertions.assertEquals("Livro não esta mais disponivel", exception.getMessage());
    }

    @Test
    void verificarDevolverLivro_EmprestimoNaoEncontrado(){
        String idEmprestimo = "987";

        Mockito.when(emprestimoRepository.acharPorId(idEmprestimo)).thenReturn(Optional.empty());

        BibliotecaException exception = Assertions.assertThrows(BibliotecaException.class, () -> {
            bibliotecaService.devolverLivro(idEmprestimo);
        });
        Assertions.assertEquals("Empréstimo não encontrado!", exception.getMessage());
    }

    @Test
    void verificarDevolverLivro_LivroJaDevolvido(){
        String idEmprestimo = "987";
        Livro livro = new Livro("4321", "titulo 1", "autor 1", 2005);
        Usuario usuario = new Usuario("42233", "Matheus", "matheus@email.com");
        Emprestimo emprestimo = new Emprestimo(idEmprestimo, livro, usuario);
        emprestimo.setStatus(Status.DEVOLVIDO);

        Mockito.when(emprestimoRepository.acharPorId(idEmprestimo)).thenReturn(Optional.of(emprestimo));

        BibliotecaException exception = Assertions.assertThrows(BibliotecaException.class, () ->{
            bibliotecaService.devolverLivro(idEmprestimo);
        });
        Assertions.assertEquals("Livro já foi devolvido", exception.getMessage());
    }
}

