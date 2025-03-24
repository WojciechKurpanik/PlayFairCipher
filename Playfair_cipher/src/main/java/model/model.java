/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author wojtekkurpanik
 */
public class model {
    private char[][] matrix = new char[5][5];
    private String keyword;
    private char separator;

    // Constructor to initialize the cipher
    /**
     *
     * @param keyword
     * @param separator
     */
    public model(String keyword, char separator) {
        this.keyword = prepareKeyword(keyword);
        this.separator = separator;
        createMatrix(this.keyword);
    }

    // Prepares the keyword by removing duplicates and treating 'J' as 'I'
    private String prepareKeyword(String keyword) {
        StringBuilder prepared = new StringBuilder();
        boolean[] alphabetUsed = new boolean[26];
        
        keyword = keyword.toUpperCase().replace("J", "I");

        for (char ch : keyword.toCharArray()) {
            if (Character.isLetter(ch) && !alphabetUsed[ch - 'A']) {
                prepared.append(ch);
                alphabetUsed[ch - 'A'] = true;
            }
        }

        return prepared.toString();
    }

    // Create the Playfair cipher matrix based on the keyword
    private void createMatrix(String keyword) {
        boolean[] alphabetUsed = new boolean[26];
        alphabetUsed['J' - 'A'] = true; // Treat 'J' as 'I'

        int row = 0, col = 0;
        for (char ch : keyword.toCharArray()) {
            matrix[row][col] = ch;
            alphabetUsed[ch - 'A'] = true;
            if (++col == 5) {
                col = 0;
                row++;
            }
        }

        // Fill the matrix with the remaining letters
        for (char ch = 'A'; ch <= 'Z'; ch++) {
            if (!alphabetUsed[ch - 'A']) {
                matrix[row][col] = ch;
                if (++col == 5) {
                    col = 0;
                    row++;
                }
            }
        }
    }

    // Method to encode the plaintext
    /**
     *
     * @param text
     * @return
     */
    public String encode(String text) {
        text = preprocessText(text, true); // Prepare text for encoding
        StringBuilder encoded = new StringBuilder();

        for (int i = 0; i < text.length(); i += 2) {
            char ch1 = text.charAt(i);
            char ch2 = text.charAt(i + 1);
            int[] pos1 = findPosition(ch1);
            int[] pos2 = findPosition(ch2);

            if (pos1[0] == pos2[0]) { // Same row
                encoded.append(matrix[pos1[0]][(pos1[1] + 1) % 5]);
                encoded.append(matrix[pos2[0]][(pos2[1] + 1) % 5]);
            } else if (pos1[1] == pos2[1]) { // Same column
                encoded.append(matrix[(pos1[0] + 1) % 5][pos1[1]]);
                encoded.append(matrix[(pos2[0] + 1) % 5][pos2[1]]);
            } else { // Rectangle
                encoded.append(matrix[pos1[0]][pos2[1]]);
                encoded.append(matrix[pos2[0]][pos1[1]]);
            }
        }

        return encoded.toString();
    }

    // Method to decode the ciphertext
    /**
     *
     * @param text
     * @return
     */
    public String decode(String text) {
        text = preprocessText(text, false); // Prepare text for decoding
        StringBuilder decoded = new StringBuilder();

        for (int i = 0; i < text.length(); i += 2) {
            char ch1 = text.charAt(i);
            char ch2 = text.charAt(i + 1);
            int[] pos1 = findPosition(ch1);
            int[] pos2 = findPosition(ch2);

            if (pos1[0] == pos2[0]) { // Same row
                decoded.append(matrix[pos1[0]][(pos1[1] + 4) % 5]);
                decoded.append(matrix[pos2[0]][(pos2[1] + 4) % 5]);
            } else if (pos1[1] == pos2[1]) { // Same column
                decoded.append(matrix[(pos1[0] + 4) % 5][pos1[1]]);
                decoded.append(matrix[(pos2[0] + 4) % 5][pos2[1]]);
            } else { // Rectangle
                decoded.append(matrix[pos1[0]][pos2[1]]);
                decoded.append(matrix[pos2[0]][pos1[1]]);
            }
        }

        return decoded.toString();
    }

    // Method to preprocess text (prepare for encoding or decoding)
    private String preprocessText(String text, boolean encoding) {
        StringBuilder processed = new StringBuilder();
        text = text.toUpperCase().replace("J", "I").replaceAll("[^A-Z]", ""); // Remove non-alphabet chars

        for (int i = 0; i < text.length(); i++) {
            char ch = text.charAt(i);
            processed.append(ch);

            if (encoding && i < text.length() - 1 && text.charAt(i) == text.charAt(i + 1)) {
                processed.append(separator); // Add separator between repeated letters
            }
        }

        if (processed.length() % 2 != 0) {
            processed.append(separator); // Add separator if odd-length text
        }

        return processed.toString();
    }

    // Find the position of a character in the matrix
    private int[] findPosition(char ch) {
        for (int row = 0; row < 5; row++) {
            for (int col = 0; col < 5; col++) {
                if (matrix[row][col] == ch) {
                    return new int[]{row, col};
                }
            }
        }
        return null; // Should never happen for valid input
    }
}
