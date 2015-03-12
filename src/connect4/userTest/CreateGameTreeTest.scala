package connect4.userTest

import connect4.scala._

object CreateGameTreeTest extends App {

  val tempState = new State(RED, BuildUp.sortOfFullTestBoard, null)

  AI.createGameTree(tempState, 3) //depth of the tree includes the root?
  val ai = new AI(RED, 3)

  var tempChildren = tempState.children

  println(tempState)
  println("Children of temp state: ")
  for (c <- tempChildren) {
    println(c)
  }

}