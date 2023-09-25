package com.dynamic.program.top.hundred.hard

/**
 * https://leetcode.com/problems/encode-n-ary-tree-to-binary-tree/description/?envType=study-plan-v2&envId=premium-algo-100
 *
 * 431. Encode N-ary Tree to Binary Tree
 * Hard
 * 503
 * 26
 * Design an algorithm to encode an N-ary tree into a binary tree and decode the binary tree to get the original N-ary tree. An N-ary tree is a rooted tree in which each node has no more than N children. Similarly, a binary tree is a rooted tree in which each node has no more than 2 children. There is no restriction on how your encode/decode algorithm should work. You just need to ensure that an N-ary tree can be encoded to a binary tree and this binary tree can be decoded to the original N-nary tree structure.
 *
 * Nary-Tree input serialization is represented in their level order traversal, each group of children is separated by the null value (See following example).
 *
 * For example, you may encode the following 3-ary tree to a binary tree in this way:
 *
 *
 *
 * Input: root = [1,null,3,2,4,null,5,6]
 * Note that the above is just an example which might or might not work. You do not necessarily need to follow this format, so please be creative and come up with different approaches yourself.
 *
 *
 *
 * Example 1:
 *
 * Input: root = [1,null,3,2,4,null,5,6]
 * Output: [1,null,3,2,4,null,5,6]
 * Example 2:
 *
 * Input: root = [1,null,2,3,4,5,null,null,6,7,null,8,null,9,10,null,null,11,null,12,null,13,null,null,14]
 * Output: [1,null,2,3,4,5,null,null,6,7,null,8,null,9,10,null,null,11,null,12,null,13,null,null,14]
 * Example 3:
 *
 * Input: root = []
 * Output: []
 *
 *
 * Constraints:
 *
 * The number of nodes in the tree is in the range [0, 104].
 * 0 <= Node.val <= 104
 * The height of the n-ary tree is less than or equal to 1000
 * Do not use class member/global/static variables to store states. Your encode and decode algorithms should be stateless.
 */
class `EncodeN-aryTreeToBinaryTree` {
    class Node(var `val`: Int) {
        var children: List<Node?> = listOf()
    }

    class TreeNode(var `val`: Int) {
        var left: TreeNode? = null
        var right: TreeNode? = null
    }

    // Encodes a tree to a single string.
    fun encode(node: Node?): TreeNode? {
        if (node == null) {
            return null
        } else {
            val n = TreeNode(node.`val`)
            if (node.children.isNotEmpty()) {
                n.left = encode(node.children[0])
                var newRight = n.left
                for (i in 1 until node.children.size) {
                    newRight?.right = encode(node.children[i])
                    newRight = newRight?.right
                }
            }
            return n
        }
    }

    // Decodes your encoded data to tree.
    fun decode(root: TreeNode?): Node? {
        if (root == null) {
            return null
        } else {
            val n = Node(root.`val`)
            if (root.left != null) {
                val lst = mutableListOf<Node?>()
                var right = root.left
                while (right != null) {
                    lst.add(decode(right))
                    right = right?.right
                }
                n.children = lst
            }
            return n
        }
    }
}
