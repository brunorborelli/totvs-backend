package com.totvs.backend.service;

import com.totvs.backend.exceptions.NegocioException;
import com.totvs.backend.repository.TelefoneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.totvs.backend.util.RegexValidator.isTelefoneValido;

@Service
public class TelefoneService {

    @Autowired
    private TelefoneRepository telefoneRepository;


    public void validaCadastroTelefone(String telefone){
        if(!isTelefoneValido(telefone)){
            throw new NegocioException("Formato de telefone inválido");
        } else if (!validaTelefoneDuplicado(telefone)) {
            throw new NegocioException("Não é possível cadastrar esse número de telefone," +
                    " tente outra opção ou entre em contato com o administrador do sistema");
        }
    }

    private Boolean validaTelefoneDuplicado(String telefone){
        return telefoneRepository.findByTelefone(telefone).isEmpty();
    }
}
