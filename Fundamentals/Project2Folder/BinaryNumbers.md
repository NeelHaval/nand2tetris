# Binary Numbers

Binary forms an important aspect of chip desgin in this project. Unlike project 1, we are now tasked with the `addition` and - indirectly - the `subraction` of bits. This can be explained using the simple maths.

In our implementation of the `HACK` computer, we use 16 bits. This means there are $2^{16}$ possible combinations of bits. It is worth noting that all of these bits can only be `0 or 1`. Bit string are read from right to left.

It is crucial to note that each binary number can represent decimal number (base 10) and conversion between these two number systems is also possible but not necessary for our chips.

Another thing worth highlighting is that there are two types of binary values or **bit string** as they are better known. They may be **unsigned** or **signed**:

- Unsigned are where all bits are positive and in our case, range from index 0 (`[0]`) to index 15 (`[15]`).

- Signed are where negative numbers are also present. For example, for our 16-Bit computer the 16 bits may be divided into the following chunks:

    - Bits 1 to 7 are positive
    - Bit 0 is negative
    - Bits 8 to 15 are negative

The nature of signed bit strings is such that we can use the well known method of `two's complements` to compute the addition and subtraction of negative and positive numbers with relative ease. The following is a number line which shows this clearly:

    Binary   | Decimal (Two's Complement)
    ---------|-------------------
    0000     |  0   (0)          
    0001     |  1   (1)         ____________________
    0010     |  2   (2)        | The negative number|
    0011     |  3   (3)        | numbers and their  |
    0100     |  4   (4)        | index are shown    |
    0101     |  5   (5)        |____________________|
    0110     |  6   (6)
    0111     |  7   (7)
    1000     | -8   (8)
    1001     | -7   (9)
    1010     | -6   (10)
    1011     | -5   (11)
    1100     | -4   (12)
    1101     | -3   (13)
    1110     | -2   (14)
    1111     | -1   (15)

This method can be used in understanding how the various **Adder** chips have been implemented in this project.

Another direct implication of the two's complement system is the fact that no matter the number of bits, a 1 present in place of the most significant bit **`msb`** tells us that the particular number is negative. One way to see this is to change a positive number to its negative counterpart. This can be done by:

<br>

- **1.** Converting the number to a bit string.

- **2.** Negating the bit string.

- **3.** Adding 1 - which is done by switching every 1 in the bit string to 0 and stopping as soon as a 0 has been flipped to a 1.

You should now notice that the **msb** has a value of 1 (provided a positive number was switched to a negative one).