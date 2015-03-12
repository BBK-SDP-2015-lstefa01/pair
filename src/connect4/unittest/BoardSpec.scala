package connect4.unittest

import connect4.scala.{YELLOW, RED, Move, Board}
import org.scalatest.FlatSpec

/**
 * Created by liliya on 12/03/2015.
 */
class BoardSpec extends FlatSpec {

  "A Player" should "make move on board" in {
    val b1 = new Board(Board(), new Move(RED, 3))
    assert(b1.getTile(0, 3).opponent == YELLOW)

  }
  it should "get an an IllegalArgumentException if the column is full" in {
    val b = new Board()
    b.makeMove(new Move(RED, 0))
    b.makeMove(new Move(YELLOW, 0))
    b.makeMove(new Move(RED, 0))
    b.makeMove(new Move(YELLOW, 0))
    b.makeMove(new Move(RED, 0))
    b.makeMove(new Move(YELLOW, 0))
    intercept[IllegalArgumentException] {
      b.makeMove(new Move(RED, 0))

    }
  }

  it should "get the possible moves to make on a board" in {
    val b = new Board()
    b.makeMove(new Move(RED, 0))
    b.makeMove(new Move(YELLOW, 0))
    b.makeMove(new Move(RED, 0))
    b.makeMove(new Move(YELLOW, 0))
    b.makeMove(new Move(RED, 0))
    b.makeMove(new Move(RED, 1))
    b.makeMove(new Move(YELLOW, 2))

    val move = new Move(YELLOW, 3)
    assert(b.getPossibleMoves(RED).length == 7)
    assert(b.getPossibleMoves(YELLOW).contains(move))

  }

  it should "get one less possible move if the column is full"
  val b = new Board()
  b.makeMove(new Move(RED, 0))
  b.makeMove(new Move(YELLOW, 0))
  b.makeMove(new Move(RED, 0))
  b.makeMove(new Move(YELLOW, 0))
  b.makeMove(new Move(RED, 0))
  b.makeMove(new Move(YELLOW, 0))
  b.makeMove(new Move(RED, 1))
  b.makeMove(new Move(YELLOW, 2))

  assert(b.getPossibleMoves(RED).length == 6)
}
