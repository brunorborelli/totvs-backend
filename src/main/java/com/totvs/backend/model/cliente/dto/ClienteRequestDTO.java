package com.totvs.backend.model.cliente.dto;

import com.totvs.backend.model.endereco.dto.EnderecoRequestDTO;
import com.totvs.backend.model.telefone.dto.TelefoneRequestDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ClienteRequestDTO {

    private String nome;
    private EnderecoRequestDTO endereco;
    private List<TelefoneRequestDTO> telefones;
}