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
    public static ArrayList<String> parse(String instr) {

        // Return single instruction as an ArrayList<>
        ArrayList<String> subList = new ArrayList<>(Arrays.asList(instr.split(" ")));

        for (int i = 0; i < subList.size(); i++) {

            subList.set(i, subList.get(i).trim());

        }

        return subList;

    }

}
