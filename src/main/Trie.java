package main;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.joining;

class Trie {
    private TrieNode last;

    private class TrieNode {
        private final Map<Character, TrieNode> children = new HashMap<>();
        private boolean endOfWord;
        private Character element;
        private TrieNode parent;

        public TrieNode(Character element) {
            this.element = element;
        }

        public TrieNode() {
        }

        public void setParent(TrieNode parent) {
            this.parent = parent;
        }

        public TrieNode getParent() {
            return parent;
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

//        char[] chars = word.toCharArray();
////        for (int i = 0; i < chars.length; i++) {
//////            char parent = i > 0 ? chars[i-1] :
////            TrieNode finalCurrent = current;
////            current = current.getChildren().computeIfAbsent(chars[i], (c) -> {
////                TrieNode node = new TrieNode(c);
////                node.setParent(finalCurrent);
////                return node;
////            });
////        }
////
        for (char l : word.toCharArray()) {
            TrieNode finalCurrent1 = current;
            current = current.getChildren().computeIfAbsent(l, c -> {
                TrieNode node = new TrieNode(c);
                node.setParent(finalCurrent1);
                return node;
            });

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

    public LinkedList<String> positionsPre() {
        LinkedList<String> lista = new LinkedList<>();
        LinkedList<String> res = new LinkedList<>();
        StringBuilder a = new StringBuilder();
        positionsPreAux(root, lista, a, res, root.parent);
        return res;
    }

    private void positionsPreAux(TrieNode n, LinkedList<String> lista, StringBuilder builder, List<String> res, TrieNode previous) { // recursao
        if (n != null) {
            if (n.element != null) {
                lista.add(String.valueOf(n.element));
            }
            StringBuilder finalBuilder = extractDataFromParent(n, builder, previous);
            n.getChildren().forEach((key, value) -> {
                positionsPreAux(value, lista, finalBuilder, res, n);
                if (value.isEndOfWord()) {
                    this.addThingsFromBuilder(lista, finalBuilder);
                    res.addAll(lista);
                    res.add("-----------");
                    lista.clear();
                }
            });
        }
    }

    private StringBuilder extractDataFromParent(TrieNode n, StringBuilder builder, TrieNode previous) {
        TrieNode parent = n.parent != null ? n.parent.parent : n;
        if (parent != null && parent.isEndOfWord()
                || (
                builder.length() == 0 && n.parent != null
        )) {
            do {
                if (parent != null) {
                    parent = parent.getParent();
                    if (parent != null && parent.element != null) {
                        builder.append(parent.element);
                    }
                }
            } while (parent != null);
            builder = builder.reverse();
        } else {
            builder = new StringBuilder();
        }
        return builder;
    }

    private void addThingsFromBuilder(LinkedList<String> lista, StringBuilder finalBuilder) {
        if (finalBuilder.length() > 0) {
            char[] chars = finalBuilder.toString().toCharArray();
            for (int i = 0; i < chars.length; i++) {
                lista.add(i, String.valueOf(chars[i]));
            }
        }
    }

    public LinkedList<String> positionsPos() {
        LinkedList<String> lista = new LinkedList<>();
        StringBuilder builder = new StringBuilder();
        positionsPosAux(root, lista, builder);
        return lista;
    }

    private void positionsPosAux(TrieNode n, LinkedList<String> lista, StringBuilder builder) {
        TrieNode parent = n;
        if (n != null) {
            if (n.isEndOfWord()) {
                builder.append(n.element);
                do {
                    parent = parent.getParent();
                    if (parent != null && parent.element != null) {
                        builder.append(parent.element);
                    }
                } while (parent != null);
            }

            String text = builder.reverse().toString();
            if (!text.isEmpty()) {
                lista.add(text);
            }

            n.getChildren().forEach((key, value) -> {
                positionsPosAux(value, lista, new StringBuilder());
            });
        }
    }
}
