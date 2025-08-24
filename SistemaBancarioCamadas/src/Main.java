import entidades.Cliente;
import entidades.Conta;
import service.ServiceEntidade;
import service.ServiceGenerico;

import java.math.BigDecimal;

//Demonstração das duas variáveis
public class Main {
    public static void main(String[] args) {
        testarVarianteEntidade();
        System.out.println("\n-----------------------------------------------------------\n");
        testarVarianteGenerica();
    }

    private static void testarVarianteEntidade() {
        ServiceEntidade banco = new ServiceEntidade();

        try {
            System.out.println("Sistema bancário com DAOs específicos\n");

            // Criar cliente
            Cliente cliente = banco.criarCliente("João Silva", "123.456.789-00", "joao@email.com");
            System.out.println("Cliente criado: " + cliente);

            // Abrir conta
            Conta conta = banco.abrirConta("12345-6", "0001", cliente.getId());
            System.out.println("Conta criada: " + conta);

            // Fazer depósito
            banco.depositar(conta.getId(), new BigDecimal("1000.00"));
            System.out.println("Depósito de R$ 1.000,00 realizado");

            // Fazer saque
            banco.sacar(conta.getId(), new BigDecimal("200.00"));
            System.out.println("Saque de R$ 200,00 realizado");

            // Verificar saldo
            System.out.println("Saldo conta: R$ " + banco.obterSaldo(conta.getId()));
        } catch(Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    private static void testarVarianteGenerica() {
        ServiceGenerico banco = new ServiceGenerico();

        try {
            System.out.println("Sistema bancário com DAO genérico\n");

            // Criar cliente
            Cliente cliente = banco.criarCliente("Maria Souza", "111.222.333-44", "maria@email.com");
            System.out.println("Cliente criado: " + cliente);

            // Abrir conta
            Conta conta = banco.abrirConta("11111-1", "0002", cliente.getId());
            System.out.println("Conta criada: " + conta);

            // Fazer depósito
            banco.depositar(conta.getId(), new BigDecimal("2000.00"));
            System.out.println("Depósito de R$ 2.000,00 realizado");

            // Fazer saque
            banco.sacar(conta.getId(), new BigDecimal("500.00"));
            System.out.println("Saque de R$ 500,00 realizado");

            // Verificar saldos
            System.out.println("Saldo conta: R$ " + banco.obterSaldo(conta.getId()));
        } catch(Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }
}
