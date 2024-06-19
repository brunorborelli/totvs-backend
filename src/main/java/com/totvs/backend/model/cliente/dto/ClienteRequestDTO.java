package com.totvs.backend.model.cliente.dto;

import com.totvs.backend.model.endereco.dto.EnderecoRequestDTO;
import com.totvs.backend.model.telefone.dto.TelefoneRequestDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ClienteRequestDTO {

    @Schema(type = "string", example = "Bruno")
    private String nome;
    private EnderecoRequestDTO endereco;
    private List<TelefoneRequestDTO> telefones;
}