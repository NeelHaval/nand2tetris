// Package location
package src;

// Imports
import java.util.ArrayList;
import java.util.Arrays;


// Create Parser Class
public class Parser {

    // Create enum
    public enum instruction {

        PUSH,
        POP,
        Arithmetic

    }
    
    // Define parse() method
    public static ArrayList<String> parse(String str) {

        // Obtain instruction as an ArrayList<>
        return new ArrayList<>(Arrays.asList(str.split(" ")));

    }

}
