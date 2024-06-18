package com.totvs.backend.service;

import com.totvs.backend.exceptions.NegocioException;
import com.totvs.backend.exceptions.ObjetoNaoEncontradoException;
import com.totvs.backend.mapper.ClienteMapper;
import com.totvs.backend.model.cliente.Cliente;
import com.totvs.backend.model.cliente.dto.ClienteRequestDTO;
import com.totvs.backend.model.cliente.dto.ClienteResponseDTO;
import com.totvs.backend.model.telefone.dto.TelefoneRequestDTO;
import com.totvs.backend.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;

import static com.totvs.backend.util.RegexValidator.TAMANHO_DEZ;
import static com.totvs.backend.util.RegexValidator.validaTamanhoTexto;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private TelefoneService telefoneService;

    @Autowired
    private ClienteMapper mapper;

    @Transactional
    public void cadastrarCliente(ClienteRequestDTO clienteDTO) {
        validaCamposCliente(clienteDTO);
        Cliente cliente = mapper.requestDtoToEntity(clienteDTO);
        cliente.setStatus(true);
        clienteRepository.save(cliente);
    }

    public List<ClienteResponseDTO> buscarClientes(){
        return mapper.entitiyListToResponseDtoList(clienteRepository.findAllClientes());
    }

    public ClienteResponseDTO buscarClientePorId(Long id){
        Cliente cliente = clienteRepository.findClienteById(id)
                .orElseThrow(()-> new ObjetoNaoEncontradoException("Cliente não encontrado"));
        return mapper.entityToResponseDTO(cliente);
    }

    public void softDeleteCliente(Long id) {
        Cliente cliente = clienteRepository.findClienteById(id)
                .orElseThrow(() -> new ObjetoNaoEncontradoException("Cliente não encontrado"));

        cliente.setStatus(false);
        clienteRepository.save(cliente);
    }

    @Transactional
    public ClienteResponseDTO atualizarCliente(Long id, ClienteRequestDTO clienteDTO) {
        Cliente clienteExistente = clienteRepository.findById(id)
                .orElseThrow(() -> new ObjetoNaoEncontradoException("Cliente não encontrado"));

        inativaRelacionamentos(clienteExistente, clienteDTO);

        Cliente clienteAtualizado = atualizarDadosCliente(clienteDTO, clienteExistente);

        return mapper.entityToResponseDTO(clienteAtualizado);
    }

    private void inativaRelacionamentos(Cliente clienteExistente, ClienteRequestDTO clienteDTO) {
        if (!Objects.isNull(clienteDTO.getEndereco())) {
            clienteExistente.getEndereco().setStatus(false);
        }
        if (!Objects.isNull(clienteDTO.getTelefones())) {
            clienteExistente.getTelefones().forEach(telefone -> telefone.setStatus(false));
        }
    }

    private Cliente atualizarDadosCliente(ClienteRequestDTO clienteDTO, Cliente clienteExistente) {
        Cliente clienteAtualizado = mapper.requestDtoToEntity(clienteDTO);
        clienteAtualizado.setId(clienteExistente.getId());
        clienteAtualizado.setStatus(clienteExistente.getStatus());
        clienteRepository.save(clienteAtualizado);
        return clienteAtualizado;
    }

    private void validaCamposCliente(ClienteRequestDTO clienteDTO){
        validaNome(clienteDTO.getNome());
        validaTelefone(clienteDTO);
    }
    private void validaNome(String nome){
        validaNomeDuplicado(nome);
        if(!validaTamanhoTexto(nome,TAMANHO_DEZ)){
            throw new NegocioException("O nome não pode ter mais que 10 caracteres");
        }
    }

    private void validaNomeDuplicado(String nome){
        if(!clienteRepository.findByNome(nome).isEmpty()){
            throw new NegocioException("Não é possível cadastrar esse nome, " +
                                       "tente outra opção ou entre em contato com o administrador do sistema");
        }
    }

    private void validaTelefone(ClienteRequestDTO clienteDTO) {
        List<TelefoneRequestDTO> telefones = clienteDTO.getTelefones();
        for (TelefoneRequestDTO telefoneDTO : telefones) {
            telefoneService.validaCadastroTelefone(telefoneDTO.getTelefone());
        }
    }




}
