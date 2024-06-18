package com.totvs.backend.mapper;

import com.totvs.backend.model.endereco.Endereco;
import com.totvs.backend.model.endereco.dto.EnderecoRequestDTO;
import com.totvs.backend.model.endereco.dto.EnderecoResponseDTO;
import org.springframework.stereotype.Component;

/**
 * Classe responsável por realizar o mapeamento entre entidades Endereco e seus DTOs relacionados.
 */
@Component
public class EnderecoMapper {

    /**
     * Converte um EnderecoRequestDTO em uma entidade Endereco.
     *
     * @param dto EnderecoRequestDTO a ser convertido.
     * @return Endereco convertido a partir do DTO.
     */
      public Endereco requestDtoToEntity(EnderecoRequestDTO dto) {
        if (dto == null) {
            return null;
        }

        Endereco endereco = new Endereco();
        endereco.setLogradouro(dto.getLogradouro());
        endereco.setBairro(dto.getBairro());
        endereco.setNumero(dto.getNumero());
        endereco.setCidade(dto.getCidade());
        endereco.setEstado(dto.getEstado());

        return endereco;
    }

    /**
     * Converte uma entidade Endereco em um EnderecoResponseDTO.
     *
     * @param entity Entidade Endereco a ser convertida.
     * @return EnderecoResponseDTO convertido a partir da entidade.
     */
    public EnderecoResponseDTO entityToResponseDTO(Endereco entity) {
        if (entity == null) {
            return null;
        }

        EnderecoResponseDTO dto = new EnderecoResponseDTO();
        dto.setLogradouro(entity.getLogradouro());
        dto.setBairro(entity.getBairro());
        dto.setNumero(entity.getNumero());
        dto.setCidade(entity.getCidade());
        dto.setEstado(entity.getEstado());

        return dto;
    }

}
