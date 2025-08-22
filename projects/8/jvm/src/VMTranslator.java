// Package location
package src;

// Imports
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;

// Create VMTranslator class
public class VMTranslator {

    // Variable to keep track of return address in CodeWriter
    private static int n;

    // Main method
    public static void main(String[] args) {

        // Prompt user for file
        String subFile = args[0];

        // *** TRIAL ***
        System.out.println("VM file to be translated: " + subFile + ".vm");

        // Initialize folder
        String folderName;

        // Compute folder name
        if (subFile.contains("Main") || subFile.contains("Sys") || subFile.contains("SimpleFunction") || subFile.contains("Class1") 
        || subFile.contains("Class2")) {

            folderName = "FunctionCalls";

        } else if (subFile.contains("BasicLoop") || subFile.contains("FibonacciSeries")) {

            folderName = "ProgramFlow";

        } else {

            throw new IllegalArgumentException("Please Enter valid file name.");

        }

        // *** TRIAL ***
        System.out.println("FunctionCalls or ProgramFlow? " + folderName);

        // Read subFolderName
        Scanner scanner1 = new Scanner(System.in);

        System.out.print("Sub-Folder name please: ");

        String subFolderName = scanner1.nextLine().trim();

        scanner1.close();

        // Send fileName variable to CodeWriter class
        CodeWriter.fName(subFile);

        // *** TRIAL ***
        System.out.println("Sub-Folder: " + subFolderName);

        // Create fileCopyOne
        ArrayList<String> fileCopy = new ArrayList<>();

        // Try statement
        try {

            // Create Scanner object
            Scanner scanner2 = new Scanner(new File("../" + folderName + "/" + subFolderName + "/" + subFile + ".vm"));

            // Add all lines to fileCopyOne ArrayList
            while (scanner2.hasNextLine()) {

                String line = scanner2.nextLine().trim();
                fileCopy.add(line);

            }

            scanner2.close();

        // Catch statement
        } catch (FileNotFoundException err) {

            System.out.println("Please enter valid file.");

        }

        // Remove comments by looping backwards
        for (int i = fileCopy.size() - 1; i >= 0; i--) {

          if (fileCopy.get(i).contains("//")) {

            int position = fileCopy.get(i).indexOf("//");
            String instrOnly = ((fileCopy.get(i)).substring(0, position)).trim();
            fileCopy.set(i, instrOnly);

          }  

        }

        // Remove empty lines by looping backwards
        for (int i = fileCopy.size() - 1; i >= 0; i--) {

            if (fileCopy.get(i).isEmpty()) {

                fileCopy.remove(i);

            }

        }

        // *** TRIAL ***
        System.out.println("Current state of VM instructions to be parsed: " + fileCopy);

        // Open file and erase
        try (FileWriter asm = new FileWriter("../asmInstructions/" + subFile + ".asm", false);) {

            asm.write("");

        } catch (IOException err) {

            err.printStackTrace();

        }

        // Pass each instruction to Parser class
        for (int i = 0; i < fileCopy.size(); i++) {

            // Retrieve instructions and use parse() method from Parser class
            ArrayList<String> instrArrList = Parser.parse(fileCopy.get(i));

            if (i == 0) {

                n = 0;

            }

            // Use incN() from CodeWriter class to keep track of return address label
            CodeWriter.incN(n);

            // Use writeAsm() from CodeWriter class to write assembly
            CodeWriter.writeAsm(instrArrList);

        }

    }

}
