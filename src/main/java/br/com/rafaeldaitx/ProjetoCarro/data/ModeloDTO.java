package br.com.rafaeldaitx.ProjetoCarro.data;


import br.com.rafaeldaitx.ProjetoCarro.model.Marca;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonPropertyOrder({"id", "nome", "modelo_id", "valor_fipe","marca_id" ,"nomeMarca"})
public class ModeloDTO {
    private Long id;
    private String nome;
    private Double valor_fipe;
    private Long marca_id;
    private String nomeMarca;

    public ModeloDTO(Long id, String nome, Double valor_fipe, Long marca_id, String nomeMarca) {
        this.id = id;
        this.nome = nome;
        this.valor_fipe = valor_fipe;
        this.marca_id = marca_id;
        this.nomeMarca = nomeMarca;
    }




}
