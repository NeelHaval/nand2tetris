# Create dictionary to store predefined symbols and programmer defined labels and symbols (symbol table)
symbolTable = {

    "R0": "0",
    "R1": "1",
    "R2": "2",
    "R3": "3",
    "R4": "4",
    "R5": "5",
    "R6": "6",
    "R7": "7",
    "R8": "8",
    "R9": "9",
    "R10": "10",
    "R11": "11",
    "R12": "12",
    "R13": "13",
    "R14": "14",
    "R15": "15",

    "SP": "0",
    "LCL": "1",
    "ARG": "2",
    "THIS": "3",
    "THAT": "4",
    "SCREEN": "16384",
    "KBD": "24576"
    
}

# Create dictionaries to store predefined C-instruction operations along with all possible permutations

# C-instruction comp
comp = {
    "0": "101010",
    "1": "111111",
    "-1": "111010",
    "D": "001100",
    "A": "110000",
    "!D": "001101",
    "!A": "110001",
    "-D": "001111",
    "-A": "110011",
    "D+1": "011111",
    "A+1": "110111",
    "D-1": "001110",
    "A-1": "110010",
    "D+A": "000010",
    "D-A": "010011",
    "A-D": "000111",
    "D&A": "000000",
    "D|A": "010101",
    "M": "110000",
    "!M": "110001",
    "-M": "110011",
    "M+1": "110111",
    "M-1": "110010",
    "D+M": "000010",
    "D-M": "010011",
    "M-D": "000111",
    "D&M": "000000",
    "D|M": "010101"
}

# C-instruction dest
dest = {
    "null": "000",
    "M": "001",
    "D": "010",
    "DM": "011",
    "MD": "011",
    "A": "100",
    "AM": "101",
    "MA": "101",
    "AD": "110",
    "DA": "110",
    "ADM": "111",
    "AMD": "111",
    "DAM": "111",
    "DMA": "111",
    "MAD": "111",
    "MDA": "111"
}

# C-instruction jump
jump = {
    "null": "000",
    "JGT": "001",
    "JEQ": "010",
    "JGE": "011",
    "JLT": "100",
    "JNE": "101",
    "JLE": "110",
    "JMP": "111"
}

# Prompt user for the assembly file to be translated
userFile = input("What assembly code would you like translated? Please remember to provide the extension .asm: ")

# Check which folder the selected file is in
if userFile.startswith("Add"):
    folder = "add"
elif userFile.startswith("Max"):
    folder = "max"
elif userFile.startswith("Pong"):
    folder = "pong"
elif userFile.startswith("Rect"):
    folder = "rect"

# Create file to save cleaned assembly code
asmClean = []

# Store .asm file in variable and open
asmFile = open(f"projects/6/{folder}/{userFile}", "r")

# Read .asm file
asmCode = asmFile.readlines()

# Remove all white space and comments from assembly code in place
for asmLine in range(len(asmCode)):
    line = asmCode[asmLine].strip()
    if "//" in line:
        line = line[:line.index("//")].strip()
    asmCode[asmLine] = line

# At this point, if line is not empty it should be appended to asmClean
for line in asmCode:
    if line.strip() != "":
        asmClean.append(line.strip())

# Close the .asm file
asmFile.close()

# Function to process first pass
def passOne():
    
    # Assigning instruction increment variable
    instrInc = 0

    # For loop to iterate over asmClean
    for element in range(len(asmClean)):

        # Assigning line variable
        line = asmClean[element].strip()

        # If block for label check
        if "(" in line and ")" in line:
            labelStart = line.index("(")
            labelEnd = line.index(")")
            label = line[labelStart + 1:labelEnd].strip()
            symbolTable[label] = instrInc
        
        # Else block for otherwise increment variable
        else:
            instrInc += 1

# Call passOne()
passOne()

print(symbolTable)

# Create output file
with open("projects/6/Prog.hack", "w") as hackOut:
    pass

# Function to process second pass
def passTwo():

    # Assigning new variable RAM[16]
    currentRam = 16

    # For loop to iterate over asmClean
    for element in range(len(asmClean)):

        # Assigning line variable
        line = asmClean[element].strip()

        # Determine if instruction is A-type
        aInstr = None
        if "@" in line:
            position = line.find("@")
            aInstr = line[position + 1:].strip()

        # If A-type instruction positive, parse to output binary file
        if aInstr is not None:

            # If present in symbol table then compute binary result and parse
            if aInstr in symbolTable:
                with open("projects/6/Prog.hack", "a") as hackOut:
                    hackOut.write(f"{format(int(symbolTable[aInstr]), '016b')}")

            # Else block for checking whether constant or new variable
            else:

                # If constant then parse immediately
                if aInstr.isdigit():
                    with open("projects/6/Prog.hack", "a") as hackOut:
                        hackOut.write(f"{format(int(aInstr), '016b')}")

                # Else block to use in assumption of new variable
                else:
                    symbolTable[aInstr] = currentRam
                    with open("projects/6/Prog.hack", "a") as hackOut:
                        hackOut.write(f"{format(currentRam, '016b')}")
                    currentRam += 1
        
        # If instruction is not A-type, assume C-type
        if "(" not in line and ")" not in line:
            
            # For Dest=Comp;Jump format
            if "=" in line and ";" in line:
                destinationBits = line.split("=")
                compJumpBits = destinationBits[1].split(";")
                aBit = "1" if "M" in compJumpBits[0] else "0"
                with open("projects/6/Prog.hack", "a") as hackOut:
                    hackOut.write("111" + aBit + comp[compJumpBits[0].strip()] + dest[destinationBits[0].strip()] + jump[compJumpBits[1].strip()])

            # For Dest=Comp format
            elif "=" in line:
                tempList1 = line.split("=")
                aBit = "1" if "M" in tempList1[1] else "0"
                with open("projects/6/Prog.hack", "a") as hackOut:
                    hackOut.write("111" + aBit + comp[tempList1[1].strip()] + dest[tempList1[0].strip()] + "000")

            # For Jump format
            elif ";" in line:
                tempList2 = line.split(";")
                aBit = "1" if "M" in tempList2[0] else "0"
                with open("projects/6/Prog.hack", "a") as hackOut:
                    hackOut.write("111" + aBit + comp[tempList2[0].strip()] + "000" + jump[tempList2[1].strip()])

        # If on last instruction or label don't add new line
        if element != len(asmClean) - 1 and "(" not in line:
            with open("projects/6/Prog.hack", "a") as hackOut:
                    hackOut.write("\n")

# Call passTwo()
passTwo()