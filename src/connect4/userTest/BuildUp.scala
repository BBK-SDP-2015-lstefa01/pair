package connect4.userTest

import connect4.scala._

object BuildUp {

  /**
   * Creates an empty new board
   */
  val emptyTestBoard = new Board()

  /**
   * Creates a sort-of filled board
   */
  val sortOfFullTestBoard = new Board()
  sortOfFullTestBoard.makeMove(new Move(RED, 6))
  sortOfFullTestBoard.makeMove(new Move(YELLOW, 2))
  sortOfFullTestBoard.makeMove(new Move(RED, 4))
  sortOfFullTestBoard.makeMove(new Move(YELLOW, 3))

  /**
   * Creates a nearly full board
   */
  val nearlyFullBoard = new Board()
  nearlyFullBoard.makeMove(new Move(YELLOW, 0))
  nearlyFullBoard.makeMove(new Move(RED, 6))
  nearlyFullBoard.makeMove(new Move(YELLOW, 2))
  nearlyFullBoard.makeMove(new Move(RED, 4))
  nearlyFullBoard.makeMove(new Move(YELLOW, 3))
  nearlyFullBoard.makeMove(new Move(RED, 6))
  nearlyFullBoard.makeMove(new Move(YELLOW, 2))
  nearlyFullBoard.makeMove(new Move(RED, 4))
  nearlyFullBoard.makeMove(new Move(YELLOW, 3))
  nearlyFullBoard.makeMove(new Move(RED, 1))
  nearlyFullBoard.makeMove(new Move(YELLOW, 5))

  /**
   * Creates a full board
   */
  val fullBoard = new Board()
  for (r <- 0 until Board.NUM_ROWS; c <- 0 until Board.NUM_COLS) {
      
    
    }
}