package com.dynamic.program.graphs.dfs

import com.dynamic.program.trees.TreeNode

/**
 * Longest ZigZag Path in a Binary Tree
 *
 * You are given the root of a binary tree.
 * A ZigZag path for a binary tree is defined as follow:
 * Choose any node in the binary tree and a direction (right or left).
 * If the current direction is right, move to the right child of the current node; otherwise, move to the left child.
 * Change the direction from right to left or from left to right.
 * Repeat the second and third steps until you can't move in the tree.
 * Zigzag length is defined as the number of nodes visited - 1. (A single node has a length of 0).
 *
 * Return the longest ZigZag path contained in that tree.
 *
 *
 * Example 1:
 * Input: root = [1,null,1,1,1,null,null,1,1,null,1,null,null,null,1,null,1]
 * Output: 3
 * Explanation: Longest ZigZag path in blue nodes (right -> left -> right).
 *
 * Example 2:
 * Input: root = [1,1,1,null,1,null,null,1,1,null,1]
 * Output: 4
 * Explanation: Longest ZigZag path in blue nodes (left -> right -> left -> right).
 *
 * Example 3:
 * Input: root = [1]
 * Output: 0
 *
 *
 * Constraints:
 * The number of nodes in the tree is in the range [1, 5 * 10^4].
 * 1 <= Node.val <= 100
 */
class LongestZigZagPathBinaryTree {
    var max = 0
    fun longestZigZag(root: TreeNode?): Int {
        dfs(root)
        // why max - 1?
        // null is node is considered to initial count
        return max - 1
    }

    fun dfs(node: TreeNode?): Pair<Int, Int> {
        if (node == null) {
            return Pair(0,0)
        }
        val left = dfs(node.left)
        val right = dfs(node.right)
        //here pair contains left and ring counts
        // for left node consider right count + 1
        // for right node consider left count +
        // why? as per algorithm program has to mover from left to right or right to left
        max = Math.max(Math.max(max, 1+left.second), 1 + right.first)
        return Pair(1+left.second, 1 + right.first)
    }

    // below code is also DFS but, failed to handle edge cases
    /* fun longestZigZag(root: TreeNode?): Int {
        root?.let{r ->
            r.left?.let{
                max = 1
                dfs(it, 'l', 1)
            }
            r.right?.let{
                max = 1
                dfs(it, 'r', 1)
            }
        }
        return max
    }

    fun dfs(node: TreeNode, direction: Char, currentCont: Int) {
        if (node.left == null && node.right == null) {
            return
        }
        if (direction == 'l') {
            node.right?.let{
                max = Math.max(max, currentCont + 1)
                dfs(it, 'r', currentCont + 1)
            }
            node.left?.let{dfs(it, 'l', 1)}
        }

        if (direction == 'r') {
            node.right?.let{dfs(it, 'r', 1)}
            node.left?.let{
                max = Math.max(max, currentCont + 1)
                dfs(it, 'l', currentCont + 1)
            }
        }
    }
    */
}

fun main() {
    val r = TreeNode(1, null, TreeNode(1, TreeNode(1), TreeNode(1, TreeNode(1, null, TreeNode(1)), TreeNode(1))))
    println( LongestZigZagPathBinaryTree().longestZigZag(r))
}