package com.dynamic.program.graphs.bfs.medium

import com.dynamic.program.trees.TreeNode

/**
 * https://leetcode.com/problems/maximum-width-of-binary-tree/
 *
 * 662. Maximum Width of Binary Tree
 * Medium
 * 7.6K
 * 1K
 * company
 * Amazon
 * company
 * Bloomberg
 * company
 * Google
 * Given the root of a binary tree, return the maximum width of the given tree.
 *
 * The maximum width of a tree is the maximum width among all levels.
 *
 * The width of one level is defined as the length between the end-nodes (the leftmost and rightmost non-null nodes), where the null nodes between the end-nodes that would be present in a complete binary tree extending down to that level are also counted into the length calculation.
 *
 * It is guaranteed that the answer will in the range of a 32-bit signed integer.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: root = [1,3,2,5,3,null,9]
 * Output: 4
 * Explanation: The maximum width exists in the third level with length 4 (5,3,null,9).
 * Example 2:
 *
 *
 * Input: root = [1,3,2,5,null,null,9,6,null,7]
 * Output: 7
 * Explanation: The maximum width exists in the fourth level with length 7 (6,null,null,null,null,null,7).
 * Example 3:
 *
 *
 * Input: root = [1,3,2,5]
 * Output: 2
 * Explanation: The maximum width exists in the second level with length 2 (3,2).
 *
 *
 * Constraints:
 *
 * The number of nodes in the tree is in the range [1, 3000].
 * -100 <= Node.val <= 100
 */
class MaximumWidthOfBinaryTree {
    fun widthOfBinaryTree(root: TreeNode?): Int {
        if (root == null) return 0
        val q = ArrayDeque<TreeNode>()
        q.addLast(root)

        var result = Int.MIN_VALUE
        val dummy = TreeNode(-1)
        while(q.isNotEmpty()) {
            result = maxOf(result, q.size)
            for(i in q.size downTo 1) {
                val n = q.removeFirst()
                if (n == dummy) {
                    q.addLast(dummy)
                    q.addLast(dummy)
                } else  {
                    n?.left?.let{q.addLast(it)} ?: q.addLast(dummy)
                    n?.right?.let{q.addLast(it)} ?: q.addLast(dummy)
                }
            }
            while(q.isNotEmpty() && q.last() == dummy) {
                q.removeLast()
            }
            while(q.isNotEmpty() && q.first() == dummy) {
                q.removeFirst()
            }
        }
        return result
    }
}
fun main(){
    //val node = TreeNode(1, TreeNode(3, TreeNode(5), TreeNode(3)), TreeNode(2, null, TreeNode(9)))
    val node = TreeNode(1, TreeNode(3, TreeNode(5, TreeNode(6))), TreeNode(2, null, TreeNode(9, TreeNode(7))))
    //val node = TreeNode(1, TreeNode(3, TreeNode(5)), TreeNode(2))
    println(MaximumWidthOfBinaryTree().widthOfBinaryTree(node))
}
