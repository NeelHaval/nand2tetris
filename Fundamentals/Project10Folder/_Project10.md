# Project 10

The conversion of high level `Jack` code into lower level `VM instructions` occurs in two stages. Project 10 deals with the conversion of Jack code into an `XML file output` and subsequent projects deal with the XML file conversion to `VM instructions`. The programming language of choice for the implementation of this compiler was python due to its object oriented feature set, efficiency and ease of file management. Currently, the implementation is only built to handle single files and excludes if else logic from the jack language. This is a point on which a future implementation could improve. The code is split into three files:

- **JackAnalyzer.py**
- **JackTokenizer.py**
- **CompilationEngine.py**

The contents of each of these files are described below to aid in understanding of the role each of them plays in the running of the program.

## **JackAnalyzer.py**

This file deals with the overall flow of the program. It is in charge of receiving command line arguments which the user passes in as the jack file to be translated. It uses these arguments to retrieve the file to be translated from the appropriate directory and reads this file. All input read from the file is then passed to the JackTokenizer object.

## **JackTokenizer.py**

The input is received as a stream of text from `JackAnalyzer.py` and is passed to the constructor of `JackTokenizer` object. After this point, patterns matching begins to take place. Python regexes were used to detect valid matches in the jack file to be translated. Once the input stream - strings - are passed to the constructor, a method which returns a list of all matched tokens is run. These matched tokens are stored as a list as an instance of the `JackTokenizer` object. Control is passed to `JackAnalyzer.py` to initialize the output file and pass data to `CompilationEngine.py`.

## **CompilationEngine.py**

In order for compilation engine to have access to all methods and list of matched tokens inside, the first thing the constructor of this class does is store an instance of the `JackTokenizer` object as one of its own instances.

Control is again passed momentarily to `JackAnalyzer.py` where the rest of the methods in `CompilationEngine.py` are called. This stage involves the parsing of `jack tokens` to the XML file with the appropriate formatting. A series of methods are used to accomplish this all of which expect very specific patterns of Jack tokens in order to parse meaningful and valid Jack instructions.

## End

By the end, the file entered for translation in the command line argument will have been processed and a new file ending with a `.xml` will have been created within the same directory as the original jack file.