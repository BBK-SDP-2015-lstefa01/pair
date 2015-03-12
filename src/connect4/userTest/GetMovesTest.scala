package connect4.userTest

import connect4.scala._

object GetMovesTest extends App {

  val b = BuildUp.sortOfFullTestBoard

  val ai = new AI(YELLOW, 3)
  
  val result = ai.getMoves(b)

  result.foreach { x => println(x)}

}