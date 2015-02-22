package connect4.scala

/**
 * 
 * Created by liliya & Esha on 09/02/2015.
 * A representation of a binary tree with the current node as the root.
 * Invariant: There are no duplicate values in the tree.* 
 */
 //Constructor: A tree with root value v, left subtree left, and right subtree right.

case class TreeNode(private var left: TreeNode, v: Int, private var right: TreeNode) {
  private var value: Int = v

  /**
   * Return true if this tree is a Binary Search Tree (BST).
   * A BST has these following properties:
   *
   * 1. The values in theconnect4.java.TreeNodebtree of every TreeNode in
   * this connect4.java.TreeNode less than the TreeNode's value.
   *
   * 2. The values in the connect4.java.TreeNodebtree of every TreeNode in
   * this treconnect4.java.TreeNode eater than the TreeNode's value.
   *
   * 3. The left and right subtree each must also be a binary search tree.
   * <p/>
   * Precondition: This tree has no duplicate values.
   */
  def isBST(): Boolean = false
  //TODO: implement the function isBST
}


