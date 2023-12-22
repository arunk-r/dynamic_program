package com.dynamic.program.trees.dfs.medium

import com.dynamic.program.trees.TreeNode

class LowestCommonAncestorOfDeepestLeaves {
    var max = 0
    var lca: TreeNode? = null

    fun lcaDeepestLeaves(root: TreeNode?): TreeNode? {
        lca = root
        helper(root, 0)
        return lca
    }


    private fun helper(node: TreeNode?, depth: Int): Int {
        if (node == null) {
            return 0
        }

        if (depth > max) {
            max = depth
            lca = node
        }

        val maxLeft = helper(node.left, depth + 1)
        val maxRight = helper(node.right, depth + 1)

        if (max == maxLeft && max == maxRight) {
            lca = node
        }
        return maxOf(depth, maxOf(maxLeft, maxRight))
    }
}
