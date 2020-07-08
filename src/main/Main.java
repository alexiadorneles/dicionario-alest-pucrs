package main;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        Trie trie = new Trie();
        Map<String, String> dicionario = new HashMap<>();
        LeituraArquivo leitura = new LeituraArquivo(trie, dicionario);

        System.out.println("Digite o nome do arquivo: (deve estar na pasta resources e ser um csv)");
        leitura.leitura(in.next());
        System.out.println("Digite o início de uma palavrva");
        List<String> nodeFromString = trie.findNodeFromString(in.next());
        nodeFromString.forEach(System.out::println);
        System.out.println("Digite a palavra desejada");
        String significado = dicionario.get(in.next());
        if (significado == null || significado.isEmpty()) {
            System.out.println("Palavra não encontrada");
        } else {
            System.out.println(significado);
        }
    }
}
