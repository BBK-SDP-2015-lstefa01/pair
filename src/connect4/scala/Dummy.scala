package connect4.scala

import java.util.Random

/
object Dummy{
  def apply(myColour:Player):Dummy = new Dummy(myColour)
}
/**
* Dummy class
* @param myColour
*/

class Dummy(private var myColour: Player) extends Solver {

  override def getMoves(b: Board): Array[Move] = {
    val rand = new Random()
    var column = rand.nextInt(Board.NUM_COLS)
    while (b.getTile(0, column) != null) {
      column = rand.nextInt(Board.NUM_COLS)
    }
    Array(new Move(myColour, column))
  }
}

