package connect4.scala

/**
 * Created by liliya on 09/02/2015.
 */
case class TreeNode(private var left: TreeNode, v: Int, private var right: TreeNode) {
  private var value: Int = v

  def isBST(): Boolean = false
}
