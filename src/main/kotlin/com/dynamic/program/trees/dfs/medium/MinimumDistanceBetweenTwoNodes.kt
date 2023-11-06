package com.dynamic.program.trees.dfs.medium

/**
 * Write a function that given a BST, it will return the distance (number of edges) between 2 nodes.
 * For example, given this tree
 *
 *          5
 *         / \
 *        3   6
 *       / \   \
 *      2   4   7
 *     /         \
 *    1           8
 * The distance between 1 and 4 is 3: [1 -> 2 -> 3 -> 4]
 * The distance between 1 and 8 is 6: [1 -> 2 -> 3 -> 5 -> 6 -> 7 -> 8]
 */
class MinimumDistanceBetweenTwoNodes {
    data class TreeNode(val v: Int, val left: TreeNode? = null, val right: TreeNode? = null)
    fun distance(root: TreeNode, min: Int, max: Int): Int {
        val parent = findCommonNode(root, min, max)
        return getDistance(parent, min) + getDistance(parent, max)
    }

    private fun getDistance(node: TreeNode?, v: Int): Int {
        if (node == null) return -1
        if (node.v == v) return 0
        var src = node.left
        if (node.v < v) {
            src = node.right
        }
        return 1 + getDistance(src, v)
    }

    private fun findCommonNode(node: TreeNode, min: Int, max: Int): TreeNode? {
        var n: TreeNode? = node
        var prev: TreeNode? = null
        while (n != null && n != prev) {
            if (n.v >  min && n.v > max) {
                prev = n
                n = n.left
            } else if (n.v < min && n.v < max) {
                prev = n
                n = n.right
            } else {
                prev = n
            }
        }
        return n
    }
}

fun main() {
    val node = MinimumDistanceBetweenTwoNodes.TreeNode(5,
        MinimumDistanceBetweenTwoNodes.TreeNode(3,
            MinimumDistanceBetweenTwoNodes.TreeNode(2,
                MinimumDistanceBetweenTwoNodes.TreeNode(1)),
            MinimumDistanceBetweenTwoNodes.TreeNode(4)),
        MinimumDistanceBetweenTwoNodes.TreeNode(6,null,
            MinimumDistanceBetweenTwoNodes.TreeNode(7, null,
                MinimumDistanceBetweenTwoNodes.TreeNode(8)))
        )
    println(MinimumDistanceBetweenTwoNodes().distance(node, 1, 4))
    println(MinimumDistanceBetweenTwoNodes().distance(node, 0, 12))
}

