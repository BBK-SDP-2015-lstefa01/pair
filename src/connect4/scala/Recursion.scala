package connect4.scala

/**
 * Created by liliya & Esha on 09/02/2015.
 * A class to hold static methods about recursion.
 */


object Recursion {
  
/* You have to implement two recursion problems.
   * 1. matchTarget in Recursion.java
   * 2. isBST in TreeNode.java
   */
  
  /**
   * Return true if is it possible to choose a group of ints in nums
   * that sums to t. You may create a helper method if you see fit.
   * For example:
   * matchTarget({2, 4, 8}, 10) -> true  because 2+8 == 10
   * matchTarget({2, 4, 8}, 14) -> true  because 2+8+4 == 14
   * matchTarget({2, 4, 8},  9) -> false because no grouping of these numbers can sum to 9
   * <p/>
   * NOTE: There may be duplicate numbers in nums.
   * You may only use each number in nums at most once.
   * (i.e. matchTarget({2},6) -> false despite 2+2+2 == 6
   * but matchTarget({2, 2, 2}, 6} -> true)
   */

  def matchTarget(nums: Array[Int], t: Int): Boolean = false
  //TODO: Fill in the method matchTarget
  
  /*
   * This method possibly checks the path down the tree to take to achieve the 'target' value-- 
   * i.e. once a path is identified by the AI as desirable, this method may 'trace' the summing of 'value'
   * to achieve that path.
  Potential implementation notes:
  1. Helper method to do the following:
     1. Take first element in array and check if it's equal to t -> if yes, return true
     2. If not, then check if the sum of second and third equal t -> if yes, true
     3. If not, check sum of first, second, third
   */
}
