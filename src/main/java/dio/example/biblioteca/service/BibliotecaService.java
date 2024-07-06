package dio.example.biblioteca.service;

import dio.example.biblioteca.model.Cliente;
import dio.example.biblioteca.model.Emprestimo;
import dio.example.biblioteca.model.Livro;

import java.time.LocalDate;
import java.util.List;

public interface BibliotecaService {
    List<Livro> listarLivrosDisponiveis();
    List<Cliente> listarClientes();
    List<Emprestimo> listarEmprestimos();
    void emprestarLivro(Long idCliente, Long idLivro, LocalDate dataEmprestimo);
    Emprestimo buscarEmprestimoPorCliente(Long id);
    Emprestimo buscarEmprestimoPorLivro(Livro livro);
    void prorrogarEmprestimo(Long id);
    void devolverLivro(Long id);
}
