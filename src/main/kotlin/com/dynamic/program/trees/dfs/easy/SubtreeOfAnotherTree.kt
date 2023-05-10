package com.dynamic.program.trees.dfs.easy

import com.dynamic.program.trees.TreeNode

/**
 * 572. Subtree of Another Tree
 * Easy
 *
 * company
 * Amazon
 * company
 * Adobe
 * company
 * Apple
 * Given the roots of two binary trees root and subRoot, return true if there is a subtree of root with the same structure and node values of subRoot and false otherwise.
 *
 * A subtree of a binary tree tree is a tree that consists of a node in tree and all of this node's descendants. The tree tree could also be considered as a subtree of itself.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: root = [3,4,5,1,2], subRoot = [4,1,2]
 * Output: true
 * Example 2:
 *
 *
 * Input: root = [3,4,5,1,2,null,null,null,null,0], subRoot = [4,1,2]
 * Output: false
 *
 *
 * Constraints:
 *
 * The number of nodes in the root tree is in the range [1, 2000].
 * The number of nodes in the subRoot tree is in the range [1, 1000].
 * -104 <= root.val <= 104
 * -104 <= subRoot.val <= 104
 */
class SubtreeOfAnotherTree {

    fun isSubtree(root: TreeNode?, subRoot: TreeNode?): Boolean {
        if (root == null && subRoot == null) return true
        if (root == null || subRoot == null) return false

        return if (root.`val` == subRoot.`val` && isSameTree(root, subRoot)) {
            true
        } else {
            isSubtree(root.left, subRoot) || isSubtree(root.right, subRoot)
        }
    }

    private fun isSameTree(root: TreeNode?, subRoot: TreeNode?): Boolean {
        if (root == null && subRoot == null) return true
        if (root == null || subRoot == null) return false

        if (root.`val` != subRoot.`val`) {
            return false
        }
        return isSameTree(root.left, subRoot.left) && isSameTree(root.right, subRoot.right)
    }

    var found = false
    fun isSubtree1(root: TreeNode?, subRoot: TreeNode?): Boolean {
        if (root == null || subRoot == null) return false
        val key = getKey(subRoot)
        findSubTree(root, key)
        return found
    }

    private fun getKey(node: TreeNode?): String {
        if (node == null) return ""
        val left = getKey(node.left)
        val right = getKey(node.right)
        return "$left,l,${node.`val`},$right,r"
    }

    private fun findSubTree(node: TreeNode?, key: String): String {
        if (!found) {
            if (node == null) return ""
            val left = findSubTree(node.left, key)
            val right = findSubTree(node.right, key)
            val newKey = "$left,l,${node.`val`},$right,r"
            if (newKey == key || left == key || right == key) found = true
            return newKey
        } else return ""
    }
}
