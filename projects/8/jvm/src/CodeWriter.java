// Package location
package src;

// Imports
import java.util.ArrayList;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

// Create CodeWriter class
public class CodeWriter {

    // Initialize variable to keep track of function
    private static String currentFunction = "";

    // Create segments HashMap
    private static HashMap <String, String> segments = new HashMap<>() {{

        put("local", "LCL");
        put("argument", "ARG");
        put("this", "THIS");
        put("that", "THAT");

    }};

    // Create enum
    public enum instruction {

        PUSH,
        POP,
        ADD,
        SUB,
        NEG,
        EQ,
        LT,
        GT,
        AND,
        OR,
        NOT,
        LABEL,
        GOTO,
        IFGOTO,
        FUNCTION,
        CALL,
        RETURN

    }

    // Static variable for name of file
    private static String fileName;

    // Static variable for return address incrementor
    private static Integer n = 0;

    // Retrieve fileName from VMTranslator class
    public static void fName(String name) {

        fileName = name;

    }

    public static void incN (Integer number) {

        n = number;

    }

    // Static variable for jump label counter
    private static int labelCounter = 0;

    // Write bootstrap code at the start of asm files
    public static ArrayList<String> writeBootStrap() {
        ArrayList<String> boot = new ArrayList<>();

        boot.add("@256");
        boot.add("D=A");
        boot.add("@SP");
        boot.add("M=D");
        boot.add("@RET_ADD" + "Sys.init" + "_" + String.valueOf(n));
        boot.add("D=A");
        boot.add("@SP");
        boot.add("A=M");
        boot.add("M=D");
        boot.add("@SP");
        boot.add("M=M+1");
        boot.add("@LCL");
        boot.add("D=M");
        boot.add("@SP");
        boot.add("A=M");
        boot.add("M=D");
        boot.add("@SP");
        boot.add("M=M+1");
        boot.add("@ARG");
        boot.add("D=M");
        boot.add("@SP");
        boot.add("A=M");
        boot.add("M=D");
        boot.add("@SP");
        boot.add("M=M+1");
        boot.add("@THIS");
        boot.add("D=M");
        boot.add("@SP");
        boot.add("A=M");
        boot.add("M=D");
        boot.add("@SP");
        boot.add("M=M+1");
        boot.add("@THAT");
        boot.add("D=M");
        boot.add("@SP");
        boot.add("A=M");
        boot.add("M=D");
        boot.add("@SP");
        boot.add("M=M+1");
        boot.add("@SP");
        boot.add("D=M");
        boot.add("@5");
        boot.add("D=D-A");
        boot.add("@ARG");
        boot.add("M=D");
        boot.add("@SP");
        boot.add("D=M");
        boot.add("@LCL");
        boot.add("M=D");
        boot.add("@Sys.init");
        boot.add("0;JMP");
        boot.add("(RET_ADD" + "Sys.init" + "_" + String.valueOf(n) + ")");
        n++;
        return boot;
    }

    

    // Write assembly using retrieved instruction
    public static void writeAsm(ArrayList<String> instr) {

        // Initialize instruction
        String instruction_case = (instr.get(0)).toUpperCase();

        // Check if instruction includes - for IF-GOTO
        if (instruction_case.contains("-")) {

            instruction_case = instruction_case.replace("-", "");

        }

        // Evaluating instruction type
        switch (instruction.valueOf(instruction_case)) {

            case PUSH:

                push(instr);

                break;

            case POP:

                pop(instr);

                break;

            case ADD:

                try (FileWriter asm = new FileWriter("../asmInstructions/" + fileName + ".asm", true)) {

                    asm.write("@SP" + "\n");
                    asm.write("AM=M-1" + "\n");
                    asm.write("D=M" + "\n");
                    asm.write("A=A-1" + "\n");
                    asm.write("M=M+D" + "\n");

                } catch (IOException err) {

                    err.printStackTrace();

                }

                break;

            case SUB:

                try (FileWriter asm = new FileWriter("../asmInstructions/" + fileName + ".asm", true)) {

                    asm.write("@SP" + "\n");
                    asm.write("AM=M-1" + "\n");
                    asm.write("D=M" + "\n");
                    asm.write("A=A-1" + "\n");
                    asm.write("M=M-D" + "\n");

                } catch (IOException err) {

                    err.printStackTrace();

                }

                break;

            case NEG:

                try (FileWriter asm = new FileWriter("../asmInstructions/" + fileName + ".asm", true)) {

                    asm.write("@SP" + "\n");
                    asm.write("A=M-1" + "\n");
                    asm.write("M=-M" + "\n");

                } catch (IOException err) {

                    err.printStackTrace();

                }

                break;

            case EQ:

                try (FileWriter asm = new FileWriter("../asmInstructions/" + fileName + ".asm", true)) {

                    String labelTrue = "EQ_TRUE_" + labelCounter;
                    String labelEnd = "EQ_END_" + labelCounter;
                    labelCounter++;

                    asm.write("@SP" + "\n");
                    asm.write("AM=M-1" + "\n");
                    asm.write("D=M" + "\n");
                    asm.write("A=A-1" + "\n");
                    asm.write("D=M-D" + "\n");
                    asm.write("@" + labelTrue + "\n");
                    asm.write("D;JEQ" + "\n");
                    asm.write("@SP" + "\n");
                    asm.write("A=M-1" + "\n");
                    asm.write("M=0" + "\n");           
                    asm.write("@" + labelEnd + "\n");
                    asm.write("0;JMP" + "\n");         
                    asm.write("(" + labelTrue + ")\n");
                    asm.write("@SP" + "\n");
                    asm.write("A=M-1" + "\n");
                    asm.write("M=-1" + "\n");
                    asm.write("(" + labelEnd + ")\n");

                } catch (IOException err) {

                    err.printStackTrace();

                }

                break;

            case LT:

                try (FileWriter asm = new FileWriter("../asmInstructions/" + fileName + ".asm", true)) {

                    String labelTrue = "LT_TRUE_" + labelCounter;
                    String labelEnd = "LT_END_" + labelCounter;
                    labelCounter++;

                    asm.write("@SP" + "\n");
                    asm.write("AM=M-1" + "\n");
                    asm.write("D=M" + "\n");
                    asm.write("A=A-1" + "\n");
                    asm.write("D=M-D" + "\n");
                    asm.write("@" + labelTrue + "\n");
                    asm.write("D;JLT" + "\n");
                    asm.write("@SP" + "\n");
                    asm.write("A=M-1" + "\n");
                    asm.write("M=0" + "\n");
                    asm.write("@" + labelEnd + "\n");
                    asm.write("0;JMP" + "\n");
                    asm.write("(" + labelTrue + ")\n");
                    asm.write("@SP" + "\n");
                    asm.write("A=M-1" + "\n");
                    asm.write("M=-1" + "\n");
                    asm.write("(" + labelEnd + ")\n");
                } catch (IOException err) {
                    err.printStackTrace();
                }
                break;

            case GT:

                try (FileWriter asm = new FileWriter("../asmInstructions/" + fileName + ".asm", true)) {
                    String labelTrue = "GT_TRUE_" + labelCounter;
                    String labelEnd = "GT_END_" + labelCounter;
                    labelCounter++;

                    asm.write("@SP" + "\n");
                    asm.write("AM=M-1" + "\n");
                    asm.write("D=M" + "\n");
                    asm.write("A=A-1" + "\n");
                    asm.write("D=M-D" + "\n");
                    asm.write("@" + labelTrue + "\n");
                    asm.write("D;JGT" + "\n");
                    asm.write("@SP" + "\n");
                    asm.write("A=M-1" + "\n");
                    asm.write("M=0" + "\n");
                    asm.write("@" + labelEnd + "\n");
                    asm.write("0;JMP" + "\n");
                    asm.write("(" + labelTrue + ")\n");
                    asm.write("@SP" + "\n");
                    asm.write("A=M-1" + "\n");
                    asm.write("M=-1" + "\n");
                    asm.write("(" + labelEnd + ")\n");
                } catch (IOException err) {
                    err.printStackTrace();
                }
                break;

            case AND:

                try (FileWriter asm = new FileWriter("../asmInstructions/" + fileName + ".asm", true)) {

                    asm.write("@SP" + "\n");
                    asm.write("AM=M-1" + "\n");
                    asm.write("D=M" + "\n");
                    asm.write("A=A-1" + "\n");
                    asm.write("M=M&D" + "\n");

                } catch (IOException err) {

                    err.printStackTrace();

                }

                break;

            case OR:

                try (FileWriter asm = new FileWriter("../asmInstructions/" + fileName + ".asm", true)) {

                    asm.write("@SP" + "\n");
                    asm.write("AM=M-1" + "\n");
                    asm.write("D=M" + "\n");
                    asm.write("A=A-1" + "\n");
                    asm.write("M=M|D" + "\n");

                } catch (IOException err) {

                    err.printStackTrace();

                }

                break;

            case NOT:

                try (FileWriter asm = new FileWriter("../asmInstructions/" + fileName + ".asm", true)) {

                    asm.write("@SP" + "\n");
                    asm.write("A=M-1" + "\n");
                    asm.write("M=!M" + "\n");

                } catch (IOException err) {

                    err.printStackTrace();

                }

                break;

            case LABEL:

                try (FileWriter asm = new FileWriter("../asmInstructions/" + fileName + ".asm", true)) {

                    asm.write("(" + currentFunction + "$" + instr.get(1) + ")" + "\n");

                } catch (IOException err) {

                        err.printStackTrace();

                }

                break;

            case GOTO:

                try (FileWriter asm = new FileWriter("../asmInstructions/" + fileName + ".asm", true)) {

                    asm.write("@" + currentFunction + "$" + instr.get(1) + "\n");
                    asm.write("0;JMP" + "\n");

                } catch (IOException err) {

                        err.printStackTrace();

                }

                break;

            case IFGOTO:

                try (FileWriter asm = new FileWriter("../asmInstructions/" + fileName + ".asm", true)) {

                    asm.write("@SP" + "\n");
                    asm.write("AM=M-1" + "\n");
                    asm.write("D=M" + "\n");
                    asm.write("@" + currentFunction + "$" + instr.get(1) + "\n");
                    asm.write("D;JNE" + "\n");

                } catch (IOException err) {

                        err.printStackTrace();

                }

                break;

            case FUNCTION:

                // Set current function
                currentFunction = instr.get(1);

                try (FileWriter asm = new FileWriter("../asmInstructions/" + fileName + ".asm", true)) {

                    asm.write("(" + instr.get(1) + ")" + "\n");

                } catch (IOException err) {

                    err.printStackTrace();

                }

                // For loop to iterate through local variable initialization
                for (int i = 0; i < Integer.parseInt(instr.get(2)); i++) {

                    try (FileWriter asm = new FileWriter("../asmInstructions/" + fileName + ".asm", true)) {

                        asm.write("@SP" + "\n");
                        asm.write("A=M" + "\n");
                        asm.write("M=0" + "\n");
                        asm.write("@SP" + "\n");
                        asm.write("M=M+1" + "\n");

                    } catch (IOException err) {

                            err.printStackTrace();

                    }
                
                }

                break;

            case CALL:
                
                try (FileWriter asm = new FileWriter("../asmInstructions/" + fileName + ".asm", true)) {

                    asm.write("@RET_ADD" + currentFunction + "_" + String.valueOf(n) + "\n");
                    asm.write("D=A" + "\n");
                    asm.write("@SP" + "\n");
                    asm.write("A=M" + "\n");
                    asm.write("M=D" + "\n");
                    asm.write("@SP" + "\n");
                    asm.write("M=M+1" + "\n");
                    asm.write("@LCL" + "\n");
                    asm.write("D=M" + "\n");
                    asm.write("@SP" + "\n");
                    asm.write("A=M" + "\n");
                    asm.write("M=D" + "\n");
                    asm.write("@SP" + "\n");
                    asm.write("M=M+1" + "\n");
                    asm.write("@ARG" + "\n");
                    asm.write("D=M" + "\n");
                    asm.write("@SP" + "\n");
                    asm.write("A=M" + "\n");
                    asm.write("M=D" + "\n");
                    asm.write("@SP" + "\n");
                    asm.write("M=M+1" + "\n");
                    asm.write("@THIS" + "\n");
                    asm.write("D=M" + "\n");
                    asm.write("@SP" + "\n");
                    asm.write("A=M" + "\n");
                    asm.write("M=D" + "\n");
                    asm.write("@SP" + "\n");
                    asm.write("M=M+1" + "\n");
                    asm.write("@THAT" + "\n");
                    asm.write("D=M" + "\n");
                    asm.write("@SP" + "\n");
                    asm.write("A=M" + "\n");
                    asm.write("M=D" + "\n");
                    asm.write("@SP" + "\n");
                    asm.write("M=M+1" + "\n");
                    asm.write("@SP" + "\n");
                    asm.write("D=M" + "\n");
                    asm.write("@" + instr.get(2) + "\n");
                    asm.write("D=D-A" + "\n");
                    asm.write("@5" + "\n");
                    asm.write("D=D-A" + "\n");
                    asm.write("@ARG" + "\n");
                    asm.write("M=D" + "\n");
                    asm.write("@SP" + "\n");
                    asm.write("D=M" + "\n");
                    asm.write("@LCL" + "\n");
                    asm.write("M=D" + "\n");
                    asm.write("@" + instr.get(1) + "\n");
                    asm.write("0;JMP" + "\n");
                    asm.write("(RET_ADD" + currentFunction + "_" + String.valueOf(n) + ")" + "\n");
                    n++;

                } catch (IOException err) {

                        err.printStackTrace();

                }

                break;

            case RETURN:

                try (FileWriter asm = new FileWriter("../asmInstructions/" + fileName + ".asm", true)) {

                    asm.write("@LCL" + "\n");
                    asm.write("D=M" + "\n");
                    asm.write("@R13" + "\n");
                    asm.write("M=D" + "\n");
                    asm.write("@5" + "\n");
                    asm.write("A=D-A" + "\n");
                    asm.write("D=M" + "\n");
                    asm.write("@R14" + "\n");
                    asm.write("M=D" + "\n");
                    asm.write("@SP" + "\n");
                    asm.write("AM=M-1" + "\n");
                    asm.write("D=M" + "\n");
                    asm.write("@ARG" + "\n");
                    asm.write("A=M" + "\n");
                    asm.write("M=D" + "\n");
                    asm.write("@ARG" + "\n");
                    asm.write("D=M+1" + "\n");
                    asm.write("@SP" + "\n");
                    asm.write("M=D" + "\n");
                    asm.write("@R13" + "\n");
                    asm.write("AM=M-1" + "\n");
                    asm.write("D=M" + "\n");
                    asm.write("@THAT" + "\n");
                    asm.write("M=D" + "\n");
                    asm.write("@R13" + "\n");
                    asm.write("AM=M-1" + "\n");
                    asm.write("D=M" + "\n");
                    asm.write("@THIS" + "\n");
                    asm.write("M=D" + "\n");
                    asm.write("@R13" + "\n");
                    asm.write("AM=M-1" + "\n");
                    asm.write("D=M" + "\n");
                    asm.write("@ARG" + "\n");
                    asm.write("M=D" + "\n");
                    asm.write("@R13" + "\n");
                    asm.write("AM=M-1" + "\n");
                    asm.write("D=M" + "\n");
                    asm.write("@LCL" + "\n");
                    asm.write("M=D" + "\n");
                    asm.write("@R14" + "\n");
                    asm.write("A=M" + "\n");
                    asm.write("0;JMP" + "\n");

                } catch (IOException err) {

                        err.printStackTrace();

                }

                break;

        }

    }

    // Define push() method
    public static void push(ArrayList<String> instr) {

        // Evaluate PUSH instruction
        if (instr.get(1).contains("constant")) {

            try (FileWriter asm = new FileWriter("../asmInstructions/" + fileName + ".asm", true)) {

                asm.write("@" + instr.get(2) + "\n");
                asm.write("D=A" + "\n");
                asm.write("@SP" + "\n");
                asm.write("A=M" + "\n");
                asm.write("M=D" + "\n");
                asm.write("@SP" + "\n");
                asm.write("M=M+1" + "\n");

            } catch (IOException err) {

                err.printStackTrace();

            }

        } else if (instr.get(1).contains("temp")) {

            try (FileWriter asm = new FileWriter("../asmInstructions/" + fileName + ".asm", true)) {

                asm.write("@" + instr.get(2) + "\n");
                asm.write("D=A" + "\n");
                asm.write("@5" + "\n");
                asm.write("A=D+A" + "\n");
                asm.write("D=M" + "\n");
                asm.write("@SP" + "\n");
                asm.write("A=M" + "\n");
                asm.write("M=D" + "\n");
                asm.write("@SP" + "\n");
                asm.write("M=M+1" + "\n");

            } catch (IOException err) {

                err.printStackTrace();

            }

        } else if (instr.get(1).contains("pointer") && instr.get(2).contains("0")) {

            try (FileWriter asm = new FileWriter("../asmInstructions/" + fileName + ".asm", true)) {

                asm.write("@THIS" + "\n");
                asm.write("D=M" + "\n");
                asm.write("@SP" + "\n");
                asm.write("A=M" + "\n");
                asm.write("M=D" + "\n");
                asm.write("@SP" + "\n");
                asm.write("M=M+1" + "\n");

            } catch (IOException err) {

                err.printStackTrace();

            }

        } else if (instr.get(1).contains("pointer") && instr.get(2).contains("1")) {

            try (FileWriter asm = new FileWriter("../asmInstructions/" + fileName + ".asm", true)) {

                asm.write("@THAT" + "\n");
                asm.write("D=M" + "\n");
                asm.write("@SP" + "\n");
                asm.write("A=M" + "\n");
                asm.write("M=D" + "\n");
                asm.write("@SP" + "\n");
                asm.write("M=M+1" + "\n");

            } catch (IOException err) {

                err.printStackTrace();

            }

        } else if (instr.get(1).contains("static")) {

            try (FileWriter asm = new FileWriter("../asmInstructions/" + fileName + ".asm", true)) {

                asm.write("@" + fileName + "." + instr.get(2) + "\n");
                asm.write("D=M" + "\n");
                asm.write("@SP" + "\n");
                asm.write("A=M" + "\n");
                asm.write("M=D" + "\n");
                asm.write("@SP" + "\n");
                asm.write("M=M+1" + "\n");

            } catch (IOException err) {

                err.printStackTrace();

            }

        } else {

            try (FileWriter asm = new FileWriter("../asmInstructions/" + fileName + ".asm", true)) {

                asm.write("@" + instr.get(2) + "\n");
                asm.write("D=A" + "\n");
                asm.write("@" + segments.get(instr.get(1)) + "\n");
                asm.write("D=D+M" + "\n");
                asm.write("A=D" + "\n");
                asm.write("D=M" + "\n");
                asm.write("@SP" + "\n");
                asm.write("A=M" + "\n");
                asm.write("M=D" + "\n");
                asm.write("@SP" + "\n");
                asm.write("M=M+1" + "\n");

            } catch (IOException err) {

                err.printStackTrace();

            }

        }

    }

    public static void pop(ArrayList<String> instr) {

        // Evaluate POP instruction
        if (instr.get(1).contains("temp")) {

            try (FileWriter asm = new FileWriter("../asmInstructions/" + fileName + ".asm", true)) {

                asm.write("@" + instr.get(2) + "\n");
                asm.write("D=A" + "\n");
                asm.write("@5" + "\n");
                asm.write("D=A+D" + "\n");
                asm.write("@R13" + "\n");
                asm.write("M=D" + "\n");
                asm.write("@SP" + "\n");
                asm.write("M=M-1" + "\n");
                asm.write("A=M" + "\n");
                asm.write("D=M" + "\n");
                asm.write("@R13" + "\n");
                asm.write("A=M" + "\n");
                asm.write("M=D" + "\n");

            } catch (IOException err) {

                err.printStackTrace();

            }

        } else if (instr.get(1).contains("pointer") && instr.get(2).contains("0")) {

            try (FileWriter asm = new FileWriter("../asmInstructions/" + fileName + ".asm", true)) {

                asm.write("@SP" + "\n");
                asm.write("M=M-1" + "\n");
                asm.write("A=M" + "\n");
                asm.write("D=M" + "\n");
                asm.write("@THIS" + "\n");
                asm.write("M=D" + "\n");

            } catch (IOException err) {

                err.printStackTrace();

            }

        } else if (instr.get(1).contains("pointer") && instr.get(2).contains("1")) {

            try (FileWriter asm = new FileWriter("../asmInstructions/" + fileName + ".asm", true)) {

                asm.write("@SP" + "\n");
                asm.write("M=M-1" + "\n");
                asm.write("A=M" + "\n");
                asm.write("D=M" + "\n");
                asm.write("@THAT" + "\n");
                asm.write("M=D" + "\n");

            } catch (IOException err) {

                err.printStackTrace();

            }

        } else if (instr.get(1).contains("static")) {

            try (FileWriter asm = new FileWriter("../asmInstructions/" + fileName + ".asm", true)) {

                asm.write("@SP" + "\n");
                asm.write("M=M-1" + "\n");
                asm.write("A=M" + "\n");
                asm.write("D=M" + "\n");
                asm.write("@" + fileName + "." + instr.get(2) + "\n");
                asm.write("M=D" + "\n");

            } catch (IOException err) {

                err.printStackTrace();

            }

        } else {

            try (FileWriter asm = new FileWriter("../asmInstructions/" + fileName + ".asm", true)) {

                asm.write("@" + instr.get(2) + "\n");
                asm.write("D=A" + "\n");
                asm.write("@" + segments.get(instr.get(1)) + "\n");
                asm.write("D=M+D" + "\n");
                asm.write("@R13" + "\n");
                asm.write("M=D" + "\n");
                asm.write("@SP" + "\n");
                asm.write("M=M-1" + "\n");
                asm.write("A=M" + "\n");
                asm.write("D=M" + "\n");
                asm.write("@R13" + "\n");
                asm.write("A=M" + "\n");
                asm.write("M=D" + "\n");

            } catch (IOException err) {

                err.printStackTrace();

            }

        }

    }

}
