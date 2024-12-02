import java.io.*;
import java.util.*;

public class CompoundWord {
    public static void main(String[] args) {
        processInputFile("Input_01.txt");
        processInputFile("Input_02.txt");
    }

    private static void processInputFile(String fileName) {
        long start = System.currentTimeMillis();

        List<String> wordList = readWordsFromFile(fileName);
        TrieNode rootNode = new TrieNode();

        for (String word : wordList) {
            addWordToTrie(rootNode, word);
        }

        wordList.sort((w1, w2) -> w2.length() - w1.length());

        String longestWord = "";
        String secondLongestWord = "";

        for (String word : wordList) {
            if (isCompound(word, rootNode, 0, 0)) {
                if (longestWord.isEmpty()) {
                    longestWord = word;
                } else if (secondLongestWord.isEmpty()) {
                    secondLongestWord = word;
                    break;
                }
            }
        }

        long end = System.currentTimeMillis();

        System.out.println("File: " + fileName);
        System.out.println("Longest Compound Word: " + longestWord);
        System.out.println("Second Longest Compound Word: " + secondLongestWord);
        System.out.println("Time Taken: " + (end - start) + " milliseconds\n");
    }

    private static List<String> readWordsFromFile(String fileName) {
        List<String> words = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                words.add(line.trim());
            }
        } catch (IOException ex) {
            System.err.println("Failed to read file " + fileName + ": " + ex.getMessage());
        }
        return words;
    }

    static class TrieNode {
        Map<Character, TrieNode> children = new HashMap<>();
        boolean isWordEnd = false;
    }

    private static void addWordToTrie(TrieNode root, String word) {
        TrieNode currentNode = root;
        for (char ch : word.toCharArray()) {
            currentNode = currentNode.children.computeIfAbsent(ch, k -> new TrieNode());
        }
        currentNode.isWordEnd = true;
    }

    private static boolean isCompound(String word, TrieNode root, int index, int parts) {
        TrieNode current = root;
        for (int i = index; i < word.length(); i++) {
            char ch = word.charAt(i);
            if (!current.children.containsKey(ch)) {
                return false;
            }
            current = current.children.get(ch);
            if (current.isWordEnd) {
                if (i == word.length() - 1) {
                    return parts >= 1;
                }
                if (isCompound(word, root, i + 1, parts + 1)) {
                    return true;
                }
            }
        }
        return false;
    }
}
