package connect4.scala

object Board {
  val NUM_ROWS = 6
  val NUM_COLS = 7

  def apply(b: Board) =
    new Board(b)
}

class Board {

  private val FOUR = 4

  private val deltas = Array(Array(1, 0), Array(0, 1), Array(-1, 1), Array(1, 1))

  private val board = Array.ofDim[Player](Board.NUM_ROWS, Board.NUM_COLS)

  /**
     * Constructor: a duplicate of Board b.
  */
  
  def this(b: Board) {
    this()
    for (r <- 0 until Board.NUM_ROWS; c <- 0 until Board.NUM_COLS) {
      board(r)(c) = b.board(r)(c)
    }
  }

  def getPlayer(r: Int, c: Int): Player = {
    assert(0 <= r && r < Board.NUM_ROWS && 0 <= c && c < Board.NUM_COLS)
    board(r)(c)
  }

  def this(b: Board, nextMove: Move) {
    this(b)
    makeMove(nextMove)
  }

  def getTile(row: Int, col: Int): Player = board(row)(col)

  /**
   * Apply Move move to this Board by placing a piece from move's
   * player into move's column on this Board.
   * Throw an IllegalArgumentException if move's column is full on this Board.
   * @param move
   *
   */
  //TODO Need to ensure the IllegalArgument exception is handled properly
  //TODO Need to ensure that Connect4 assigns a winner when 4 coloured Players are adjacent. Currently not detected (15.02.015)
  
  def makeMove(move: Move): Unit = {

    if (getTile(0, move.column) != null) throw new IllegalArgumentException()

    if (getTile(Board.NUM_ROWS-1, move.column) == null)  board(Board.NUM_ROWS-1)(move.column) = move.player

    else{
      var i = 0 //counter to go through rows
      while (getTile(i, move.column) == null && i < Board.NUM_ROWS - 1) {
        i=i+1
      }
      //If the current position is full, place the Player in the next position
      board(i - 1)(move.column) = move.player
    }

  }


  /**
   * Return an array of all moves that can possibly be made by Player p on
   * this board. The moves must be in order of increasing column number. Note:
   * The length of the array must be the number of possible moves. Note: If
   * the board has a winner (four things of the same colour in a row), no move
   * is possible because the game is over. Note: If the game is not over, the
   * number of possible moves is the number of columns that are not full.
   * Thus, if all columns are full, return an array of length 0.
   */
  def getPossibleMoves(p: Player): Array[Move] = null

  /* Needs to return an array of moves - a move is essentially a column and a player
   * Something like look through the whole board and find all possible positions
   * Possible implementation: 
   * 1. Create ArrayList of length (num_cols)
   * 2. Check board.getTile(0,everyColumn), And if that tile != null, then it is full, so remove this element from the ArrayList
   * 3. Each element of the ArrayList is a Move object with player = player, and column (1-7)
   * 4. ArrayList to Array as final step to return [Move]. 
   * Where is this used though? do we need to find out possible moves in certain direction?
   */

  override def toString(): String = toString("")

  def toString(prefix: String): String = {
    val str = new StringBuilder("")
    for (row <- board) {
      str.append(prefix + "|")
      for (spot <- row) {
        if (spot == null) {
          str.append(" |")
        } else if (spot == RED) {
          str.append("R|")
        } else {
          str.append("Y|")
        }
      }
      str.append("\n")
    }
    str.toString
  }

  def hasConnectFour(): Player = {
    winLocations().find(loc => loc(0) != null && loc(0) == loc(1) && loc(0) == loc(2) &&
      loc(0) == loc(3))
      .map(_(0))
      .getOrElse(null)
  }

  def winLocations(): List[Array[Player]] = {
    val locations = List[Array[Player]]()
    for (delta <- deltas;
            r <- 0 until Board.NUM_ROWS;
                c <- 0 until Board.NUM_COLS) {
      val loc = possibleWin(r, c, delta)
      if (loc != null) {
        locations :+ loc
      }
    }
    locations
  }

  def possibleWin(r: Int, c: Int, delta: Array[Int]): Array[Player] = {
    val location = Array.ofDim[Player](FOUR)
    for (i <- 0 until FOUR) {
      val newR = r + i * delta(0)
      val newC = c + i * delta(1)
      if (0 <= newR && newR < Board.NUM_ROWS && 0 <= newC && newC < Board.NUM_COLS)
        location(i) = board(newR)(newC)
    }
    location
  }
}