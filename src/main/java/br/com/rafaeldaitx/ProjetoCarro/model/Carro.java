package br.com.rafaeldaitx.ProjetoCarro.model;

import jakarta.persistence.*;

import java.util.Date;
import java.util.List;
import lombok.Data;

@Entity
@Data
@Table(name = "carro")
public class Carro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "timestamp_cadastro")
    private Date timestamp_cadastro;

    @Column(name = "ano")
    private int ano;

    @Column(name = "combustivel")
    private String combustivel;

    @Column(name = "num_portas")
    private int num_portas;

    @Column(name = "cor")
    private String cor;

    @ManyToOne
    @JoinColumn(name = "modelo_id")
    private Modelo modelo;
}
