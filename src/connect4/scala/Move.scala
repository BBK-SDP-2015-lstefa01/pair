package connect4.scala

class Move(var player: Player, var column: Int) {

  if (player == null) {
    throw new IllegalArgumentException("Cannot create a connect4.scala.Move with a null player")
  }

  if (column < 0 || Board.NUM_COLS <= column) {
    throw new IllegalArgumentException("Cannot create a connect4.scala.Move with column that " + "is not in 0..connect4.scala.Board.NUM_COLS-1")
  }

  override def toString(): String = {
    player + " put a piece in column " + column
  }
}