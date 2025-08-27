package filter;

import model.Venda;
import java.util.ArrayList;
import java.util.List;

public class ParseVendaFilter implements Filter<List<String>, List<Venda>> {

    @Override
    public List<Venda> process(List<String> lines) {
        List<Venda> vendas = new ArrayList<>();

        for (String line : lines) {
            try {
                Venda venda = parseLineToVenda(line);
                if (venda != null) {
                    vendas.add(venda);
                }
            } catch (Exception e) {
                System.err.println("Erro ao converter linha: " + line + " - " + e.getMessage());
            }
        }

        System.out.println("Total de vendas: " + vendas.size());

        return vendas;
    }

    private Venda parseLineToVenda(String line) {
        String[] parts = line.split(",");

        if (parts.length != 3) {
            return null;
        }

        try {
            int id = Integer.parseInt(parts[0].trim());
            String produto = parts[1].trim();
            double valor = Double.parseDouble(parts[2].trim());

            return new Venda(id, produto, valor);

        } catch (NumberFormatException e) {
            return null;
        }
    }
}
