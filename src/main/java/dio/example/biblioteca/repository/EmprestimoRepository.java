package dio.example.biblioteca.repository;

import dio.example.biblioteca.model.Emprestimo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmprestimoRepository extends JpaRepository<Emprestimo, Long>{
}
