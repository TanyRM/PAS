package filter;

import java.util.ArrayList;
import java.util.List;

public class CleanDataFilter implements Filter<List<String>, List<String>> {

    @Override
    public List<String> process(List<String> lines) {
        List<String> cleanLines = new ArrayList<>();
        int invalidCount = 0;

        for (String line : lines) {
            if (isValidLine(line)) {
                cleanLines.add(line);
            } else {
                invalidCount++;
            }
        }

        System.out.println("Linhas válidas: " + cleanLines.size() +
                ", Linhas descartadas: " + invalidCount);

        return cleanLines;
    }

    private boolean isValidLine(String line) {
        if (line == null || line.trim().isEmpty()) {
            return false;
        }

        String[] parts = line.split(",");
        if (parts.length != 3) {
            return false;
        }

        try {
            // Verifica se o produto não está vazio
            String produto = parts[1].trim();
            if (produto.isEmpty()) {
                return false;
            }

            // Verifica se o valor é válido e não negativo
            double valor = Double.parseDouble(parts[2].trim());
            if (valor < 0) {
                return false;
            }

            return true;

        } catch (NumberFormatException e) {
            return false;
        }
    }
}
