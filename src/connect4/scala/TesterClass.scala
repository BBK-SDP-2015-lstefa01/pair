package connect4.scala

object TesterClass extends App{
  
  //Tests makeMove method works! Yep!
  val b= new Board()
  var move = new Move(RED, 4)
  b.makeMove(move)
 // println(b)
 
  b.makeMove(new Move(RED, 4))
  b.makeMove(new Move(RED, 4))
  b.makeMove(new Move(RED, 4))
  b.makeMove(new Move(RED, 4))
  b.makeMove(new Move(RED, 4))
  val state = new State(b.getPlayer(5, 4), b,move)

  
  state.initializeChildren()
  println("Length of children:" + state.children.length)
  println("Level 0-Child elements: ")
  for(child<-state.children) {
    
    println("Child player:" + child.player)
    println("Child board" + "\n" + child.board)
    println("Child last move: " + child.lastMove)
  }
    state.children(0).initializeChildren()
    println("Level 1-Child elements: ")
    for(child2<-state.children(0).children){
      println("Child player:"+child2.player)
      println("Child board"+"\n"+child2.board)
      println("Child last move: "+child2.lastMove)
  }

 // println(b)
  
//  b.makeMove(new Move(RED, 4))
//  b.makeMove(new Move(RED, 4))
//  b.makeMove(new Move(RED, 4))
//  b.makeMove(new Move(YELLOW, 3))
//  b.makeMove(new Move(RED, 5))
 // println(b.toString())
 // println("hasConnectFour: "+b.hasConnectFour()) //returns colour of player
 // println("Length of poss moves: "+b.getPossibleMoves(YELLOW).length)
 // b.getPossibleMoves(YELLOW).foreach(println)
  
  /* Testing the moves that have been made persist on the board once players are instantiated
  *  checked that even if a player is put into column 3 or 4 (where moves have been made 
  *  already that this is not over-ridden in the GUI.
  */
  val p1 = new Human(RED)
  val p2 = new Human(YELLOW)
  
  val tempState = new State(YELLOW, b, move) //is this parameter move correct??
  AI.createGameTree(tempState, 2) //depth of the tree includes the root?
  tempState.writeToFile()
  
  val game= new Game(p1, p2, b, true)
  //we realised that the game constructor with the 4th parameter set to 'false' does not work just yet
//  game.setGUI(new GUI(game, Board.NUM_COLS, Board.NUM_ROWS));
 // game.runGame()
 // println(b.toString())


}
