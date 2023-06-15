package com.dynamic.program.graphs.dfs.medium

import com.dynamic.program.trees.TreeNode

/**
 * https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-search-tree/description/
 *  Lowest Common Ancestor of a Binary Search Tree
 *
 * Given a binary search tree (BST), find the lowest common ancestor (LCA) node of two given nodes in the BST.
 * According to the definition of LCA on Wikipedia: “The lowest common ancestor is defined between two nodes p and q as
 * the lowest node in T that has both p and q as descendants (where we allow a node to be a descendant of itself).”
 *
 *
 * Example 1:
 * Input: root = [6,2,8,0,4,7,9,null,null,3,5], p = 2, q = 8
 * Output: 6
 * Explanation: The LCA of nodes 2 and 8 is 6.
 *
 *
 * Example 2:
 * Input: root = [6,2,8,0,4,7,9,null,null,3,5], p = 2, q = 4
 * Output: 2
 * Explanation: The LCA of nodes 2 and 4 is 2, since a node can be a descendant of itself according to the LCA definition.
 *
 *
 * Example 3:
 * Input: root = [2,1], p = 2, q = 1
 * Output: 2
 *
 *
 */
class LowestCommonAncestorOfBinarySearchTree {
    val depth = mutableListOf<Int>()
    val nodes = mutableListOf<TreeNode>()
    val lastVisit = hashMapOf<Int, Int>()
    fun lowestCommonAncestor(root: TreeNode?, p: TreeNode?, q: TreeNode?): TreeNode? {
        if (root == null || (p == null || q == null)) return null

        dfs(root, 1)

        val pIdx = lastVisit[p.`val`]?:0
        val qIdx = lastVisit[q.`val`]?:0

        val lftIdx = Math.min(pIdx, qIdx)
        val rhtIdx = Math.max(pIdx, qIdx)

        var min = Int.MAX_VALUE
        var idx = 0
        (lftIdx .. rhtIdx).forEach { i ->
            if (depth[i] < min) {
                min = depth[i]
                idx = i
            }
        }
        return nodes[idx]
    }

    private fun dfs(root: TreeNode?, depth: Int) {
        if (root == null) return
        // capture current node data
        captureDepth(root, depth)
        // move left
        root.left?.let{
            dfs(it, depth + 1)
            // capture current node data
            captureDepth(root, depth)
        }
        // move right
        root.right?.let {
            dfs(it, depth + 1)
            // capture current node data
            captureDepth(root, depth)
        }
    }

    private fun captureDepth(node:TreeNode, d: Int) {
        depth.add(d)
        nodes.add(node)
        lastVisit[node.`val`] = depth.size - 1
    }



}

fun main() {
    val t5 = TreeNode(5)
    val t3 = TreeNode(3)
    val t7 = TreeNode(7)
    val h = TreeNode(6, TreeNode(2, TreeNode(0), TreeNode(4, t3, t5)), TreeNode(8, t7, TreeNode(9)))
    println(LowestCommonAncestorOfBinarySearchTree().lowestCommonAncestor(h, t5, t3))
}
