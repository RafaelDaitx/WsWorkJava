package br.com.rafaeldaitx.ProjetoCarro.model;

import jakarta.persistence.*;
import lombok.Data;
@Entity
@Data
@Table(name = "marca")
public class Marca {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome_marca")
    private String nomeMarca;
}
