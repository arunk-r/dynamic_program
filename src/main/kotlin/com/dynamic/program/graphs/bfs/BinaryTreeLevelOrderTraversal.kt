package com.dynamic.program.graphs.bfs

import com.dynamic.program.trees.TreeNode

/**
 * Binary Tree Level Order Traversal
 *
 * Given the root of a binary tree, return the level order traversal of its nodes' values. (i.e., from left to right, level by level).
 *
 * Example 1:
 * Input: root = [3,9,20,null,null,15,7]
 * Output: [[3],[9,20],[15,7]]
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
 * -1000 <= Node.val <= 1000
 *
 */
class BinaryTreeLevelOrderTraversal {
    fun levelOrder(root: TreeNode?): List<List<Int>> {
        val out = mutableListOf<MutableList<Int>>()
        val q = ArrayDeque<TreeNode>()
        root?.let{q.addLast(it)}
        while (q.isNotEmpty()) {
            val size = q.size
            val lst = mutableListOf<Int>()
            for (i in 0 until size) {
                val c = q.removeFirst()
                lst.add(c.`val`)

                c.left?.let{q.addLast(it)}
                c.right?.let{q.addLast(it)}
            }
            out.add(lst)
        }
        return out
    }
}