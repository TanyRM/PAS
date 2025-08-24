package entidades;

import java.math.BigDecimal;

public class Conta {
    private Long id;
    private String numero;
    private String agencia;
    private BigDecimal saldo;
    private Long clienteId;

    public Conta() {
    }

    public Conta(String numero, String agencia, Long clienteId) {
        this.numero = numero;
        this.agencia = agencia;
        this.clienteId = clienteId;
        this.saldo = BigDecimal.ZERO;
    }

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumero() {
        return numero;
    }

    public BigDecimal getSaldo() {
        return saldo;
    }

    public void setSaldo(BigDecimal saldo) {
        this.saldo = saldo;
    }

    @Override
    public String toString() {
        return String.format("id=%d, numero='%s', agencia='%s', saldo=%s, clienteId=%d",
                id, numero, agencia, saldo, clienteId);
    }
}