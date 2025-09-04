# Import statements
import re

# Creates class object
class JackTokenizer:

    # Define constructor method
    def __init__(self, input_stream: str):
        
        # Store list of tokens in JackTokenizer's tokens variable (self.tokens)
        self.tokens = self.tokenize(input_stream)

        # Variables to keep track of current jack token to be parsed
        self.current_token_index = -1
        self.current_token = None

    # Method returns a list of all matched symbols
    def tokenize(self, input_stream: str):
        
        # Remove inline and block comments comments
        input_stream = re.sub(r"//.*", "", input_stream)
        input_stream = re.sub(r"/\*.*?\*/", "", input_stream, flags=re.S)

        # Specify jack language symbols to remove
        symbols = r"[\{\}\(\)\[\]\.\,\;\+\-\*\/&\|<>=~]"

        # Construct regex object of symbols to look for
        token_pattern = re.compile(
            rf'(".*?")|([0-9]+)|({symbols})|([A-Za-z_]\w*)'
        )

        # Returns list of matched object
        return [match.group(0) for match in token_pattern.finditer(input_stream)]

    # Checks if more tokens are present
    def has_more_tokens(self):
        return self.current_token_index < len(self.tokens) - 1

    # Moves to next token in input stream
    def advance(self):
        if self.has_more_tokens():
            self.current_token_index += 1
            self.current_token = self.tokens[self.current_token_index]

    # Returns the type of token
    def token_type(self):

        if self.current_token in {
            "class","constructor","function","method","field","static",
            "var","int","char","boolean","void","true","false","null",
            "this","let","do","if","else","while","return"
        }:
            return "KEYWORD"
        elif re.match(r"[\{\}\(\)\[\]\.\,\;\+\-\*\/&\|<>=~]", self.current_token):
            return "SYMBOL"
        elif re.match(r"[0-9]+", self.current_token):
            return "INT_CONST"
        elif re.match(r"\".*\"", self.current_token):
            return "STRING_CONST"
        else:
            return "IDENTIFIER"

    # Methods below are defined in per the project 10 specification
    def keyword(self):
        return self.current_token

    def symbol(self):
        return self.current_token

    def identifier(self):
        return self.current_token

    def int_val(self):
        return int(self.current_token)

    def string_val(self):
        return self.current_token.strip('"')