package connect4.scala

import java.io.{FileNotFoundException, PrintWriter, UnsupportedEncodingException}
import scala.beans.BeanProperty
import State.length0

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

  /**
   * How desirable this State is.
   */
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
    var newPlayer = player.opponent
    var possMoves = board.getPossibleMoves(newPlayer)
    println("Possible moves length: "+possMoves.length)
    for (move <- possMoves) {
      val newState = new State(newPlayer, new Board(board, move), move)
      children = children.:+(newState)
    }
   // println("Children length:"+children.length)
   /* 
    * TODO this method needs to do something with lastMove --  needs
    * check! does this.board represent the last move reflect the move just made??
    * is it a new board each level of tree? Or, is the last move used to from 
    * leaves to parents in the algo? Parents dont care about last moves, leaves do
    */
  }

  /**
   * Write this State to a file called "output.txt", including its
   * children, their children, etc.. connect4.java.Statemethod allows the State to
   * be viewed in a file even when it is too large to print to console.
   * Beep when printing is done.
   */
  def writeToFile() {
    try {
      val writer = new PrintWriter("output.txt", "UTF-8")
      writer.println(this)
      java.awt.Toolkit.getDefaultToolkit.beep()
    } catch {
      case e@(_: FileNotFoundException | _: UnsupportedEncodingException) => e.printStackTrace()
    }
  }

  override def toString(): String = {
    println("connect4.scala.State.toString printing")
    toStringHelper(0, "")
  }

  /**
   * Return a string that contains a representation of this board indented
   * with string ind (expected to be a string of blank characters) followed
   * by a similar representation of all its children,
   * indented an additional ind characters. d is the depth of this state.
   */
  private def toStringHelper(d: Int, ind: String): String = {
    var str = ind + player + " to play\n"
    str = str + ind + "Value: " + value + "\n"
    str = str + board.toString(ind) + "\n"
    if (children != null && children.length > 0) {
      str = str + ind + "Children at depth " + (d+1) + ":\n" + ind + //plus one is because the children are one below the parent node.
        "----------------\n"
      for (s <- children) {
        str = str + s.toStringHelper(d+1, ind + "   ")
      }
    }
    str
  }

  //Need to add Comparable as an interface State implements if we decide to use this
  override def compareTo(ob: Any): Int = ???
}

