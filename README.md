# pair

Courswork Submission for Pair
Esha Massand emassa01 and Liliya Stefanova lstefa01

Pair programming project for SDP 
ConnectFour

This was a pair assignment to implement an Artificial Intelligence (AI) concept that utilizes trees. A game-tree determines the best possible move for a given
game board in a game of Connect Four. 

The algorithm implemented in the minimax algorithm.

The AI/game is written in Scala, using the functional programming aspects of the language.

Highlights:
* Trees and recursion.
* Minimax algorithm.
* Using the functional programming paradigm.

Optimisations:
We noticed a way in which the game could be further optimised.
When each tile of the Board is checked for nullity (i.e. empty or full):
If a Dummy is playing a (smart) AI, the AI should check the Board rows in the order 6 --> 0 as the Dummy is likely to lose earlier on in the game.
If the AI is not playing a Dummy, but instead a smart AI, the game is likely to continue for longer and the Board is likely to fill up and get closer to row 0, so the AI could start checking the rows 0-->6 
