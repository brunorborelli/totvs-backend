package com.totvs.backend.mapper;


import com.totvs.backend.model.cliente.Cliente;
import com.totvs.backend.model.cliente.dto.ClienteRequestDTO;
import com.totvs.backend.model.cliente.dto.ClienteResponseDTO;
import com.totvs.backend.model.telefone.Telefone;
import com.totvs.backend.model.telefone.dto.TelefoneResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Classe responsável por realizar o mapeamento entre entidades Cliente e DTOs relacionados.
 */
@Component
public class ClienteMapper {

   @Autowired
   private EnderecoMapper enderecoMapper;

   @Autowired
   private TelefoneMapper telefoneMapper;

   /**
    * Converte um ClienteRequestDTO em uma entidade Cliente.
    *
    * @param dto ClienteRequestDTO a ser convertido.
    * @return Cliente convertido a partir do DTO.
    */
   public Cliente requestDtoToEntity(ClienteRequestDTO dto) {
      if (dto == null) {
         return null;
      }

      Cliente cliente = new Cliente();
      cliente.setNome(dto.getNome());
      cliente.setEndereco(enderecoMapper.requestDtoToEntity(dto.getEndereco()));

      if (dto.getTelefones() != null) {
         List<Telefone> telefones = dto.getTelefones().stream()
                 .map(telefoneMapper::requestDtoToEntity)
                 .collect(Collectors.toList());
         telefones.forEach(telefone -> telefone.setCliente(cliente));
         cliente.setTelefones(telefones);
      }

      return cliente;
   }

   /**
    * Converte uma entidade Cliente em um ClienteResponseDTO.
    *
    * @param entity Entidade Cliente a ser convertida.
    * @return ClienteResponseDTO convertido a partir da entidade.
    */
   public ClienteResponseDTO entityToResponseDTO(Cliente entity) {
      if (entity == null) {
         return null;
      }

      ClienteResponseDTO dto = new ClienteResponseDTO();
      dto.setNome(entity.getNome());
      dto.setEndereco(enderecoMapper.entityToResponseDTO(entity.getEndereco()));

      if (entity.getTelefones() != null) {
         List<TelefoneResponseDTO> telefones = entity.getTelefones().stream()
                 .map(telefoneMapper::entityToResponseDTO)
                 .collect(Collectors.toList());
         dto.setTelefones(telefones);
      }

      return dto;
   }

   /**
    * Converte uma lista de entidades Cliente em uma lista de ClienteResponseDTOs.
    *
    * @param entities Lista de entidades Cliente a ser convertida.
    * @return Lista de ClienteResponseDTOs convertida a partir da lista de entidades.
    */
   public List<ClienteResponseDTO> entitiyListToResponseDtoList(List<Cliente> entities) {
      if (entities == null) {
         return Collections.emptyList();
      }
      return entities.stream()
              .map(this::entityToResponseDTO)
              .collect(Collectors.toList());
   }



}
