package connect4.userTest

import connect4.scala._

object GetPossibleMovesTest extends App {

  val b = BuildUp.sortOfFullTestBoard
  println(b)
  
  val result = b.getPossibleMoves(RED)
  
  result.foreach { x => println(x) }
  
}