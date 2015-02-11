package connect4.scala

/**
 * Created by liliya on 09/02/2015.
 */
trait Solver {

  def getMoves(b: Board): Array[Move]
}
