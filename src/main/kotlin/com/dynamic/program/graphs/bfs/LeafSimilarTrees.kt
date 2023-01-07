package com.dynamic.program.graphs.bfs

import com.dynamic.program.trees.TreeNode

/**
 * Leaf-Similar Trees
 *
 * Consider all the leaves of a binary tree, from left to right order, the values of those leaves form a leaf value sequence.
 *
 * For example, in the given tree above, the leaf value sequence is (6, 7, 4, 9, 8).
 * Two binary trees are considered leaf-similar if their leaf value sequence is the same.
 * Return true if and only if the two given trees with head nodes root1 and root2 are leaf-similar.
 *
 *
 * Example 1:
 * Input: root1 = [3,5,1,6,2,9,8,null,null,7,4], root2 = [3,5,1,6,7,4,2,null,null,null,null,null,null,9,8]
 * Output: true
 *
 *
 * Example 2:
 * Input: root1 = [1,2,3], root2 = [1,3,2]
 * Output: false
 *
 * Constraints:
 * The number of nodes in each tree will be in the range [1, 200].
 * Both of the given trees will have values in the range [0, 200].
 *
 */
class LeafSimilarTrees {
    fun leafSimilar(root1: TreeNode?, root2: TreeNode?): Boolean {
        val root1Lst = mutableListOf<Int>()
        val root2Lst = mutableListOf<Int>()

        if(root1 == null && root2 == null) return true
        if((root1 != null && root2 == null) || (root1 == null && root2 != null))return false

        root1?.let{dfs(it, root1Lst)}
        root2?.let{dfs(it, root2Lst)}

        if (root1Lst.size != root2Lst.size) return false

        for( i in root1Lst.indices) {
            if (root1Lst[i] != root2Lst[i]) return false
        }

        return true
    }

    private fun dfs(node: TreeNode, lst: MutableList<Int>) {
        if (node.left == null && node.right == null) {
            lst.add(node.`val`)
            return
        }
        node.left?.let { dfs(it, lst) }
        node.right?.let { dfs(it, lst) }
    }
}

fun main() {
    val r1 = TreeNode(3, TreeNode(5, TreeNode(6), TreeNode(2, TreeNode(7), TreeNode(4))), TreeNode(1, TreeNode(9), TreeNode(8)))
    val r5 = TreeNode(3, TreeNode(5, TreeNode(6), TreeNode(7)), TreeNode(1, TreeNode(4), TreeNode(2, TreeNode(9), TreeNode(8))))

    println(LeafSimilarTrees().leafSimilar(r1, r5))
}