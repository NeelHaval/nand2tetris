// This file is part of www.nand2tetris.org
// and the book "The Elements of Computing Systems"
// by Nisan and Schocken, MIT Press.
// File name: projects/4/Mult.asm

// Multiplies R0 and R1 and stores the result in R2.
// (R0, R1, R2 refer to RAM[0], RAM[1], and RAM[2], respectively.)
// The algorithm is based on repetitive addition.



// Set up variables:



// R0 (loop addition variable)

@R0
D=M
@add // RAM[16] = loop addition variable
M=D

// R1 (loop counter variable)

@R1
D=M
@counter // RAM[17] = loop counter variable
M=D

// R2 (multiple)

@R2 // RAM[2] = multiple
M=0

// Icrement variable

@inc // RAM[18] = increment variable
M=0

// Loop to add R0 to R2, R1 times:



(LOOP)
@inc // Load increment variable
D=M
@counter // Check if inc == counter
D=D-M
@END // If inc == counter, end loop
D;JEQ



@R2 // Add value for nth time
D=M
@add // Load loop addition variable
D=D+M

@R2 // Store value in R2 (multiple)
M=D

@inc // Increment the inc
M=M+1

@LOOP // Check if current inc == R1 (counter)
0;JMP

(END)
@END
0;JMP