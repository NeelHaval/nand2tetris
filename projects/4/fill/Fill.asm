// This file is part of www.nand2tetris.org
// and the book "The Elements of Computing Systems"
// by Nisan and Schocken, MIT Press.
// File name: projects/4/Fill.asm

// Runs an infinite loop that listens to the keyboard input. 
// When a key is pressed (any key), the program blackens the screen,
// i.e. writes "black" in every pixel. When no key is pressed, 
// the screen should be cleared.



// Set up screen variables



@8192
D=A
@R0
M=D // RAM[0] is assigned as the number of RAM modules in screen space



@CHECK
0;JMP // Jump to check for keyboard input

(CHECK)
@SCREEN
D=A
@sRAM
M=D // sRAM represents certain screen RAM modules in screen space

@inc
M=0 // increment variable

// Condition to check if keyboard is pressed
@24576
D=M // Store current keyboard value

@FILL_SCREEN
D;JNE // If a key is pressed, jump to FILL_SCREEN
@EMPTY_SCREEN
0;JMP // If no key is pressed, jump to EMPTY_SCREEN



(FILL_SCREEN) // Fill the screen with black pixels
@inc
D=M
@R0
D=D-M // Check if all pixles have been processed
@CHECK
D;JEQ // If processing complete, jump back to CHECK

@sRAM
A=M
M=-1 // Turns all 16 pixels of current RAM module black

@inc
M=M+1 // Increment the increment variable
@sRAM
M=M+1 // Go to the next RAM module
@FILL_SCREEN
0;JMP // Loop back to FILL_SCREEN



(EMPTY_SCREEN) // Clear the screen of black pixels

@inc
D=M
@R0
D=D-M // Check if all pixles have been processed
@CHECK
D;JEQ // If processing complete, jump back to CHECK

@sRAM
A=M
M=0 // Turns all 16 pixels of current RAM module white

@inc
M=M+1 // Increment the increment variable
@sRAM
M=M+1 // Go to the next RAM module
@EMPTY_SCREEN
0;JMP // Loop back to EMPTY_SCREEN