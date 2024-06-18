package com.totvs.backend.service;

import com.totvs.backend.exceptions.NegocioException;
import com.totvs.backend.mapper.ClienteMapper;
import com.totvs.backend.mapper.EnderecoMapper;
import com.totvs.backend.mapper.TelefoneMapper;
import com.totvs.backend.model.cliente.Cliente;
import com.totvs.backend.model.cliente.dto.ClienteRequestDTO;
import com.totvs.backend.model.cliente.dto.ClienteResponseDTO;
import com.totvs.backend.repository.ClienteRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static com.totvs.backend.stub.ClienteStub.getCliente;
import static com.totvs.backend.stub.ClienteStub.getClienteRequestDTO;
import static com.totvs.backend.stub.ClienteStub.getClienteResponseDTO;
import static com.totvs.backend.stub.ClienteStub.getClienteValidoRequestDTO;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ClienteServiceTest {

    @InjectMocks
    ClienteService clienteService;
    @Mock
    ClienteRepository clienteRepository;
    @Mock
    ClienteMapper clienteMapper;

    @Mock
    EnderecoMapper enderecoMapper;

    @Mock
    TelefoneMapper telefoneMapper;

    @Mock
    TelefoneService telefoneService;

    @Test
    void deveCadastrarCliente(){
        when(clienteMapper.requestDtoToEntity(any(ClienteRequestDTO.class)))
                .thenReturn(getCliente());
        clienteService.cadastrarCliente(getClienteValidoRequestDTO());
        verify(clienteRepository, times(1)).save(any(Cliente.class));
    }
    @Test
    void deveRetornarExecacao_NomeDuplicado(){
        when(clienteRepository.findByNome("João da Silva"))
                .thenReturn(List.of(getCliente()));
        NegocioException exception = assertThrows(NegocioException.class, () -> {
            clienteService.cadastrarCliente(getClienteRequestDTO());
        });
        assertEquals("Não é possível cadastrar esse nome," +
                " tente outra opção ou entre em contato com o administrador do sistema", exception.getMessage());
        verify(clienteRepository, times(0)).save(any(Cliente.class));
    }

    @Test
    void deveRetornarExecacao_NomeInvalido(){
        NegocioException exception = assertThrows(NegocioException.class, () -> {
            clienteService.cadastrarCliente(getClienteRequestDTO());
        });
        assertEquals("O nome não pode ter mais que 10 caracteres", exception.getMessage());
        verify(clienteRepository, times(0)).save(any(Cliente.class));
    }

    @Test
    void deveRetornarClientes(){
        when(clienteRepository.findAllClientes()).thenReturn(List.of(getCliente()));
        List<ClienteResponseDTO> clienteResponseDTOS = clienteService.buscarClientes();
        Assertions.assertNotNull(clienteResponseDTOS);

    }

    @Test
    void deveBuscarClientePorId(){
        when(clienteRepository.findClienteById(1L))
                .thenReturn(Optional.ofNullable(getCliente()));
        when(clienteMapper.entityToResponseDTO(getCliente()))
                .thenReturn(getClienteResponseDTO());
        ClienteResponseDTO clienteResponseDTO = clienteService.buscarClientePorId(1L);
        Assertions.assertNotNull(clienteResponseDTO);
    }

    @Test
    void deveAtualizarCliente(){
        when(clienteRepository.findById(1L))
                .thenReturn(Optional.ofNullable(getCliente()));
        when(clienteMapper.entityToResponseDTO(getCliente()))
                .thenReturn(getClienteResponseDTO());
        when(clienteMapper.requestDtoToEntity(any(ClienteRequestDTO.class)))
                .thenReturn(getCliente());
        clienteService.atualizarCliente(1L, getClienteRequestDTO());
        verify(clienteRepository, times(1)).save(any(Cliente.class));
    }

}