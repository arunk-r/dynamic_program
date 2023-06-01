package com.dynamic.program.trees.bfs

import com.dynamic.program.trees.TreeNode
import java.util.*

/**
 * Binary Tree Right Side View
 *
 * Given the root of a binary tree, imagine yourself standing on the right side of it. Return the values of the nodes you can see ordered from top to bottom.
 */
class BinaryTreeRightSideView {
    fun rightSideView(root: TreeNode?): List<Int> {
        val result = mutableListOf<Int>()
        if (root == null) return result
        val q = ArrayDeque<TreeNode>()
        q.add(root)
        while(q.isNotEmpty()) {
            for(i in q.size-1 downTo 0) {
                val cur = q.removeFirst()
                cur.left?.let{q.addLast(it)}
                cur.right?.let{q.addLast(it)}

                if(i == 0) {
                    result.add(cur.`val`)
                }
            }
        }
        return result
    }

    fun rightSideView1(root: TreeNode): List<Int> {
        val list = mutableListOf<Int>()
        val queue = LinkedList<TreeNode?>()
        queue.offer(root)
        while (queue.isNotEmpty()) {
            val size = queue.size
            var prev: TreeNode? = null
            for (i in 0 until size) {
                prev = queue.remove()
                if (prev?.left != null) {
                    queue.add(prev.left)
                }
                if (prev?.right != null) {
                    queue.add(prev.right)
                }
            }
            prev?.let { list.add(it.`val`) }
        }
        return list
    }
}

fun main() {
    val head = TreeNode(1, TreeNode(2), TreeNode(3, TreeNode(4), TreeNode(5)))
    println(BinaryTreeRightSideView().rightSideView(head))
}
