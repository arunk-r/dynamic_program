package com.dynamic.program.data.structures.tree

import com.dynamic.program.trees.TreeNode

class BinarySearchTree {
    fun search(root: TreeNode?, x: Int): Boolean {
        return if (root == null) false
        else {
            if (root.`val` == x) true
            else if (root.`val` > x) {
                search(root.left, x)
            } else {
                search(root.right, x)
            }
        }
    }

    fun nextBiggestOfK(root: TreeNode?, k: Int): Int? {
        return if (root == null) null
        else {
            if (root.`val` > k) {
                val t = nextBiggestOfK(root.left, k)
                if (t == null && root.`val` > k) root.`val`
                else t
            } else {
                val t = nextBiggestOfK(root.right, k)
                if (t == null && root.`val` > k) root.`val`
                else t
            }
        }
    }

    fun nextSmallestOfK(root: TreeNode?, k: Int): Int? {
        return if (root == null) null
        else {
            if (root.`val` > k) {
                val t = nextSmallestOfK(root.left, k)
                if (t == null && root.`val` < k && root.left != null) root.left?.`val`
                else t
            } else {
                if (root.right != null && (root.right?.`val` ?: 0) > k && root.right?.left != null) {
                    val t = getLeft(root.right?.left)
                    t?.`val`
                } else {
                    val t = nextSmallestOfK(root.right, k)
                    if (t == null && root.`val` < k) {
                        root.`val`
                    } else {
                        t
                    }
                }
            }
        }
    }

    private fun getLeft(root: TreeNode?): TreeNode? {
        return if (root?.left != null) getLeft(root.left)
        else root
    }
}

fun main() {
    val tree = TreeNode(
        11,
        TreeNode(
            6,
            TreeNode(
                3,
                TreeNode(2), TreeNode(4)
            ),
            TreeNode(
                8,
                TreeNode(7),
                TreeNode(9)
            )
        ),
        TreeNode(
            14,
            TreeNode(12, null, TreeNode(13)),
            TreeNode(16)
        )
    )
    val bst = BinarySearchTree()
    println(bst.search(tree, 8))
    println(bst.search(tree, 10))
    println(bst.nextBiggestOfK(tree, 8))
    println(bst.nextBiggestOfK(tree, 10))
    println(bst.nextSmallestOfK(tree, 8))
    println(bst.nextSmallestOfK(tree, 10))
    println(bst.nextSmallestOfK(tree, 4))
    println(bst.nextSmallestOfK(tree, 5))
}
