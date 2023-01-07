package com.dynamic.program.graphs.dfs

import com.dynamic.program.trees.TreeNode

/**
 * Path Sum II
 *
 * Given the root of a binary tree and an integer targetSum, return all root-to-leaf paths where the sum of the node
 * values in the path equals targetSum. Each path should be returned as a list of the node values, not node references.
 *
 * A root-to-leaf path is a path starting from the root and ending at any leaf node. A leaf is a node with no children.
 *
 *
 * Example 1:
 * Input: root = [5,4,8,11,null,13,4,7,2,null,null,5,1], targetSum = 22
 * Output: [[5,4,11,2],[5,8,4,5]]
 * Explanation: There are two paths whose sum equals targetSum:
 * 5 + 4 + 11 + 2 = 22
 * 5 + 8 + 4 + 5 = 22
 *
 * Example 2:
 * Input: root = [1,2,3], targetSum = 5
 * Output: []
 *
 * Example 3:
 * Input: root = [1,2], targetSum = 0
 * Output: []
 *
 * Constraints:
 * The number of nodes in the tree is in the range [0, 5000].
 * -1000 <= Node.val <= 1000
 * -1000 <= targetSum <= 1000
 *
 */
class PathSumII {
    fun pathSum(root: TreeNode?, targetSum: Int): List<List<Int>> {
        val output = mutableListOf<MutableList<Int>>()
        dfs(root, mutableListOf(),targetSum, output)
        return output
    }

    fun dfs(node: TreeNode?, cLst: MutableList<Int>, target: Int, fLst: MutableList<MutableList<Int>>) {
        if (node == null) return
        if (node.left == null && node.right == null && target == node.`val`) {
            cLst.add(node.`val`)
            fLst.add(cLst)
            return
        } else {
            cLst.add(node.`val`)
            if (node.left != null) {
                dfs(node.left!!, cLst.toMutableList(), target - node.`val`, fLst)
            }
            if (node.right != null) {
                dfs(node.right!!, cLst.toMutableList(), target - node.`val`, fLst)
            }
        }
    }
}

fun main() {
    val r = TreeNode(5, TreeNode(4, TreeNode(11, TreeNode(7), TreeNode(2))), TreeNode(8, TreeNode(13), TreeNode(4, TreeNode(5), TreeNode(1))))
    println(PathSumII().pathSum(r, 22))
    val r2 = TreeNode(1, TreeNode(-2, TreeNode(1, TreeNode(-1)), TreeNode(3)), TreeNode(-3, TreeNode(-2)))
    println(PathSumII().pathSum(r2, -1))
}