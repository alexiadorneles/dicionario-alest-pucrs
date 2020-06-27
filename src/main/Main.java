package main;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        Trie trie = new Trie();
        trie.insert("bala");
        trie.insert("bar");
        trie.insert("baia");
        trie.insert("baiano");
        trie.insert("bola");
        trie.insert("boia");

        System.out.println("Digite o início da palavra");
        String word = in.next();
        List<String> nodeFromString = trie.findNodeFromString(word);
        nodeFromString.forEach(System.out::println);
//        trie.positionsPos();
        System.out.println();
    }
}
