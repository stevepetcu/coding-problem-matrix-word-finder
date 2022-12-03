# Daily Coding Problem #645 [Easy]

Problem asked by Microsoft.

## Problem Statement

Given a 2D matrix of characters and a target word, write a function that returns whether the word can be found in the matrix by going left-to-right, or up-to-down.

For example, given the following matrix:
```text
[['F', 'A', 'C', 'I'],
 ['O', 'B', 'Q', 'P'],
 ['A', 'N', 'O', 'B'],
 ['M', 'A', 'S', 'S']]
```

and the target word `FOAM`, you should return true, since it's the leftmost column. 
Similarly, given the target word `MASS`, you should return true, since it's the last row.

> Marginalia: I'll make this return the coordinates of the first and last letter if the word is present, or `[(-1, -1), (-1, -1)]` if it is not.
> And also be case-insensitive.
