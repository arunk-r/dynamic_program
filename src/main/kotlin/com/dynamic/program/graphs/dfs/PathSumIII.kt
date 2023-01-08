package com.dynamic.program.graphs.dfs

import com.dynamic.program.trees.TreeNode

/**
 * Path Sum III
 *
 * Given the root of a binary tree and an integer targetSum, return the number of paths where the sum of the values along the path equals targetSum.
 * The path does not need to start or end at the root or a leaf, but it must go downwards (i.e., traveling only from parent nodes to child nodes).
 *
 * Example 1:
 * Input: root = [10,5,-3,3,2,null,11,3,-2,null,1], targetSum = 8
 * Output: 3
 * Explanation: The paths that sum to 8 are shown.
 *
 * Example 2:
 * Input: root = [5,4,8,11,null,13,4,7,2,null,null,5,1], targetSum = 22
 * Output: 3
 *
 *
 * Constraints:
 * The number of nodes in the tree is in the range [0, 1000].
 * -10^9 <= Node.val <= 10^9
 * -1000 <= targetSum <= 1000
 */
class PathSumIII {
    fun pathSum(root: TreeNode?, targetSum: Int): Int {
        val cntArr = mutableListOf<Byte>()
        root?.let{dfs(it, targetSum, mutableListOf<Long>(it.`val`.toLong()), cntArr)}
        return cntArr.size
    }

    private fun dfs(node: TreeNode, targetSum: Int, lst: MutableList<Long>, cntArr: MutableList<Byte>) {
        lst.add(lst[lst.size - 1] + node.`val`)
        foundTarget(lst, targetSum).forEach {
            cntArr.add(1)
        }
        if (node.left == null && node.right == null) {
            return
        } else {
            node.left?.let{dfs(it, targetSum, lst.toMutableList(), cntArr)}
            node.right?.let{dfs(it, targetSum, lst.toMutableList(), cntArr)}
        }
    }

    private fun foundTarget(lst: MutableList<Long>, targetSum: Int): MutableList<Boolean>{
        var begin = 0
        val end = lst.size - 1
        val result = mutableListOf<Boolean>()
        while (begin < end) {
            if (lst[end] - lst[begin] == targetSum.toLong()) result.add(true)
            begin++
        }
        return result
    }
}