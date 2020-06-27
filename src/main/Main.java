package main;

import java.util.LinkedList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Trie trie = new Trie();
        trie.insert("Bala");
        trie.insert("Bar");
        trie.insert("Baia");
        trie.insert("Baiano");
        trie.insert("Bola");
        trie.insert("Boia");

        System.out.println("Digite o início da palavra");
        List<String> characters = trie.positionsPre();
//        trie.positionsPos();
        System.out.println();
    }
}
