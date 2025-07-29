# Communication between CPU and memory

A core part of programming in assembly is understanding how the CPU and memory communicate. It is necessary to understand that a computer has multiple systems working in cohesion. In the case of the HACK computer, the CPU and memory form a crucial part of this network.

There are two different parts to the memory; `ROM` (**READ ONLY MEMORY**) and `RAM` (**RANDOM ACCESS MEMORY**). They play a different role in the architecture of a computer. This is because as explained in project 3, RAM volatile - meaning once power to the RAM is cut off, all stored data is lost. However, ROM is non-volatile - meaning that it retains all originally programmed data is stored permanently. For the HACK computer, **_any machine code is stored on the ROM_**. In a typical computer this includes any startup software which initializes the rest of the operating system software. The RAM stores any data required by programs currently running on the computer and the CPU facilitates any communication between this hardware.

```
   +-------+         +-------+         +-------+
   |  ROM  | <---->  |  CPU  | <----> |  RAM  |
   +-------+         +-------+         +-------+
      |                                 |
      |<--- Machine Code (Programs)     |<--- Data for running programs
      |                                 |
   (Non-volatile)                   (Volatile)
```

Note: _The assembler converts any assembly to machine code which is stored in ROM._

Furthermore, due to the complex nature of the programs which are run by the computer, a hierarchy is in place within the memory system to maximize efficiency. Specifically, the CPU has several areas in memory in which it may store information. Some operations carried out within the CPU require faster data transfer speeds while other operations carried out by the CPU may be more complex.These complex operations may therefore involve fetching resources from disc such as HDD (Hard Disk Drive) and SSD (Solid State Drive) however it is not necessarily the CPU which does this. Note: _The HACK computer does not have any disc._

```
   +-----------+      Fastest, Smallest
   | Registers |      Registers within CPU (A-type, D-type)
   +-----------+
         |
         v
   +-----------+
   |   Cache   |
   +-----------+
         |
         v
   +----------------+
   |  Main Memory   |   (RAM)
   +----------------+
         |
         v
   +----------------------+
   |        Disc          |   (HDD/SSD, etc.)
   +----------------------+
         ^
         |
   Slowest, Largest
```

Storage of data into the RAM relies heavily on registers within the CPU. Registers can either` point to RAM addresses` (A-type Register) or they may `hold binary values` (D-type Register). This is exactly what the HACK assembly language allows us to specify. We are able to save values and RAM addresses which allows for a variety of computations. For examples, please see `Projects/4/`. The values stored within memory can be created and modified at will.