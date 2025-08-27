package filter;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ReadFileFilter implements Filter<String, List<String>> {

    @Override
    public List<String> process(String filePath) {
        List<String> lines = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            boolean isFirstLine = true;

            while ((line = reader.readLine()) != null) {
                if (isFirstLine) {
                    isFirstLine = false;
                    continue;
                }
                lines.add(line);
            }

            System.out.println("Total de linhas: " + lines.size());

        } catch (IOException e) {
            System.err.println(e.getMessage());
            throw new RuntimeException("Erro ao processar arquivo", e);
        }

        return lines;
    }
}
