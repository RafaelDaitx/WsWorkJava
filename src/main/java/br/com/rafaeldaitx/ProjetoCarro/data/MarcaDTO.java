package br.com.rafaeldaitx.ProjetoCarro.data;


import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonPropertyOrder({"nomeMarca"})
public class MarcaDTO {
    private String nomeMarca;

    public String getNomeMarca() {
        return nomeMarca;
    }

    public void setNomeMarca(String nomeMarca) {
        this.nomeMarca = nomeMarca;
    }

    public MarcaDTO(String nomeMarca) {
        this.nomeMarca = nomeMarca;
    }

}
