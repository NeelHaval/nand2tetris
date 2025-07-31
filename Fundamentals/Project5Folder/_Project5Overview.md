# Project 5 Overview

In project 5, we design the center piece of the HACK computer: The `HACK architecture`. This is what all prior projects lead up top as all chips built before have been used to construct this architecture. The HACK architecture is made of three distinctive chips. These chips are listed below:

- **ROM32K**
- **CPU**
- **Memory**

The connection between these chips is crucial as the order is important. They are connected in the same order as shown in the list above. The architecture may be thought of as two separate categories. One of these deals with the implementation of the chips individually, whereas the other deals with the connection between the chips. Two files have been given:

- `Project5Folder/Connections.md`
- `Project5Folder/Chips.md`

The former describes how the chips are interconnected by buses and the latter describes how each of the three individual chips which form the architecture have been made. An image of the implementation has also been given as in `Project5Folder/CPU.png` as this greatly helped in visualizing the overall structure and flow (time-unit-wise) of the CPU. The chips were implemented in HDL just as chips in previous projects were implemented which in turn allowed the chips to be chained together.

## Challenges

The most challenging aspect of this project was implementing the `CPU`. This was due to the complex nature of connections between chips used to design the CPU which created challenges in naming and keeping track of the names of the various internal pin names.