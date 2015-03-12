package connect4.scala

/**
 * AI implementation
 */
object AI {
  /**
   * Generate the game tree with root s of depth d.
   * The game tree's nodes are connect4.java.State objects that represent the state of a game
   * and whose children are all possible States that can result from the next move.
   * <p/>
   * NOTE: this method runs in exponential time with respect to d.
   * With d around 5 or 6, it is extremely slow and will start to take a very
   * long time to run.
   * <p/>
   * Initialize children of states recursively down the tree*
   * Note: If s has a winner (four in a row), it should be a leaf.
   */
  //TODO ensure that the winner is a leaf node (i.e., maybe use 'hasConnectFour' on the board object to check for win?
  //FIXME ensure depth > 0 else throw exception or send a note to the GUI
  def createGameTree(s: State, d: Int): Unit = {
    //    if (d > 0) {
    //      s.initializeChildren()
    //      for (child <- s.children) {
    //        createGameTree(child, d - 1) //  depth of the tree minus 1
    //      }
    //    }

    if (d > 0) {
      s.initializeChildren()
      s.children.foreach { child =>
        createGameTree(child, d - 1)
      }
    }
  }

  /**
   * Call minimax in ai with state s.
   */
  def minimax(ai: AI, s: State) {
    ai.minimax(s)
  }
}

/**
 * Constructor: an instance with player p who searches to depth d
 * when searching the game space for moves.
 */
class AI(private var player: Player, private var depth: Int) extends Solver {

  /**
   * Return this AI's preferred Moves. If this AI prefers one
   * Move above all others, return an array of length 1. Larger arrays
   * indicate equally preferred Moves.
   * An array of size 0 indicates that there are no possible moves.
   * Precondition: b is not null.
   */
  override def getMoves(b: Board): Array[Move] = {

    val rootState = new State(player, b, null) //last move is null because last move of root tree is unknown
    var bestMoves = Array[Move]()

    AI.createGameTree(rootState, depth)

    var childState: State = null
    minimax(rootState)
    
    rootState.children.foreach {child => {
      if (child.value == rootState.value) bestMoves = bestMoves.:+(child.getLastMove)
    }}

//    for (child1 <- rootState.children) {
//      if (child1.value == rootState.value) {
//        childState = child1
//        bestMoves = bestMoves.:+(childState.getLastMove)
//      }
//    }
//    rootState.writeToFile() //FIXME debugging println, delete when complete
//    println("Length: " + bestMoves.length)
    bestMoves
  }

  /**
   * Helper to find the matching value in the children: traverses to the leaf node to return the matching state's value.
   */
  //  private def findChild(state:State): State = {
  //    def helper(state:State): State = {
  //      if (state.children.length == 0) {
  //    }
  //      return state
  //  }
  //    for (i<-0 until state.children.length) {
  //      println("hiya")
  //      if (state.children(i).value == state.value) {
  //     findChild(state.children(i))
  //      }
  //    }
  //  findChild(state)
  //  }

  /**
   * connect4.java.State s is a node of a game tree (i.e. the current connect4.java.State of the game).
   * Use the Minimax algorithm to assign a numerical value to each connect4.java.State of the
   * tree rooted at s, indicating how desirable that java.connect4.java.State is to this player.
   * Approach - traverse the tree until the leaf level then call evaluateBoard() on the leaves
   * and assign value to those states then  pass the appropriate value (minValue or maxValue) from child to pass on to parent.
   */
  def minimax(s: State): Unit = {

    if (s.children.length == 0) { s.value = evaluateBoard(s.board) } 
    else {
      s.children.foreach{child => minimax(child)
//      for (child <- s.children) {
//        minimax(child)
        if (s.player == player) {s.value = maxValue(s.children)
        } else { s.value = minValue(s.children)
        }
      }
    }
  }

  /**
   * helper method for calculating minValue value of children values for minimax
   */
  private def minValue(arr: Array[State]): Int = {
    var values: Array[Int] = Array[Int]()
//    for (state <- arr) {
//      values = values.:+(state.value)
//    }
    arr.foreach { state => values = values.:+(state.value) }
    values.min
  }

  /**
   * helper method for calculating maxValue value of children values for minimax
   */
  private def maxValue(arr: Array[State]): Int = {
    var values: Array[Int] = Array[Int]()
    arr.foreach { state => values = values.:+(state.value) }

    //      for (state <- arr) {
    //      values = values.:+(state.value)
    //    }
    values.max
  }

  /**
   * Evaluate the desirability of connect4.java.Board b for this player
   * Precondition: b is a leaf node of the game tree (because that is most
   * effective when looking several moves into the future).
   */
  def evaluateBoard(b: Board): Int = {
    val winner = b.hasConnectFour()
    var value = 0
    if (!winner.isDefined) {
      val locs = b.winLocations()
      for (loc <- locs; p <- loc) {
        value += (if (p == player) 1 else if (p != null) -1 else 0)
      }
    } else {
      var numEmpty = 0
      var r = 0
      while (r < Board.NUM_ROWS) {
        var c = 0
        while (c < Board.NUM_COLS) {
          if (b.getTile(r, c) == null) numEmpty += 1
          c = c + 1
        }
        r = r + 1
      }
      value = (if (winner.get == player) 1 else -1) * 10000 * numEmpty
    }
    value
  }
}

