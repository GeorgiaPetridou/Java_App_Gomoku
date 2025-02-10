# Java_App_Gomoku
DS-Gomoku Java Game

A Data Structures Project

Project Overview

This project is a simplified variation of Gomoku, a board game that is itself a variation of the classic Connect-Four. The game is played on a 15x15 board (dimensions can be changed) by two players—one playing with black tiles and the other with white. Players take turns placing tiles on the board, aiming to be the first to form a horizontal, vertical, or diagonal line of five tiles.

More details on Gomoku: Wikipedia - Gomoku

Installation & Execution

Importing into Eclipse

Unzip the Gomoku Part A.zip file into your workspace folder.

In Eclipse, go to:

File → Import → General → Existing Projects into Workspace

Select the unzipped project folder and import it.

Running the Application

Right-click on the project in Eclipse.

Select:

Run As → Java Application

Task 1: Implementing Random Movement (AI Player)

Objective

The goal of this task is to modify or complete specific methods within the given Java classes to ensure smooth gameplay.

Key Classes & Implementation Details

1. Tile Class (Represents the board tiles)

Each tile has the following attributes:

int id – Unique identifier for each tile.

int x, int y – Tile coordinates on the board.

int color – Represents tile status:

0 → Gray (Empty tile)

1 → Black (Tile placed by the black player)

2 → White (Tile placed by the white player)

boolean mark – A helper variable for future tasks.

int playerId – Identifies which player (if any) has placed a tile:

0 → Empty tile

1 → Player A's tile

2 → Player B's tile

Methods to Implement:

Constructor

Initializes all attributes based on provided parameters.

The order of parameters must follow the listed attribute order.

Getter & Setter Methods

Implement standard getters and setters for all attributes.

2. RandomPlayer Class (Represents AI players)

Each player has the following attributes:

int id – Identifies the player (Black = 1, White = 2).

String name – Player’s name.

int score – Number of points accumulated.

Methods to Implement:

Constructor 1: Accepts an Integer pid (player ID).

Note: Integer is used instead of int specifically for this constructor.

Constructor 2: Accepts three arguments (ID, name, score) to initialize all attributes.

Getter & Setter Methods for all attributes.

AI Move Function:

int[] getNextMove(Board board):

Receives the board object as input.

Returns an array [x, y] representing the next move’s position.

The AI selects a random empty position for the move.

If the position is occupied, it must generate another random position.

Supporting Classes & Utilities

Board Class & getTile(int x, int y) Method

The board is represented as a 15x15 grid.

The method getTile(x, y) returns the tile at the specified coordinates, allowing the AI to check for empty spots.

Game Board Dimensions

NUMBER_OF_ROWS = 15, NUMBER_OF_COLUMNS = 15

These constants should be used instead of hardcoding 15, to ensure flexibility for future changes.

Random Move Selection

double Math.random() generates a random number between [0,1).

This is used to randomly select AI moves.

Expected Outcome

Once implemented, the Random AI Player should be able to:

Randomly select an empty tile on the board for its move.

Validate the move before placing a tile.

Continue playing until one player forms a five-tile sequence, winning the game.

If implemented correctly, players will automatically take turns, placing tiles in random positions until a winner is determined.
