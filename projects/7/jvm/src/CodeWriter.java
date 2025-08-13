// Package location
package src;

// Imports
import java.util.ArrayList;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

// Create CodeWriter class
public class CodeWriter {

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
        NOT

    }

    // Static variable for name of file
    private static String fileName;

    // Retrieve fileName from VMTranslator class
    public static void fName(String name) {

        fileName = name;

    }

    // Static variable for jump label counter
    private static int labelCounter = 0;
    
    // Write assembly using retrieved instruction
    public static void writeAsm(ArrayList<String> instr) {

        // Evaluating instruction type
        switch (instruction.valueOf((instr.get(0)).toUpperCase())) {

            case PUSH:

                push(instr);

                break;

            case POP:

                pop(instr);

                break;

            case ADD:

                try (FileWriter asm = new FileWriter(fileName + ".asm", true)) {

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

                try (FileWriter asm = new FileWriter(fileName + ".asm", true)) {

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

                try (FileWriter asm = new FileWriter(fileName + ".asm", true)) {

                    asm.write("@SP" + "\n");
                    asm.write("A=M-1" + "\n");
                    asm.write("M=-M" + "\n");

                } catch (IOException err) {

                    err.printStackTrace();

                }

                break;

            case EQ:

                try (FileWriter asm = new FileWriter(fileName + ".asm", true)) {

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

                try (FileWriter asm = new FileWriter(fileName + ".asm", true)) {

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

                try (FileWriter asm = new FileWriter(fileName + ".asm", true)) {
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

                try (FileWriter asm = new FileWriter(fileName + ".asm", true)) {

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

                try (FileWriter asm = new FileWriter(fileName + ".asm", true)) {

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

                try (FileWriter asm = new FileWriter(fileName + ".asm", true)) {

                    asm.write("@SP" + "\n");
                    asm.write("A=M-1" + "\n");
                    asm.write("M=!M" + "\n");

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

            try (FileWriter asm = new FileWriter(fileName + ".asm", true)) {

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

            try (FileWriter asm = new FileWriter(fileName + ".asm", true)) {

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

            try (FileWriter asm = new FileWriter(fileName + ".asm", true)) {

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

            try (FileWriter asm = new FileWriter(fileName + ".asm", true)) {

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

            try (FileWriter asm = new FileWriter(fileName + ".asm", true)) {

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

            try (FileWriter asm = new FileWriter(fileName + ".asm", true)) {

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

            try (FileWriter asm = new FileWriter(fileName + ".asm", true)) {

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

            try (FileWriter asm = new FileWriter(fileName + ".asm", true)) {

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

            try (FileWriter asm = new FileWriter(fileName + ".asm", true)) {

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

            try (FileWriter asm = new FileWriter(fileName + ".asm", true)) {

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

            try (FileWriter asm = new FileWriter(fileName + ".asm", true)) {

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
