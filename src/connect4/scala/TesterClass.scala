package connect4.scala

object TesterClass extends App{
  
  val p1 = new Dummy(RED)
  val p2 = new Dummy(YELLOW)
        
  val b= new Board()
  b.makeMove(new Move(RED, 4))
  b.makeMove(new Move(YELLOW, 3))
  b.makeMove(new Move(RED, 5))
  
  val game= new Game(p1, p2)
  game.setGUI(new GUI(game, Board.NUM_COLS, Board.NUM_ROWS));
  game.runGame()
}

