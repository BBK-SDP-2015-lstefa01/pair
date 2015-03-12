package connect4.userTest

import connect4.scala._

object InitialiseChildrenTest extends App {

  val tempState = new State(RED, BuildUp.sortOfFullTestBoard, null)
  tempState.initializeChildren()
  println(tempState)

  val tempState2 = new State(RED, BuildUp.nearlyFullBoard, null)
  tempState2.initializeChildren()
  println(tempState2)

  //  val tempState3 = new State(RED, BuildUp.fullBoard, null)
  //  tempState3.initializeChildren()
  //  println(tempState3)

}