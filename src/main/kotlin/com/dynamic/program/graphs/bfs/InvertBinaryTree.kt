package com.dynamic.program.graphs.bfs

import com.dynamic.program.trees.TreeNode

/**
 * Invert Binary Tree
 *
 * Given the root of a binary tree, invert the tree, and return its root.
 *
 * Example 1:
 * Input: root = [4,2,7,1,3,6,9]
 * Output: [4,7,2,9,6,3,1]
 *
 * Example 2:
 * Input: root = [2,1,3]
 * Output: [2,3,1]
 *
 * Example 3:
 * Input: root = []
 * Output: []
 *
 * Constraints:
 * The number of nodes in the tree is in the range [0, 100].
 * -100 <= Node.val <= 100
 *
 */
class InvertBinaryTree {
    /**
     * DFS
     */
    fun invertTree(root: TreeNode?): TreeNode? {
        if(root == null) {
            return null
        }
        val tmp = root.left
        root.left = root.right
        root.right = tmp
        invertTree(root.left)
        invertTree(root.right)
        return root
    }

    /**
     * BFS
     */
    fun invertTree1(root: TreeNode?): TreeNode? {
        val q = ArrayDeque<TreeNode?>()
        root?.let { q.addLast(root) }
        while (q.isNotEmpty()) {
            val size = q.size
            for (i in 0 until size) {
                val cur: TreeNode? = q.removeFirst()
                cur?.let { c ->
                    val tmp: TreeNode? = c.left
                    c.left = c.right
                    c.right = tmp

                    c.left?.let { q.addLast(it) }
                    c.right?.let { q.addLast(it) }
                }
            }
        }
        return root
    }
}
