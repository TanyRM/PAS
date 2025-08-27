package main;

import filter.*;
import model.Venda;
import java.util.List;

public class PipeAndFilterApp {

    public static void main(String[] args) {
        String arquivo = "Processamento de vendas/src/dados.csv";

        try {
            // Pipeline de processamento usando Pipes and Filters
            // Filtro de leitura do arquivo
            ReadFileFilter readFilter = new ReadFileFilter();
            List<String> linhasArquivo = readFilter.process(arquivo);

            // Filtro de limpeza dos dados
            CleanDataFilter cleanFilter = new CleanDataFilter();
            List<String> linhasValidas = cleanFilter.process(linhasArquivo);

            // Filtro de conversão para objetos de Venda
            ParseVendaFilter parseFilter = new ParseVendaFilter();
            List<Venda> vendas = parseFilter.process(linhasValidas);

            // Filtro de geração do relatório
            SumarioFilter sumarioFilter = new SumarioFilter();
            String relatorio = sumarioFilter.process(vendas);

            System.out.println("\n-------------------------------------------------------------------------------\n");
            System.out.println(relatorio);

        } catch (Exception e) {
            System.err.println("Erro: " + e.getMessage());
        }
    }
}
