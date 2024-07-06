package dio.example.biblioteca.repository;

import dio.example.biblioteca.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
}
