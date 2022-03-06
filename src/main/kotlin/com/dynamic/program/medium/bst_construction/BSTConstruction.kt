package com.dynamic.program.medium.bst_construction

/**
 * 
Write a BST class for a Binary Search Tree. The class should
support:


Inserting values with the insert method.

Removing values with the remove method; this method should
only remove the first instance of a given value.

Searching for values with the contains method.


Note that you can't remove values from a single-node tree. In other words,
calling the remove method on a single-node tree should simply not
do anything.


Each BST node has an integer value, a
left child node, and a right child node. A node is
said to be a valid BST node if and only if it satisfies the BST
property: its value is strictly greater than the values of every
node to its left; its value is less than or equal to the values
of every node to its right; and its children nodes are either valid
BST nodes themselves or None / null.

Sample Usage
// Assume the following BST has already been created:
10
/     \
5      15
/   \   /   \
2     5 13   22
/           \
1            14

// All operations below are performed sequentially.
insert(12):   10
/     \
5      15
/   \   /   \
2     5 13   22
/        /  \
1        12  14

remove(10):   12
/     \
5      15
/   \   /   \
2     5 13   22
/           \
1            14

contains(15): true


 */

// Do not edit the class below except for
// the insert, contains, and remove methods.
// Feel free to add new properties and methods
// to the class.
open class BST(value: Int) {
    var value = value
    var left: BST? = null
    var right: BST? = null

    fun insert(value: Int): BST {
        // Write your code here.
        // Do not edit the return statement of this method.
        if (value < this.value) {
            if (this.left == null) {
                this.left = BST(value)
            } else {
                this.left!!.insert(value)
            }
        } else {
            if (this.right == null) {
                this.right = BST(value)
            } else {
                this.right!!.insert(value)
            }
        }

        return this
    }

    fun contains(value: Int): Boolean {
        // Write your code here.
        return if (value < this.value) {
            if (this.left == null) false
            else this.left!!.contains(value)
        } else if (value > this.value) {
            if (this.right == null) false
            else this.right!!.contains(value)
        } else true
    }

    fun remove(value: Int, parentNode: BST? = null): BST {
        // Write your code here.
        // Do not edit the return statement of this method.
        if (value < this.value) {
            if (this.left != null) {
                this.left!!.remove(value, this)
            }
        } else if (value > this.value) {
            if (this.right != null) {
                this.right!!.remove(value, this)
            }
        } else {
            if (this.left != null && this.right != null) {
                this.value = this.right!!.getSmallestValue()
                this.right!!.remove(this.value, this)
            } else if (parentNode == null) {
                if (this.left != null) {
                    this.value = this.left!!.value
                    this.right = this.left!!.right
                    this.left = this.left!!.left
                } else if (this.right != null) {
                    this.value = this.right!!.value
                    this.left = this.right!!.left
                    this.right = this.right!!.right
                } else {
                    //currentNode.value
                }
            } else if (parentNode.left == this) {
                parentNode.left = if (this.left != null) this.left else this.right
            } else if (parentNode.right == this) {
                parentNode.right = if (this.left != null) this.left else this.right
            }
        }
        return this
    }

    private fun getSmallestValue(): Int {
        if (this.left == null) {
            return this.value
        }
        return this.left!!.getSmallestValue()
    }
}