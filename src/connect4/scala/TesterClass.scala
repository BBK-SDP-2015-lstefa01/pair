package connect4.scala

object TesterClass extends App{
  
  //Tests makeMove method works! Yep!
  val b= new Board()
  b.makeMove(new Move(RED, 4))
  b.makeMove(new Move(RED, 4))
  b.makeMove(new Move(RED, 4))
  b.makeMove(new Move(RED, 4))
  b.makeMove(new Move(YELLOW, 3))
  b.makeMove(new Move(RED, 5))
  println(b.toString())
  println("hasConnectFour: "+b.hasConnectFour()) //returns colour of player
  
  
  /* Testing the moves that have been made persist on the board once players are instantiated
  *  checked that even if a player is put into column 3 or 4 (where moves have been made 
  *  already that this is not over-ridden in the GUI.
  */
  val p1 = new Human(RED)
  val p2 = new Human(YELLOW)
  val game= new Game(p1, p2, b, true)
  //we realised that the game constructor with the 4th parameter set to 'false' does not work just yet
//  game.setGUI(new GUI(game, Board.NUM_COLS, Board.NUM_ROWS));
  game.runGame()
  println(b.toString())
}

