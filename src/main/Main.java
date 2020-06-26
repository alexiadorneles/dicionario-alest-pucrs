package main;

public class Main {
    public static void main(String[] args) {
        Trie trie = new Trie();
        trie.insert("Bala");
        trie.insert("Bola");
        trie.insert("Baiano");
        trie.insert("Alexia");

        System.out.println(trie);
    }
}
