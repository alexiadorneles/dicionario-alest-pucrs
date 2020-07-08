package main;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;


public class LeituraArquivo {
    private final Trie arvore;
    private Map<String, String> dicionario;

    public LeituraArquivo(Trie arvore, Map<String, String> dicionario) {
        this.arvore = arvore;
        this.dicionario = dicionario;
    }

    public void leitura(String fileName) {
        try (BufferedReader reader = Files.newBufferedReader(Path.of("resources/" + fileName + ".csv"), Charset.defaultCharset())) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] aux = line.split(";");
                this.arvore.insert(aux[0].toLowerCase());
                this.dicionario.put(aux[0].toLowerCase(), aux[1]);
            }
        } catch (IOException e) {
            System.err.format("Erro na leitura do arquivo: ", e);
            throw new RuntimeException();
        }
    }

}
