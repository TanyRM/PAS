package service;

import dao.ClienteDAO;
import dao.ContaDAO;
import dao.TransacaoDAO;
import entidades.Cliente;
import entidades.Conta;
import entidades.Transacao;

import java.math.BigDecimal;

// Variante 1: Entidade específica
public class ServiceEntidade {
    private ClienteDAO clienteDAO = new ClienteDAO();
    private ContaDAO contaDAO = new ContaDAO();
    private TransacaoDAO transacaoDAO = new TransacaoDAO();

    public Cliente criarCliente(String nome, String cpf, String email) {
        // Verifica se já existe cliente com o CPF
        if(clienteDAO.buscarPorCpf(cpf) != null) {
            throw new RuntimeException("Cliente com CPF " + cpf + " já existe");
        }

        Cliente cliente = new Cliente(nome, cpf, email);
        return clienteDAO.salvar(cliente);
    }

    public Conta abrirConta(String numero, String agencia, Long clienteId) {
        // Verifica se o cliente existe
        Cliente cliente = clienteDAO.buscarPorId(clienteId);
        if(cliente == null) {
            throw new RuntimeException("Cliente não encontrado");
        }

        Conta conta = new Conta(numero, agencia, clienteId);
        return contaDAO.salvar(conta);
    }

    public void depositar(Long contaId, BigDecimal valor) {
        if(valor.compareTo(BigDecimal.ZERO) <= 0) {
            throw new RuntimeException("Valor inválido");
        }

        Conta conta = contaDAO.buscarPorId(contaId);
        if(conta == null) {
            throw new RuntimeException("Conta não encontrada");
        }

        conta.setSaldo(conta.getSaldo().add(valor));
        contaDAO.atualizar(conta);

        Transacao transacao = new Transacao(contaId, Transacao.TipoTransacao.DEPOSITO,
                valor, "Depósito em conta");
        transacaoDAO.salvar(transacao);
    }

    public void sacar(Long contaId, BigDecimal valor) {
        if(valor.compareTo(BigDecimal.ZERO) <= 0) {
            throw new RuntimeException("Valor inválido");
        }

        Conta conta = contaDAO.buscarPorId(contaId);
        if(conta == null) {
            throw new RuntimeException("Conta não encontrada");
        }

        if(conta.getSaldo().compareTo(valor) < 0) {
            throw new RuntimeException("Saldo insuficiente");
        }

        conta.setSaldo(conta.getSaldo().subtract(valor));
        contaDAO.atualizar(conta);

        Transacao transacao = new Transacao(contaId, Transacao.TipoTransacao.SAQUE,
                valor, "Saque em conta");
        transacaoDAO.salvar(transacao);
    }

    public BigDecimal obterSaldo(Long contaId) {
        Conta conta = contaDAO.buscarPorId(contaId);
        return conta != null ? conta.getSaldo() : BigDecimal.ZERO;
    }
}