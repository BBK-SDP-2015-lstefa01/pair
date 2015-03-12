package connect4.userTest

import connect4.scala._

object MakeMoveTest extends App {

  println("testing makemove")

  val testMakeMoveBoard = new Board()
  testMakeMoveBoard.makeMove(new Move(RED, 6))
  testMakeMoveBoard.makeMove(new Move(YELLOW, 2))
  testMakeMoveBoard.makeMove(new Move(RED, 4))
  testMakeMoveBoard.makeMove(new Move(YELLOW, 3))

  println(testMakeMoveBoard)

  val testFullColumn = new Board()

  testFullColumn.makeMove(new Move(RED, 2))
  testFullColumn.makeMove(new Move(YELLOW, 2))
  testFullColumn.makeMove(new Move(RED, 2))
  testFullColumn.makeMove(new Move(YELLOW, 2))
  testFullColumn.makeMove(new Move(YELLOW, 2))
  testFullColumn.makeMove(new Move(RED, 2))

  println(testFullColumn)

  try {
    testFullColumn.makeMove(new Move(RED, 2))
    println(testFullColumn)

  } catch {
    case e: IllegalArgumentException => println("full column exception thrown")
  }

}