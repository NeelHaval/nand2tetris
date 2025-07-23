# Project 2 Overview

Project 2 builds the foundations of what later will be an intgral part of the memory system and `CPU` (central processing unit). The focus of this project is the implementation of the `ALU` - arithmetic logic unit. This chip is capable of performing various tasks, all of which involve the computation of signed and unsigned binary values.

Some of the chips which were implemented along side the `ALU` to do the required calculations are listed below: 
- **`Half Adder`**
- **`Full Adder`**
- **`16-Bit Adder`**
- **`16-Bit Incrementer`**

These chips were implemented in the same way as those in Project 1. HDL was used to implement newer chips using the abstraction of previously designed logic gates.

A large focus of this chapter was the processing of `binary numbers`. This underlying mathematics was what made the development of these chips clear. Therefore, a markdowm file is dedicated towards explaining some of this reasoning.

The impementation of these chips is given as images in `Project2Folder/` to give an additional visual representation.

The aspect most challenging about this project was the ALU implementation. The wiring of the chip was challenging because unlike in Project 1, the ALU is multi-staged meaning the final output depends on intermediate outputs from internal pins which can change.