ReadMe
Overview

This program identifies the longest and second-longest compound words from input text files. A compound word is defined as a word that can be formed entirely by concatenating other words in the list.

The program processes each input file to:

    Load all words into memory.
    Use a Trie data structure to efficiently check if a word is composed of other words.
    Sort the words by length (longest first) for prioritized processing.
    Determine the longest and second-longest compound words.
    Output the results, including processing time for each file.

Key Design Decisions

    Trie Implementation: A TrieNode structure is used for efficient prefix matching and lookup operations, crucial for the recursive checks in determining if a word is a compound word.
    Sorting: Words are sorted by length to ensure the longest compound word is found first.
    Recursive Compound Word Check: A recursive approach is used to split words into parts, ensuring all components exist in the Trie.

Steps to Execute the Program

    Prepare Input Files:
        Create text files (e.g., Input_01.txt, Input_02.txt) with one word per line.
        Ensure the file names match those referenced in the code or update the code to match your file names.

    Compile the Code:

javac CompoundWord.java

Run the Program:

java CompoundWord

View the Output:

    The program will print the longest and second-longest compound words for each input file, along with the processing time in milliseconds.