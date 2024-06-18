package com.totvs.backend.model.endereco.dto;

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

    private String logradouro;

    private String bairro;

    private String numero;

    private String cidade;

    private String estado;

    }