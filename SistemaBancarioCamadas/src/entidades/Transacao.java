package entidades;

import java.math.BigDecimal;

/**
 * Entidade Transação
 */
public class Transacao {
    public enum TipoTransacao {DEPOSITO, SAQUE}

    private Long id;
    private Long contaId;
    private TipoTransacao tipo;
    private BigDecimal valor;
    private String descricao;

    public Transacao() {
    }

    public Transacao(Long contaId, TipoTransacao tipo, BigDecimal valor, String descricao) {
        this.contaId = contaId;
        this.tipo = tipo;
        this.valor = valor;
        this.descricao = descricao;
    }

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return String.format("Transacao{id=%d, contaId=%d, tipo=%s, valor=%s, descricao='%s'}",
                id, contaId, tipo, valor, descricao);
    }
}