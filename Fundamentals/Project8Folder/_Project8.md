# Project 8

Project 8 is largely an extension of the project 7 where the VMTranslator was partially built. The VMTranslator was extended in this project to deal with handling functions and nested calls. Specifically, function implementation, call, return, label, goto and if-goto logic have been implemented. The solution is built to pass any tests with basic calls and nested calls through these implementations. 

**_Note that this specific version of the VMTranslator is designed to handle only single files. This may be patched in the future._**

For context about the workings of the VM Translator please see `_Project7.md` located in `Fundamentals/Project7Folder/_Project7.md`.

File sections:

- 1. **Implementation**
- 2. **Challenges**
- 3. **Foundations**
- 4. **Running the program**

## Implementation

The implementation as explained above is broadly the same as in project 7. The key difference is in the content which has been added. The translator now deals with functions, their calls, labels, returns, goto and if goto. The implementations of these are all located in CodeWriter.java. The main change from project 7 however is that there is an additional class file with its own main method called `asmCombine.java`. This too is located in `projects/8/jvm/src` inside the source folder (src).

Additionally, this implementation deals with nested functions and return addresses. To account for this, bootstrap code must be written at the start of assembly files. The condition which determines whether bootstrap code is be written is such that it is only written if the files to be translated include Sys.vm. This is because in cases where Sys.vm is part of the files to be translated, the _**initialization of the stack frame**_ (see `Fundamentals/Project7Folder/_Project7.md`) is done by the implementation itself. in other cases, it is done by the test package.

This is exactly why the asmCombine class file was necessary. It checks whether the arguments include the Sys.vm file and decide on printing the bootstrap.

## Challenges

The additional commands which were to be implemented were a challenge due to their more complex nature. Specifically the return and call commands which required understanding the stack frame very well. Another challenging aspect was the bootstrap code. The concept is simple - print out bootstrap code at the start if the file to be translated contains Sys.vm. In reality however, this implementation had to be tied carefully to the return and call implementations in the switch case to avoid conflicts during runtime.

## Foundations

The stack broadly remains the same as it did during the VMTranslator implementation in project 7. However due to the added complexity of functions specifically, the workings have advanced further. In project 7, (see `Fundamentals/Project7Folder/_Project7.md`) the stack was represented as the following:

```
| Address | Segment  |
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

While this still holds, there is now an important distinction. There are separate functions. This means that each of these functions get their own dedicated stack. Each time a function is called using a command such as `call function 3`, a certain set of instructions take place:

- 1. **Function return address is pushed onto the stack - ensures that instructions are carried out from the same point again.**

<br>

- 2. **LCL ARG THIS THAT are pushed onto the stack to be used in calculations**

<br>

- 3. **ARGS are set onto the stack to hold arguments.**

<br>

- 4. **Calculations occur in function block - shown by `function name lcls`. (_name - function name_, _lcls - number of local variables to be pushed_) At this point, local variables are also set to 0 after being pushed onto the stack.**

<br>

- 5. **Once the function has computed the desired result, the top of the stack contains the return value in the current function frame.**

<br>

- 6. **The return address is saved in a temporary RAM.**

<br>

- 7. **The return value is pushed to the position on the stack where the first argument of the calling function was.**

<br>

- 8. **Using the saved return address, execution resumes from one position ahead of the current function call.**