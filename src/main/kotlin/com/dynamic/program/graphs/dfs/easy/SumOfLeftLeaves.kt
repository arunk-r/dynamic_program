package com.dynamic.program.graphs.dfs.easy

import com.dynamic.program.trees.TreeNode

/**
 * 404. Sum of Left Leaves
 * Easy
 * 4.5K
 * 277
 * company
 * Amazon
 * company
 * Bloomberg
 * company
 * Adobe
 * Given the root of a binary tree, return the sum of all left leaves.
 *
 * A leaf is a node with no children. A left leaf is a leaf that is the left child of another node.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: root = [3,9,20,null,null,15,7]
 * Output: 24
 * Explanation: There are two left leaves in the binary tree, with values 9 and 15 respectively.
 * Example 2:
 *
 * Input: root = [1]
 * Output: 0
 *
 *
 * Constraints:
 *
 * The number of nodes in the tree is in the range [1, 1000].
 * -1000 <= Node.val <= 1000
 */
class SumOfLeftLeaves {
    fun sumOfLeftLeaves(root: TreeNode?): Int {
        return if (root == null) 0
        else leftLeaf(root, false)
    }

    private fun leftLeaf(root: TreeNode?, left: Boolean): Int {
        return if (root == null) 0
        else if (root.left == null && root.right == null && left) root.`val`
        else leftLeaf(root.left, true) + leftLeaf(root.right, false)
    }
}
