package br.com.rafaeldaitx.ProjetoCarro.model;


import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "Modelo")
public class Modelo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome")
    private String nome;

    @Column(name = "valor_fipe")
    private Double valor_fipe;

    @ManyToOne
    @JoinColumn(name = "marca_id")
    private Marca marca_id;
}
