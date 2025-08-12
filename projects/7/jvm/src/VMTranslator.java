// Package location
package src;

// Imports
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

// Create VMTranslator class
public class VMTranslator {

    // Main method
    public static void main(String[] args) {

        // Prompt user for file
        String fileName = args[0];

        // *** TRIAL ***
        System.out.println("VM file to be translated: " + fileName);

        // Initialize folder
        String folderName;

        // Compute folder name
        if (fileName.contains("Basic") || fileName.contains("Pointer") || fileName.contains("Static")) {

            folderName = "MemoryAccess";

        } else if (fileName.contains("Simple") || fileName.contains("Stack")) {

            folderName = "StackArithmetic";

        } else {

            throw new IllegalArgumentException("Please Enter valid file name.");

        }

        // *** TRIAL ***
        System.out.println("MemoryAccess or StackArithmetic? " + folderName);

        // Initialize sub folders
        String subFolderName;

        // Compute sub folder name
        if (fileName.contains("Basic")) {

            subFolderName = "BasicTest";

        } else if (fileName.contains("Pointer")) {

            subFolderName = "PointerTest";
            
        } else if (fileName.contains("Static")) {

            subFolderName = "StaticTest";
            
        } else if (fileName.contains("Simple")) {

            subFolderName = "SimpleAdd";
            
        } else if (fileName.contains("Stack")) {

            subFolderName = "StackTest";
            
        } else {

            throw new IllegalArgumentException("Please Enter valid file name.");

        }

        // *** TRIAL ***
        System.out.println("Sub-Folder: " + subFolderName);

        // Create fileCopyOne
        ArrayList<String> fileCopy = new ArrayList<>();

        // Try statement
        try {

            // Create Scanner object
            Scanner scanner = new Scanner(new File("../" + folderName + "/" + subFolderName + "/" + fileName));

            // Add all lines to fileCopyOne ArrayList
            while (scanner.hasNextLine()) {

                String line = scanner.nextLine().trim();
                fileCopy.add(line);

            }

            scanner.close();
            
        // Catch statement
        } catch (FileNotFoundException err) {

            System.out.println("Please enter valid file.");

        }

        // Remove empty lines by looping backwards
        for (int i = fileCopy.size() - 1; i >= 0; i--) {

            if (fileCopy.get(i).isEmpty()) {

                fileCopy.remove(i);

            }

        }

        // Remove comments by looping backwards
        for (int i = fileCopy.size() - 1; i >= 0; i--) {

          if (fileCopy.get(i).contains("//")) {

            int position = fileCopy.get(i).indexOf("//");
            String instrOnly = ((fileCopy.get(i)).substring(0, position)).trim();
            fileCopy.set(i, instrOnly);

          }  

        }

        // *** TRIAL ***
        System.out.println("Current state of VM instructions to be parsed: " + fileCopy);

        // Pass each instruction to Parser class
        for (int i = 0; i < fileCopy.size(); i++) {

            // Retrieve instructions and use parse() method from Parser class
            ArrayList<String> instrArrList = Parser.parse(fileCopy.get(i));

            // Use writeAsm() from CodeWriter class to write assembly
            CodeWriter.writeAsm(instrArrList);

        }

    }

}
