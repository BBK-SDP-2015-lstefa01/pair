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
    //until depth is met
    
//    if(d < 0){ throw new IllegalArgumentException("You can't have a depth of < 0 levels if you want to create a game tree!")}
    /*
     * with a depth of '0' nothing will be written to the file, as all that is called is the initialise children method. Nothing
     */
    if(d <= 0){
      //s.board.getPossibleMoves(s.getPlayer().opponent) do we just have to get the possible moves instead of 
      //initialising children?
     // println("InitialiseChildren being called on level d==0.")
//      s.initializeChildren()
      //println("This state's children's length is: "+s.children.length)
//      println("finished")
    }
    if(d>0) {
      s.initializeChildren()
    //  println("InitialiseChildren being called on level d>0.")
      for(child <- s.children){
     //   println("This state's children's length is: "+ s.children.length)
        createGameTree(child, d-1)//  depth of the tree minus 1
      } //TODO ensure that the winner is a leaf node (i.e., maybe use 'hasConnectFour' on the board object to check for win?
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

  override def getMoves(b: Board): Array[Move] = ???
  /// call get moves on the board which has resulted from the run of minimax? 
  
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
    
    if(s.children.length == 0) {
      s.value = evaluateBoard(s.board)
      println("The state's value is: "+s.value)
      val tempNode = new TreeNode(null, s.value, null)
    //BST?
    }
    else {
//      s.children.foreach { x => minimax(x) }
      for(child <- s.children) {
//        val leftNode = new TreeNode(null, child.children.min, null)
//        val anotherNode = new TreeNode(child.children.min, child.value, child.children.max)
        minimax(child)
//        min()
      }
    }
  }
  
  private def min() = ???

  private def max() = ???
  
  
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

