package main;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

class Trie {
    private static class TrieNode {
        private final Map<Character, TrieNode> children = new HashMap<>();
        private boolean endOfWord;
        private Character element;

        public TrieNode(Character element) {
            this.element = element;
        }

        public TrieNode() {
        }

        Map<Character, TrieNode> getChildren() {
            return children;
        }

        boolean isEndOfWord() {
            return endOfWord;
        }

        void setEndOfWord(boolean endOfWord) {
            this.endOfWord = endOfWord;
        }
    }

    private TrieNode root;

    Trie() {
        root = new TrieNode();
    }

    void insert(String word) {
        TrieNode current = root;
        for (char l : word.toCharArray()) {
            current = current.getChildren().computeIfAbsent(l, TrieNode::new);
        }
        current.setEndOfWord(true);
    }

    boolean delete(String word) {
        return delete(root, word, 0);
    }

    boolean containsNode(String word) {
        TrieNode current = root;

        for (int i = 0; i < word.length(); i++) {
            char ch = word.charAt(i);
            TrieNode node = current.getChildren().get(ch);
            if (node == null) {
                return false;
            }
            current = node;
        }
        return current.isEndOfWord();
    }

    private TrieNode findNode(String word) {
        TrieNode current = root;
        for (int i = 0; i < word.length(); i++) {
            char ch = word.charAt(i);
            TrieNode node = current.getChildren().get(ch);
            if (node == null) {
                return null;
            }
            current = node;
        }
        return current;
    }

    boolean isEmpty() {
        return root == null;
    }

    private boolean delete(TrieNode current, String word, int index) {
        if (index == word.length()) {
            if (!current.isEndOfWord()) {
                return false;
            }
            current.setEndOfWord(false);
            return current.getChildren().isEmpty();
        }
        char ch = word.charAt(index);
        TrieNode node = current.getChildren().get(ch);
        if (node == null) {
            return false;
        }
        boolean shouldDeleteCurrentNode = delete(node, word, index + 1) && !node.isEndOfWord();

        if (shouldDeleteCurrentNode) {
            current.getChildren().remove(ch);
            return current.getChildren().isEmpty();
        }
        return false;
    }

    public List<String> buscaPreFixada() {
        return buscaPreFixadaRecursiva(root);
    }

    public List<String> findNodeFromString(String word) {
        TrieNode node = this.findNode(word);
        List<String> strings = this.buscaPreFixadaRecursiva(node);
        String parsed = word.substring(0, word.length() - 1);
        return strings.stream().map(semiWord -> parsed + semiWord).collect(toList());
    }

    private List<String> buscaPreFixadaRecursiva(TrieNode n) {
        if (n != null) {
            List<String> list = new LinkedList<>();

            n.getChildren().forEach((key, value) -> {
                List<String> strings = buscaPreFixadaRecursiva(value);
                list.addAll(strings);
            });

            if (n.element != null) {
                List<String> aux = list.stream().map(a -> n.element + a).collect(toList());
                if (n.isEndOfWord()) {
                    aux.add(String.valueOf(n.element));
                }

                return aux;
            }
            return list;
        }

        return Collections.emptyList();
    }
}
