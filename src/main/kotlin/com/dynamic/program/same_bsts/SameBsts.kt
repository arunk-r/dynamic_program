package com.dynamic.program.same_bsts

/**
 * An array of integers is said to represent the Binary Search Tree (BST)
 * obtained by inserting each integer in the array, from left to right, into the
 * BST.
 *
 * Write a function that takes in two arrays of integers and determines whether
 * these arrays represent the same BST. Note that you're not allowed to
 * construct any BSTs in your code.
 *
 * A BST is a Binary Tree that consists only of BST nodes. A node is said to be a
 * valid <span>BST</span> node if and only if it satisfies the BST property: its value is
 * strictly greater than the values of every node to its left; its value is less
 * than or equal to the values of every node to its right; and its children nodes
 * are either valid BST nodes themselves or None/null
 *
 * Sample Input
 * arrayOne = [10, 15, 8, 12, 94, 81, 5, 2, 11]
 * arrayTwo = [10, 8, 5, 15, 2, 12, 11, 94, 81]
 * Sample Output
 * // both arrays represent the BST below
 *                10
 *             /     \
 *            8      15
 *          /       /   \
 *         5      12    94
 *        /       /     /
 *       2       11    81
 */

fun sameBsts(arrayOne: List<Int>, arrayTwo: List<Int>): Boolean {
    // Write your code here.
    return true
}


fun main() {
    println(sameBsts(listOf(10, 15, 8, 12, 94, 81, 5, 2, 11), listOf(10, 8, 5, 15, 2, 12, 11, 94, 81)))
}