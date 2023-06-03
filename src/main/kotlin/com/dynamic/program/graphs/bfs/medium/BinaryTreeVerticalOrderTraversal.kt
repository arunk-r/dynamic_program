package com.dynamic.program.graphs.bfs.medium

import com.dynamic.program.trees.TreeNode
import java.util.Arrays

/**
 * 314. Binary Tree Vertical Order Traversal
 * Medium
 *
 * company
 * Facebook
 * company
 * Bloomberg
 * company
 * Amazon
 * Given the root of a binary tree, return the vertical order traversal of its nodes' values. (i.e., from top to bottom, column by column).
 *
 * If two nodes are in the same row and column, the order should be from left to right.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: root = [3,9,20,null,null,15,7]
 * Output: [[9],[3,15],[20],[7]]
 * Example 2:
 *
 *
 * Input: root = [3,9,8,4,0,1,7]
 * Output: [[4],[9],[3,0,1],[8],[7]]
 * Example 3:
 *
 *
 * Input: root = [3,9,8,4,0,1,7,null,null,null,2,5]
 * Output: [[4],[9,5],[3,0,1],[8,2],[7]]
 *
 *
 * Constraints:
 *
 * The number of nodes in the tree is in the range [0, 100].
 * -100 <= Node.val <= 100
 */
class BinaryTreeVerticalOrderTraversal {
    fun verticalOrder(root: TreeNode?): List<List<Int>> {
        val map = hashMapOf<Int, MutableList<Int>>()
        dfs(root, map)
        val result = mutableListOf<MutableList<Int>>()
        for (k in -101 .. 101) {
            map[k]?.let{result.add(it)}
        }
        return result
    }

    fun dfs(node: TreeNode?, map: HashMap<Int, MutableList<Int>>) {
        if (node != null) {
            val q = ArrayDeque<Pair<TreeNode, Int>>()
            q.addLast(Pair(node, 0))

            while(q.isNotEmpty()) {
                for(i in q.size downTo 1) {
                    val (n, cnt) = q.removeFirst()
                    map.putIfAbsent(cnt, mutableListOf())
                    map[cnt]?.add(n.`val`)
                    n.left?.let{q.addLast(Pair(it, cnt-1))}
                    n.right?.let{q.addLast(Pair(it, cnt+1))}
                }
            }
        }
    }
}
