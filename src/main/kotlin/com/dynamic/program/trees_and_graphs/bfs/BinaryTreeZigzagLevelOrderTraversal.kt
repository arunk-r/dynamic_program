package com.dynamic.program.trees_and_graphs.bfs

import com.dynamic.program.trees_and_graphs.TreeNode
import java.util.*

/**
 * Binary Tree Zigzag Level Order Traversal
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

fun zigzagLevelOrder(root: TreeNode?): List<List<Int>> {
    if (root == null) return emptyList<List<Int>>()

    val q = LinkedList<TreeNode?>()
    q.offer(root)
    val list = mutableListOf<List<Int>>()
    var isRight = false

    while (q.isNotEmpty()) {
        val size = q.size
        val sL = MutableList(size) { 0 }
        var idx = size
        for (i in 0 until size) {
            val node = q.remove()
            if (isRight) {
                node?.let { sL[--idx] = it.`val` }
            } else {
                node?.let { sL[i] = it.`val` }
            }
            if (node?.left != null) {
                q.offer(node.left)
            }
            if (node?.right != null) {
                q.offer(node.right)
            }
        }
        list.add(sL)
        isRight = !isRight
    }
    return list
}

fun main() {
    val head = TreeNode(3, TreeNode(9), TreeNode(20, TreeNode(15), TreeNode(7)))
    println(zigzagLevelOrder(head))
}