# DMux4Way Implementation

4-Way DMux:

The 4-Way DMux has been realised as a binary tree rather than within the logical circuit designer. This makes the effect of the most and least significant bit clear.

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

From the diagram above, the binary tree-like structure is clearly visible. We can see that the most significant bit **sel[1]** determines which lower branch is the output. **sel[0]** then determines which of either ***a*** `or` ***b*** or ***c*** `or` ***d*** is the output.