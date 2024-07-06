    package dio.example.biblioteca.controller;

    import dio.example.biblioteca.model.Cliente;
    import dio.example.biblioteca.model.Emprestimo;
    import dio.example.biblioteca.model.Livro;
    import dio.example.biblioteca.service.impl.BibliotecaServiceImpl;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.http.ResponseEntity;
    import org.springframework.web.bind.annotation.*;

    import java.time.LocalDate;
    import java.util.List;
    import java.util.Objects;

    @RestController
    public class BibliotecaController {

        @Autowired
        private BibliotecaServiceImpl bibliotecaService;

        @GetMapping
        public String home() {
            return "Bem-vindo à biblioteca!";
        }

        @GetMapping("/livros")
        public List<Livro> biblioteca() {
            return bibliotecaService.listarLivrosDisponiveis();
        }

        @PostMapping("/emprestar")
        public ResponseEntity<String> emprestarLivro(@RequestParam Long idCliente, @RequestParam Long idLivro) {
            try {
                bibliotecaService.emprestarLivro(idCliente, idLivro, LocalDate.now());
                return ResponseEntity.ok("Empréstimo realizado com sucesso");
            } catch (IllegalArgumentException e) {
                return ResponseEntity.badRequest().body(e.getMessage());
            }
        }

        @GetMapping("/clientes")
        public List<Cliente> listarClientes() {
            return bibliotecaService.listarClientes();
        }

        @GetMapping("/clientes/emprestimo/{id}")
        public ResponseEntity<?> buscarEmprestimoPorCliente(@PathVariable Long id) {
            Emprestimo emprestimo = bibliotecaService.buscarEmprestimoPorCliente(id);
            return ResponseEntity.ok(Objects.requireNonNullElse(emprestimo, "Cliente não possui empréstimos"));
        }

        @GetMapping("/emprestimos")
        public List<Emprestimo> listarEmprestimos() {
            return bibliotecaService.listarEmprestimos();
        }

        @PostMapping("/emprestimos/prorrogar/{id}")
        public ResponseEntity<String> prorrogarEmprestimo(@PathVariable Long id) {
            try {
                bibliotecaService.prorrogarEmprestimo(id);
                return ResponseEntity.ok("Empréstimo prorrogado com sucesso");
            } catch (IllegalArgumentException e) {
                return ResponseEntity.badRequest().body(e.getMessage());
            }
        }

        @PostMapping("/devolver/{id}")
        public ResponseEntity<String> devolverLivro(@PathVariable Long id) {
            try {
                bibliotecaService.devolverLivro(id);
                return ResponseEntity.ok("Livro devolvido com sucesso");
            } catch (IllegalArgumentException e) {
                return ResponseEntity.badRequest().body(e.getMessage());
            }
        }
    }
