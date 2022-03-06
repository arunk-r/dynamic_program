package com.dynamic.program.easy.branch_sum

/**
 *
Write a function that takes in a Binary Tree and returns a list of its branch
sums ordered from leftmost branch sum to rightmost branch sum.

A branch sum is the sum of all values in a Binary Tree branch. A Binary Tree
branch is a path of nodes in a tree that starts at the root node and ends at
any leaf node.

Each BinaryTree node has an integer value, a
left child node, and a right child node. Children
nodes can either be BinaryTree nodes themselves or
None / null.

Sample Input
=          1
        /     \
       2       3
     /   \    /  \
    4     5  6    7
  /   \  /
 8    9 10

Sample Output
[15, 16, 18, 10, 11]
// 15 == 1 + 2 + 4 + 8
// 16 == 1 + 2 + 4 + 9
// 18 == 1 + 2 + 5 + 10
// 10 == 1 + 3 + 6
// 11 == 1 + 3 + 7

 */
open class BinaryTree(value: Int) {
    var value = value
    var left: BinaryTree? = null
    var right: BinaryTree? = null
}

fun branchSums(root: BinaryTree): List<Int> {
    // Write your code here.
    val branchSum = ArrayList<Int>()
    branchSumHelper(root, 0, branchSum)
    return branchSum
}

fun branchSumHelper(root: BinaryTree?, sum: Int, sumList: ArrayList<Int>) {
    if(root == null) {
        return
    }

    val sumVal = sum + root.value
    if (root.left == null && root.right == null) {
        sumList.add(sumVal)
        return
    }
    branchSumHelper(root.left, sumVal, sumList)
    branchSumHelper(root.right, sumVal, sumList)
}
