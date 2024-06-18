package com.totvs.backend.service;

import com.totvs.backend.exceptions.NegocioException;
import com.totvs.backend.mapper.TelefoneMapper;
import com.totvs.backend.repository.TelefoneRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static com.totvs.backend.stub.TelefoneStub.getTelefone1;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

/**
 * Testes Unitarios for {@link TelefoneService}.
 */
@ExtendWith(MockitoExtension.class)
public class TelefoneServiceTest {

    @InjectMocks
    TelefoneService telefoneService;

    @Mock
    TelefoneRepository telefoneRepository;

    @Mock
    TelefoneMapper telefoneMapper;

    /**
     * Testa o método validaCadastroTelefone() da classe TelefoneService quando um telefone inválido é fornecido.
     * Deve lançar NegocioException com a mensagem apropriada.
     */
    @Test
    void deveRetornarExecacao_TelefoneInvalido(){
        NegocioException exception = assertThrows(NegocioException.class, () -> {
            telefoneService.validaCadastroTelefone("555");
        });
        assertEquals("Formato de telefone inválido", exception.getMessage());
    }

    /**
     * Testa o método validaCadastroTelefone() da classe TelefoneService quando um telefone duplicado é fornecido.
     * Deve lançar NegocioException com a mensagem apropriada.
     */
    @Test
    void deveRetornarExecacao_TelefoneDuplicado() {
        when(telefoneRepository.findByTelefone("5562982971994"))
                .thenReturn(List.of(getTelefone1()));
        NegocioException exception = assertThrows(NegocioException.class, () -> {
            telefoneService.validaCadastroTelefone("5562982971994");
        });
        assertEquals("Não é possível cadastrar esse número de telefone," +
                " tente outra opção ou entre em contato com o administrador do sistema", exception.getMessage());
    }
}
