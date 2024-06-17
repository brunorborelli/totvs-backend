package com.totvs.backend.model.telefone.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TelefoneRequestDTO {
    private String codigoDiscagem;
    private String ddd;
    private String telefone;
}