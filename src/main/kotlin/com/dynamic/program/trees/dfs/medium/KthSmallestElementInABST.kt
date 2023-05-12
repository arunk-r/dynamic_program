package com.dynamic.program.trees.dfs.medium

import com.dynamic.program.trees.TreeNode

/**
 * 230. Kth Smallest Element in a BST
 * Medium
 *
 * company
 * Uber
 * company
 * Amazon
 * company
 * Facebook
 * Given the root of a binary search tree, and an integer k, return the kth smallest value (1-indexed) of all the values of the nodes in the tree.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: root = [3,1,4,null,2], k = 1
 * Output: 1
 * Example 2:
 *
 *
 * Input: root = [5,3,6,2,4,null,null,1], k = 3
 * Output: 3
 *
 *
 * Constraints:
 *
 * The number of nodes in the tree is n.
 * 1 <= k <= n <= 104
 * 0 <= Node.val <= 104
 *
 *
 * Follow up: If the BST is modified often (i.e., we can do insert and delete operations) and you need to find the kth smallest frequently, how would you optimize?
 */
class KthSmallestElementInABST {
    var cur = -1
    var idx = 0
    fun kthSmallest(root: TreeNode?, k: Int): Int {
        find(root, k)
        return cur
    }

    fun find(root: TreeNode?, k: Int) {
        if (root == null) {
            return
        }
        find(root.left, k)
        idx++
        if (idx == k) {
            cur = root.`val`
        }
        find(root.right, k)
    }

    fun kthSmallest1(root: TreeNode?, k: Int): Int {
        val lst = mutableListOf<Int>()
        inorder(root, lst)
        return lst[k-1]
    }

    private fun inorder(root: TreeNode?, lst: MutableList<Int>) {
        if (root != null) {
            inorder(root.left, lst)
            lst.add(root.`val`)
            inorder(root.right, lst)
        }
    }
}
