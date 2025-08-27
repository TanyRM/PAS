package filter;

import model.Venda;
import java.text.DecimalFormat;
import java.util.List;

public class SumarioFilter implements Filter<List<Venda>, String> {

    @Override
    public String process(List<Venda> vendas) {
        if (vendas.isEmpty()) {
            return "RELATÓRIO DE VENDAS\nNenhuma venda válida encontrada.";
        }

        int quantidadeVendas = vendas.size();
        double faturamentoTotal = calcularFaturamentoTotal(vendas);
        double ticketMedio = faturamentoTotal / quantidadeVendas;

        DecimalFormat df = new DecimalFormat("#,##0.00");

        StringBuilder relatorio = new StringBuilder();
        relatorio.append("RELATÓRIO DE VENDAS\n");
        relatorio.append("Quantidade de vendas: ").append(quantidadeVendas).append("\n");
        relatorio.append("Faturamento total: R$ ").append(df.format(faturamentoTotal)).append("\n");
        relatorio.append("Ticket médio: R$ ").append(df.format(ticketMedio));

        return relatorio.toString();
    }

    private double calcularFaturamentoTotal(List<Venda> vendas) {
        return vendas.stream()
                .mapToDouble(Venda::getValor)
                .sum();
    }
}
