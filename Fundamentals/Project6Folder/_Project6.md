# Project 6 - Assembler

Content relating to project 6 is presented in this file only due to the fact that the assembler is the primary focus of this project. In this project, as described above, the main task was to create an assembler. The purpose of the assembler is simple: Convert HACK assembly code into machine code - or binary - used by the HACK computer. The realization however is not as simple. Python - which is a high level programming language - was used to implement the assembler. The overall flow of the program and the techniques used to achieve translation of the assembly code are explained below. See `projects/6/Assembler.py` for context.

The python code for the assembler may be split into three parts: 

- 1. **Initialization**
- 2. **Formatting**
- 3. **First pass**
- 4. **Second pass**

In the initialization stage, the dictionaries are manually set to include any predefined symbols and C-type `instruction bits`. The symbol table - `symbolTable` - dictionary stores any predefined symbols. The C-type instruction bits are stored in dictionaries including the **_destination_**, **_computation bits_** (_crucial for the ALU_) and **_jump bits_**. In addition to these bits there is also an a-Bit in the C-type instruction. This a-Bit is used to determine whether the computation by the ALU accesses the `A-Register` or whether it accesses the value stored in `RAM[A]`.

The assembly instructions are formatted by adding all valid lines into a new list. This list does not have any comments or empty lines. The purpose of this list is to be `iterated` through in the `second pass`.

In the first pass the assembly code is read again, but this time, any labels are `stored as keys` in the symbol table. The respective value for any given label (key) is the _n<sup>th</sup>_ instruction at which the label appears in the assembly instructions.

Note: _**The significance of the symbol table is that it is used to look up symbols for A-type instructions and therefore directly influences the way the A-type instruction is processed. It is also used to store labels as explained above.**_

The second pass is where the binary is parsed. Parsing occurs by iterating through the list created to store assembly instructions where every (16-Bit) instruction (whether A-type or C-type) is printed on a `new line`. As iteration occurs through the list of instructions, each symbol/C-type instruction bit is parsed into the appropriate binary to the output file `Prog.hack`.

The flow diagram below depicts the operations occurring as the assembler runs:

```
+------------------+
|  Initialization  |
| (Set up symbol   |
|  tables, etc.)   |
+------------------+
         |
         v
+------------------+
|   Formatting     |
| (Clean and store |
|  valid lines)    |
+------------------+
         |
         v
+------------------+
|   First Pass     |
| (Store labels in |
|  symbol table)   |
+------------------+
         |
         v
+------------------+
|   Second Pass    |
| (Parse and write |
|  binary output)  |
+------------------+
```