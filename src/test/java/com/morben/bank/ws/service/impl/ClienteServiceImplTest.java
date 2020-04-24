package com.morben.bank.ws.service.impl;

import com.morben.bank.ws.exceptions.BankServiceException;
import com.morben.bank.ws.io.entity.*;
import com.morben.bank.ws.repository.ClienteRepository;
import com.morben.bank.ws.repository.PessoaRepository;
import com.morben.bank.ws.shared.*;
import com.morben.bank.ws.shared.dto.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class ClienteServiceImplTest {

    @InjectMocks
    ClienteServiceImpl clienteService;

    @Mock
    ClienteRepository clienteRepository;

    @Mock
    PessoaRepository pessoaRepository;

    @Mock
    Utils utils;

    @Mock
    BCryptPasswordEncoder bCryptPasswordEncoder;

    String encryptedPassword = "sdfx76hhbh";

    String clienteId = "dfdfd6783478";
    String contaId = "dfdfd67833478";
    String contaNumero = "123444";
    String ccvNumero = "122";
    String cartaoNumero = "1234123412341234";
    String enderecoId = "df33dfd678478";
    String cartaoId = "dfd33fd67833478";

    String clienteIdPj = "pjfdfd6783478";
    String contaIdPj = "pjfdfd67833478";
    String enderecoIdPj = "pjf33dfd678478";
    String cartaoIdPj = "pjfd33fd67833478";
    String contaNumeroPj = "323444";
    String ccvNumeroPj = "322";
    String cartaoNumeroPj = "3334123412341234";

    ClienteEntity clienteEntity;
    ClienteEntity clienteEntityPj;
    PessoaFisicaEntity cadastroPessoaFisicaEntity;
    PessoaJuridicaEntity cadastroPessoaJuridicaEntity;
    List<EnderecoEntity> enderecosPJ;
    List<EnderecoEntity> enderecos;
    ContaCorrenteEntity contaCorrenteEntity;
    List<CartaoEntity> cartoes;
    List<CartaoEntity> getCartoesPj;
    EnderecoEntity enderecoEntity;
    EnderecoEntity enderecoPJEntity;
    CartaoEntity cartaoEntity;
    ContaCorrenteEntity contaCorrenteEntityPj;
    List<CartaoEntity> cartoesPj ;
    EnderecoEntity enderecoEntityPj;
    CartaoEntity cartaoEntityPj;
    ZoneId defaultZoneId = ZoneId.systemDefault();
    LocalDate localDate = LocalDate.now().plusYears(Constantes.VENCIMENTO_ANOS);
	Date dtVencimento = Date.from(localDate.atStartOfDay(defaultZoneId).toInstant());

    @BeforeEach
    void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        //Pessoa Fisica
        clienteEntity = new ClienteEntity();
        clienteEntity.setId(1L);
        clienteEntity.setClienteId(clienteId);
        clienteEntity.setPessoaTipo(PessoaTipo.PF);

        cadastroPessoaFisicaEntity = new PessoaFisicaEntity();
        cadastroPessoaFisicaEntity.setId(1L);
        cadastroPessoaFisicaEntity.setEmail("mm@gmail.com");
        cadastroPessoaFisicaEntity.setFone("48991823322");
        cadastroPessoaFisicaEntity.setClienteId(clienteId);
        cadastroPessoaFisicaEntity.setApelido("apelido");
        cadastroPessoaFisicaEntity.setCpf("12345678");
        cadastroPessoaFisicaEntity.setNome("Nome Pessoa Fisica");
        cadastroPessoaFisicaEntity.setRg("12344");
        cadastroPessoaFisicaEntity.setOrgaoEmissor("PR");
        cadastroPessoaFisicaEntity.setScore(5);
        cadastroPessoaFisicaEntity.setClienteDetalhes(clienteEntity);

        clienteEntity.setCadastroDetalhes(cadastroPessoaFisicaEntity);

        enderecos = new ArrayList<>();
        enderecoEntity = new EnderecoEntity();
        enderecoEntity.setClienteId(clienteId);
        enderecoEntity.setEnderecoId(enderecoId);
        enderecoEntity.setId(1L);
        enderecoEntity.setPessoaDetalhes(cadastroPessoaFisicaEntity);
        enderecoEntity.setBairro("Bairro");
        enderecoEntity.setCep("85660000");
        enderecoEntity.setComplemento("casa");
        enderecoEntity.setEstado("PR");
        enderecoEntity.setRua("Rua");
        enderecoEntity.setNumero(123);
        enderecoEntity.setPais("BR");
        enderecos.add(enderecoEntity);
        cadastroPessoaFisicaEntity.setEnderecos(enderecos);

        contaCorrenteEntity = new ContaCorrenteEntity();
        contaCorrenteEntity.setTipo(PessoaTipo.PF.tipoConta());
        contaCorrenteEntity.setSaldo(0.0);
        contaCorrenteEntity.setNumero(contaNumero);
        contaCorrenteEntity.setLimite(LimiteContaPorScore.getLimite(cadastroPessoaFisicaEntity.getScore()));
        contaCorrenteEntity.setAgencia(Constantes.AGENCIA);
        contaCorrenteEntity.setContaId(contaId);
        contaCorrenteEntity.setClienteId(clienteId);

        contaCorrenteEntity.setClienteDetalhes(clienteEntity);
        clienteEntity.setContaDetalhes(contaCorrenteEntity);

        if (LimiteCartaoPorScore.getLimite(cadastroPessoaFisicaEntity.getScore()) > 0.0){
            cartoes = new ArrayList<>();
            cartaoEntity = new CartaoEntity();
            cartaoEntity.setContaId(contaId);
            cartaoEntity.setValidade(dtVencimento);
            cartaoEntity.setSaldo(LimiteCartaoPorScore.getLimite(cadastroPessoaFisicaEntity.getScore()));
            cartaoEntity.setNumero(cartaoNumero);
            cartaoEntity.setLimite(LimiteCartaoPorScore.getLimite(cadastroPessoaFisicaEntity.getScore()));
            cartaoEntity.setClienteId(clienteId);
            cartaoEntity.setCcv(ccvNumero);
            cartaoEntity.setCartaoId(cartaoId);
            cartaoEntity.setContaDetalhes(contaCorrenteEntity);
            cartoes.add(cartaoEntity);
            contaCorrenteEntity.setCartoesDetalhes(cartoes);
        }

        //Pessoa juridica
        clienteEntityPj = new ClienteEntity();
        clienteEntityPj.setId(2L);
        clienteEntityPj.setClienteId(clienteIdPj);
        clienteEntityPj.setPessoaTipo(PessoaTipo.PJ);

        cadastroPessoaJuridicaEntity = new PessoaJuridicaEntity();
        cadastroPessoaJuridicaEntity.setId(2L);
        cadastroPessoaJuridicaEntity.setEmail("mm@gmail.com");
        cadastroPessoaJuridicaEntity.setFone("48991823322");
        cadastroPessoaJuridicaEntity.setClienteId(clienteIdPj);
        cadastroPessoaJuridicaEntity.setFantasia("Fantasia");
        cadastroPessoaJuridicaEntity.setCnpj("12345678901234");
        cadastroPessoaJuridicaEntity.setRazaoSocial("Nome Pessoa Juridica");
        cadastroPessoaJuridicaEntity.setInscricaoEstadual("Isento");
        cadastroPessoaJuridicaEntity.setScore(8);
        cadastroPessoaJuridicaEntity.setClienteDetalhes(clienteEntityPj);

        enderecosPJ = new ArrayList<>();
        enderecoPJEntity = new EnderecoEntity();
        enderecoPJEntity.setClienteId(clienteIdPj);
        enderecoPJEntity.setEnderecoId(enderecoIdPj);
        enderecoPJEntity.setId(2L);
        enderecoPJEntity.setPessoaDetalhes(cadastroPessoaJuridicaEntity);
        enderecoPJEntity.setBairro("Bairro");
        enderecoPJEntity.setCep("85660000");
        enderecoPJEntity.setComplemento("casa");
        enderecoPJEntity.setEstado("PR");
        enderecoPJEntity.setRua("Rua");
        enderecoPJEntity.setNumero(11);
        enderecoPJEntity.setPais("BR");
        enderecosPJ.add(enderecoPJEntity);
        cadastroPessoaJuridicaEntity.setEnderecos(enderecosPJ);

        clienteEntityPj.setCadastroDetalhes(cadastroPessoaJuridicaEntity);

        contaCorrenteEntityPj = new ContaCorrenteEntity();
        contaCorrenteEntityPj.setTipo(PessoaTipo.PJ.tipoConta());
        contaCorrenteEntityPj.setSaldo(0.0);
        contaCorrenteEntityPj.setNumero(contaNumeroPj);
        contaCorrenteEntityPj.setLimite(LimiteContaPorScore.getLimite(cadastroPessoaJuridicaEntity.getScore()));
        contaCorrenteEntityPj.setAgencia(Constantes.AGENCIA);
        contaCorrenteEntityPj.setContaId(contaIdPj);
        contaCorrenteEntityPj.setClienteId(clienteIdPj);

        contaCorrenteEntityPj.setClienteDetalhes(clienteEntityPj);

        clienteEntityPj.setContaDetalhes(contaCorrenteEntityPj);

        if (LimiteCartaoPorScore.getLimite(cadastroPessoaJuridicaEntity.getScore()) > 0.0){
            cartoesPj = new ArrayList<>();
            cartaoEntityPj = new CartaoEntity();
            cartaoEntityPj.setContaId(contaIdPj);
            cartaoEntityPj.setValidade(dtVencimento);
            cartaoEntityPj.setSaldo(LimiteCartaoPorScore.getLimite(cadastroPessoaJuridicaEntity.getScore()));
            cartaoEntityPj.setNumero(cartaoNumeroPj);
            cartaoEntityPj.setLimite(LimiteCartaoPorScore.getLimite(cadastroPessoaJuridicaEntity.getScore()));
            cartaoEntityPj.setClienteId(clienteIdPj);
            cartaoEntityPj.setCcv(ccvNumeroPj);
            cartaoEntityPj.setCartaoId(cartaoIdPj);
            cartaoEntityPj.setContaDetalhes(contaCorrenteEntityPj);
            cartoesPj.add(cartaoEntityPj);
            contaCorrenteEntityPj.setCartoesDetalhes(cartoesPj);
        }

    }

    @Test
    void getClientePessoaFisicaByClienteId() {
        when(clienteRepository.findByClienteId(anyString())).thenReturn(clienteEntity);

        ClienteDTO clienteDTO = clienteService.getClienteByClienteId(clienteId);

        assertNotNull(clienteDTO);
        assertNotNull(clienteDTO.getCadastroDetalhes());
        assertNotNull(clienteDTO.getClienteId());
        assertNotNull(clienteDTO.getPessoaTipo());
        assertNotNull(clienteDTO.getCadastroDetalhes().getEnderecos());
        assertNotNull(clienteDTO.getContaDetalhes());

        assertEquals(PessoaTipo.PF, clienteDTO.getPessoaTipo());

        assertEquals("mm@gmail.com", clienteDTO.getCadastroDetalhes().getEmail());
        assertEquals("48991823322", clienteDTO.getCadastroDetalhes().getFone());
        assertEquals(clienteId, clienteDTO.getCadastroDetalhes().getClienteId());

        List<EnderecoDTO> enderecos = clienteDTO.getCadastroDetalhes().getEnderecos();

        assertEquals(1, enderecos.size());
        assertEquals(clienteId, enderecos.get(0).getClienteId());
        assertEquals(enderecoId, enderecos.get(0).getEnderecoId());
        assertEquals("Bairro", enderecos.get(0).getBairro());

    }

    @Test
    void getClientePessoaJuridicaByClienteId() {

        when(clienteRepository.findByClienteId(anyString())).thenReturn(clienteEntityPj);

        ClienteDTO clienteDTO = clienteService.getClienteByClienteId(clienteIdPj);

        assertNotNull(clienteDTO);
        assertNotNull(clienteDTO.getCadastroDetalhes());
        assertNotNull(clienteDTO.getClienteId());
        assertNotNull(clienteDTO.getPessoaTipo());
        assertNotNull(clienteDTO.getCadastroDetalhes().getEnderecos());

        assertEquals(PessoaTipo.PJ, clienteDTO.getPessoaTipo());

        assertEquals("mm@gmail.com", clienteDTO.getCadastroDetalhes().getEmail());
        assertEquals("48991823322", clienteDTO.getCadastroDetalhes().getFone());

        assertEquals(clienteIdPj, clienteDTO.getCadastroDetalhes().getClienteId());

        List<EnderecoDTO> enderecos = clienteDTO.getCadastroDetalhes().getEnderecos();

        assertEquals(1, enderecos.size());
        assertEquals(clienteIdPj, enderecos.get(0).getClienteId());
        assertEquals(enderecoIdPj, enderecos.get(0).getEnderecoId());
        assertEquals("Bairro", enderecos.get(0).getBairro());

    }

    @Test
    void createClientePF() {
        when(clienteRepository.findByClienteId(anyString())).thenReturn(null);
        when(bCryptPasswordEncoder.encode(anyString())).thenReturn(encryptedPassword);
        when(clienteRepository.save(any(ClienteEntity.class))).thenReturn(clienteEntity);
        //Mockito.doNothing().when(amazonSES).verifyEmail(any(ClienteDTO.class));//exclude integration code from unit test;

        ClienteDTO clienteDTO = new ClienteDTO();
        PessoaFisicaDTO pfDTO = new PessoaFisicaDTO();
        List<EnderecoDTO> enderecos = new ArrayList<>();
        EnderecoDTO enderecoDTO = new EnderecoDTO();

        clienteDTO.setPessoaTipo(PessoaTipo.PF);

        pfDTO.setEmail("mmwwwww@gmail.com");
        pfDTO.setFone("48991823322");
        pfDTO.setApelido("apelido");
        pfDTO.setCpf("12345678");
        pfDTO.setNome("Nome Pessoa Fisica");
        pfDTO.setRg("12344");
        pfDTO.setOrgaoEmissor("PR");

        enderecoDTO.setBairro("Bairro 1");
        enderecoDTO.setCep("85660000");
        enderecoDTO.setComplemento("casa 1");
        enderecoDTO.setEstado("PR");
        enderecoDTO.setRua("Rua 1");
        enderecoDTO.setNumero(123);
        enderecoDTO.setPais("BR");
        enderecoDTO.setPessoaDetalhes(pfDTO);
        enderecos.add(enderecoDTO);

        pfDTO.setEnderecos(enderecos);

        clienteDTO.setCadastroDetalhes(pfDTO);

        ClienteDTO storedClienteDetails = clienteService.createCliente(clienteDTO);

        //Detalhes cadastro pessoa fisica
        assertNotNull(storedClienteDetails);
        assertNotNull(storedClienteDetails.getClienteId());
        assertNotNull(storedClienteDetails.getCadastroDetalhes());
        assertNotNull(storedClienteDetails.getCadastroDetalhes().getScore());
        assertNotNull(storedClienteDetails.getCadastroDetalhes().getEmail());
        assertNotNull(storedClienteDetails.getCadastroDetalhes().getClienteId());

        assertNotNull(storedClienteDetails.getCadastroDetalhes().getEnderecos());
        assertNotNull(storedClienteDetails.getCadastroDetalhes().getEnderecos().get(0).getClienteId());
        assertNotNull(storedClienteDetails.getCadastroDetalhes().getEnderecos().get(0).getEnderecoId());
        assertNotNull(storedClienteDetails.getCadastroDetalhes().getEnderecos().get(0).getPessoaDetalhes());
        assertNotNull(storedClienteDetails.getCadastroDetalhes().getClienteDetalhes());

        //Detalhes conta corrente
        assertNotNull(storedClienteDetails.getContaDetalhes());
        assertNotNull(storedClienteDetails.getContaDetalhes().getContaId());
        assertNotNull(storedClienteDetails.getContaDetalhes().getClienteId());
        assertNotNull(storedClienteDetails.getContaDetalhes().getAgencia());
        assertNotNull(storedClienteDetails.getContaDetalhes().getNumero());
        assertNotNull(storedClienteDetails.getContaDetalhes().getTipo());
        assertNotNull(storedClienteDetails.getContaDetalhes().getClienteDetalhes());

        assertEquals(LimiteContaPorScore.getLimite(storedClienteDetails.getCadastroDetalhes().getScore()), storedClienteDetails.getContaDetalhes().getLimite());

        verify(this.clienteRepository, times(1)).save(any(ClienteEntity.class));

        //Detalhes cartao
        if (LimiteCartaoPorScore.getLimite(storedClienteDetails.getCadastroDetalhes().getScore()) > 0.0) {

            assertNotNull(storedClienteDetails.getContaDetalhes().getCartoesDetalhes());
            assertNotNull(storedClienteDetails.getContaDetalhes().getCartoesDetalhes().get(0).getContaDetalhes());
            assertNotNull(storedClienteDetails.getContaDetalhes().getCartoesDetalhes().get(0).getClienteId());
            assertNotNull(storedClienteDetails.getContaDetalhes().getCartoesDetalhes().get(0).getContaId());
            assertNotNull(storedClienteDetails.getContaDetalhes().getCartoesDetalhes().get(0).getCartaoId());
            assertNotNull(storedClienteDetails.getContaDetalhes().getCartoesDetalhes().get(0).getLimite());
            assertNotNull(storedClienteDetails.getContaDetalhes().getCartoesDetalhes().get(0).getValidade());
            assertNotNull(storedClienteDetails.getContaDetalhes().getCartoesDetalhes().get(0).getNumero());

            assertEquals(LimiteCartaoPorScore.getLimite(storedClienteDetails.getCadastroDetalhes().getScore()),
                    storedClienteDetails.getContaDetalhes().getCartoesDetalhes().get(0).getLimite());

        }

    }

    @Test
    void createClientePJ() {
        when(clienteRepository.findByClienteId(anyString())).thenReturn(null);
        when(bCryptPasswordEncoder.encode(anyString())).thenReturn(encryptedPassword);
        when(clienteRepository.save(any(ClienteEntity.class))).thenReturn(clienteEntity);
        //Mockito.doNothing().when(amazonSES).verifyEmail(any(ClienteDTO.class));//exclude integration code from unit test;

        ClienteDTO clienteDTO = new ClienteDTO();
        PessoaJuridicaDTO pjDto = new PessoaJuridicaDTO();
        List<EnderecoDTO> enderecos = new ArrayList<>();
        EnderecoDTO enderecoDTO = new EnderecoDTO();

        clienteDTO.setPessoaTipo(PessoaTipo.PJ);

        pjDto.setEmail("mmwer@gmail.com");
        pjDto.setFone("48991823322");
        pjDto.setFantasia("apelido");
        pjDto.setCnpj("12345678");
        pjDto.setRazaoSocial("Nome Pessoa Fisica");
        pjDto.setInscricaoEstadual("12344");

        enderecoDTO.setBairro("Bairro 1");
        enderecoDTO.setCep("85660000");
        enderecoDTO.setComplemento("casa 1");
        enderecoDTO.setEstado("PR");
        enderecoDTO.setRua("Rua 1");
        enderecoDTO.setNumero(123);
        enderecoDTO.setPais("BR");
        enderecos.add(enderecoDTO);

        enderecoDTO.setPessoaDetalhes(pjDto);

        pjDto.setEnderecos(enderecos);

        clienteDTO.setCadastroDetalhes(pjDto);

        ClienteDTO storedClienteDetails = clienteService.createCliente(clienteDTO);

        //Detalhes cadastro pessoa fisica
        assertNotNull(storedClienteDetails);
        assertNotNull(storedClienteDetails.getClienteId());
        assertNotNull(storedClienteDetails.getCadastroDetalhes());
        assertNotNull(storedClienteDetails.getCadastroDetalhes().getScore());
        assertNotNull(storedClienteDetails.getCadastroDetalhes().getEmail());
        assertNotNull(storedClienteDetails.getCadastroDetalhes().getClienteId());
        assertNotNull(storedClienteDetails.getCadastroDetalhes().getEnderecos());
        assertNotNull(storedClienteDetails.getCadastroDetalhes().getEnderecos().get(0).getClienteId());
        assertNotNull(storedClienteDetails.getCadastroDetalhes().getEnderecos().get(0).getEnderecoId());
        assertNotNull(storedClienteDetails.getCadastroDetalhes().getEnderecos().get(0).getPessoaDetalhes());
        assertNotNull(storedClienteDetails.getCadastroDetalhes().getClienteDetalhes());

        //Detalhes conta corrente
        assertNotNull(storedClienteDetails.getContaDetalhes());
        assertNotNull(storedClienteDetails.getContaDetalhes().getContaId());
        assertNotNull(storedClienteDetails.getContaDetalhes().getClienteId());
        assertNotNull(storedClienteDetails.getContaDetalhes().getAgencia());
        assertNotNull(storedClienteDetails.getContaDetalhes().getNumero());
        assertNotNull(storedClienteDetails.getContaDetalhes().getTipo());
        assertNotNull(storedClienteDetails.getContaDetalhes().getClienteDetalhes());

        assertEquals(LimiteContaPorScore.getLimite(storedClienteDetails.getCadastroDetalhes().getScore()), storedClienteDetails.getContaDetalhes().getLimite());

        verify(this.clienteRepository, times(1)).save(any(ClienteEntity.class));

        //Detalhes cartao
        if (LimiteCartaoPorScore.getLimite(storedClienteDetails.getCadastroDetalhes().getScore()) > 0.0) {

            assertNotNull(storedClienteDetails.getContaDetalhes().getCartoesDetalhes());
            assertNotNull(storedClienteDetails.getContaDetalhes().getCartoesDetalhes().get(0).getContaDetalhes());
            assertNotNull(storedClienteDetails.getContaDetalhes().getCartoesDetalhes().get(0).getClienteId());
            assertNotNull(storedClienteDetails.getContaDetalhes().getCartoesDetalhes().get(0).getContaId());
            assertNotNull(storedClienteDetails.getContaDetalhes().getCartoesDetalhes().get(0).getCartaoId());
            assertNotNull(storedClienteDetails.getContaDetalhes().getCartoesDetalhes().get(0).getLimite());
            assertNotNull(storedClienteDetails.getContaDetalhes().getCartoesDetalhes().get(0).getValidade());
            assertNotNull(storedClienteDetails.getContaDetalhes().getCartoesDetalhes().get(0).getNumero());

            assertEquals(LimiteCartaoPorScore.getLimite(storedClienteDetails.getCadastroDetalhes().getScore()),
                    storedClienteDetails.getContaDetalhes().getCartoesDetalhes().get(0).getLimite());

        }

    }

    @Test
    final void testGetCliente_ClienteIdNotFoundException() {

        when(clienteRepository.findByClienteId(anyString())).thenReturn(null);

        //if (clienteEntity == null) throw new ClientenameNotFoundException("Cliente with email: " + email + " not found.");

        assertThrows(BankServiceException.class,
                ()->{
                    clienteService.getClienteByClienteId(clienteId);
                });

    }

}
