package com.dynamic.program.graphs.bfs.medium

import com.dynamic.program.trees.TreeNode

/**
 * 513. Find Bottom Left Tree Value - https://leetcode.com/problems/find-bottom-left-tree-value/description/
 * Medium
 * 2.9K
 * 242
 * company
 * Amazon
 * company
 * Yahoo
 * company
 * Apple
 * Given the root of a binary tree, return the leftmost value in the last row of the tree.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: root = [2,1,3]
 * Output: 1
 * Example 2:
 *
 *
 * Input: root = [1,2,3,4,null,5,6,null,null,7]
 * Output: 7
 *
 *
 * Constraints:
 *
 * The number of nodes in the tree is in the range [1, 104].
 * -231 <= Node.val <= 231 - 1
 */
class FindBottomLeftTreeValue {
    fun findBottomLeftValue(root: TreeNode?): Int {
        if (root == null) return 0
        val q = ArrayDeque<TreeNode>()
        var ans = 0
        q.addLast(root)
        while(q.isNotEmpty()) {
            val s = q.size
            for(i in s downTo 1) {
                val cur = q.removeFirst()
                if (i == s) {
                    ans = cur.`val`
                }

                cur.left?.let{q.addLast(it)}
                cur.right?.let{q.addLast(it)}
            }
        }
        return ans
    }
}
