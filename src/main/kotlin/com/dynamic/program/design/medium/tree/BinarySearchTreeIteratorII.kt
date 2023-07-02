package com.dynamic.program.design.medium.tree

import com.dynamic.program.trees.TreeNode
import java.util.Stack

/**
 * https://leetcode.com/problems/binary-search-tree-iterator-ii/
 *
 * 1586. Binary Search Tree Iterator II
 * Medium
 * 247
 * 30
 * company
 * Microsoft
 * company
 * Facebook
 * Implement the BSTIterator class that represents an iterator over the in-order traversal of a binary search tree (BST):
 *
 * BSTIterator(TreeNode root) Initializes an object of the BSTIterator class. The root of the BST is given as part of the constructor. The pointer should be initialized to a non-existent number smaller than any element in the BST.
 * boolean hasNext() Returns true if there exists a number in the traversal to the right of the pointer, otherwise returns false.
 * int next() Moves the pointer to the right, then returns the number at the pointer.
 * boolean hasPrev() Returns true if there exists a number in the traversal to the left of the pointer, otherwise returns false.
 * int prev() Moves the pointer to the left, then returns the number at the pointer.
 * Notice that by initializing the pointer to a non-existent smallest number, the first call to next() will return the smallest element in the BST.
 *
 * You may assume that next() and prev() calls will always be valid. That is, there will be at least a next/previous number in the in-order traversal when next()/prev() is called.
 *
 *
 *
 * Example 1:
 *
 *
 *
 * Input
 * ["BSTIterator", "next", "next", "prev", "next", "hasNext", "next", "next", "next", "hasNext", "hasPrev", "prev", "prev"]
 * [[[7, 3, 15, null, null, 9, 20]], [null], [null], [null], [null], [null], [null], [null], [null], [null], [null], [null], [null]]
 * Output
 * [null, 3, 7, 3, 7, true, 9, 15, 20, false, true, 15, 9]
 *
 * Explanation
 * // The underlined element is where the pointer currently is.
 * BSTIterator bSTIterator = new BSTIterator([7, 3, 15, null, null, 9, 20]); // state is   [3, 7, 9, 15, 20]
 * bSTIterator.next(); // state becomes [3, 7, 9, 15, 20], return 3
 * bSTIterator.next(); // state becomes [3, 7, 9, 15, 20], return 7
 * bSTIterator.prev(); // state becomes [3, 7, 9, 15, 20], return 3
 * bSTIterator.next(); // state becomes [3, 7, 9, 15, 20], return 7
 * bSTIterator.hasNext(); // return true
 * bSTIterator.next(); // state becomes [3, 7, 9, 15, 20], return 9
 * bSTIterator.next(); // state becomes [3, 7, 9, 15, 20], return 15
 * bSTIterator.next(); // state becomes [3, 7, 9, 15, 20], return 20
 * bSTIterator.hasNext(); // return false
 * bSTIterator.hasPrev(); // return true
 * bSTIterator.prev(); // state becomes [3, 7, 9, 15, 20], return 15
 * bSTIterator.prev(); // state becomes [3, 7, 9, 15, 20], return 9
 *
 *
 * Constraints:
 *
 * The number of nodes in the tree is in the range [1, 105].
 * 0 <= Node.val <= 106
 * At most 105 calls will be made to hasNext, next, hasPrev, and prev.
 *
 *
 * Follow up: Could you solve the problem without precalculating the values of the tree?
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
class BinarySearchTreeIteratorII(root: TreeNode?) {
    private val data = mutableListOf<Int>()
    private var counter = -1
    private val stk = Stack<TreeNode>()

    init {
        findLeft(root)
    }

    private fun findLeft(root: TreeNode?) {
        var n = root
        while(n != null) {
            stk.push(n)
            n = n.left
        }
    }

    fun hasNext(): Boolean {
        return counter < data.size-1 || stk.isNotEmpty()
    }

    fun next(): Int {
        counter++
        if(counter == 0 || counter == data.size) {
            val d = stk.pop()
            data.add(d.`val`)
            findLeft(d.right)
        }
        return data[counter]
    }

    fun hasPrev(): Boolean {
        return counter > 0
    }

    fun prev(): Int {
        counter--
        return data[counter]
    }

}

fun main() {
    val data = TreeNode(7, TreeNode(3), TreeNode(15, TreeNode(9), TreeNode(20)))
    val bst = BinarySearchTreeIteratorII(data)
    println(bst.next())
    println(bst.next())
    println(bst.prev())
    println(bst.next())
    println(bst.hasNext())
    println(bst.next())
    println(bst.next())
    println(bst.next())
    println(bst.hasNext())
    println(bst.hasPrev())
    println(bst.prev())
    println(bst.prev())
}

/**
 * Your BSTIterator object will be instantiated and called as such:
 * var obj = BSTIterator(root)
 * var param_1 = obj.hasNext()
 * var param_2 = obj.next()
 * var param_3 = obj.hasPrev()
 * var param_4 = obj.prev()
 */
