package com.dynamic.program.graphs.dfs

import com.dynamic.program.trees.TreeNode
import java.util.*

/**
 * All Elements in Two Binary Search Trees
 *
 * Given two binary search trees root1 and root2, return a list containing all the integers from both trees sorted in ascending order.
 *
 * Example 1:
 * Input: root1 = [2,1,4], root2 = [1,0,3]
 * Output: [0,1,1,2,3,4]
 *
 *
 * Example 2:
 * Input: root1 = [1,null,8], root2 = [8,1]
 * Output: [1,1,8,8]
 *
 *
 * Constraints:
 * The number of nodes in each tree is in the range [0, 5000].
 * -10^5 <= Node.val <= 10^5
 *
 */
class AllElementsInTwoBinarySearchTrees {
    fun getAllElements(root1: TreeNode?, root2: TreeNode?): List<Int> {
        val lst = mutableListOf<Int>()

        val stack1 = Stack<TreeNode?>()
        val stack2 = Stack<TreeNode?>()
        var r1 = root1
        var r2 = root2
        while (r1 != null || r2 != null || stack1.isNotEmpty() || stack2.isNotEmpty()) {

            // get all left values from root1
            while (r1 != null) {
                stack1.push(r1)
                r1 = r1.left
            }

            // get all left values from root2
            while (r2 != null) {
                stack2.push(r2)
                r2 = r2.left
            }

            // check if stack is empty or stack1 data is less than stack2
            if (stack2.isEmpty() || stack1.isNotEmpty() && stack1.peek()!!.`val` <= stack2.peek()!!.`val`) {
                stack1.pop()?.let { c ->
                    lst.add(c.`val`)
                    r1 = c.right // all left values are captured; hence move right
                }
            } else {
                stack2.pop()?.let { c ->
                    lst.add(c.`val`)
                    r2 = c.right // all left values are captured; hence move right
                }
            }

        }
        return lst
    }

    fun getAllElements2(root1: TreeNode?, root2: TreeNode?): List<Int> {
        val lst = mutableListOf<Int>()
        val left = mutableListOf<Int>()
        val right = mutableListOf<Int>()

        bfs(root1, left)
        bfs(root2, right)

        while (left.isNotEmpty() || right.isNotEmpty()) {
            if (left.isNotEmpty() && right.isNotEmpty() && left[0] <= right[0]) {
                lst.add(left[0])
                left.removeAt(0)
            } else if (left.isNotEmpty() && right.isNotEmpty()) {
                lst.add(right[0])
                right.removeAt(0)
            }

            if (left.isEmpty()) {
                lst.addAll(right)
                break
            } else if (right.isEmpty()) {
                lst.addAll(left)
                break
            }
        }

        return lst
    }

    fun bfs(root: TreeNode?, lst: MutableList<Int>) {
        if (root == null) return
        bfs(root.left, lst)
        lst.add(root.`val`)
        bfs(root.right, lst)
    }
}