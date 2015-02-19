package connect4.scala

import java.io.{FileNotFoundException, PrintWriter, UnsupportedEncodingException}

import connect4.scala.State._

import scala.beans.BeanProperty

object State {

  val length0 = Array[State]()
}

class State(@BeanProperty var player: Player, @BeanProperty var board: Board, @BeanProperty var lastMove: Move)
  extends Comparable[Any] {

  /**
     * All possible game States that can result from the next player's Move.
     * The length of the array equals the number of States.
     * It is an array of length 0 if there are no possible moves
     * (once it has been set; initially, it is an array of length0)
     */
  @BeanProperty
  var children: Array[State] = length0

  @BeanProperty
  var value: Int = 0
  /**
     * Retrieves the possible moves and initializes this State's children.
     * The result is that this State's children reflect the possible
     * States that can exist after the next move. Remember, in the
     * children it is the opposite player's turn. This method
     * initializes only this State's children; it does not recursively
     * initialize all descendants.
     */
  def initializeChildren() {
    /*
     * Possible implementation
     * 1. check the current player and switch it to the opponent
     * 2. get possible moves for that player
     * 3. for each Move, create a new state (passed parameter player, this board, lm) 
     * //check! does this.board represent the last move reflect the move just made??
     * 4. add the State to the children Array[State]
     * ...not recursive!
     * # of states = number of columns that are not full
     * is it a new board each level of tree? Or, is the last move used to from leaves to parents in the algo? 
     * parents dont care about last moves, leaves do
     */
  }

  def writeToFile() {
    try {
      var writer = new PrintWriter("output.txt", "UTF-8")
      writer.println(this)
      java.awt.Toolkit.getDefaultToolkit.beep()
    } catch {
      case e @ (_: FileNotFoundException | _: UnsupportedEncodingException) => e.printStackTrace()
    }
  }

  override def toString(): String = {
    println("connect4.scala.State.toString printing")
    toStringHelper(0, "")
  }

  private def toStringHelper(d: Int, ind: String): String = {
    var str = ind + player + " to play\n"
    str = str + ind + "Value: " + value + "\n"
    str = str + board.toString(ind) + "\n"
    if (children != null && children.length > 0) {
      str = str + ind + "Children at depth " + (d + 1) + ":\n" + ind +
        "----------------\n"
      for (s <- children) {
        str = str + s.toStringHelper(d + 1, ind + "   ")
      }
    }
    str
  }

  override def compareTo(ob: AnyRef): Int = 0
}

