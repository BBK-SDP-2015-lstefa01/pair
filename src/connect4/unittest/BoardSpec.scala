package connect4.unittest


import connect4.scala.{YELLOW, RED, Move, Board}
import connect4.userTest.BuildUp
import org.scalatest.FlatSpec

/**
 * Created by liliya on 12/03/2015.
 */
class BoardSpec extends FlatSpec {

  "A Player" should "make move on board" in {
    val b1 = new Board(Board(), new Move(RED, 3))
    assert(b1.getTile(5, 3).opponent == YELLOW)

  }
  it should "get an an IllegalArgumentException if the column is full" in {
    val b = BuildUp.fullBoard
    intercept[IllegalArgumentException] {
      b.makeMove(new Move(RED, 0))
    }
  }

  it should "get the possible moves to make on a board" in {
    val b = BuildUp.nearlyFullBoard
    assert(b.getPossibleMoves(RED).length == 7)

  }

  it should "get one less possible move if a column on the board is full"
  val b = BuildUp.oneFullColumnBoard

  assert(b.getPossibleMoves(RED).length == 6)
}
