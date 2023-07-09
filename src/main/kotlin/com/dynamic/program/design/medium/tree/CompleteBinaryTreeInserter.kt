package com.dynamic.program.design.medium.tree

import com.dynamic.program.trees.TreeNode

/**
 * https://leetcode.com/problems/complete-binary-tree-inserter/
 *
 * 919. Complete Binary Tree Inserter
 * Medium
 * 993
 * 109
 * company
 * PhonePe
 * company
 * Facebook
 * company
 * Google
 * A complete binary tree is a binary tree in which every level, except possibly the last, is completely filled, and all nodes are as far left as possible.
 *
 * Design an algorithm to insert a new node to a complete binary tree keeping it complete after the insertion.
 *
 * Implement the CBTInserter class:
 *
 * CBTInserter(TreeNode root) Initializes the data structure with the root of the complete binary tree.
 * int insert(int v) Inserts a TreeNode into the tree with value Node.val == val so that the tree remains complete, and returns the value of the parent of the inserted TreeNode.
 * TreeNode get_root() Returns the root node of the tree.
 *
 *
 * Example 1:
 *
 *
 * Input
 * ["CBTInserter", "insert", "insert", "get_root"]
 * [[[1, 2]], [3], [4], []]
 * Output
 * [null, 1, 2, [1, 2, 3, 4]]
 *
 * Explanation
 * CBTInserter cBTInserter = new CBTInserter([1, 2]);
 * cBTInserter.insert(3);  // return 1
 * cBTInserter.insert(4);  // return 2
 * cBTInserter.get_root(); // return [1, 2, 3, 4]
 *
 *
 * Constraints:
 *
 * The number of nodes in the tree will be in the range [1, 1000].
 * 0 <= Node.val <= 5000
 * root is a complete binary tree.
 * 0 <= val <= 5000
 * At most 104 calls will be made to insert and get_root.
 */
class CompleteBinaryTreeInserter(val root: TreeNode?) {
    val parent = mutableListOf<TreeNode>()
    val children = mutableListOf<TreeNode>()

    init{
        if(root != null) {
            val q = ArrayDeque<TreeNode>()
            q.addLast(root)
            while(q.isNotEmpty()) {
                val chld = mutableListOf<TreeNode>()
                for(i in q.size downTo 1) {
                    val n = q.removeFirst()
                    chld.add(n)
                    n.left?.let{q.addLast(it)}
                    n.right?.let{q.addLast(it)}
                }

                if(parent.isEmpty() || parent.size * 2 == chld.size) {
                    parent.clear()
                    parent.addAll(chld)
                } else {
                    children.clear()
                    children.addAll(chld)
                    break
                }
            }
        }
    }

    fun insert(`val`: Int): Int {
        if(children.isEmpty()) {
            val p = parent[0]
            val c = TreeNode(`val`)
            p.left = c
            children.add(c)
            return p.`val`
        } else {

            val rmd = children.size % 2
            val idx = children.size / 2
            val p = parent[idx]
            val c = TreeNode(`val`)

            children.add(c)

            if(rmd == 1) {
                p.right = c
            } else {
                p.left = c
            }

            if(parent.size * 2 == children.size) {
                parent.clear()
                parent.addAll(children)
                children.clear()
            }
            return p.`val`
        }
    }

    fun get_root(): TreeNode? {
        val q = ArrayDeque<TreeNode>()
        q.first()
        return root
    }
}

/**
 * Your CompleteBinaryTreeInserter object will be instantiated and called as such:
 * var obj = CompleteBinaryTreeInserter(root)
 * var param_1 = obj.insert(`val`)
 * var param_2 = obj.get_root()
 */

class CBTInserter(val root: TreeNode?) {
    val q = ArrayDeque<TreeNode>()

    init {
        if(root != null) {
            q.addLast(root)
            while(q.isNotEmpty()) {
                val n = q.removeFirst()
                if(n.left != null && n.right != null){
                    q.addLast(n.left!!)
                    q.addLast(n.right!!)
                } else {
                    if(n.left != null) {
                        q.addLast(n.left!!)
                    } else if(n.right != null) {
                        q.addLast(n.right!!)
                    }
                    q.addFirst(n)
                    break
                }
            }
        }
    }

    fun insert(`val`: Int): Int {
        val n = q.first()
        q.addLast(TreeNode(`val`))
        if(n.left == null) {
            n.left = q.last()
        } else {
            n.right = q.last()
            q.removeFirst()
        }
        return n.`val`
    }

    fun get_root(): TreeNode? {
        return root
    }

}

/**
 * Your CBTInserter object will be instantiated and called as such:
 * var obj = CBTInserter(root)
 * var param_1 = obj.insert(`val`)
 * var param_2 = obj.get_root()
 */
