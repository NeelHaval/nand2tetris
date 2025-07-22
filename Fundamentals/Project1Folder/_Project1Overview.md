# Project 1 Overview

In project 1, we implement a series of logic gates which are necessary for building the next component of the computer. A significant component this includes is the `ALU`, or arithmetic logic unit.

Implementation of these logic gates begins from the most basic gates: Not, And, Or, Xor, Mux and DMux. From this we can then design the respective `multi-bit` and/or `multi-way` counter parts. These are all fundamentally designed using a Nand gate - a primitive gate given to us in this case.

To implement these logic gates, we use a HDL, or (hardware description language). Any file with a `1/` with a `.hdl` extension is where these the logic gates are implemented. Crucially, HDL is not a programming language. It describes: 
<br>

- **the interface** of a given chip (or gate) **outside** the PARTS section. This merely shows the overall I/O of the chip.

- **the implementation** is shown **within** the parts section. This shows the connections of all pins, be they internal or external.

Visual representations of the logic gates are present in `Project1Folder/` as images of the chip set up for further understanding.

To avoid over - complicating the images, any 16-Bit/multi-Way gates have been realised as 2-Bit/2-Way gates instead. In addition to this, DMux8Way and Mux8Way16 are omitted due to their similarity with their 4 way counter parts.