package com.dynamic.program.graphs.bfs

import com.dynamic.program.trees.TreeNode

/**
 * Same Tree
 *
 * Given the roots of two binary trees p and q, write a function to check if they are the same or not.
 * Two binary trees are considered the same if they are structurally identical, and the nodes have the same value.
 *
 * Example 1:
 * Input: p = [1,2,3], q = [1,2,3]
 * Output: true
 *
 * Example 2:
 * Input: p = [1,2], q = [1,null,2]
 * Output: false
 *
 * Example 3:
 * Input: p = [1,2,1], q = [1,1,2]
 * Output: false
 *
 * Constraints:
 * The number of nodes in both trees is in the range [0, 100].
 * -10^4 <= Node.val <= 10^4
 */
class SameTree {
    fun isSameTree(p: TreeNode?, q: TreeNode?): Boolean {
        val leftQ = ArrayDeque<TreeNode?>()
        val rightQ = ArrayDeque<TreeNode?>()

        if (p == null && q == null) return true
        if ((p == null && q != null) || (p != null && q == null)) return false
        leftQ.addLast(p)
        rightQ.addLast(q)

        while(leftQ.isNotEmpty() && rightQ.isNotEmpty()) {
            val size = leftQ.size
            for(i in 0 until size) {
                val l = leftQ.removeFirst()
                val r = rightQ.removeFirst()
                if (l != null && r != null && l.`val` == r.`val`) {

                    if (l.left != null && r.left != null){
                        leftQ.addLast(l.left)
                        rightQ.addLast(r.left)
                    } else if ((l.left == null && r.left != null) || (l.left != null && r.left == null)){
                        return false
                    }
                    if (l.right != null && r.right != null){
                        leftQ.addLast(l.right)
                        rightQ.addLast(r.right)
                    } else if ((l.right == null && r.right != null) || (l.right != null && r.right == null)) {
                        return false
                    }
                } else {
                    return false
                }
            }
        }

        return true
    }
}