package br.com.rafaeldaitx.ProjetoCarro.data;


import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonPropertyOrder({"id", "timestamp_cadastro", "modelo_id", "ano", "combustivel", "num_portas", "cor", "nome_modelo", "valor"})
public class CarroDTO {
    private Long id;
    private Long timestamp_cadastro;
    private Long modelo_id;
    private int ano;
    private String combustivel;
    private int num_portas;
    private String cor;
    private String nome_modelo;
    private Double valor;

    // Getters and setters

    public CarroDTO(
            Long id,
            Long timestamp_cadastro,
            Long modelo_id,
            int ano,
            String combustivel,
            int num_portas,
            String cor,
            String nome_modelo,
            Double valor) {
        this.id = id;
        this.timestamp_cadastro = timestamp_cadastro;
        this.modelo_id = modelo_id;
        this.ano = ano;
        this.combustivel = combustivel;
        this.num_portas = num_portas;
        this.cor = cor;
        this.nome_modelo = nome_modelo;
        this.valor = valor;
    }

    // Getters and Setters
}
