package dio.example.biblioteca;

import dio.example.biblioteca.model.Cliente;
import dio.example.biblioteca.model.Emprestimo;
import dio.example.biblioteca.model.Livro;
import dio.example.biblioteca.repository.ClienteRepository;
import dio.example.biblioteca.repository.EmprestimoRepository;
import dio.example.biblioteca.repository.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Arrays;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private LivroRepository livroRepository;

    @Autowired
    private EmprestimoRepository emprestimoRepository;

    @Override
    public void run(String... args) throws Exception {
        Cliente cliente1 = new Cliente();
        cliente1.setNome("João Silva");
        cliente1.setTelefone("123456789");
        Cliente cliente2 = new Cliente();
        cliente2.setNome("Maria Oliveira");
        cliente2.setTelefone("987654321");

        Livro livro1 = new Livro();
        livro1.setTitulo("Dom Casmurro");
        livro1.setAutor("Machado de Assis");
        Livro livro2 = new Livro();
        livro2.setTitulo("O Alquimista");
        livro2.setAutor("Paulo Coelho");

        clienteRepository.saveAll(Arrays.asList(cliente1, cliente2));
        livroRepository.saveAll(Arrays.asList(livro1, livro2));

        Emprestimo emprestimo1 = new Emprestimo();
        emprestimo1.setCliente(cliente1);
        emprestimo1.setLivro(livro1);
        emprestimo1.setDataEmprestimo(LocalDate.now());

        emprestimoRepository.save(emprestimo1);
    }

}
