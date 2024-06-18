package com.totvs.backend.service;

import com.totvs.backend.exceptions.NegocioException;
import com.totvs.backend.repository.TelefoneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.totvs.backend.util.RegexValidator.isTelefoneValido;

/**
 * Service que define operações relacionadas a Telefone.
 */
@Service
public class TelefoneService {

    @Autowired
    private TelefoneRepository telefoneRepository;

    /**
     * Valida se o formato do telefone é válido e se não está duplicado no sistema.
     *
     * @param telefone Número de telefone a ser validado.
     * @throws NegocioException se o formato do telefone for inválido ou se já estiver cadastrado.
     */
    public void validaCadastroTelefone(String telefone){
        if(!isTelefoneValido(telefone)){
            throw new NegocioException("Formato de telefone inválido");
        } else if (!validaTelefoneDuplicado(telefone)) {
            throw new NegocioException("Não é possível cadastrar esse número de telefone," +
                    " tente outra opção ou entre em contato com o administrador do sistema");
        }
    }

    /**
     * Verifica se o telefone já está cadastrado no sistema.
     *
     * @param telefone Número de telefone a ser verificado.
     * @return true se o telefone não estiver duplicado, false caso contrário.
     */
    private Boolean validaTelefoneDuplicado(String telefone){
        return telefoneRepository.findByTelefone(telefone).isEmpty();
    }
}
