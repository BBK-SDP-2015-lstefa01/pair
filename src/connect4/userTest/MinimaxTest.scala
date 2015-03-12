package connect4.userTest

import connect4.scala._

object MinimaxTest extends App {

  val tempState = new State(RED, BuildUp.sortOfFullTestBoard, null)

  val ai = new AI(YELLOW, 3)
  tempState.initializeChildren()
  for (child <- tempState.children) {
    ai.evaluateBoard(child.board)
    println(child.board)
    println("Child value: " + child.value)
  }
  val arr: Array[State] = tempState.children
  AI.createGameTree(tempState, 3)
  AI.minimax(ai, tempState)
  for (child <- tempState.children) {
    println(child.board)
    println("Child value: " + child.value)
  }
  tempState.writeToFile()

}