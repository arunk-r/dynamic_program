package com.dynamic.program.trees.dfs

import com.dynamic.program.trees.TreeNode
import java.util.Stack

/**
 * Maximum Depth of Binary Tree
 * Given the root of a binary tree, find the length of the longest path from the root to a leaf.
 */

fun maxDepth(root: TreeNode?): Int {
    if (root == null) return 0
    val left = maxDepth(root.left)
    val right = maxDepth(root.right)
    return (Math.max(left, right) + 1)
}

fun maxDepthUsingStack(root: TreeNode?): Int {
    if (root == null) return 0
    val stk = Stack<Pair<TreeNode?, Int>>()
    stk.push(Pair(root, 1))
    var ans = 0
    while (stk.isNotEmpty()) {
        val p = stk.pop()
        ans = Math.max(ans, p.second)
        if (p.first?.left != null) {
            stk.push(Pair(p.first?.left, p.second + 1))
        }
        if (p.first?.right != null) {
            stk.push(Pair(p.first?.right, p.second + 1))
        }
    }
    return ans
}

fun main() {
    val root = TreeNode(0,
        TreeNode(1, TreeNode(3), TreeNode(4)),
        TreeNode(2, null, TreeNode(5, null, TreeNode(6)))
    )
    println(maxDepth(root))
    println(maxDepthUsingStack(root))
}
