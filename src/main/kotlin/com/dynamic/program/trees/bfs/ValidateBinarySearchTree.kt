package com.dynamic.program.trees.bfs

import com.dynamic.program.trees.TreeNode

/**
 * 98. Validate Binary Search Tree
Medium

Bloomberg
Amazon
Zillow

Given the root of a binary tree, determine if it is a valid binary search tree (BST).

A valid BST is defined as follows:

The left
subtree
of a node contains only nodes with keys less than the node's key.
The right subtree of a node contains only nodes with keys greater than the node's key.
Both the left and right subtrees must also be binary search trees.


Example 1:


Input: root = [2,1,3]
Output: true
Example 2:


Input: root = [5,1,4,null,null,3,6]
Output: false
Explanation: The root node's value is 5 but its right child's value is 4.


Constraints:

The number of nodes in the tree is in the range [1, 104].
-231 <= Node.val <= 231 - 1
 */
class ValidateBinarySearchTree {
    fun isValidBST(root: TreeNode?): Boolean {
        return valid(root, Long.MIN_VALUE, Long.MAX_VALUE)
    }

    fun valid(node: TreeNode?, min: Long, max: Long): Boolean {
        if (node == null) return true
        if (node.`val` <= min || node.`val` >= max) {
            return false
        }
        return valid(node.left, min, node.`val`.toLong()) && valid(node.right, node.`val`.toLong(), max)

    }
}
fun main() {
    val head = TreeNode(10,
            TreeNode(5, TreeNode(2), TreeNode(8)),
            TreeNode(12, null, TreeNode(23))
    )
    println(ValidateBinarySearchTree().isValidBST(head))
}
