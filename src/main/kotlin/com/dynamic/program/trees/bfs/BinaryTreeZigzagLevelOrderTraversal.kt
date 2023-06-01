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
        val result = mutableListOf<MutableList<Int>>()
        if (root == null) return result
        var q = ArrayDeque<TreeNode>()
        q.addLast(root)

        var dir = 1
        while(q.isNotEmpty()) {
            val size = q.size
            val r = MutableList<Int>(size){0}
            val q1 = ArrayDeque<TreeNode>()
            var idx = size-1
            for(i in 0 until size) {
                val n = q.removeFirst()
                if (dir == 1) {
                    r[i] = (n.`val`)
                } else {
                    r[idx--] = (n.`val`)
                }
                n.left?.let{q1.addLast(it)}
                n.right?.let{q1.addLast(it)}
            }
            q = q1
            result.add(r)
            dir *= -1
        }

        return result
    }

    fun zigzagLevelOrder1(root: TreeNode?): List<List<Int>> {
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
    val head = TreeNode(1, TreeNode(2, TreeNode(4)), TreeNode(3, null, TreeNode(5)))
    println(BinaryTreeZigzagLevelOrderTraversal().zigzagLevelOrder(head))
}
