# Creates class object
class CompilationEngine:

    # Define constructor method
    def __init__(self, tokenizer, output):

        # Store instance of JackTokenizer object as tokenizer in Compilation object for easy access
        self.tokenizer = tokenizer

        # Variables to keep track of output file, current XML indentation and current token
        self.output = output
        self.indent = 0
        self.current_token = None

    # Define write method to write to output file
    def write(self, text):
        self.output.write("  " * self.indent + text + "\n")

    def eat(self, expected_type=None, expected_value=None):

        # Obtain current token type and value
        token_type = self.tokenizer.token_type()
        token_val = self.tokenizer.current_token

        # If discrepancy found in expected and current token type/value then raise ValueError
        if expected_type and token_type != expected_type:
            raise ValueError(f"Expected {expected_type}, got {token_type}")

        if expected_value and token_val != expected_value:
            raise ValueError(f"Expected {expected_value}, got {token_val}")

        # Write the token into the output
        self.write(f"<{token_type.lower()}> {token_val} </{token_type.lower()}>")

        # Move to next token
        if self.tokenizer.has_more_tokens():
            self.tokenizer.advance()

    # Compile class in appropriate XML format
    def compile_class(self):
        self.write("<class>")
        self.indent += 1

        self.tokenizer.advance()
        self.eat("KEYWORD", "class")
        self.eat("IDENTIFIER")
        self.eat("SYMBOL", "{")

        while self.tokenizer.current_token in ("static", "field"):
            self.compile_class_var_dec()

        while self.tokenizer.current_token in ("constructor", "function", "method"):
            self.compile_subroutine()

        self.eat("SYMBOL", "}")

        self.indent -= 1
        self.write("</class>")

    # Lay out class variable declaration in XML format
    def compile_class_var_dec(self):
        self.write("<classVarDec>")
        self.indent += 1

        self.eat("KEYWORD")
        self.compile_type()
        self.eat("IDENTIFIER")

        while self.tokenizer.current_token == ",":
            self.eat("SYMBOL", ",")
            self.eat("IDENTIFIER")

        self.eat("SYMBOL", ";")

        self.indent -= 1
        self.write("</classVarDec>")

    # Lay out Subroutine declaration in XML format
    def compile_subroutine(self):
        self.write("<subroutineDec>")
        self.indent += 1

        self.eat("KEYWORD")
        if self.tokenizer.current_token == "void":
            self.eat("KEYWORD", "void")
        else:
            self.compile_type()

        self.eat("IDENTIFIER")
        self.eat("SYMBOL", "(")
        self.compile_parameter_list()
        self.eat("SYMBOL", ")")

        self.compile_subroutine_body()

        self.indent -= 1
        self.write("</subroutineDec>")

    # Lay out Subroutine body in XML format
    def compile_subroutine_body(self):
        self.write("<subroutineBody>")
        self.indent += 1

        self.eat("SYMBOL", "{")

        while self.tokenizer.current_token == "var":
            self.compile_var_dec()

        self.compile_statements()

        self.eat("SYMBOL", "}")

        self.indent -= 1
        self.write("</subroutineBody>")

    # Processes primitive types
    def compile_type(self):
        if self.tokenizer.current_token in ("int", "char", "boolean"):
            self.eat("KEYWORD")
        else:
            self.eat("IDENTIFIER")  # className

    # Processes the parameters within methods/functions
    def compile_parameter_list(self):
        self.write("<parameterList>")
        self.indent += 1

        if self.tokenizer.current_token != ")":
            self.compile_type()
            self.eat("IDENTIFIER")

            while self.tokenizer.current_token == ",":
                self.eat("SYMBOL", ",")
                self.compile_type()
                self.eat("IDENTIFIER")

        self.indent -= 1
        self.write("</parameterList>")

    # Lay out variable declaration in appropriate format
    def compile_var_dec(self):
        self.write("<varDec>")
        self.indent += 1

        self.eat("KEYWORD", "var")
        self.compile_type()
        self.eat("IDENTIFIER")

        while self.tokenizer.current_token == ",":
            self.eat("SYMBOL", ",")
            self.eat("IDENTIFIER")

        self.eat("SYMBOL", ";")

        self.indent -= 1
        self.write("</varDec>")

    # Process blocks of code
    def compile_statements(self):
        self.write("<statements>")
        self.indent += 1

        while self.tokenizer.current_token in ("let", "if", "while", "do", "return"):
            self.write(f"<keyword> {self.tokenizer.current_token} </keyword>")
            self.tokenizer.advance()

            while self.tokenizer.current_token not in (";", "{", "}"):
                self.write(f"<{self.tokenizer.token_type().lower()}> {self.tokenizer.current_token} </{self.tokenizer.token_type().lower()}>")
                self.tokenizer.advance()

            if self.tokenizer.current_token == ";":
                self.write("<symbol> ; </symbol>")
                self.tokenizer.advance()

            elif self.tokenizer.current_token == "{":
                self.write("<symbol> { </symbol>")
                self.tokenizer.advance()
                self.compile_statements()
                self.write("<symbol> } </symbol>")
                if self.tokenizer.current_token == "}":
                    self.tokenizer.advance()

        self.indent -= 1
        self.write("</statements>")