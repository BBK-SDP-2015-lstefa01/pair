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
  def createGameTree(s: State, d: Int): Unit = {
    if (d > 0) {
      s.initializeChildren()
      s.children.foreach { child => createGameTree(child, d - 1)}
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

    //if b is null
    if (!Option(b).isDefined) {
      throw new IllegalArgumentException("A valid board is required")
    }

    //if depth is 0 - Exception not thrown up to GUI as we were instructed to not change the Game class
    if (depth == 0) {
      depth = 1; println("No depth of 0 allowed - depth set to default of 1")
    }

    AI.createGameTree(rootState, depth)

    minimax(rootState)

    rootState.children.foreach { child => {
      if (child.value == rootState.value) bestMoves = bestMoves.:+(child.getLastMove)
    }
    }
    bestMoves
  }

  /**
   * connect4.java.State s is a node of a game tree (i.e. the current connect4.java.State of the game).
   * Use the Minimax algorithm to assign a numerical value to each connect4.java.State of the
   * tree rooted at s, indicating how desirable that java.connect4.java.State is to this player.
   * Approach - traverse the tree until the leaf level then call evaluateBoard() on the leaves
   * and assign value to those states then  pass the appropriate value (minValue or maxValue) from child to pass on to parent.
   */
  def minimax(s: State): Unit = {

    if (s.children.length == 0) {
      s.value = evaluateBoard(s.board)
    }
    else {
      s.children.foreach {
        child => minimax(child)
          if (s.player == player) {
            s.value = maxValue(s.children)
          }
          else {
            s.value = minValue(s.children)
        }
      }
    }
  }

  /**
   * helper method for calculating minValue value of children values for minimax
   */
  private def minValue(arr: Array[State]): Int = {
    var values: Array[Int] = Array[Int]()
    arr.foreach { state => values = values.:+(state.value)}
    values.min
  }

  /**
   * helper method for calculating maxValue value of children values for minimax
   */
  private def maxValue(arr: Array[State]): Int = {
    var values: Array[Int] = Array[Int]()
    arr.foreach { state => values = values.:+(state.value)}
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

