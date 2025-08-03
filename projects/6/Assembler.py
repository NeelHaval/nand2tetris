# Create dictionary to store predefined symbols and programmer defined labels and symbols
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

# Prompt user for the assembly file to be translated
userFile = input("What assembly code would you like translated? Please remember to provide the extension .asm: ")

# Create file to save cleaned assembly code
asmClean = []

# Store .asm file in variable and open
asmFile = open(userFile, "r")

# Read .asm file
asmCode = asmFile.readlines()

# Remove all white space and comments from assembly code in place
for index in range(len(asmCode)):
    line = asmCode[index].strip()
    if "//" in line:
        line = line[:line.index("//")].strip()
    asmCode[index] = line

# At this point, if line is not empty it should be appended to asmClean
for line in asmCode:
    if line.strip() != "":
        asmClean.append(line.strip())