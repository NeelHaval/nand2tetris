# Import statements
import sys
import os
from JackTokenizer import JackTokenizer
from CompilationEngine import CompilationEngine

# Main method to control overall flow
def main():

    # Handle when user fails to give valid command line argument
    if len(sys.argv) != 2:
        print("Please enter correct file name:")
        sys.exit(1)

    # Assign path to file
    path = sys.argv[1]

    # Allow option for directory for future expansion of code
    if os.path.isdir(path):
        jack_files = [os.path.join(path, f) for f in os.listdir(path) if f.endswith(".jack")]
    else:
        jack_files = [path]

    # Read jack file and assign stream of jack text to source
    for jack_file in jack_files:
        with open(jack_file, "r") as f:
            source = f.read()

        # Create instance of JackTokenizer object and pass stream of jack text to JackTokenizer constructor
        tokenizer = JackTokenizer(source)

        # Create valid name for new file
        out_file = jack_file.replace(".jack", ".xml")

        # Write to new file
        with open(out_file, "w") as out:
            engine = CompilationEngine(tokenizer, out)
            engine.compile_class()

# Call main method only when file is run directly
if __name__ == "__main__":
    main()