package com.dynamic.program.trees.bfs

import com.dynamic.program.trees.TreeNode
import java.util.LinkedList

/**
 * Find Largest Value in Each Tree Row
 * Given the root of a binary tree, return an array of the largest value in each row of the tree.
 */

fun largestValue(root: TreeNode): List<Int> {
    val list = mutableListOf<Int>()
    val queue = LinkedList<TreeNode?>()
    queue.add(root)
    while (queue.isNotEmpty()) {
        var max = Int.MIN_VALUE
        val size = queue.size
        for (i in 0 until size) {
            val node = queue.remove()
            node?.let { max = Math.max(max, it.`val`) }
            if (node?.left != null) {
                queue.offer(node.left)
            }
            if (node?.right != null) {
                queue.offer(node.right)
            }
        }
        list.add(max)
    }
    return list
}

fun main() {
    val head = TreeNode(1,
            TreeNode(3, TreeNode(5), TreeNode(3)),
            TreeNode(2, null, TreeNode(9))
    )
    println(largestValue(head))
}