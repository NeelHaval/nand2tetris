# ALU Implementation

The ALU (arithmetic logic unit) is the chip which manages a multitude of computations concerning binary numbers. As mentioned in `Project2Overview.md/`, these involve operations with both signed and unsigned bit strings. 

The bulk of the connections for the ALU were made with Mux, Not and Or gates. Some of these were single bit variants and others were 16bit variants. Not that the processing of **_zr_** was done using 8-way Or gates by splitting the positive and negative bit strings of the output.

