# RAM Implementations

As project 3 dvelves into memory, RAM (`Random Access Memory`) is one of the most significant areas of study. Various different RAM modules were implemented including:

- RAM8
- RAM64
- RAM512
- RAM4K
- RAM16K

RAM is fast and volatile memory accessed by programmes actively running on the computer. The implementation of these RAM units can be realised using the more simpler 16-Bit Register. Bellow is a diagram of how a RAM8 uses a 16-Bit Register:

```
           Input
             |
             v
         +--------+
         | RAM8   |
         |        |
         | +----+ |   Register 0
         | |Reg0| |
         | +----+ |
         | +----+ |   Register 1
         | |Reg1| |
         | +----+ |
         |  ...   |
         | +----+ |   Register 7
         | |Reg7| |
         | +----+ |
         +--------+
             |
             v
           Output
```

From the diagram above, it is possible to see that RAM8 contains 8 16-Bit Registers. Each Register here has a particular address unique to itself. The address is a binary string. The width of this binary string is determined by the number of Registers within the RAM module, in this case 8. The following relationship may be used to relate the width of the address and the number of Registers:

> log₂(number of Registers) = address width

For example, the relationship correctly tells us that a RAM module with 64 Registers should have address bit strings of width 6. Each of which are unique to each of the 64 Registers inside. To verify this, we can do the following:

- **1.** Address width of 6.
- **2.** Therefore 2⁶ possible permutations for the address bit string.
- **3.** 2⁶ = 64 which is the exact number of Registers our RAM64 module can address to and hence we have verified this claim.

The Implementation of subsequent RAM modules such as RAM4K can be done by combining RAM modules with a Register count which is 8 times lower. The larger the Register count, the greater the address width. This is because using Mux and DMux gates select certain RAM/Registers depending on the specific bit in the address bit string. This process continues until a certain Register is reached to eventually store information.

An interesting fact is that the Registers within the RAM modules don't store much values in practice and hence it is not economical for the computer to store values in every Register.