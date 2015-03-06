package connect4.scala

//  implicit def convertValue(v: Value): connect4.scala.Player = v.asInstanceOf[connect4.scala.Player]

sealed trait Player {
  def opponent: Player
}

final case object RED extends Player {
  def opponent = YELLOW
}
final case object YELLOW extends Player {
  def opponent = RED
}