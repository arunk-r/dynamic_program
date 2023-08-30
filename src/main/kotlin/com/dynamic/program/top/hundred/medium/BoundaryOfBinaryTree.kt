package com.dynamic.program.top.hundred.medium

import com.dynamic.program.trees.TreeNode

/**
 * https://leetcode.com/problems/boundary-of-binary-tree/description/?envType=study-plan-v2&envId=premium-algo-100
 *
 * 545. Boundary of Binary Tree
 * Medium
 * 1.3K
 * 2K
 * company
 * Amazon
 * company
 * Microsoft
 * company
 * Facebook
 * The boundary of a binary tree is the concatenation of the root, the left boundary, the leaves ordered from left-to-right, and the reverse order of the right boundary.
 *
 * The left boundary is the set of nodes defined by the following:
 *
 * The root node's left child is in the left boundary. If the root does not have a left child, then the left boundary is empty.
 * If a node in the left boundary and has a left child, then the left child is in the left boundary.
 * If a node is in the left boundary, has no left child, but has a right child, then the right child is in the left boundary.
 * The leftmost leaf is not in the left boundary.
 * The right boundary is similar to the left boundary, except it is the right side of the root's right subtree. Again, the leaf is not part of the right boundary, and the right boundary is empty if the root does not have a right child.
 *
 * The leaves are nodes that do not have any children. For this problem, the root is not a leaf.
 *
 * Given the root of a binary tree, return the values of its boundary.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: root = [1,null,2,3,4]
 * Output: [1,3,4,2]
 * Explanation:
 * - The left boundary is empty because the root does not have a left child.
 * - The right boundary follows the path starting from the root's right child 2 -> 4.
 *   4 is a leaf, so the right boundary is [2].
 * - The leaves from left to right are [3,4].
 * Concatenating everything results in [1] + [] + [3,4] + [2] = [1,3,4,2].
 * Example 2:
 *
 *
 * Input: root = [1,2,3,4,5,6,null,null,null,7,8,9,10]
 * Output: [1,2,4,7,8,9,10,6,3]
 * Explanation:
 * - The left boundary follows the path starting from the root's left child 2 -> 4.
 *   4 is a leaf, so the left boundary is [2].
 * - The right boundary follows the path starting from the root's right child 3 -> 6 -> 10.
 *   10 is a leaf, so the right boundary is [3,6], and in reverse order is [6,3].
 * - The leaves from left to right are [4,7,8,9,10].
 * Concatenating everything results in [1] + [2] + [4,7,8,9,10] + [6,3] = [1,2,4,7,8,9,10,6,3].
 *
 *
 * Constraints:
 *
 * The number of nodes in the tree is in the range [1, 104].
 * -1000 <= Node.val <= 1000
 */
class BoundaryOfBinaryTree {
    enum class NodeType {LEFT, RIGHT, ROOT, MIDDLE}
    fun boundaryOfBinaryTree(root: TreeNode?): List<Int> {
        val left = mutableListOf<Int>()
        val right = mutableListOf<Int>()
        val leaves = mutableListOf<Int>()
        preorder(root, left, leaves, right, NodeType.ROOT)
        left.addAll(leaves)
        left.addAll(right)
        return left
    }

    private fun preorder(node: TreeNode?, left: MutableList<Int>, leaves: MutableList<Int>, right: MutableList<Int>, type: NodeType) {
        if (node == null) return
        // root
        if (type == NodeType.ROOT || type == NodeType.LEFT) {
            node.`val`.let { left.add(it) }
        } else if (type == NodeType.RIGHT) {
            node.`val`.let { right.add(0, it) }
        } else if (node.left == null && node.right == null){
            node.`val`.let { leaves.add(it) }
        }
        if (node.left != null) {
            preorder(node.left, left, leaves, right, getNodeType(node, type, true))
        }
        if (node.right != null) {
            preorder(node.right, left, leaves, right, getNodeType(node, type, false))
        }
    }

    private fun getNodeType(node: TreeNode?, type: NodeType, isLeftNode: Boolean): NodeType {
        if (type == NodeType.ROOT) {
            return if (isLeftNode) NodeType.LEFT else NodeType.RIGHT
        }
        if (type == NodeType.LEFT && !isLeftNode && node?.left != null) {
            return NodeType.MIDDLE
        }

        if (type == NodeType.RIGHT && isLeftNode && node?.right != null) {
            return NodeType.MIDDLE
        }
        return type
    }
}
