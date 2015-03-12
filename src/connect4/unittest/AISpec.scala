package connect4.unittest

import connect4.scala.{RED, State, YELLOW, AI}
import connect4.userTest.BuildUp
import org.scalatest.FlatSpec

/**
 * Created by liliya on 12/03/2015.
 */
class AISpec extends FlatSpec {

  "An AI" should "get an array of potential moves to make next" in {

    val b = BuildUp.sortOfFullTestBoard

    val ai = new AI(YELLOW, 3)

    val result = ai.getMoves(b)

    assert(result.nonEmpty)

  }

  it should "evaluate a game tree and assign value to each state of the tree" in {

    val b = BuildUp.sortOfFullTestBoard
    val tempState = new State(RED, b, null)
    val ai = new AI(YELLOW, 3)

    ai.minimax(tempState)
    //FIXME not a very good test currently
    assert(tempState.value != 0)

  }

  it should "create a game tree of all possible states to the required depth" in {

    val tempState = new State(RED, BuildUp.sortOfFullTestBoard, null)

    AI.createGameTree(tempState, 2) //depth of the tree includes the root?

    assert(tempState.children != Array[State]())


  }

}
