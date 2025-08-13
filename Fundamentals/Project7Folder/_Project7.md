# Project 7 - VM Translator

Project 7 serves as the basis for the VM Translator. The purpose of the VM Translator is to convert VM instructions (e.g. `push x segment`) to assembly instructions - which the assembler implemented in the last project can translate into binary machine code. This project only deals with the initial implementation of the VM Translator as described in the `implementation` section. This file in `Fundamentals/Project7Folder/` includes information on the implementation of the VM Translator, the challenges faces during the process and the foundations used to realize the translator. The sections are summarized below:

- 1. **Implementation**
- 2. **Challenges**
- 3. **Foundations**

## Implementation

The programming language used in this project was java. This language was chosen as it has very **`strict type`** rules where the compiler this. Another advantage is that java also allows for **`easy encapsulation`** of methods simplifying code and greatly enhancing readability. The source code folder is located in `projects/7/jvm/`. From the package declaration at the top of each file, it is possible to see that the java code is contained within `src/`. The implementation consists of three classes:

- **VMTranslator.java** - The entry point into the VM Translator algorithm. It contains the main method and sends and receives input to and from the classes mentioned below. Crucially, the class is also responsible for ensuring the inputs passed to the `Parser.java` and `CodeWriter.java` classes is ready to be processed. This consists of stripping away any empty lines and comments ensuring only raw assembly is passed onto the appropriate methods. Specifically, this class sends `Parser.java` each file instruction line by line. This decision was made as it simplifies the translation process later in the flow.

- **Parser.java** - The only job of this class is to receive raw file input, filter this into clear VM instructions, and resend these instructions back to `VMTranslator.java`. It is the smallest class but still allows for a clear distinction between the process of reading VM instructions and outputting assembly.

- **CodeWriter.java** - This class is where all of the output assembly code is written to the output file. It receives clear instructions as formatted by `Parser.java` and uses these instructions to write the appropriate assembly code. Java supports **`enumerations`** and **`HashMaps`** which allowed the instructions to be stored and associated with assembly. A greater emphasis on code clarity and efficiency was places in this project and so **`switch statements`** were used to decide the order of execution as switch statements work faster than conventional `if-else` logic.

## Challenges

The main challenge with the project was deciding how to link the classes together using various methods in java. Doing this in turn strengthened understanding of scope in java with respect to variables and methods in different classes. Another significant challenge was translating the assembly code from VM instructions. A large number of assembly lines had to be printed out to the output file for each VM instruction resulting in slightly clunky file management. An improvement to this would be to use `bufferReader` instead which works faster with larger data.

## Foundations

The implementation described above relies heavily on stack logic where values are either **_pushed_** onto the top of the stack or they are **_popped_** off the top of the stack. The stack is stored on RAM along with other `segments` in memory each designed to store various types of values. These segments include:

- SP - RAM[0]
- LCL - RAM [1]
- ARG - RAM[2]
- THIS - RAM[3]
- THAT - RAM[4]
- TEMP - RAM[5] to RAM[12]
- STATIC - RAM[16] to RAM[255]
- Stack - RAM[256] at the start

The values pushed/popped onto/off the stack come from the addresses shown above and are unique to the computation currently being processed by the hardware. Below is a flow diagram showing this arrangement:

```
| Address | Segment   |
|---------|----------|
|   0     | SP       |  // Stack Pointer
|   1     | LCL      |  // Local segment base
|   2     | ARG      |  // Argument segment base
|   3     | THIS     |  // This segment base
|   4     | THAT     |  // That segment base
|  5-12   | TEMP     |  // Temporary segment (Fixed)
| 16-255  | STATIC   |  // Static variables (Fixed)
| 256-... | STACK    |  // Stack grows upward
| ...     | (Other)  |
```

Note: **_`Fixed` above means that the base address of the temporary and static segments are fixed. For other segments such as SP and LCL these base addresses are mutable - meaning they can change._**