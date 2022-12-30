package com.dynamic.program.trees.bfs

import com.dynamic.program.trees.TreeNode

/**
 * Find the height of a binary tree. The height of a tree is the number of edges from the root to the lowest leaf
 */
class TreeHeight {
    fun heightBFS(root: TreeNode): Int {
        var cnt = -1
        val queue = ArrayDeque<TreeNode>()
        queue.addLast(root)
        while (queue.isNotEmpty()) {
            val size = queue.size
            for (i in 0 until size) {
                val node = queue.removeFirst()
                node.left?.let { queue.addLast(it) }
                node.right?.let { queue.addLast(it) }
            }
            cnt++
        }
        return cnt
    }
    fun heightDFS(root: TreeNode): Int {
        if (root.left == null && root.right == null) return 0
        var left = 0
        if (root.left != null) {
            left = heightDFS(root.left!!)
        }
        var right = 0
        if (root.right != null) {
            right = heightDFS(root.right!!)
        }
        return Math.max(left, right) + 1
    }
}

fun main() {
    val head = TreeNode(1,
            TreeNode(2, TreeNode(3, TreeNode(5), TreeNode(6)), TreeNode(4)),
            TreeNode(7, TreeNode(8), TreeNode(9))
    )
    println(TreeHeight().heightBFS(head))
    println(TreeHeight().heightDFS(head))
}