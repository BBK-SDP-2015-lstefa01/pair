package connect4.unittest

import connect4.scala._
import org.scalatest.FlatSpec

/**
 * Created by liliya on 12/03/2015.
 */
class StateSpec extends FlatSpec {

  "A State" should "have children states equal to the number of possible next moves" in {

    val b = new Board(Board(), new Move(RED, 3))
    val testState = new State(YELLOW, b, null)
    val children = testState.initializeChildren()

    val testChildMove = new Move(YELLOW, 3)
  }

}
