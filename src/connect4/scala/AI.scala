package connect4.scala

//remove if not needed

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
   * Note: If s has a winner (four in a row), it should be a leaf.
   */
  def createGameTree(s: State, d: Int): Unit = {
    //the first action is initializing the children of state and then recursively initializing their children
    //    if(d <= 0){
    //     nothing to do
    //    }
    if (d > 0) {
      s.initializeChildren()
      for (child <- s.children) {
        createGameTree(child, d - 1) //  depth of the tree minus 1
      } //TODO ensure that the winner is a leaf node (i.e., maybe use 'hasConnectFour' on the board object to check for win?
    }
  }
    /*
     * Planning:
     * 1. Tree of States (state has a value of how desirable it is).
     * 2. Call evaluate board on a leaf node
     * 3. base case of recursion when d iterator == 'd'
     
     * In another method:
     * Call initialise children on each level until we get to the depth of the tree
     * When we get to the final level of depth, retain the last level
     * Call evalChildren on the last level of the children
     * Call minimax
     * add Value to tree
     */

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
  // call getMoves on the board which has resulted from the run of minimax
  override def getMoves(b: Board): Array[Move] = {
  val newState = new State(player, b, null) //lastMove?
  AI.createGameTree(newState, depth)
  minimax(newState) 
  /*
   * Have a tree with all values in states. newState has a numerical value to represent the preferred moves
   * 
   */
  
  
  val moves = Array[Move]()
  
  
  moves
  }
  
  /**
   * connect4.java.State s is a node of a game tree (i.e. the current connect4.java.State of the game).
   * Use the Minimax algorithm to assign a numerical value to each connect4.java.State of the
   * tree rooted at s, indicating how desirable that java.connect4.java.State is to this player.
   */
  def minimax(s: State): Unit = {
    /*
     * traverse the tree until the leaf 
     * call evaluateBoard() on the leaves
     * and assign value to those state
     * then implement algo for child in children...value from child to pass on to parent.
    */

    if (s.children.length == 0) {
      s.value = evaluateBoard(s.board)
//      println("The state's value is: " + s.value)
    }
    else {
      
      for (child <- s.children) {
        minimax(child)
        if (s.player == child.player.opponent) {
        s.value = min(s.children)
        }
        else {
        s.value = max(s.children)
        }
      }
    }
  }

  /**
   * helper method for calculating min value of children values for minimax
   */
  private def min(arr: Array[State]): Int = {

    var values: Array[Int] = Array[Int]()
    for (state <- arr) {
      values = values.:+(state.value)
    }
    values.min
  }

  /**
   * helper method for calculating max value of children values for minimax
   */
  private def max(arr: Array[State]): Int = {
    var values: Array[Int] = Array[Int]()
    for (state <- arr) {
      values = values.:+(state.value)
    }
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

