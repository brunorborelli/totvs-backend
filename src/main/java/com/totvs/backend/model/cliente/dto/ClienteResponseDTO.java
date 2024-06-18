package com.totvs.backend.model.cliente.dto;

import com.totvs.backend.model.endereco.dto.EnderecoRequestDTO;
import com.totvs.backend.model.endereco.dto.EnderecoResponseDTO;
import com.totvs.backend.model.telefone.dto.TelefoneRequestDTO;
import com.totvs.backend.model.telefone.dto.TelefoneResponseDTO;
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
public class ClienteResponseDTO {

    private String nome;
    private EnderecoResponseDTO endereco;
    private List<TelefoneResponseDTO> telefones;
}