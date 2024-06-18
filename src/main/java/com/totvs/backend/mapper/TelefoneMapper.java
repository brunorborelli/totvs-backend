package com.totvs.backend.mapper;


import com.totvs.backend.model.telefone.Telefone;
import com.totvs.backend.model.telefone.dto.TelefoneRequestDTO;
import com.totvs.backend.model.telefone.dto.TelefoneResponseDTO;
import org.springframework.stereotype.Component;

/**
 * Classe responsável por realizar o mapeamento entre entidades Telefone e seus DTOs relacionados.
 */
@Component
public class TelefoneMapper {

   /**
    * Converte um TelefoneRequestDTO em uma entidade Telefone.
    *
    * @param dto TelefoneRequestDTO a ser convertido.
    * @return Telefone convertido a partir do DTO.
    */
   public Telefone requestDtoToEntity(TelefoneRequestDTO dto) {
      if (dto == null) {
         return null;
      }

      Telefone telefone = new Telefone();
      telefone.setTelefone(dto.getTelefone());

      return telefone;
   }

   /**
    * Converte uma entidade Telefone em um TelefoneResponseDTO.
    *
    * @param entity Entidade Telefone a ser convertida.
    * @return TelefoneResponseDTO convertido a partir da entidade.
    */
   public TelefoneResponseDTO entityToResponseDTO(Telefone entity) {
      if (entity == null) {
         return null;
      }

      TelefoneResponseDTO dto = new TelefoneResponseDTO();
      dto.setTelefone(entity.getTelefone());

      return dto;
   }

}
