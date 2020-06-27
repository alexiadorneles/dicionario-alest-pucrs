package main;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TrieTest {

    @Test
    public void shouldReadAllTree() {
        // arrange
        Trie trie = new Trie();
        trie.insert("bala");
        trie.insert("bar");
        trie.insert("baia");
        trie.insert("baiano");
        trie.insert("bola");
        trie.insert("boia");

        // act
        List<String> result = trie.positionsPre();

        // assert
        assertTrue(result.contains("bala"));
        assertTrue(result.contains("bar"));
        assertTrue(result.contains("baia"));
        assertTrue(result.contains("baiano"));
        assertTrue(result.contains("bola"));
        assertTrue(result.contains("boia"));
    }

}