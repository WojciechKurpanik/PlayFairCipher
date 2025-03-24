/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.playfair_cipher;

import model.model;
import view.view;

/**
 *
 * @author wojtekkurpanik
 * @version 1.0
 */


public class Playfair_cipher {
    
    /**
     *
     * @param args
     */
    public static void main(String[] args) {
    int numOfParam = args.length;
       
    // Create instances of the View and Model
    if(numOfParam==0){ 
        view view = new view();

        // Get keyword and separator from the user
        String keyword = view.getKeyword();
        char separator = view.getSeparator();

        if (!Character.isLetter(separator) || separator == 'J') {
            view.displayMessage("Invalid separator. It should be a letter other than 'J'.");
            return;
        }

        // Initialize the Playfair cipher model with the keyword and separator
        model model = new model(keyword, separator);

        // Get text to encode and decode
        String textToEncode = view.getTextToEncode();
        String encodedText = model.encode(textToEncode);
        view.displayEncodedText(encodedText);

        String textToDecode = view.getTextToDecode();
        String decodedText = model.decode(textToDecode);
        view.displayDecodedText(decodedText);
    }
    else{
        view view = new view();


        // Get keyword and separator from the user
        String keyword = args[0];
        char separator = args[1].charAt(0);

        if (!Character.isLetter(separator) || separator == 'J') {
            view.displayMessage("Invalid separator. It should be a letter other than 'J'.");
            return;
        }

        // Initialize the Playfair cipher model with the keyword and separator
        model model = new model(keyword, separator);

        // Get text to encode and decode
        String textToEncode = args[2];
        String encodedText = model.encode(textToEncode);
        view.displayEncodedText(encodedText);

        String textToDecode = encodedText;
        String decodedText = model.decode(textToDecode);
        view.displayDecodedText(decodedText);
    }
}
}

