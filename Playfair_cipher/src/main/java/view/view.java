/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

import java.util.Scanner;

/**
 *
 * @author wojtekkurpanik
 */
public class view {
    private Scanner scanner = new Scanner(System.in);

    // Get keyword from the user
    /**
     *
     * @return
     */
    public String getKeyword() {
        System.out.print("Enter the keyword: ");
        return scanner.next();
    }

    // Get separator letter from the user
    /**
     *
     * @return
     */
    public char getSeparator() {
        System.out.print("Enter the separator letter: ");
        return scanner.next().toUpperCase().charAt(0);
    }

    // Get the text to encode from the user
    /**
     *
     * @return
     */
    public String getTextToEncode() {
        System.out.print("Enter text to encode: ");
        return scanner.next();
    }

    // Get the text to decode from the user
    /**
     *
     * @return
     */
    public String getTextToDecode() {
        System.out.print("Enter text to decode: ");
        return scanner.next();
    }

    // Display encoded text
    /**
     *
     * @param encodedText
     */
    public void displayEncodedText(String encodedText) {
        System.out.println("Encoded: " + encodedText);
    }

    // Display decoded text
    /**
     *
     * @param decodedText
     */
    public void displayDecodedText(String decodedText) {
        System.out.println("Decoded: " + decodedText);
    }

    // Display a message to the user
    /**
     *
     * @param message
     */
    public void displayMessage(String message) {
        System.out.println(message);
    }
}
