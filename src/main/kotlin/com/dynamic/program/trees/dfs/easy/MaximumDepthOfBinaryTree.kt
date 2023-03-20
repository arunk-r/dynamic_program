package com.dynamic.program.trees.dfs.easy

import com.dynamic.program.trees.TreeNode

class MaximumDepthOfBinaryTree {
    var max = 0
    fun maxDepth(root: TreeNode?): Int {
        depth(root, 0)
        return max
    }

    private fun depth(node: TreeNode?, d: Int) {
        if (node == null) {
            max = maxOf(max, d)
        } else {
            depth(node.left, d+1)
            depth(node.right, d+1)
        }
    }
}
