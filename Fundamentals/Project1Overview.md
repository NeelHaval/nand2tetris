# Project 1 Overview

In project 1, we implement a series of logic gates which are necessary for building the next component of the computer. A significant component this includes is the `ALU`, or arithmetic logic unit.

Implementation of these logic gates begins from the most basic gates: Not, And, Or, Xor, Mux and DMux. From this we can then desgin the repective `multi - bit` and/or `multi - way` counter parts.

To implement these logic gates, we use a HDL, or hardware description language for short. Crucially, HDL is not a programming language. It describes: 
<br>

- **the interface** of a given chip (or gate) **outwith** the PARTS section. This merely shows the overall I/O of the chip.

- **the implementation** is shown **within** the parts section. This shows the connections of all pins, be they internal or external.

Visual representations of the logic gates are present in `Fundamentals/` as images of the chip set up for further understanding.