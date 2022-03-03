package com.dynamic.program.hard.max_path_sum

import kotlin.math.max

/**
 *
 * Write a function that takes in a Binary Tree and returns its max path sum.
 *
 * A path is a collection of connected nodes in a tree, where no node is
 * connected to more than two other nodes; a path sum is the sum of the values of
 * the nodes in a particular path.
 *
 * Each BinaryTree node has an integer value, a
 * left child node, and a right child node. Children
 * nodes can either be BinaryTree nodes themselves or None/null.
 *
 * Sample Input
 *      tree = 1
 *          /     \
 *         2       3
 *       /   \   /   \
 *      4     5 6     7
 *
 * Sample Output
 * 18 // 5 + 2 + 1 + 3 + 7
 *
 */
open class BinaryTree(value: Int) {
    var value = value
    var left: BinaryTree? = null
    var right: BinaryTree? = null
}

fun maxPathSum(tree: BinaryTree): Int {
    // Write your code here.
    val (_, maxSum) = findPathSum(tree)
    return maxSum
}

fun findPathSum(tree: BinaryTree?): List<Int> {
    // Write your code here.
    if (tree == null) return listOf(0, Int.MIN_VALUE)
    val (leftMaxSumAsBranch, leftMaxPathSum) = findPathSum(tree.left)
    val (rightMaxSumAsBranch, rightMaxPathSum) = findPathSum(tree.right)
    val maxChildSumAsBranch = max(leftMaxSumAsBranch, rightMaxSumAsBranch)

    val value = tree.value

    val maxSumAsBranch = max(maxChildSumAsBranch + value, value)
    val maxSumAsRoot = max(leftMaxSumAsBranch + value + rightMaxSumAsBranch, maxSumAsBranch)
    val maxPathSum = max(max(leftMaxPathSum, rightMaxPathSum), maxSumAsRoot)
    return listOf(maxSumAsBranch, maxPathSum)
}

fun main() {
    val root = BinaryTree(1)
    val left1 = BinaryTree(2)
    val right1 = BinaryTree(3)
    root.left = left1
    root.right = right1

    val leftLeft1 = BinaryTree(4)
    val leftRight1 = BinaryTree(5)
    left1.left = leftLeft1
    left1.right = leftRight1

    val rightLeft1 = BinaryTree(4)
    val rightRight1 = BinaryTree(5)
    right1.left = rightLeft1
    right1.right = rightRight1

    println(maxPathSum(root))
}