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


/**
 * Service que define operações relacionadas a clientes.
 */
@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private TelefoneService telefoneService;

    @Autowired
    private ClienteMapper mapper;

    /**
     * Cadastra um novo cliente.
     *
     * @param clienteDTO Dados do cliente a ser cadastrado.
     * @throws NegocioException se houver problemas na validação dos campos.
     */
    @Transactional
    public void cadastrarCliente(ClienteRequestDTO clienteDTO) {
        validaCamposCliente(clienteDTO);
        Cliente cliente = mapper.requestDtoToEntity(clienteDTO);
        cliente.setStatus(true);
        clienteRepository.save(cliente);
    }

    /**
     * Busca todos os clientes ativos.
     *
     * @return Lista de DTOs de clientes ativos.
     */
    public List<ClienteResponseDTO> buscarClientes(){
        return mapper.entitiyListToResponseDtoList(clienteRepository.findAllClientes());
    }

    /**
     * Busca um cliente ativo pelo ID.
     *
     * @param id ID do cliente a ser buscado.
     * @return DTO do cliente encontrado.
     * @throws ObjetoNaoEncontradoException se o cliente não for encontrado.
     */
    public ClienteResponseDTO buscarClientePorId(Long id){
        Cliente cliente = clienteRepository.findClienteById(id)
                .orElseThrow(()-> new ObjetoNaoEncontradoException("Cliente não encontrado"));
        return mapper.entityToResponseDTO(cliente);
    }

    /**
     * Marca um cliente como deletado (soft delete) pelo ID.
     *
     * @param id ID do cliente a ser deletado.
     * @throws ObjetoNaoEncontradoException se o cliente não for encontrado.
     */
    public void softDeleteCliente(Long id) {
        Cliente cliente = clienteRepository.findClienteById(id)
                .orElseThrow(() -> new ObjetoNaoEncontradoException("Cliente não encontrado"));

        cliente.setStatus(false);
        clienteRepository.save(cliente);
    }

    /**
     * Atualiza um cliente pelo ID.
     *
     * @param id         ID do cliente a ser atualizado.
     * @param clienteDTO Dados atualizados do cliente.
     * @return DTO do cliente atualizado.
     * @throws ObjetoNaoEncontradoException se o cliente não for encontrado.
     */
    @Transactional
    public ClienteResponseDTO atualizarCliente(Long id, ClienteRequestDTO clienteDTO) {
        Cliente clienteExistente = clienteRepository.findById(id)
                .orElseThrow(() -> new ObjetoNaoEncontradoException("Cliente não encontrado"));

        inativaRelacionamentos(clienteExistente, clienteDTO);

        Cliente clienteAtualizado = atualizarDadosCliente(clienteDTO, clienteExistente);

        return mapper.entityToResponseDTO(clienteAtualizado);
    }

    /**
     * Inativa relacionamentos de um cliente existente com base nos dados do DTO.
     *
     * @param clienteExistente Cliente existente a ser atualizado.
     * @param clienteDTO       Dados do cliente a serem comparados.
     */
    private void inativaRelacionamentos(Cliente clienteExistente, ClienteRequestDTO clienteDTO) {
        if (!Objects.isNull(clienteDTO.getEndereco())) {
            clienteExistente.getEndereco().setStatus(false);
        }
        if (!Objects.isNull(clienteDTO.getTelefones())) {
            clienteExistente.getTelefones().forEach(telefone -> telefone.setStatus(false));
        }
    }

    /**
     * Atualiza os dados de um cliente existente com base nos dados do DTO.
     *
     * @param clienteDTO        Dados do cliente a serem atualizados.
     * @param clienteExistente  Cliente existente a ser atualizado.
     * @return Cliente atualizado.
     */
    private Cliente atualizarDadosCliente(ClienteRequestDTO clienteDTO, Cliente clienteExistente) {
        Cliente clienteAtualizado = mapper.requestDtoToEntity(clienteDTO);
        clienteAtualizado.setId(clienteExistente.getId());
        clienteAtualizado.setStatus(clienteExistente.getStatus());
        clienteRepository.save(clienteAtualizado);
        return clienteAtualizado;
    }


    /**
     * Valida os campos obrigatórios do cliente.
     *
     * @param clienteDTO Dados do cliente a serem validados.
     * @throws NegocioException se os campos do cliente não estiverem de acordo com as regras de negócio.
     */
    private void validaCamposCliente(ClienteRequestDTO clienteDTO){
        validaNome(clienteDTO.getNome());
        validaTelefone(clienteDTO);
    }

    /**
     * Valida o nome do cliente.
     *
     * @param nome Nome do cliente a ser validado.
     * @throws NegocioException se o nome for inválido.
     */
    private void validaNome(String nome){
        validaNomeDuplicado(nome);
        if(!validaTamanhoTexto(nome,TAMANHO_DEZ)){
            throw new NegocioException("O nome não pode ter mais que 10 caracteres");
        }
    }

    /**
     * Valida se o nome do cliente já está cadastrado.
     *
     * @param nome Nome do cliente a ser verificado.
     * @throws NegocioException se o nome do cliente já estiver cadastrado.
     */
    private void validaNomeDuplicado(String nome){
        if(!clienteRepository.findByNome(nome).isEmpty()){
            throw new NegocioException("Não é possível cadastrar esse nome, " +
                                       "tente outra opção ou entre em contato com o administrador do sistema");
        }
    }

    /**
     * Valida os telefones do cliente.
     *
     * @param clienteDTO Dados do cliente contendo os telefones a serem validados.
     */
    private void validaTelefone(ClienteRequestDTO clienteDTO) {
        List<TelefoneRequestDTO> telefones = clienteDTO.getTelefones();
        for (TelefoneRequestDTO telefoneDTO : telefones) {
            telefoneService.validaCadastroTelefone(telefoneDTO.getTelefone());
        }
    }




}
