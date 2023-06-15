package com.dynamic.program.trees.dfs.medium

import com.dynamic.program.trees.TreeNode

/**
 * https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-search-tree/description/
 * Lowest Common Ancestor of a Binary Tree
 *
 * Given the root of a binary tree and two nodes p and q that are in the tree, return the lowest common ancestor (LCA) of the two nodes.
 * The LCA is the lowest node in the tree that has both p and q as descendants (note: a node is a descendant of itself).
 *
 * 1. The root node is p or q. The answer cannot be below the root node, because then it would be missing the root (which is either p or q) as a descendant.
 * 2. One of p or q is in the left subtree, and the other one is in the right subtree. The root must be the answer,
 *    because it is the connection point between the two subtrees, and thus the lowest node to have both p and q as descendants.
 * 3. Both p and q are in one of the subtrees. In that case, the root is not the answer because we could look inside the subtree and find a "lower" node.
 */
class LowestCommonAncestorOfBinaryTree {

    fun lowestCommonAncestor(root: TreeNode?, p: TreeNode?, q: TreeNode?): TreeNode? {
        if (root == null) return root
        else if (root == p || root == q) return root

        val left = lowestCommonAncestor(root.left, p, q)
        val right = lowestCommonAncestor(root.right, p, q)

        return if (left == null) right
        else if (right == null) left
        else root
    }
}

fun main() {
    val q = TreeNode(4)
    val p = TreeNode(5, TreeNode(6), TreeNode(2, TreeNode(7), q))
    val head = TreeNode(3, p, TreeNode(1, TreeNode(0), TreeNode(8)))
    println(LowestCommonAncestorOfBinaryTree().lowestCommonAncestor(head, p, q))
}
