package dio.example.biblioteca.service.impl;

import dio.example.biblioteca.model.Cliente;
import dio.example.biblioteca.model.Emprestimo;
import dio.example.biblioteca.model.Livro;
import dio.example.biblioteca.repository.ClienteRepository;
import dio.example.biblioteca.repository.EmprestimoRepository;
import dio.example.biblioteca.repository.LivroRepository;
import dio.example.biblioteca.service.BibliotecaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class BibliotecaServiceImpl implements BibliotecaService {

    @Autowired
    private EmprestimoRepository emprestimoRepository;
    @Autowired
    private LivroRepository livroRepository;
    @Autowired
    private ClienteRepository clienteRepository;

    @Override
    public List<Livro> listarLivrosDisponiveis() {
        List<Livro> allLivros = livroRepository.findAll();
        List<Livro> emprestados = emprestimoRepository.findAll().stream()
                .map(Emprestimo::getLivro)
                .toList();

        return allLivros.stream()
                .filter(livro -> !emprestados.contains(livro))
                .toList();
    }

    @Override
    public List<Cliente> listarClientes() {
        return clienteRepository.findAll();
    }

    @Override
    public List<Emprestimo> listarEmprestimos() {
        return emprestimoRepository.findAll();
    }

    @Override
    public void emprestarLivro(Long idCliente, Long idLivro, LocalDate dataEmprestimo) {
        Cliente cliente = clienteRepository.findById(idCliente)
                .orElseThrow(() -> new IllegalArgumentException("Cliente não encontrado"));
        Livro livro = livroRepository.findById(idLivro).orElseThrow(() -> new IllegalArgumentException("Livro não encontrado"));
        if(buscarEmprestimoPorCliente(idCliente) != null){
            throw new IllegalArgumentException("Cliente já possui um empréstimo ativo");
        }
        if( buscarEmprestimoPorLivro(livro) != null){
            throw new IllegalArgumentException("Livro já está emprestado");
        }
        Emprestimo emprestimo = new Emprestimo();
        emprestimo.setCliente(cliente);
        emprestimo.setLivro(livro);
        emprestimo.setDataEmprestimo(dataEmprestimo);
        emprestimoRepository.save(emprestimo);

    }

    @Override
    public Emprestimo buscarEmprestimoPorCliente(Long id) {
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Cliente não encontrado"));
        return emprestimoRepository.findAll().stream()
                .filter(emprestimo -> emprestimo.getCliente().equals(cliente))
                .findFirst()
                .orElse(null);
    }

    @Override
    public Emprestimo buscarEmprestimoPorLivro(Livro livro) {
        return emprestimoRepository.findAll().stream()
                .filter(emprestimo -> emprestimo.getLivro().equals(livro))
                .findFirst()
                .orElse(null);
    }

    @Override
    public void prorrogarEmprestimo(Long id) {
        Emprestimo emprestimo = emprestimoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Empréstimo não encontrado"));
        if (emprestimo == null) {
            throw new IllegalArgumentException("Empréstimo não encontrado");
        }
        if (emprestimo.getDataDevolucao().isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("Empréstimo está atrasado, não é possível prorrogar");
        }
        emprestimo.setDataDevolucao(emprestimo.getDataDevolucao().plusDays(7));
        emprestimoRepository.save(emprestimo);
    }

    @Override
    public void devolverLivro(Long id) {
        Emprestimo emprestimo = emprestimoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Empréstimo não encontrado"));
        if (emprestimo == null) {
            throw new IllegalArgumentException("Empréstimo não encontrado");
        }
        emprestimoRepository.delete(emprestimo);
    }
}
