package src;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class asmCombine {
    
    public static void main(String[] args) {

        // Read subFolderName
        Scanner scanner = new Scanner(System.in);

        System.out.print("Sub-Folder name please: ");

        String subFolderName = scanner.nextLine().trim();

        scanner.close();

        // Initialize folder name and fileName
        String overallDirectory=  null;
        String subFile = null;
        String sys = "Sys";
        Boolean hasSys = false;

        if (subFolderName.contains("FibonacciElement") || subFolderName.contains("NestedCall") || subFolderName.contains("SimpleFunction")
        || subFolderName.contains("StaticsTest")) {

            overallDirectory = "FunctionCalls";

        } else if (subFolderName.contains("BasicLoop") || subFolderName.contains("FibonacciSeries")) {

            overallDirectory = "ProgramFlow";

        }

        // Initialize empty ArrayList to store asm lines
        ArrayList<String> asmCopy = new ArrayList<>();

        for (int i = 0; i < args.length; i++) {

            subFile = args[i].trim();

            try {

                // Create Scanner object - Enter file name without asm extension
                Scanner scanner2 = new Scanner(new File("../asmInstructions/" + subFile + ".asm"));

                // Add all lines to fileCopyOne ArrayList
                while (scanner2.hasNextLine()) {

                    String line = scanner2.nextLine().trim();
                    asmCopy.add(line);

                }

                scanner2.close();

            // Catch statement
            } catch (FileNotFoundException err) {

                System.out.println("Please enter valid file.");

            }

        }

        for (int i = 0; i < args.length; i++) {

            if (args[i].trim().contains(sys)) {

                hasSys = true;
                break;

            }

        }

        // Open file and erase
        try (FileWriter asm = new FileWriter("../" + overallDirectory + "/" + subFolderName + "/" + subFolderName + ".asm", false);) {

            asm.write("");

        } catch (IOException err) {

            err.printStackTrace();

        }

        // Write to FINAL asm file
        try (FileWriter asm = new FileWriter("../" + overallDirectory + "/" + subFolderName + "/" + subFolderName + ".asm", true)) {

            if (hasSys) {

                ArrayList<String> boot = CodeWriter.writeBootStrap();

                for (int element = 0; element < boot.size(); element++) {

                    asm.write(boot.get(element) + "\n");

                }

            }

            for (int element = 0; element < asmCopy.size(); element++) {

                asm.write(asmCopy.get(element) + "\n");

            }

        } catch (IOException err) {

            err.printStackTrace();

        }

    }

}
