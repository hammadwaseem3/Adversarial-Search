<h1>Connect-Four</h1>
It is a two player board game played for centuries around the world. The game involves the two
players alternating turns. It consists of a grid with "c" columns and "r" rows. At each turn, a player
drops a checker into the column of her choice. The game play continues until one of the players
gets 4 checkers in a horizontal, vertical, or diagonal line. In this assignment you need to program
an adversarial search strategy on a variation of connect-four with 4X6 grid.

The rules of the game are similar to standard version. Each player takes alternate turns dropping a
checker into a column, a player cannot remove checkers from any column, a full column can not
have any more checker. The game ends when a player places four one’s own checkers next to each
other vertically, horizontally or diagonally. A tie occurs if all player’s checkers consumed or grid
is fully occupied with checkers and no player has four consecutive checkers.

<h3>Board and Moves</h3>
The design of the board is your choice; an initial empty board has a value zero at each cell. The
player one has a value 1 and player two has a 2 at each position where it placed it checker. Given
a board position you need to decide whether it is a valid state or not. The move strategy is through
generating all possible actions sequences given a state. The evaluation function is another
challenge that you need to overcome. Your move will be evaluated the best as per the opponent
possibilities using adversarial search techniques.

<h3>Computer Play</h3>
There are two parts of this assignment prepare the classes to manage the board and displaying
move. The other part is to decide the move automatically. You need to prepare an adversarial
search using both MiniMax and alpha-beta pruning. Starting from an initial board it will first
decide who will move first. For a computer move it will search the game tree with MiniMax search
or alpha-beta search. You can configure your program to use either as a configuration parameter.
Alternate move will be entered by human player.

Note:
Code contain both min-max and alpha-beta pruning approach. To play the proper game you have to 
uncomment the code in main file (as mention in code). This commented code is set to min-max by default
to use alpha-beta you have to comment line number 78 and uncomment the line number 77 in the main file
