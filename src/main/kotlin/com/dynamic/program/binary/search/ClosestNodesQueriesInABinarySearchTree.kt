package com.dynamic.program.binary.search

import com.dynamic.program.trees.TreeNode

/**
 * Closest Nodes Queries in a Binary Search Tree
 *
 * You are given the root of a binary search tree and an array queries of size n consisting of positive integers.

Find a 2D array answer of size n where answer[i] = [mini, maxi]:

mini is the largest value in the tree that is smaller than or equal to queries[i]. If a such value does not exist, add -1 instead.
maxi is the smallest value in the tree that is greater than or equal to queries[i]. If a such value does not exist, add -1 instead.
Return the array answer.



Example 1:


Input: root = [6,2,13,1,4,9,15,null,null,null,null,null,null,14], queries = [2,5,16]
Output: [[2,2],[4,6],[15,-1]]
Explanation: We answer the queries in the following way:
- The largest number that is smaller or equal than 2 in the tree is 2, and the smallest number that is greater or equal than 2 is still 2. So the answer for the first query is [2,2].
- The largest number that is smaller or equal than 5 in the tree is 4, and the smallest number that is greater or equal than 5 is 6. So the answer for the second query is [4,6].
- The largest number that is smaller or equal than 16 in the tree is 15, and the smallest number that is greater or equal than 16 does not exist. So the answer for the third query is [15,-1].
Example 2:


Input: root = [4,null,9], queries = [3]
Output: [[-1,4]]
Explanation: The largest number that is smaller or equal to 3 in the tree does not exist, and the smallest number that is greater or equal to 3 is 4. So the answer for the query is [-1,4].


Constraints:

The number of nodes in the tree is in the range [2, 10^5].
1 <= Node.val <= 10^6
n == queries.length
1 <= n <= 10^5
1 <= queries[i] <= 10^6
 */
class ClosestNodesQueriesInABinarySearchTree {
    fun closestNodes(root: TreeNode?, queries: List<Int>): List<List<Int>> {
        val lst = mutableListOf<Int>()
        getListData(root, lst)
        val result = mutableListOf<List<Int>>()
        queries.forEach{ q ->
            result.add(binarySearch(lst, q))
        }

        return result
    }

    private fun binarySearch(lst: MutableList<Int>, k: Int): List<Int> {
        var left = 0
        var right = lst.size - 1
        while (left <= right) {
            val mid = left + (right - left) / 2
            val v = lst[mid]
            if (v == k) return listOf(v, v)
            else if (v > k) right = mid - 1
            else left = mid + 1
        }
        if (right != -1) {
            right = lst[right]
        }
        left = if (left <= lst.size - 1) {
            lst[left]
        } else {
            -1
        }
        return listOf(right,left)
    }

    private fun getListData(root: TreeNode?, lst: MutableList<Int>) {
        if (root == null) return
        getListData(root.left, lst)
        lst.add(root.`val`)
        getListData(root.right, lst)
    }

    fun closestNodes1(root: TreeNode?, queries: List<Int>): List<List<Int>> {
        val lst = mutableListOf<List<Int>>()

        queries.forEach{ q ->
            lst.add(find(root, q))
        }

        return lst
    }

    fun find(node: TreeNode?, k: Int): List<Int> {
        var lb = Int.MIN_VALUE
        var rb = Int.MAX_VALUE
        var n = node
        while(n != null) {
            if (n.`val` > k) {
                rb = Math.min(rb, n.`val`)
                n = n.left
            } else if (n.`val` < k) {
                lb = Math.max(lb, n.`val`)
                n = n.right
            } else {
                return listOf(n.`val`, n.`val`)
            }
        }
        if (lb == Int.MIN_VALUE) lb = -1
        if (rb == Int.MAX_VALUE) rb = -1
        return listOf(lb, rb)
    }
}