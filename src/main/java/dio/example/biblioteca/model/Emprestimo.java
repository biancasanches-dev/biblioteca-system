package dio.example.biblioteca.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
public class Emprestimo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    @OneToOne
    @JoinColumn(name = "livro_id")
    private Livro livro;

    private LocalDate dataEmprestimo;
    private LocalDate dataDevolucao;

    public void setDataEmprestimo(LocalDate dataEmprestimo) {
        this.dataEmprestimo = dataEmprestimo;
        this.dataDevolucao = dataEmprestimo.plusDays(7);
    }

    @Override
    public String toString() {
        return "Emprestimo{" +
                "cliente=" + cliente +
                ", livro=" + livro +
                ", Data de emprestimo=" + dataEmprestimo +
                ", Data de devolucao=" + dataDevolucao +
                '}';
    }
}
