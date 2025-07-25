# Project 3 Overview

Project 3 focuses on a crucial aspect of any complete computer system: memory. Specifically, the `RAM` of the computer was built - where RAM stands for random access memory. RAM is essential in a computer because it stores `values` (in most cases: numbers as binary) which are essential for allowing programmes to run. Various different types of memory and RAM were built as part of this project and they have been summarised below.

_**Memory built:**_

- **`1-Bit Register`**
- **`Register`**
- **`RAM8`**
- **`RAM64`**
- **`RAM512`**
- **`RAM4K`**
- **`RAM16K`**
- **`Program Counter (PC)`**

_The reason these components were built specifically is because the `Hack` computer which these projects are working towards requires this exact selection of RAM chips._

The implementation of the RAM happened using certain chips designed in project 1 and 2. Just as before, implementation of the new chips happened upon the abstraction of the older chips. I.e., in order to use the older chips, we need to know their inputs and outputs.

Importantly, with the introduction to Project 3, we now have to design around more than one type of logic. In addition to combinatorial logic, we now have sequential logic to implement as well. Due to the significance of sequential logic in this project, it is worth expanding on further. Therefore, a more detailed breakdown of it may be found in `Project3Folder/SequentialLogic.md`. This file also contains explainations about the DFF and Program Counter.

Implementation details about some of the chips designed have been given as images within this directory (`Project3Folder/`). The full implementations of the 1-Bit register `(Bit.md)` has been given as an image. The RAM modules have one markdown file `(RAM.md)` dedicated to explanations on their functioning and implementation. However, a full implementation is not available to view for the RAM, PC and Register modules due to their multi-Bit nature.