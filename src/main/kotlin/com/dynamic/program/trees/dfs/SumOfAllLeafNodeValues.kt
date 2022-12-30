package com.dynamic.program.trees.dfs

import com.dynamic.program.trees.TreeNode

/**
 * Sum of all leaf node count.
 *
 * leaf node do not have any child nodes
 */
class SumOfAllLeafNodeValues {
    fun sum(node: TreeNode): Int {
        if (node.left == null && node.right == null) {
            return node.`val`
        }
        var cnt = 0
        if (node.left != null) {
            cnt += sum(node.left!!)
        }
        if (node.right != null) {
            cnt += sum(node.right!!)
        }
        return cnt
    }
}

fun main() {
    val head = TreeNode(5,
            TreeNode(4, TreeNode(1, TreeNode(2), TreeNode(9)), TreeNode(-6)),
            TreeNode(3, TreeNode(0), TreeNode(7, TreeNode(8), TreeNode(-4)))
    )
    println(SumOfAllLeafNodeValues().sum(head))
}