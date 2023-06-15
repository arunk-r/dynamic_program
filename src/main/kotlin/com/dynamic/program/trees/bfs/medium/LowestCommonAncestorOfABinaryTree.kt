package com.dynamic.program.trees.bfs.medium

import com.dynamic.program.trees.TreeNode

/**
 * 236. Lowest Common Ancestor of a Binary Tree
Medium

Given a binary tree, find the lowest common ancestor (LCA) of two given nodes in the tree.

According to the definition of LCA on Wikipedia: “The lowest common ancestor is defined between two nodes p and q as the lowest node in T that has both p and q as descendants (where we allow a node to be a descendant of itself).”



Example 1:


Input: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 1
Output: 3
Explanation: The LCA of nodes 5 and 1 is 3.
Example 2:


Input: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 4
Output: 5
Explanation: The LCA of nodes 5 and 4 is 5, since a node can be a descendant of itself according to the LCA definition.
Example 3:

Input: root = [1,2], p = 1, q = 2
Output: 1


Constraints:

The number of nodes in the tree is in the range [2, 105].
-109 <= Node.val <= 109
All Node.val are unique.
p != q
p and q will exist in the tree.
 */
class LowestCommonAncestorOfABinaryTree {
    fun lowestCommonAncestor(root: TreeNode?, p: TreeNode?, q: TreeNode?): TreeNode? {
        var cur = root
        while (cur != null) {
            if (p!!.`val` > cur.`val` && q!!.`val` > cur.`val`) {
                cur = cur.right
            } else if (p!!.`val` < cur.`val` && q!!.`val` < cur.`val`) {
                cur = cur.left
            } else return cur
        }
        return root
    }

    val path = mutableListOf<Int>()
    val depth = mutableListOf<Int>()
    val map = hashMapOf<Int, Int>()
    val ref = hashMapOf<Int, TreeNode>()

    fun lowestCommonAncestor1(root: TreeNode?, p: TreeNode?, q: TreeNode?): TreeNode? {
        if (root == null) return null
        dfs(root, 0)
        val idx1 = map[p!!.`val`]!!
        val idx2 = map[q!!.`val`]!!

        var min = Int.MAX_VALUE
        var item = 0
        for(i in minOf(idx1, idx2) .. maxOf(idx1, idx2)) {
            if (depth[i] < min) {
                min = depth[i]
                item = path[i]
            }
        }
        return ref[item]!!
    }

    fun dfs(node: TreeNode, level: Int) {
        path.add(node.`val`)
        depth.add(level)

        if (!map.containsKey(node.`val`)) {
            map.put(node.`val`, path.size-1)
            ref.put(node.`val`, node)
        }

        if(node.left != null) {
            dfs(node.left!!, level+1)
            path.add(node.`val`)
            depth.add(level)
        }
        if(node.right != null) {
            dfs(node.right!!, level+1)
            path.add(node.`val`)
            depth.add(level)
        }

    }
}
