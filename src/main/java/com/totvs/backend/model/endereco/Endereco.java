package com.totvs.backend.model.endereco;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


/**
 * Entidade que representa um endereço.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
public class Endereco {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String logradouro;
    private String bairro;
    private String numero;
    private String cidade;
    private String estado;
    private boolean status = true;
}
