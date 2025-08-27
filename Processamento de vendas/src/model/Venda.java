package model;

public class Venda {
    private int id;
    private String produto;
    private double valor;

    public Venda() {}

    public Venda(int id, String produto, double valor) {
        this.id = id;
        this.produto = produto;
        this.valor = valor;
    }

    public double getValor() { return valor; }

    @Override
    public String toString() {
        return String.format("Venda{id=%d, produto='%s', valor=%.2f}", id, produto, valor);
    }
}