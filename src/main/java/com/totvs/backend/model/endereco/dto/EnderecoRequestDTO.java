package com.totvs.backend.model.endereco.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EnderecoRequestDTO {

    @Schema(type = "string", example = "Rua 200")
    private String logradouro;

    @Schema(type = "string", example = "Setor Teste")
    private String bairro;

    @Schema(type = "string", example = "55")
    private String numero;

    @Schema(type = "string", example = "Goiânia")
    private String cidade;

    @Schema(type = "string", example = "GO")
    private String estado;

    }