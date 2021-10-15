package com.junieldantas.ediaristas.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.junieldantas.ediaristas.models.Diarista;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DiaristasPagedResponse {

    private List<Diarista> diaristas;

    @JsonProperty("quantidade_diaristas")
    private Long quantidadeDiaristas;
}
