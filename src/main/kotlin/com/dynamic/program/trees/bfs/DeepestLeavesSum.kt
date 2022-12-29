package com.dynamic.program.trees.bfs

import com.dynamic.program.trees.TreeNode
import java.util.LinkedList

/**
 * Deepest Leaves Sum
 * Given the root of a binary tree, return the sum of values of its deepest leaves.
 *
 * Example 1:
 * Input: root = [1,2,3,4,5,null,6,7,null,null,null,null,8]
 * Output: 15
 *
 * Example 2:
 * Input: root = [6,7,8,2,7,1,3,9,null,1,4,null,null,null,5]
 * Output: 19
 *
 * Constraints:
 * The number of nodes in the tree is in the range [1, 10^4].
 * 1 <= Node.val <= 100
 */

fun deepestLeavesSum(root: TreeNode?): Int {
    if (root == null) return 0
    var sum = 0
    val queue = LinkedList<TreeNode?>()
    queue.offer(root)
    while(queue.isNotEmpty()) {
        val size = queue.size
        sum = 0
        for (i in 0 until size) {
            val node = queue.remove()
            node?.let{ sum += it.`val`}
            if (node?.left != null) {
                queue.add(node.left)
            }
            if (node?.right != null) {
                queue.add(node.right)
            }
        }
    }

    return sum
}