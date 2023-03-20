package com.dynamic.program.trees.bfs

import com.dynamic.program.trees.TreeNode
import java.util.*

/**
 * 103. Binary Tree Zigzag Level Order Traversal
Medium

Bloomberg
Amazon
Microsoft

 * Given the root of a binary tree, return the zigzag level order traversal of its nodes' values.
 * (i.e., from left to right, then right to left for the next level and alternate between).
 *
 * Example 1:
 * Input: root = [3,9,20,null,null,15,7]
 * Output: [[3],[20,9],[15,7]]
 *
 * Example 2:
 * Input: root = [1]
 * Output: [[1]]
 *
 * Example 3:
 * Input: root = []
 * Output: []
 *
 * Constraints:
 * The number of nodes in the tree is in the range [0, 2000].
 * -100 <= Node.val <= 100
 */

class BinaryTreeZigzagLevelOrderTraversal  {
    fun zigzagLevelOrder(root: TreeNode?): List<List<Int>> {
        val q = ArrayDeque<TreeNode>()
        root?.let{q.addLast(it)}
        val result = mutableListOf<MutableList<Int>>()
        var right = false
        while(q.isNotEmpty()) {
            val s = q.size
            var lst = mutableListOf<Int>()
            for (i in 0 until s) {
                val n = q.removeFirst()
                lst.add(n.`val`)
                n.left?.let{q.addLast(it)}
                n.right?.let{q.addLast(it)}
            }

            if(right) {
                result.add(lst.reversed().toMutableList())
            } else {
                result.add(lst)
            }

            right = !right
        }

        return result
    }
}

fun main() {
    val head = TreeNode(3, TreeNode(9), TreeNode(20, TreeNode(15), TreeNode(7)))
    println(BinaryTreeZigzagLevelOrderTraversal().zigzagLevelOrder(head))
}
