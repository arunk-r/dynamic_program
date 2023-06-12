package com.dynamic.program.graphs.dfs.medium

import com.dynamic.program.trees.TreeNode

/**
 * https://leetcode.com/problems/print-binary-tree/description/
 *
 * 655. Print Binary Tree
 * Medium
 * 375
 * 378
 * company
 * Uber
 * company
 * LinkedIn
 * company
 * Amazon
 * Given the root of a binary tree, construct a 0-indexed m x n string matrix res that represents a formatted layout of the tree. The formatted layout matrix should be constructed using the following rules:
 *
 * The height of the tree is height and the number of rows m should be equal to height + 1.
 * The number of columns n should be equal to 2height+1 - 1.
 * Place the root node in the middle of the top row (more formally, at location res[0][(n-1)/2]).
 * For each node that has been placed in the matrix at position res[r][c], place its left child at res[r+1][c-2height-r-1] and its right child at res[r+1][c+2height-r-1].
 * Continue this process until all the nodes in the tree have been placed.
 * Any empty cells should contain the empty string "".
 * Return the constructed matrix res.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: root = [1,2]
 * Output:
 * [["","1",""],
 *  ["2","",""]]
 * Example 2:
 *
 *
 * Input: root = [1,2,3,null,4]
 * Output:
 * [["","","","1","","",""],
 *  ["","2","","","","3",""],
 *  ["","","4","","","",""]]
 *
 *
 * Constraints:
 *
 * The number of nodes in the tree is in the range [1, 210].
 * -99 <= Node.val <= 99
 * The depth of the tree will be in the range [1, 10].
 */
/**
 * Example:
 * var ti = TreeNode(5)
 * var v = ti.`val`
 * Definition for a binary tree node.
 * class TreeNode(var `val`: Int) {
 *     var left: TreeNode? = null
 *     var right: TreeNode? = null
 * }
 */
class PrintBinaryTree {
    fun printTree(root: TreeNode?): List<List<String>> {
        val height = height(root)
        val width = Math.pow(2.0, height * 1.0).toInt() - 1
        val result = MutableList(height){MutableList(width){""}}
        fillData(root, 0, 0, width-1, result)
        return result
    }

    fun fillData(root: TreeNode?, row: Int, left: Int, right: Int, result: MutableList<MutableList<String>>) {
        if (root != null) {
            val mid = left + ((right - left))/2
            result[row][mid] = root.`val`.toString()
            fillData(root.left, row+1, left, mid-1, result)
            fillData(root.right, row+1, mid+1, right, result)
        }
    }

    fun height(root: TreeNode?) : Int {
        if (root == null) return 0
        return 1 + maxOf(height(root.left), height(root.right))
    }
}
