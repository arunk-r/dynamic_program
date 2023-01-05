package com.dynamic.program.graphs.dfs

import com.dynamic.program.trees.TreeNode

/**
 * All Nodes Distance K in Binary Tree
 * Given the root of a binary tree, the value of a target node target, and an integer k, return an array of the values of all nodes
 * that have a distance k from the target node.
 *
 * You can return the answer in any order.
 *
 *
 * Example 1:
 * Input: root = [3,5,1,6,2,0,8,null,null,7,4], target = 5, k = 2
 * Output: [7,4,1]
 * Explanation: The nodes that are a distance 2 from the target node (with value 5) have values 7, 4, and 1.
 *
 * Example 2:
 * Input: root = [1], target = 1, k = 3
 * Output: []
 *
 * Constraints:
 * The number of nodes in the tree is in the range [1, 500].
 * 0 <= Node.val <= 500
 * All the values Node.val are unique.
 * target is the value of one of the nodes in the tree.
 * 0 <= k <= 1000
 */
class AllNodesDistanceKInBinaryTree {
    private val parent = hashMapOf<TreeNode, TreeNode?>()
    fun distanceK(root: TreeNode?, target: TreeNode?, k: Int): List<Int> {
        if (root == null) return emptyList()
        dfs(root, null)

        val queue = ArrayDeque<TreeNode>()
        val seen = mutableSetOf<TreeNode>()

        target?.let {
            queue.add(it)
        }

        var distance = 0

        while (queue.isNotEmpty() && distance < k) {
            val size = queue.size
            for (i in 0 until size) {
                val node = queue.removeFirst()
                if (!seen.contains(node)) {
                    parent[node]?.let { queue.addLast(it) }
                    node.left?.let { queue.addLast(it) }
                    node.right?.let { queue.addLast(it) }
                    seen.add(node)
                }
            }
            distance++
        }

        val list = mutableListOf<Int>()
        while (queue.isNotEmpty()) {
            val node = queue.removeFirst()
            if (!seen.contains(node)) {
                list.add(node.`val`)
            }
        }
        return list.toList()
    }

    fun dfs(root: TreeNode, p: TreeNode?) {
        parent[root] = p
        root.left?.let { dfs(it, root) }
        root.right?.let { dfs(it, root) }
    }
}

fun main() {
    val target = TreeNode(5, TreeNode(6), TreeNode(2, TreeNode(7), TreeNode(4)))
    val head = TreeNode(3, target, TreeNode(1, TreeNode(0), TreeNode(8)))
    println(AllNodesDistanceKInBinaryTree().distanceK(head, target, 2))
}