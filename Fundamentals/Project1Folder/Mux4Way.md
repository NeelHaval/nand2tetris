# Mux4Way Implementation

4-Way Mux:

The 4-Way Mux has been realised as a binary tree rather than within the logical circuit designer. This makes the effect of the most and least significant bit clear.

```
         input
           |
        ┌──┴──┐
        │DMux │  sel[1]
        └┬───┬┘
       0 |   | 1
         |   |
     ┌───┴┐ ┌┴───┐
     │DMux│ │DMux│  sel[0]
     └┬─┬─┘ └─┬─┬┘
     0| |1   0| |1
      a b     c d
```

From the diagram above, the binary tree-like structure is clearly visible. We can see that the least significant bit **sel[0]** determines which of either ***a*** `or` ***b*** and ***c*** `or` ***d*** is the output at the lower branches. The most significant bit -**sel[1]** - Then determines which of these (a, b,c or d) are the overall output.